<%-- 
    Document   : registrationresponse
    Created on : 3 May, 2021, 9:27:39 AM
    Author     : kundankumargupta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    boolean result=(boolean)request.getAttribute("result");
    boolean userfound=(boolean)request.getAttribute("userfound");
    if(userfound==true)
        out.println("uap");
    else if(result==true)
        out.println("success");
    else
        out.println("error");
%>
