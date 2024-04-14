<%@ page import="dao.HistoryDao" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    HistoryDao historyDao = new HistoryDao();

    try {
        historyDao.delete(id);
        response.sendRedirect("history.jsp");
    } catch (Exception e) {
        out.println("<script>alert('삭제 중 에러 발생: " + e.getMessage() + "'); history.back();</script>");
    }
%>