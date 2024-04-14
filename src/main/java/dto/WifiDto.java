package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WifiDto {

    private double dist;            // 거리
    private String xSwifiMgrNo;           // 관리번호
    private String xSwifiWrdofc;          // 자치구
    private String xSwifiMainNm;          // 와이파이명
    private String xSwifiAdres1;          // 도로명주소
    private String xSwifiAdres2;          // 상세주소
    private String xSwifiInstlFloor;      // 설치위치(층)
    private String xSwifiInstlTy;         // 설치유형
    private String xSwifiInstlMby;        // 설치기관
    private String xSwifiSvcSe;           // 서비스구분
    private String xSwifiCmcwr;           // 망종류
    private String xSwifiCnstcYear;          // 설치년도
    private String xSwifiInoutDoor;       // 실내외구분
    private String xSwifiRemars3;         // wifi접속환경
    private String lat;             // Y좌표
    private String lnt;             // X좌표
    private String workDttm;        // 작업일자

    @Builder
    public WifiDto(double dist, String xSwifiMgrNo, String xSwifiWrdofc, String xSwifiMainNm, String xSwifiAdres1, String xSwifiAdres2, String xSwifiInstlFloor, String xSwifiInstlTy, String xSwifiInstlMby, String xSwifiSvcSe, String xSwifiCmcwr, String xSwifiCnstcYear, String xSwifiInoutDoor, String xSwifiRemars3, String lat, String lnt, String workDttm) {
        this.dist = dist;
        this.xSwifiMgrNo = xSwifiMgrNo;
        this.xSwifiWrdofc = xSwifiWrdofc;
        this.xSwifiMainNm = xSwifiMainNm;
        this.xSwifiAdres1 = xSwifiAdres1;
        this.xSwifiAdres2 = xSwifiAdres2;
        this.xSwifiInstlFloor = xSwifiInstlFloor;
        this.xSwifiInstlTy = xSwifiInstlTy;
        this.xSwifiInstlMby = xSwifiInstlMby;
        this.xSwifiSvcSe = xSwifiSvcSe;
        this.xSwifiCmcwr = xSwifiCmcwr;
        this.xSwifiCnstcYear = xSwifiCnstcYear;
        this.xSwifiInoutDoor = xSwifiInoutDoor;
        this.xSwifiRemars3 = xSwifiRemars3;
        this.lat = lat;
        this.lnt = lnt;
        this.workDttm = workDttm;
    }

    @Builder
    public WifiDto(String xSwifiMgrNo, String xSwifiWrdofc, String xSwifiMainNm, String xSwifiAdres1, String xSwifiAdres2, String xSwifiInstlFloor, String xSwifiInstlTy, String xSwifiInstlMby, String xSwifiSvcSe, String xSwifiCmcwr, String xSwifiCnstcYear, String xSwifiInoutDoor, String xSwifiRemars3, String lat, String lnt, String workDttm) {
        this.xSwifiMgrNo = xSwifiMgrNo;
        this.xSwifiWrdofc = xSwifiWrdofc;
        this.xSwifiMainNm = xSwifiMainNm;
        this.xSwifiAdres1 = xSwifiAdres1;
        this.xSwifiAdres2 = xSwifiAdres2;
        this.xSwifiInstlFloor = xSwifiInstlFloor;
        this.xSwifiInstlTy = xSwifiInstlTy;
        this.xSwifiInstlMby = xSwifiInstlMby;
        this.xSwifiSvcSe = xSwifiSvcSe;
        this.xSwifiCmcwr = xSwifiCmcwr;
        this.xSwifiCnstcYear = xSwifiCnstcYear;
        this.xSwifiInoutDoor = xSwifiInoutDoor;
        this.xSwifiRemars3 = xSwifiRemars3;
        this.lat = lat;
        this.lnt = lnt;
        this.workDttm = workDttm;
    }

}


