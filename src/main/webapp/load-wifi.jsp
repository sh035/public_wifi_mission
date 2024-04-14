<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="service.WifiService" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>와이파이 정보 구하기</title>
	<link href="/res/css/main.css" rel="stylesheet"/>

	<style>
	    *{
	        text-align: center;
	    }
    </style>
</head>

<body>
	<%
	    WifiService wifiService = new WifiService();

	    int cnt = wifiService.totalCnt();
	    wifiService.getJson();
    %>

    <div>
        <% if (cnt > 0) {%>
            <h1>wifi <%=cnt%>개 정보를 정상적으로 저장했습니다.</h1>
        <% } else { %>
            <h1>wifi 정보 저장을 실패하였습니다.</h1>
        <% } %>
        <a href="index.jsp">홈으로 가기</a>
    <div>
</body>
</html>