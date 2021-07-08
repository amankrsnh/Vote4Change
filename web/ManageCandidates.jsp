<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="jsscript/AdminOptions.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">
        <title>Admin Actions</title>
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
            StringBuffer sbf=new StringBuffer("<div class='sticky'><div class='candidate'>Vote For Change</div><br/><div class='subcandidate'>Admin Actions Page</div><br/><br/><div class='logout'><a href='login.html'>Logout</a></div></div><div class='container'><div id='dv1' onclick='addCandidatesform()'><img src='images/addcandidate.png' height='300px' width='300px' /><br><h3>Add Candidate</h3></div><div id='dv2' onclick='updateCandidatesform()'><img src='images/update1.jpg' height='300px' width='300px'><br><h3>Update Candidates</h3></div><div id='dv3' onclick='showCandidatesform()'><img src='images/candidate.jpg' height='300px' width='300px'><br><h3>Show Candidates</h3></div><div id='dv4' onclick='deleteCandidatesform()'><img src='images/update3.jpg' height='300px' width='300px'><br><h3>Delete Candidates</h3></div></div><br/><br/><div align='center' id='result'></div>");
            out.println(sbf);
        %>
    </body>
</html>