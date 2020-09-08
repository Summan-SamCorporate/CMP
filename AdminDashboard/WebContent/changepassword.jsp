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
<script type="text/javascript">

var check = function() {
	  if (document.getElementById('new_password').value ==
	    document.getElementById('confirm_password').value) {
	    document.getElementById('confirm_password').style.color = 'green';
	    document.getElementById("login").disabled = false;
	  } else {
	    document.getElementById('confirm_password').style.color = 'red';

	    document.getElementById("login").disabled = true;
	  }
	}
</script>
</head>
<body>

	<div class="container">

		<div class="main">
			<form action="LoginServlet" method="post">
				<img src="" />
				<div class="header-div">
					<h1>
						<img alt="SAM Corporate" src="./images/SAM_Corporate.png">
					</h1>
				</div>
				<br>
				<hr>
				<table style="width:100%;">
 				 <tr>
    				<th><label>User Name :</label></th>
   				    <th align="left"><input type="text" name="" id="" value="<%= request.getAttribute("user_name") %>"  disabled/></th>
    			 </tr>
    			 <tr>
    				<th><label>New Password :</label></th>
   				    <th align="left"><input type="password" name="new_password" id="new_password" onkeyup="check();"></th>
    			 </tr>
    			 <tr>
    				<th><label>Retype :</label></th>
   				    <th align="left"><input type="password" name="confirm_password" id="confirm_password" onkeyup="check();"></th>
    			 </tr>
    			 <tr>
    				<th><label>Prototype :</label></th>
   				    <th align="left"><select name="model" disabled>
					<option value="0"></option>
					<option value="1">CMP</option>
					<option value="2">ICAAP</option>
					<option value="3">ST</option>
				</select></th>
    			 </tr>
  				</table>
				 
				 <input type="hidden" name="username" id="username" value="<%= request.getAttribute("user_name") %>"  />
				
				<input type="submit" name ="login" id="login" value="Login"></input>
			 
				<input type="button" name="reset" id="reset" value="Reset">


			</form>
		</div>
	</div>

</body>
</html>
