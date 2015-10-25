<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="database.User" %>
<!DOCTYPE html>

<html>
<head>
    <link href="Css/index.css" rel="stylesheet" />
    <title>O' Diary</title>
</head>
<body onload="loadLogin()" onload="disp()">
    <header>
        <div class="Logo">
            <h1>O'Diary</h1>
        </div>
        <div class="tab_link user_name">        
		<%
			String name = (String) session.getAttribute("Name");
			String id = (String) session.getAttribute("ID");
			session.setAttribute("Name", name);
			session.setAttribute("ID", id);
        	out.print(name);
        %></div>
        <div class="tab_link access_mem"><a href="#" onclick="dispTimeline()">Timeline</a></div>
        <div class="tab_link search_box"><a href="#" onclick="dispsearchUser()">Users</a></div>
        <div class="tab_link create_mem"><a href="#" onclick="dispcreateMem()">Create</a></div>
    </header>
    <section class="Main_body">
    <div id="display">
    
	<%
		String[] array = (String[]) request.getAttribute("userlist");
		int arraylength = array.length;
		if(arraylength != 0){
			out.println("<h3> Following Users Found: </h3>");
			for(int i=0,j=1;i< arraylength;i=i+3,j++){
				out.print(j+". ");
				out.print("<b>");
				out.print(array[i]+" ");
				out.print("</b><i>");
				out.print(array[i+1]+" ");
				out.print("</i> (");
				out.print(array[i+2]+")<br>");
			}
		}
		else{
			out.print("<h3>");
			out.print("NO USER FOUND.</h3><br>");
		}
	%>
	
	</div>
	<div class="Timeline" id="timeline" style="display:none">
        <%
        	List<String> tl = User.timeline(id);
        	if(tl.size() != 0){
        		for(int i=0; i<tl.size(); i=i+2){
        			out.print("<h3><i>"+tl.get(i)+"</i></h3>");
        			out.print(tl.get(i+1)+"<br><br>");
        		}
        	}
        	else{
        		out.print("<h3>You do not have any memories.</h3>");
        	}
        %>
    </div>
    </section>
    <footer></footer>
    <script src="Js/jquery-1.11.3.min.js"></script>
    <script src="Js/index.js"></script>
</body>
</html>