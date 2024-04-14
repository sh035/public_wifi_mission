<%@ page import ="dto.WifiDto" %>
<%@ page import ="dto.HistoryDto" %>
<%@ page import ="dao.WifiDao" %>
<%@ page import ="dao.HistoryDao" %>
<%@ page import ="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>와이파이 정보 구하기</title>
	<style>
	    #table-list {
        	font-family: Arial, Helvetica, sans-serif;
        	border-collapse: collapse;
        	width: 100%;
        }

        #table-list td, #table-list th {
        	border: 1px solid #ddd;
        	padding: 8px;
        }

        #table-list tr:nth-child(even) {
        	background-color: #f2f2f2;
        }

        #table-list tr:hover {
        	background-color: #ddd;
        }

        #table-list th {
        	padding-top: 12px;
        	padding-bottom: 12px;
        	text-align: center;
        	background-color: #04AA6D;
        	color: white;
        }

        #td-center {
        	text-align: center;
        }
	</style>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
    <jsp:include page="inc_menu.jsp"/>
    <%
        String lat = request.getParameter("lat") == null ? "0.0" : request.getParameter("lat");
        String lnt = request.getParameter("lnt") == null ? "0.0" : request.getParameter("lnt");
    %>
    <form method="get" action="index.jsp" id="form-list">
        <label>
            LAT: <input type="text" id="lat" name="lat" value="<%=lat%>">
        </label>
        <label>
            LNT: <input type="text" id="lnt" name="lnt" value="<%=lnt%>">
        </label>
        <input type="button" value="내 위치 가져오기" onclick="getLocation();">
        <input id="found-wifi"type="submit" value="근처 WIFI 정보 보기">
    </form>

    <table id="table-list">
        <thead>
        <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        </thead>
        <tbody>
        <%

            if (!("0.0").equals(lat) && !("0.0").equals(lnt)) {
                HistoryDto historyDto = new HistoryDto();
                historyDto.setLat(lat);
                historyDto.setLnt(lnt);

                HistoryDao historyDao = new HistoryDao();
                historyDao.insert(historyDto);

                WifiDao wifiDao = new WifiDao();
                List<WifiDto> wifiList = wifiDao.getNearList(lat,lnt);

                for (WifiDto dto : wifiList) {
        %>
        <tr>
            <td><%=dto.getDist()%></td>
            <td><%=dto.getXSwifiMgrNo()%></td>
            <td><%=dto.getXSwifiWrdofc()%></td>
            <td><%= dto.getXSwifiMainNm()%></td>
            <td><%=dto.getXSwifiAdres1()%></td>
            <td><%=dto.getXSwifiAdres2()%></td>
            <td><%=dto.getXSwifiInstlFloor()%></td>
            <td><%=dto.getXSwifiInstlMby()%></td>
            <td><%=dto.getXSwifiInstlTy()%></td>
            <td><%=dto.getXSwifiSvcSe()%></td>
            <td><%=dto.getXSwifiCmcwr()%></td>
            <td><%=dto.getXSwifiCnstcYear()%></td>
            <td><%=dto.getXSwifiInoutDoor()%></td>
            <td><%=dto.getXSwifiRemars3()%></td>
            <td><%=dto.getLat()%></td>
            <td><%=dto.getLnt()%></td>
            <td><%=dto.getWorkDttm()%></td>
        </tr>
            <%
                    }
                } else {
            %>
        <tr>
            <td colspan="17"> 위치 정보를 입력해주세요.</td>
        </tr>
            <%  } %>
        </tbody>

<script>
    const params = new URLSearchParams(window.location.search)
    const lnt = params.get("lnt")
    const lat = params.get("lat")

    if (lnt && lat) {
        const latId = document.getElementById("lat")
        latId.setAttribute("value", lat)
        const lntId = document.getElementById("lnt")
        lntId.setAttribute("value", lnt)

    }

    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            alert("위치 정보를 확인할 수 없습니다.");
        }
    }

    function showPosition(position) {

        document.getElementById('lat').value = position.coords.latitude;
        document.getElementById('lnt').value = position.coords.longitude;
    }
    </script>
</script>
</body>
</html>