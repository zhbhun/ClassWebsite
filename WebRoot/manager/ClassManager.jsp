<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
     <title>班级信息管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    
	<link href="css/base.css" rel="stylesheet" type="text/css" />
	<link href="css/admin.css" rel="stylesheet" type="text/css" />
	 <link rel="stylesheet" type="text/css" href="jquery.fancybox/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/header.js"></script>
	<script type="text/javascript" src="jquery.fancybox/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
    <script type="text/javascript" src="jquery.fancybox/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
    <script type="text/javascript">
              $(document).ready(function(){
            	  
            	  $(".fancybox").fancybox({
      				'width'				: '100%',
      				'height'			: '100%',
      				'autoScale'			: false,
      				'transitionIn'		: 'none',
      				'transitionOut'		: 'none',
      				'type'				: 'iframe'
      			});
              });
    </script>
  </head>
  
  <body >
  <div class="header" style="width:1300px;height:60px">
   <%@ include file="Header.html" %>
   </div>
   <div id="main">
       <div style="padding: 10px 0px 10px 10px;height: 20px;"> <a style="font: 15px tahoma, Arial, '宋体', Verdana, sans-serif;" href="banjiAction_accessChange" class="fancybox">修改班级信息</a></div>
       <div>
          <div>
            <h3>&nbsp;&nbsp;<s:property value="#request.banji.school"/>-<s:property value="#request.banji.major"/>专业-<s:property value="#request.banji.grade"/>级-<s:property value="#request.banji.classNum"/>班</h3>
          </div>
          <div>${requestScope.banji.indroduct}</div>
       </div>
   </div>
  </body>
</html>
