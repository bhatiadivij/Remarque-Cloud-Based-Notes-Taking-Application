<%-- 
    Document   : NoteOperations
    Created on : 16 Apr, 2017, 4:28:04 PM
    Author     : Divij Bhatia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function checkMessage()
            {
                var url=window.location.href;
                if(url.includes("msg"))
                { 
                    var message=url.split("msg=")[1];
                    message=message.split("_").join("<br>");
                    message=message.split("%20").join(" ");
                    document.getElementById("message").innerHTML=message;
                }
            }
            
            function display(op)
            {
                switch (op) {
                     case "ctn":
                        document.getElementById("dtn").style.display="none";
                        document.getElementById("utn").style.display="none";
                        document.getElementById("ctn").style.display="inline";
                        document.getElementById("rc").style.display="none";
                        document.getElementById("rac").style.display="none";
                        document.getElementById("ac").style.display="none";
                        document.getElementById("sf").style.display="none";
                        break;
                    case "utn":
                        document.getElementById("ctn").style.display="none";
                        document.getElementById("dtn").style.display="none";
                        document.getElementById("utn").style.display="inline";
                        document.getElementById("rc").style.display="none";
                        document.getElementById("rac").style.display="none";
                        document.getElementById("ac").style.display="none";
                        document.getElementById("sf").style.display="none";
                        break;
                    case "dtn":
                        document.getElementById("ctn").style.display="none";
                        document.getElementById("utn").style.display="none";
                        document.getElementById("dtn").style.display="inline";
                        document.getElementById("rc").style.display="none";
                        document.getElementById("rac").style.display="none";
                        document.getElementById("ac").style.display="none";
                        document.getElementById("sf").style.display="none";
                        break;
                    case "ac":
                        document.getElementById("ctn").style.display="none";
                        document.getElementById("utn").style.display="none";
                        document.getElementById("dtn").style.display="none";
                        document.getElementById("rc").style.display="none";
                        document.getElementById("rac").style.display="none";
                        document.getElementById("ac").style.display="inline";
                        document.getElementById("sf").style.display="none";
                        document.getElementById("lac").style.display="none";
                        break;
                    case "rc":
                        document.getElementById("ctn").style.display="none";
                        document.getElementById("utn").style.display="none";
                        document.getElementById("dtn").style.display="none";
                        document.getElementById("rc").style.display="inline";
                        document.getElementById("rac").style.display="none";
                        document.getElementById("ac").style.display="none";
                        document.getElementById("sf").style.display="none";
                        document.getElementById("lac").style.display="none";
                        break;
                    case "rac":
                        document.getElementById("ctn").style.display="none";
                        document.getElementById("utn").style.display="none";
                        document.getElementById("dtn").style.display="none";
                        document.getElementById("rc").style.display="none";
                        document.getElementById("rac").style.display="inline";
                        document.getElementById("ac").style.display="none";
                        document.getElementById("sf").style.display="none";
                        document.getElementById("lac").style.display="none";
                        break;
                    case "sf":
                        document.getElementById("ctn").style.display="none";
                        document.getElementById("utn").style.display="none";
                        document.getElementById("dtn").style.display="none";
                        document.getElementById("rc").style.display="none";
                        document.getElementById("rac").style.display="none";
                        document.getElementById("ac").style.display="none";
                        document.getElementById("sf").style.display="inline";
                        document.getElementById("lac").style.display="none";
                        break;
                    case "lac":
                        document.getElementById("ctn").style.display="none";
                        document.getElementById("utn").style.display="none";
                        document.getElementById("dtn").style.display="none";
                        document.getElementById("rc").style.display="none";
                        document.getElementById("rac").style.display="none";
                        document.getElementById("ac").style.display="none";
                        document.getElementById("sf").style.display="none";
                        document.getElementById("lac").style.display="inline";
                        break;    
                }
            }
        </script>
    </head>
    <body onload="checkMessage();">
       <%
                    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                    response.setHeader("Pragma", "no-cache");
                    response.setDateHeader("Expires", 0);
       %>
       
       
        <center>
        <br><br><br><br>
        <h3>NOTE Operations</h3>
        <form>
            Create Text Note <input type="radio" name="op" onclick="display('ctn');" ><br>
            Update Text Note <input type="radio" name="op" onclick="display('utn');"><br>
            Delete Text Note <input type="radio" name="op" onclick="display('dtn');"><br>
            Add Collaborator <input type="radio" name="op" onclick="display('ac');"><br>
            Delete Collaborator <input type="radio" name="op" onclick="display('rc');"><br>
            Delete All Collaborator <input type="radio" name="op" onclick="display('rac');"><br>
            List Collaborators of a note <input type="radio" name="op" onclick="display('lac');"><br>
            Submit Feedback <input type="radio" name="op" onclick="display('sf');"><br>
        </form>
        <br>
        <form action="logout">
            <input type="submit" value="Logout">
        </form>
        <br>
        <form id="ctn" style="display:none;" action="NoteOperations" method="POST">
            ----------------------------------------------------------------------
            <br>
            <input type="hidden" name="op" value="ctn" ><br>
            <textarea name="data" placeholder="Enter Data"></textarea><br>
            <input type="hidden" name="isTodoList" value="false"><br>
            <input type="submit"><br>
        </form>
        
        <form id="utn" style="display:none;" action="NoteOperations" method="POST">
            ----------------------------------------------------------------------
            <br>
            <input type="hidden" name="op" value="utn" ><br>
            <input type="text" name="noteId" placeholder="Enter NoteId"><br>
            <textarea name="data" placeholder="Enter Data"></textarea><br>
            <input type="submit"><br>
        </form>
        
        <form id="dtn" style="display:none;" action="NoteOperations" method="POST">
            ----------------------------------------------------------------------
            <br>
            <input type="hidden" name="op" value="dtn" ><br>
            <input type="text" name="noteId" placeholder="Enter NoteId"><br>
            <input type="submit"><br>
        </form>
        <form id="ac" style="display:none;" action="CollaboratorOperations" method="POST">
            ----------------------------------------------------------------------
            <br>
            <input type="hidden" name="op" value="ac" ><br>
            <input type="text" name="cUsername" placeholder="Username of Collaborator"><br>
            <input type="text" name="noteId" placeholder="Enter NoteId"><br>
            <input type="submit"><br>
        </form>
        
        <form id="rac" style="display:none;" action="CollaboratorOperations" method="POST">
            ----------------------------------------------------------------------
            <br>
            <input type="hidden" name="op" value="rac" ><br>
            <input type="text" name="noteId" placeholder="Note Id" ><br>
            Are you sure you want to remove all collaborators ? 
            <input type="submit" value="Remove"><br>
            <button onclick="function(){document.getElementById('rac').style.display='none';};" >Cancel</button>
        </form>
        
        <form id="lac" style="display:none;" action="CollaboratorOperations" method="POST">
            ----------------------------------------------------------------------
            <br>
            <input type="hidden" name="op" value="lac" ><br>
            <input type="text" name="noteId" placeholder="Note Id" ><br>
            <input type="submit" value="Submit"><br>
        </form>
        
        <form id="rc" style="display:none;" action="CollaboratorOperations" method="POST">
            ----------------------------------------------------------------------
             <br>
            <input type="hidden" name="op" value="rc" ><br>
            <input type="text" name="cUsername" placeholder="Username of Collaborator"><br>
            <input type="text" name="noteId" placeholder="Enter NoteId"><br>
            <input type="submit"><br>
        </form>
        
        <form id="sf" style="display:none;" action="GetFeedback" method="POST">
            ----------------------------------------------------------------------
             <br>
             <input type="hidden" name="username" value="<%=request.getSession().getAttribute("username")%>" placeholder="Username of Collaborator"><br>
             <textarea type="text" name="feedback" placeholder="Enter Feedback"></textarea><br>
            <input type="submit"><br>
        </form>
             
        <br><br>
        <span id="message" style="color:#FF0000;"></span>
        <br><br>
        <span id="sn">
            <jsp:include page="LoadNotes"/>
        </span>
        <br><br>
        <span id="message" style="color:#FF0000;"></span>
        </center>
    </body>
</html>
