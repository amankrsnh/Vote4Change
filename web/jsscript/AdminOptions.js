function addCandidatesform()
{
    removeCandidateform();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Add New Candidate</h3>";
    newdiv.innerHTML=newdiv.innerHTML+"\n\
    <form method='POST' enctype='multipart/form-data' id='fileUploadForm'>\n\
        <table>\n\
            <tr><th>Candidate Id:</th><td><input type='text' id='cid'></td></tr>\n\
            <tr><th>User Id:</th><td><input type='text' id='uid' onkeypress='return getdetails(event)'></td></tr>\n\
            <tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>\n\
            <tr><th>City:</th><td><select id='city'></select></td></tr>\n\
            <tr><th>Party:</th><td><input type='text' id='party'></td></tr>\n\
            <tr><td colspan='2'><input type='file' name='files' value='Select Image'></td></tr>\n\
            <tr><th><input type='button' value='Add Candidate' onclick='addCandidate()' id='addcnd'></th><th><input type='reset' value='clear' onclick='clearText()'></th></tr>\n\
        </table>\n\
    </form>";
    newdiv.innerHTML=newdiv.innerHTML+"<br /><span id='addresp'></span>";
    var addcand=$("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn(3500);
    data={id:"getid"};
    $.post("AddCandidateControllerServlet",data,function(responseText)
            {
                console.log(responseText);
                $("#cid").val(responseText);
                $("#cid").prop("disabled",true);
            }
           );
}

function removeCandidateform()
{
    $("#candidateform").remove();
}

function clearText()
{
    $("#addresp").html("");
}

function getdetails(e)
{
    if(e.keyCode===13)
    {
        data={"uid":$("#uid").val()};
        $.post("AddCandidateControllerServlet",data,
        function(responseText)
        {
            let details=JSON.parse(responseText);
            let city=details.city;
            let uname=details.username;
            if(uname==="wrong")
                swal("Wrong Adhaar No!","Adhaar no is invalid!","error");
            else
            {
                $("#cname").val(uname);
                $("#city").empty();
                $("#city").append(city);
                $("#cname").prop("disabled",true);
            }
        });
    }
}

function addCandidate()
{
    var form=$("#fileUploadForm")[0];
    var data=new FormData(form);
    data.append("cid",$("#cid").val());
    data.append("uid",$("#uid").val());
    data.append("cname",$("#cname").val());
    data.append("party",$("#party").val());
    data.append("city",$("#city").val());
    $.ajax({
        type: "POST",
        enctype: "multipart/form-data",
        url: "AddNewCandidateControllerServlet",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function(data)
        {
            swal("Admin!","Candidate added Successfully.","success").then((value)=> {
                addCandidatesform();
            });
        },
        error: function(e)
        {
            swal("Admin!",e,"error");
        }
    });
}
function redirectAdministratorPage()
{
    swal("Admin!","Redirecting to Administrator Page","success").then(value=>{window.location="AdminActions.jsp"});
}

function redirectVotingpage()
{
    swal("Admin!","Redirecting To Voting Controller Page!","success").then(value=>{window.location="VotingControllerServlet"});
}

function redirectUserManagementPage()
{
    swal("Admin!","Redirecting To User Management Page!","success").then(value=>{window.location="ManageUser.jsp"});
}

function redirectManageCandidatespage()
{
    swal("Admin!","Redirecting To Manage Candidates Page!","success").then(value=>{window.location="ManageCandidates.jsp"});
}

function redirectResultpage()
{
    swal("Admin!","Redirecting To Result Page!","success").then(value=>{window.location="result.jsp"});
}



function updateCandidatesform()
{
    removeCandidateform();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Update Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML+"<div style='color:white; font-weight:bold'>Candidate Id: </div><select id='cid'></select>";
    newdiv.innerHTML = newdiv.innerHTML+"<br><span id='addresp'></span>";
    var addcand=$("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    data={data:"cid"};
    $.post("ShowUpdateCandidateControllerServlet",data,function(responseText)
    {
        var candidatelist=JSON.parse(responseText);
	$("#cid").append(candidatelist.cid);
    });
    $("#cid").change(function()
    {
        var cid=$("#cid").val();
        if(cid===''){
                swal("No selection","Please select an id","error");
                return;
        }
        data = {data: cid};
    	$.post("ShowUpdateCandidateControllerServlet", data, function (responseText) 
        {
            clearText();
            var details=JSON.parse(responseText);
            $("#addresp").append(details.subdetails);
    	});
    });
}

function updateCandidate()
{
    var form =$("#fileUploadForm")[0];
    var data = new FormData(form);
    var cid = $("#cid").val();
    var cname = $("#cname").val();
    var city = $("#city").val();
    var party = $("#party").val();
    var uid = $("#uid").val();
    data.append("cid", cid);
    data.append("uid", uid);
    data.append("cname", cname);
    data.append("party", party);
    data.append("city", city);
    $.ajax({
        type: "POST",
        enctype: "multiple/form-data",
        url: "UpdateCandidateControllerServlet",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            swal("Admin!", "Candidate Details Updated Successfully.", "success").then((value) => {
                updateCandidatesform();
            });
        },
        error: function (e) {
            swal("Admin", e, "error");
        }
    });
}

function showCandidatesform()
{
    removeCandidateform();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Show Candidate</h3>";
    newdiv.innerHTML=newdiv.innerHTML+"<div style='color:white; font-weight:bold'>Candidate Id</div><div><select id='cid'></select></div>";
    newdiv.innerHTML=newdiv.innerHTML+"<br /><span id='addresp'></span>";
    
    var addcand=$("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    data={data:"cid"};
    $.post("ShowCandidateControllerServlet",data,function(responseText)
    {
        var candidateList=JSON.parse(responseText);
        $("#cid").append(candidateList.cid);
    });
    $("#cid").change(function()
        {
            var cid=$("#cid").val();
            if(cid==='')
            {
                swal("No Selection","Please select an Id","error");
                return;
            }
            data={data:cid};
            $.post("ShowCandidateControllerServlet", data, function (responseText) 
            {
                clearText();
                var details=JSON.parse(responseText);
                $("#addresp").append(details.subdetails);
            });
        });
}

function deleteCandidatesform()
{
    removeCandidateform();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Remove Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML+"<div style='color:white; font-weight:bold'>Candidate Id</div><div><select id='cid'></select></div>";
    newdiv.innerHTML = newdiv.innerHTML+"<br><span id='addresp'></span>";
    var addcand=$("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    data = {data: "cid"};
    $.post("ShowCandidateControllerServlet", data, function (responseText) 
    {
        var candidatelist=JSON.parse(responseText);
        $("#cid").append(candidatelist.cid);
    });
    $("#cid").change(function()
    {
        var cid=$("#cid").val();
        if(cid==='')
        {
            swal("No selection","Please select an id","error");
            return;
        }
        data = {data: cid};
    	$.post("ShowCandidateControllerServlet", data, function (responseText) 
        {
            clearText();
            var details=JSON.parse(responseText);
            $("#addresp").append(details.subdetails);
            $("#addresp").append(`<br><input type='button' value='Confirm' onclick="confirmdelete('${cid}')"/>`);

    	});
    });
}

function confirmdelete(cid)
{
    data={data:cid};
    $.post("RemoveCandidateControllerServlet",data,function(responseText)
    {
        if(responseText.trim()==='success')
        {
            swal("Succes!","Candidate Removed Successfully.","success").then((value)=>{
                deleteCandidatesform();
            });
        }
        else if(responseText.trim()==='failed')
        {
            swal("Error","Error while removing the candidate","error");
        }
    });
}

function showUser()
{
    $.post("ShowUserControllerServlet",null,function(responseText)
    {
        $("#result").html(responseText.trim());
    });
}

function removeUser(){
    removeCandidateform();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Remove User</h3>";
    newdiv.innerHTML = newdiv.innerHTML+"<div style='color:white; font-weight:bold'>User Id</div><div><select id='uid'></select></div>";
    newdiv.innerHTML = newdiv.innerHTML+"<br><span id='addresp'></span>";

    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    data = {data: "getId"};
    $.post("ShowRemoveUserControllerServlet", data, function (responseText) 
    {
        clearText();
        var userList=JSON.parse(responseText);
        $("#uid").append(userList.uid);
    });
    $("#uid").change(function()
    {
        var uid=$("#uid").val();
        if(uid==='')
        {
            swal("Error","Please select an Id!","error");
            return;
        }
        data = {data: uid};
    	$.post("ShowRemoveUserControllerServlet", data, function (responseText) 
        {
            clearText();
            var details=JSON.parse(responseText);
            $("#addresp").append(details.userDetails);
            $("#addresp").append(`<br><input type='button' value='Confirm' onclick="confirmRemove('${uid}')"/>`);

    	});
    });
}

function confirmRemove(uid){
	data = {data: uid};
	$.post("RemoveUserControllerServlet",data,function(responseText)
        {
		if(responseText.trim()==='success')
                {
                    swal("Success!","Candidate Remove Successfully ", "success").then((value) => 
                    {
                        removeUser();
                    });
		}
                else if(responseText.trim()==='failed')
                {
                    swal("Error","Error in deleting the user","error");
		}
	});
}

function electionresult(){
	data={data:"result"};
	$.post("ElectionResultControllerServlet",data,function(responseText){
		swal("Result fetched","Success","success");
		$("#result").html(responseText.trim());
	});
}

function partywiseresult(){
	data={data:"party"};
	$.post("ElectionResultControllerServlet",data,function(responseText){
		swal("Result fetched","Success","success");
		$("#result").html(responseText.trim());
	});
}

function genderpercentage(){
	data={data:"gender"};
	$.post("ElectionResultControllerServlet",data,function(responseText){
		swal("Result fetched","Success","success");
		$("#result").html(responseText.trim());
	});
}
