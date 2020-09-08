<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
 	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=10, user-scalable=yes">
    <meta charset="UTF-8">

  <title>CMP Dashboard</title>
	
    <script type="text/javascript">
        var Ext = Ext || {}; // Ext namespace won't be defined yet...
        // This function is called by the Microloader after it has performed basic
        // device detection. The results are provided in the "tags" object. You can
        // use these tags here or even add custom tags. These can be used by platform
        // filters in your manifest or by platformConfig expressions in your app.
        //
        //HttpSession session=request.getParameter("session");
        //console.log(request.getAttribute("user_name"));
        Ext.beforeLoad = function (tags) {
            var s = location.search,  // the query string (ex "?foo=1&bar")
                profile;

            // For testing look for "?classic" or "?modern" in the URL to override
            // device detection default.
            //
            if (s.match(/\bclassic\b/)) {
                profile = 'classic';
            }
            else if (s.match(/\bmodern\b/)) {
                profile = 'modern';
            }
            // uncomment this if you have added native build profiles to your app.json
            /*else if (tags.webview) {
                if (tags.ios) {
                    profile = 'ios';
                }
                // add other native platforms here
            }*/
            else {
                //profile = tags.desktop ? 'classic' : 'modern';
                profile = tags.phone ? 'modern' : 'classic';
            }

            Ext.manifest = profile; // this name must match a build profile name
            // This function is called once the manifest is available but before
            // any data is pulled from it.
            //
            //return function (manifest) {
                // peek at / modify the manifest object
            //};
        };
    </script>
    

    <!-- The line below must be kept intact for Sencha Cmd to build your application -->
    <script id="microloader" type="text/javascript" src="bootstrap.js"></script>
</head>
<body>
 <!-- Set the user session for Ext.js dashboard-->
<% 
		String userName="",firstName="", lastName="";
	  	Cookie[] cookies = request.getCookies();
		  	if(cookies != null){
			  	for(Cookie cookie : cookies){
			   	if(cookie.getName().equals("user")) {
			   		userName = cookie.getValue();
			   	}else if(cookie.getName().equals("firstName")) {
			   		firstName = cookie.getValue();
			   	}else if(cookie.getName().equals("lastName")) {
			   		lastName = cookie.getValue();
			   	}
			   
		   }
	   }
 %>
<input type="hidden" value="<%=userName%>" id="userId">
<input type="hidden" value="<%=firstName%>" id="firstName">
<input type="hidden" value="<%=lastName%>" id="lastName">
</body>
</html>