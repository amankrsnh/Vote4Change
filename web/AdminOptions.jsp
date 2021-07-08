<%-- 
    Document   : AdminOptions
    Created on : 9 May, 2021, 8:10:05 PM
    Author     : Aman Kumar Singh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jsscript/AdminOptions.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">
        <title>Admin Options</title>
    </head>
    <body>
        <%
            String userid=(String)session.getAttribute("userid");
            String usertype=(String)session.getAttribute("usertype");
            if(userid==null || usertype==null || usertype.equalsIgnoreCase("voter"))
            {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
                return;
            }
            StringBuffer sbf=new StringBuffer("<div class='sticky'><div class='candidate'>Vote For Change</div><br/><div class='subcandidate'>Admin Actions Page</div><br/><br/><div class='logout'><a href='login.html'>Logout</a></div></div><div class='container'><div id='dv1' onclick='redirectAdministratorPage()'><img src='images/administrator.png' height='300px' width='300px' /><br><h3>Admin Options</h3></div><div id='dv2' onclick='redirectVotingpage()'><img src='images/voteadmin.jpg' height='300px' width='300px'><br><h3>Voting Page</h3></div></div><br/><br/><div align='center' id='result'></div>");
            out.println(sbf);
        %>
    </body>
</html>