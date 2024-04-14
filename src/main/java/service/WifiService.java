package service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.WifiDao;
import dto.WifiDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WifiService {
    private static final String WIFI_URL = "http://openapi.seoul.go.kr:8088/4a6b415a4165736835304272797775/json/TbPublicWifiInfo";
    private static OkHttpClient client = new OkHttpClient();
    public static int totalCnt() throws IOException {
        int total = 0;
        try {
            URL url = new URL(WIFI_URL + "/1/1");

            Request.Builder builder = new Request.Builder().url(url).get();     // OkHttp 클라이언트 객체 생성
            Request request = builder.build();

            Response response = client.newCall(request).execute();              // OKHttp 클라이언트로 GET 요청 객체 전송

            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    JsonObject jsonObject = JsonParser.parseString(body.string()).getAsJsonObject();
                    total = jsonObject.getAsJsonObject().get("TbPublicWifiInfo")
                            .getAsJsonObject().get("list_total_count")
                            .getAsInt();
                }
            } else {
                System.out.println("API 호출을 실패하였습니다. \n" + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }
    public static void getJson() throws IOException {
        int total = totalCnt();
        List<WifiDto> dtos = new ArrayList<>();

        try {
            for (int i = 0; i <= total / 1000; i++) {
                int start = 1000 * i + 1;
                int end = 1000 * (i + 1);

                URL url = new URL(WIFI_URL + "/" + start + "/" + end);

                Request.Builder builder = new Request.Builder().url(url).get();
                Request request = builder.build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        JsonObject jsonObject = JsonParser.parseString(body.string()).getAsJsonObject();
                        JsonArray jsonArray = jsonObject.getAsJsonObject("TbPublicWifiInfo").getAsJsonArray("row");

                        for (int j = 0; j < jsonArray.size(); j++) {
                            JsonObject object = jsonArray.get(j).getAsJsonObject();

                            WifiDto wifiDto = new WifiDto();
                            wifiDto.setXSwifiMgrNo(object.get("X_SWIFI_MGR_NO").getAsString());
                            wifiDto.setXSwifiWrdofc(object.get("X_SWIFI_WRDOFC").getAsString());
                            wifiDto.setXSwifiMainNm(object.get("X_SWIFI_MAIN_NM").getAsString());
                            wifiDto.setXSwifiAdres1(object.get("X_SWIFI_ADRES1").getAsString());
                            wifiDto.setXSwifiAdres2(object.get("X_SWIFI_ADRES2").getAsString());
                            wifiDto.setXSwifiInstlFloor(object.get("X_SWIFI_INSTL_FLOOR").getAsString());
                            wifiDto.setXSwifiInstlTy(object.get("X_SWIFI_INSTL_TY").getAsString());
                            wifiDto.setXSwifiInstlMby(object.get("X_SWIFI_INSTL_MBY").getAsString());
                            wifiDto.setXSwifiSvcSe(object.get("X_SWIFI_SVC_SE").getAsString());
                            wifiDto.setXSwifiCmcwr(object.get("X_SWIFI_CMCWR").getAsString());
                            wifiDto.setXSwifiCnstcYear(object.get("X_SWIFI_CNSTC_YEAR").getAsString());
                            wifiDto.setXSwifiInoutDoor(object.get("X_SWIFI_INOUT_DOOR").getAsString());
                            wifiDto.setXSwifiRemars3(object.get("X_SWIFI_REMARS3").getAsString());
                            wifiDto.setLat(object.get("LAT").getAsString());
                            wifiDto.setLnt(object.get("LNT").getAsString());
                            wifiDto.setWorkDttm(object.get("WORK_DTTM").getAsString());

                            dtos.add(wifiDto);
                        }
                    }
                }
            }

            WifiDao wifiDao = new WifiDao();
            wifiDao.insert(dtos);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
