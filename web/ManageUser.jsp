<%-- 
    Document   : ManageUser
    Created on : 27 Jun, 2021, 1:29:18 PM
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
        <title>Manage User</title>
    </head>
    <body>
        <%
            String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
                return;
            }
        %>
        <div class='sticky'>
            <div class='candidate'>VOTE FOR CHANGE</div><br>
            <div class='subcandidate'>Admin Actions Page</div><br><br>
            <div class='logout'><a href='LoginControllerServlet?logout=logout'>logout</a></div>
        </div>
        
        <div class='container'>
            <div id="dv1" onclick="showUser()" style="cursor:pointer">
                <img src="images/show.png" style="height:300px;width:300px" />
                <br>
                <h3>Show User</h3>
            </div>
            <div id='dv2' onclick='removeUser()' style='cursor: pointer'>
                <img src='images/delete.jpg' style="height:300px;width:300px" />
                <br>
                <h3> Remove User</h3>
            </div>
            <br><br>
            <div align="center" id="result"></div>
        </div>
        
    </body>
</html>
