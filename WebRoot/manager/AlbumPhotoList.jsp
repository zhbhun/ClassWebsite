<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
     <title>XXX相册</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    
	<link href="css/base.css" rel="stylesheet" type="text/css" />
	<link href="css/admin.css" rel="stylesheet" type="text/css" />
	<link href="css/AlbumPhotoManager.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="jquery.fancybox/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
        
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/header.js"></script>
	<script type="text/javascript" src="jquery.fancybox/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
    <script type="text/javascript" src="jquery.fancybox/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
    <script type="text/javascript">
              $(document).ready(function(){
            	  $(".fancybox").fancybox({
      				'width'				: '75%',
      				'height'			: '75%',
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
  
       <div id="top" class="bar"><span>相片列表&nbsp;总记录数：0(共0页)</span></div>
        <div id="operate" style >
         <input type="button" class="formButton" onclick="location.href='AlbumList.jsp'" value="班级相册" style="float:left;"> 
         <span style='float: left;font-size: 15px;padding-top: 5px;'>》相册名:&nbsp;&nbsp;</span>
         <input type="button" class="formButton" onclick="location.href='AlbumList.jsp'" value="上传照片" style="float:left;"> 
         
         <form action="" method="post" >
         <span>&nbsp;&nbsp;&nbsp;&nbsp;查找：&nbsp;相片名称:</span>
         <input type="hidden" name="album_id" value=""/>
         <input type="text" name=""/>
         <input type="submit" class="formButton" value="搜索"/>
         </form>
         
      </div>
     <div id="middle">
       <table id="listTable" class="listTable" style="width: 100%;clear: both;">
			<tr>
			        <th class="c1">
						<a href="#" class="sort" >相片编号</a>
					</th>
					<th class="c2">
						<a href="#" class="sort"  >相片名称</a>
					</th>
					
					<th class="c3">
						<a href="#" class="sort"  >上传时间</a>
					</th>
					<th class="c4">
						<span style="font-size: 13px;font-family:Microsoft YaHei;color: #666;" >操作</span>
					</th>
			</tr>
			<tr class="checked">
			 <td class="c1">1</td>
			 <td class="c2">09级3班合照</td>
			 <td class="c3">2011年10月1日</td>
			 <td class="c4">
					<a href="" title="删除">[删除]</a>
					<a href="PhotoCommend.jsp" title="查看评论" class="fancybox">[查看评论]</a>
					<a href="" target="_blank" title="浏览">[浏览]</a>
			 </td>
			</tr>		
		</table>
     
     </div>
     <div id="bottom"></div>
   </div>
  </body>
</html>
