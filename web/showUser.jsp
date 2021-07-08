<%-- 
    Document   : showUser
    Created on : 27 Jun, 2021, 2:06:49 PM
    Author     : Aman Kumar Singh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="eVoting.dto.UserDetails, java.util.ArrayList" %>
<%
	String userid=(String)session.getAttribute("userid");
	if(userid==null)
        {
		session.invalidate();
		response.sendRedirect("accessdenied.html");
		return;
	}
	ArrayList<UserDetails> userList=(ArrayList<UserDetails>)request.getAttribute("users");
	StringBuffer displayBlock = new StringBuffer("<table class='showUser'>");
	displayBlock.append("<tr><th>User Id</th><th>Username</th><th>Address</th><th>City</th><th>Email</th><th>Mobile No</th></tr>");
	for(UserDetails user:userList)
        {
		displayBlock.append("<tr><td>"+user.getUserid()+"</td><td>"+user.getUsername()+"</td><td>"+user.getAddress()+"</td><td>"+user.getCity()+"</td><td>"+user.getEmail()+"</td><td>"+user.getMobile()+"</td><tr>");
	}
	displayBlock.append("</table>");
	out.println(displayBlock.toString());
	System.out.println(displayBlock);
%>