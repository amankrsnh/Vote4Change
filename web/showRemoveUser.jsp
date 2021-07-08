<%-- 
    Document   : showRemoveUser
    Created on : 30 Jun, 2021, 4:33:53 PM
    Author     : Aman Kumar Singh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="eVoting.dto.UserDetails, java.util.ArrayList,org.json.JSONObject" %>    
<%
      String userid=(String)session.getAttribute("userid");
      if(userid==null){
         session.invalidate();
         response.sendRedirect("accessdenied.html");
         return;
      }

      String result=(String)request.getAttribute("data");
      StringBuffer displayBlock=new StringBuffer("");
      
      if(result.equalsIgnoreCase("id")){
        ArrayList<String> userId=(ArrayList<String>)request.getAttribute("userId");  
    	displayBlock.append("<option value=''>Choose Id</option>");
      	for(String id:userId){
      		displayBlock.append("<option value='"+id+"'>"+id+"</option>");
      	}
      	JSONObject json = new JSONObject();
      	json.put("uid", displayBlock.toString());
      	out.println(json);
      	System.out.println(displayBlock);
      }else if(result.equalsIgnoreCase("user")){
    	  UserDetails user=(UserDetails)request.getAttribute("user");
    	  displayBlock.append("<table><tr><th>Username:</th><td>"+user.getUsername()+"</td></tr>");
    	  displayBlock.append("<tr><th>Email:</th><td>"+user.getEmail()+"</td></tr>");
    	  displayBlock.append("<tr><th>Mobile No:</th><td>"+user.getMobile()+"</td></tr>");
    	  displayBlock.append("<tr><th>Address:</th><td>"+user.getAddress()+"</td></tr>");
    	  displayBlock.append("<tr><th>City:</th><td>"+user.getCity()+"</td></tr></table>");
    	  JSONObject json = new JSONObject();
    	  json.put("userDetails", displayBlock.toString());
    	  out.println(json);
    	  System.out.println(displayBlock);
      }
%>