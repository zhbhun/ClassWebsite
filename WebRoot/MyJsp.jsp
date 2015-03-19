<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="jqModal/css/jqModal_gray.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="jqModal/jqModal.js"></script>
	 <script type="text/javascript">
	$(document).ready(function(){
	
	  $('#addDiv').jqm({
            	      trigger:'#addStudent',
            	      overlay: 0,
            	      onShow: function(h) {
            	        /* callback executed when a trigger click. Show notice */
            	        h.w.css('opacity',0.92).slideDown(); 
            	        },
            	      onHide: function(h) {
            	        /* callback executed on window hide. Hide notice, overlay. */
            	        h.w.slideUp("slow",function() { if(h.o) h.o.remove(); }); } 
            	      });
            	      alert('13');
	  });
    </script>
  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
