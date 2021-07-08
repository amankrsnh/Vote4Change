<%-- 
    Document   : showexception
    Created on : 3 May, 2021, 9:32:57 AM
    Author     : kundankumargupta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Exception ex=(Exception)request.getAttribute("exception");
    System.out.println("Exception is:"+ex);
    out.println("Some Exception occurred:"+ex.getMessage());
%>    
