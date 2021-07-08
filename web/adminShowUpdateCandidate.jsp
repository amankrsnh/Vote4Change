<%-- 
    Document   : adminShowUpdateCandidate
    Created on : 26 Jun, 2021, 12:08:30 PM
    Author     : Aman Kumar Singh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="eVoting.dto.CandidateDetails" %>
<%@page import="org.json.JSONObject" %>

<%
    String userid=(String)session.getAttribute("userid");
    if(userid==null)
    {
    	session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    String result=(String)request.getAttribute("result");
    StringBuffer displayBlock=new StringBuffer();
    if(result!=null && result.equalsIgnoreCase("candidateList"))
    {
    	ArrayList<String> candidateList=(ArrayList)request.getAttribute("candidateId");
    	displayBlock.append("<option value=''>Choose Id</option>");
    	for(String c:candidateList){
    		displayBlock.append("<option value='"+c+"'>"+c+"</option>");
    	}
    	JSONObject json = new JSONObject();
    	json.put("cid", displayBlock.toString());
    	out.println(json);
    	System.out.println(displayBlock);
    }
    else if(result!=null && result.equalsIgnoreCase("details")) 
    {
    	CandidateDetails cd =(CandidateDetails)request.getAttribute("candidate");
    	ArrayList<String> cityList=(ArrayList<String>)request.getAttribute("cities");
    	String str="<img src='data:image/png;base64,"+cd.getSymbol()+"' style='width:200px;height:130px;' />";
    	displayBlock.append("<form method='post' enctype='multipart/form-data' id='fileUploadForm'>");
    	displayBlock.append("<table><tr><th>User Id:</th><td><input type='text' id='uid' disabled value='"+cd.getUserId()+"' /></td></tr>"
    			+"<tr><th>Candidate Name:</th><td><input type='text' id='cname' disabled value='"+cd.getCandidateName()+"' /></td></tr>"
    			+"<tr><th>City</th><td><select id='city'>");
    	for(String c:cityList){
               displayBlock.append("<option value='"+c+"'>"+c+"</option>");
        }
    	displayBlock.append("</select></td></tr>"
    			+"<tr><th>Party:</th><td><input type='text' id='party' value='"+cd.getParty()+"' /></td></tr>"
    			+"<tr><td colspan='2'><input type='file' name='files' value='Select Image'></td></tr>"
    			+"<tr><th><input type='button' value='Update Candidate' onclick='updateCandidate()' id='updatecnd'></th>"
    			+"<th><input type='reset' value='Clear' onclick='clearText()'</th></tr></table></form>"
    			+"<tr><th>Symbol:</th><td id='image'>"+str+"</td></tr>");
    	JSONObject json=new JSONObject();
    	json.put("subdetails", displayBlock.toString());
    	out.println(json);
        System.out.println(displayBlock);
    }
%>