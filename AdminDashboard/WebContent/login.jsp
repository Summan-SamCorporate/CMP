<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
<head>
<title>CMP Automation</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/login/loginStyle.css" />

<script>
function myFunction() {
	  var x = document.getElementById("password");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
	} 
function clear_all() {
	document.getElementById("username").value ="";
	document.getElementById("password").value ="";/* 
	document.getElementById("show_password").setChecked(false) */;
	document.getElementById("model").value ="";
	
	}

</script>
</head>
<body>

	<div class="container">

		<div class="main">
			<form action="LoginServlet" method="post" id="login_form">
				<img src="" />
				<div class="header-div">
					<h1>
						<img alt="SAM Corporate" src="./resources/images/SAM_Corporate.png">
					</h1>
				</div>
				<br>
				<hr>			
				<table style="width:100%;">
 				 <tr>
    				<th><label>User Name :</label></th>
   				    <th align="left"><input type="text" name="username" id="username"></th>
    			 </tr>
  				 <tr>
				<tr>
    				<th><label>Password :</label></th>
   				    <th align="left"><input type="password" name="password" id="password"></th>
   				</tr>
   				<tr>
   					<th></th>
   				    <th align="left"><input type="checkbox" onclick="myFunction()" id="show_password">Show Password</th>
    			 </tr>
    			 <tr>
    				<th><label>Prototype :</label></th>
   				    <th align="left">
   				    <select name="model" id="model">
						<option value="0"></option>
						<option value="1">CMP</option>
						<option value="2">ICAAP</option>
						<option value="3">ST</option>
					</select> </th>
    			 </tr>   	
    			 <tr>
    			 <th></th>
    			 <th><%
					String login_msg = (String) request.getAttribute("error");
					if (login_msg != null)
						out.println("<font color=red size=2px>" + login_msg + "</font>");
				%></th>
    			 </tr>		 
  
			</table> 
				
			
    			 <input type="submit" name="login" id="login" value="Login">
    			 <input type="button" name="reset" id="reset" value="Reset" onClick="clear_all()">
				
			</form>
		</div>
	</div>

</body>
</html>
