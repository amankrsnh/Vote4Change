<%-- 
    Document   : LoginResponse
    Created on : 9 May, 2021, 5:27:15 PM
    Author     : Aman Kumar Singh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String usertype=(String)request.getAttribute("result");
    String userid=(String)request.getAttribute("userid");
    if(userid!=null && usertype!=null)
    {
        HttpSession sess=request.getSession();
        sess.setAttribute("userid",userid);
        sess.setAttribute("usertype",usertype);
        if(usertype.equalsIgnoreCase("admin"))
        {
            String url="AdminControllerServlet;jsessionid="+session.getId();
            out.println(url);
        }
        else if(usertype.equalsIgnoreCase("voter"))
        {
            String url="VotingControllerServlet;jsessionid="+session.getId();
            out.println(url);
        }
        else
            out.println("error");
    }
%>