<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
     <title>通知管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    
	<link href="css/base.css" rel="stylesheet" type="text/css" />
	<link href="css/admin.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript" src="js/admin.js"></script>
	<script type="text/javascript" src="js/header.js"></script>
	<script type="text/javascript">
		/*$().ready(function() {	
			var $menuItem = $("#menu .menuItem");	
			var $previousMenuItem;		
			$menuItem.click( function() {		
			   var $this = $(this);	
			   if ($previousMenuItem != null) {		
					$previousMenuItem.removeClass("current");		
				}		
				$previousMenuItem = $this;	
				$this.addClass("current");
				})
		})*/
		</script>
  </head>
  
  <body >
  <div class="header" style="width:1300px;height:60px">
   <%@ include file="Header.html" %>
   </div>
   <div id="main">
    <div align="center" style="padding-top: 100px;">
      <a href="informAction_getInformsByTimeAndPageForManager?page.currentPage=1" style="font: 30px tahoma, Arial, '宋体', Verdana, sans-serif;">通知列表</a><br/>
      <a href="informAction_accessAddInformJsp" style="font: 30px tahoma, Arial, '宋体', Verdana, sans-serif;">发布通知</a>
      </div>
   </div>
  </body>
</html>
