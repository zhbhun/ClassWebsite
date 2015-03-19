<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
     <title>管理中心</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    
	<link href="css/base.css" rel="stylesheet" type="text/css" />
	<link href="css/admin.css" rel="stylesheet" type="text/css" />
	<link href="css/ManagerIndex.css" rel="stylesheet" type="text/css" />
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
   <div id="mainInformation">
   <div  style="width: 1100px;height: 100%;text-align: center;margin-left: 150px;margin-top: 10px;">
    <div id="softInformation">
    <table class="listTable">
				<tr >
					<th colspan="2" class="messageTitle">
						软件信息
					</th>
				</tr>
				<tr>
					<td width="110" class="messageItem">
						系统名称: 
					</td>
					<td class="messageItem">
						班级管理系统
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						系统版本: 
					</td>
					<td class="messageItem">
						1.0.0演示版
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						官方网站: 
					</td>
					<td class="messageItem">
						<a href="/">软件09级三班</a>
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						交流论坛: 
					</td>
					<td class="messageItem">
						<a href="、">软件09级三班</a>
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						BUG反馈邮箱: 
					</td>
					<td class="messageItem">
						<a href="1098974370@qq.com">1098974370@qq.com</a>
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						商业授权: 
					</td>
					<td class="messageItem">
						未取得商业授权之前,您不能将本软件应用于商业用途
						<a class="red" href="/" target="_blank">[授权查询]</a>
					</td>
				</tr>
			
			</table>
    </div>
    <div id="systemInformation">
      <table class="listTable">
				<tbody><tr>
					<th colspan="2" class="messageTitle">
						系统信息
					</th>
				</tr>
				<tr>
					<td width="110" class="messageItem">
						Java版本: 
					</td>
					<td class="messageItem">
						1.6.0_29
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						操作系统名称: 
					</td >
					<td class="messageItem">
						window7
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						操作系统构架: 
					</td>
					<td class="messageItem">
						???
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						操作系统版本: 
					</td>
					<td class="messageItem">
						???
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						Server信息: 
					</td>
					<td class="messageItem">
						Apache Tomcat/6.0.33
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						Servlet版本: 
					</td>
					<td class="messageItem">
						3.0
					</td>
				</tr>
			</tbody></table>
    </div>
    <div id="statisticsInformation">
      <table class="listTable">
				<tbody><tr>
					<th colspan="2" class="messageTitle" style="width: 700px;">
						信息统计
					</th>
				</tr>
				<tr>
					<td width="110" class="messageItem">
						已发通知数: 
					</td>
					<td class="messageItem">
						0
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						已建相册数：
					</td>
					<td class="messageItem">
						0
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						已上传相片数：
					</td>
					<td class="messageItem">
						0
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						班级成员数： 
					</td>
					<td class="messageItem">
						0
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						共享资源数目: 
					</td>
					<td class="messageItem">
						0
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						留言总数: 
					</td>
					<td class="messageItem">
						0
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						班级课程总数: 
					</td>
					<td class="messageItem">
						0
					</td>
				</tr>
				<tr>
					<td class="messageItem">
						友情链接总数: 
					</td>
					<td class="messageItem">
						0
					</td>
				</tr>
			</tbody></table>
    </div>
    </div>
   </div>
  </body>
</html>
