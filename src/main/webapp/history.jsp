<%@ page import="dto.HistoryDto" %>
<%@ page import="dao.HistoryDao" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<h1>와이파이 정보 구하기</h1>
	<jsp:include page="inc_menu.jsp"/>
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
    <table id="table-list">
        <thead>
        <tr>
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <%
            HistoryDao historyDao = new HistoryDao();
            List<HistoryDto> list = historyDao.historyList();

            if (list.isEmpty()) {
        %>
        <tr>
            <td colspan="5">위치 기록이 없습니다.</td>
        </tr>
        <%  } else { %>
        <% for (HistoryDto dto : list) { %>
        <tr>
            <td><%=dto.getId()%></td>
            <td><%=dto.getLat()%></td>
            <td><%=dto.getLnt()%></td>
            <td><%=dto.getSearchDate()%></td>
            <td><button onclick="deleteHistory(<%=dto.getId()%>)">삭제</button></td>
        </tr>
        <%      }
            }
        %>
        </tbody>
    </table>
<script>
function deleteHistory(id) {
        var confirmation = confirm("이 기록을 삭제하시겠습니까?");
        if (confirmation) {
            location.href = "/wifi_mission/delete-history.jsp?id=" + id;
        }
    }
</script>
</body>
</html>