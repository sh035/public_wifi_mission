package dao;

import dto.WifiDto;
import common.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WifiDao {
    private static Connection con;  // db연결해주는 구분 인터페이스처럼
    private static PreparedStatement ps;   // sql 실행시켜주는 구문
    private static ResultSet rs; // 한줄씩읽는애

    public int insert(List<WifiDto> dtos) throws SQLException {
        con = null;
        ps = null;
        rs = null;
        int batchSize = 1000;
        int cnt = 0;
        int[] total = null;

        try {
            con = DBConnection.getConnect();
            con.setAutoCommit(false);

            String sql = "INSERT INTO wifi"
                    + " (x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm, x_swifi_adres1, x_swifi_adres2, "
                    + " x_swifi_instl_floor, x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se, x_swifi_cmcwr, "
                    + " x_swifi_cnstc_year, x_swifi_inout_door, x_swifi_remars3, lat, lnt, work_dttm) "
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

            ps = con.prepareStatement(sql);

            for (WifiDto dto : dtos) {
                ps.setString(1, dto.getXSwifiMgrNo());
                ps.setString(2, dto.getXSwifiWrdofc());
                ps.setString(3, dto.getXSwifiMainNm());
                ps.setString(4, dto.getXSwifiAdres1());
                ps.setString(5, dto.getXSwifiAdres2());
                ps.setString(6, dto.getXSwifiInstlFloor());
                ps.setString(7, dto.getXSwifiInstlTy());
                ps.setString(8, dto.getXSwifiInstlMby());
                ps.setString(9, dto.getXSwifiSvcSe());
                ps.setString(10, dto.getXSwifiCmcwr());
                ps.setString(11, dto.getXSwifiCnstcYear());
                ps.setString(12, dto.getXSwifiInoutDoor());
                ps.setString(13, dto.getXSwifiRemars3());
                ps.setString(14, dto.getLat());
                ps.setString(15, dto.getLnt());
                ps.setString(16, dto.getWorkDttm());

                ps.addBatch();
                cnt++;

                if (cnt % batchSize == 0) {
                    total = ps.executeBatch();
                    con.commit();
                    cnt = 0;
                }
            }
            if (cnt > 0) {
                total = ps.executeBatch();
                con.commit();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, con);
        }

        return total != null ? total.length : 0;
    }
    public List<WifiDto> getNearList(String lat, String lnt) {
        con = null;
        ps = null;
        rs = null;

        List<WifiDto> wifiList = new ArrayList<>();

        try {
            con = DBConnection.getConnect();

            String sql = " SELECT *, " +
                    " round(6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT) " +
                    " -radians(?))+sin(radians(?))*sin(radians(LAT))), 4) " +
                    " AS distance " +
                    " FROM wifi " +
                    " ORDER BY distance " +
                    " LIMIT 20;";

            ps = con.prepareStatement(sql);
            ps.setDouble(1, Double.parseDouble(lat));
            ps.setDouble(2, Double.parseDouble(lnt));
            ps.setDouble(3, Double.parseDouble(lat));

            rs = ps.executeQuery();

            while (rs.next()) {
                WifiDto dto = WifiDto.builder()
                        .dist(rs.getDouble("distance"))
                        .xSwifiMgrNo(rs.getString("x_swifi_mgr_no"))
                        .xSwifiWrdofc(rs.getString("x_swifi_wrdofc"))
                        .xSwifiMainNm(rs.getString("x_swifi_main_nm"))
                        .xSwifiAdres1(rs.getString("x_swifi_adres1"))
                        .xSwifiAdres2(rs.getString("x_swifi_adres2"))
                        .xSwifiInstlFloor(rs.getString("x_swifi_instl_floor"))
                        .xSwifiInstlTy(rs.getString("x_swifi_instl_ty"))
                        .xSwifiInstlMby(rs.getString("x_swifi_instl_mby"))
                        .xSwifiSvcSe(rs.getString("x_swifi_svc_se"))
                        .xSwifiCmcwr(rs.getString("x_swifi_cmcwr"))
                        .xSwifiCnstcYear(rs.getString("x_swifi_cnstc_year"))
                        .xSwifiInoutDoor(rs.getString("x_swifi_inout_door"))
                        .xSwifiRemars3(rs.getString("x_swifi_remars3"))
                        .lat(rs.getString("lat"))
                        .lnt(rs.getString("lnt"))
                        .workDttm(rs.getString("work_dttm"))
                        .build();

                wifiList.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs,ps,con);
        }

        return wifiList;
    }

    public WifiDto getDetail(String mgrNo) {

        con = null;
        ps = null;
        rs = null;
        WifiDto dto = new WifiDto();
        try {
            con = DBConnection.getConnect();

            String sql = "select * from wifi where x_swifi_mgr_no = ?";
            ps = con.prepareStatement(sql);

            ps.setString(1, mgrNo);
            rs = ps.executeQuery();

            while (rs.next()) {

                dto = WifiDto.builder()
                    .xSwifiMgrNo(rs.getString("x_swifi_mgr_no"))
                    .xSwifiWrdofc(rs.getString("x_swifi_wrdofc"))
                    .xSwifiMainNm(rs.getString("x_swifi_main_nm"))
                    .xSwifiAdres1(rs.getString("x_swifi_adres1"))
                    .xSwifiAdres2(rs.getString("x_swifi_adres2"))
                    .xSwifiInstlFloor(rs.getString("x_swifi_instl_floor"))
                    .xSwifiInstlTy(rs.getString("x_swifi_instl_ty"))
                    .xSwifiInstlMby(rs.getString("x_swifi_instl_mby"))
                    .xSwifiSvcSe(rs.getString("x_swifi_svc_se"))
                    .xSwifiCmcwr(rs.getString("x_swifi_cmcwr"))
                    .xSwifiCnstcYear(rs.getString("x_swifi_cnstc_year"))
                    .xSwifiInoutDoor(rs.getString("x_swifi_inout_door"))
                    .xSwifiRemars3(rs.getString("x_swifi_remars3"))
                    .lat(rs.getString("lat"))
                    .lnt(rs.getString("lnt"))
                    .workDttm(rs.getString("work_dttm"))
                    .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, con);
        }

        return dto;
    }

}
