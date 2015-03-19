<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    <%
	System.out.println(445545454);
%>
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="imagetoolbar" content="no" />
	<title>FancyBox 1.3.4 | Demonstration</title>
	<script type="text/javascript" src="/DiTan/js/jquery.js"></script>
	<script>
		!window.jQuery && document.write('<script src="jquery-1.4.3.min.js"><\/script>');
	</script>
	<script type="text/javascript" src="jquery.fancybox/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="jquery.fancybox/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="jquery.fancybox/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
 	<link rel="stylesheet" href="jquery.fancybox/style.css" />
	<script type="text/javascript">
		$(document).ready(function() {
			/*
			*   Examples - images
			*/

			$("a#example1").fancybox();

			
		});
	</script>
</head>
<body>
<div id="content">
	<h1>fancybox <span>v1.3.4</span></h1>

	<p>This is a demonstration. <a href="http://fancybox.net">Home page</a></p>

	<hr />

	<p>
		Different animations<br />

		<a id="example1" href="jquery.fancybox/example/1_b.jpg"><img alt="example1" src="jquery.fancybox/example/1_s.jpg" /></a>

		
	</p>

	
</div>
</body>
</html>