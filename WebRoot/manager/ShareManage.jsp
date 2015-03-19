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
    
     <title>分享管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    
	<link href="css/base.css" rel="stylesheet" type="text/css" />
	<link href="css/admin.css" rel="stylesheet" type="text/css" />
	<link href="css/ShareManager.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/header.js"></script>
	
  </head>
  
  <body >
  <div class="header" style="width:1300px;height:60px">
   <%@ include file="Header.html" %>
   </div>
  <div id="main">
     <div id="top" class="bar"><span>分享列表&nbsp;总记录数：0(共0页)</span></div>
     <div id="operate" >
         <form action="shareAction_getShareForAdmin" method="post">
         <span>&nbsp;&nbsp;&nbsp;&nbsp;查找：&nbsp;分享名称</span>
         <input type="text" name="page.name" value="<s:property value="#request.page.name"/>"/>
         <input type="submit" class="formButton" value="搜索"/>
         </form>
      </div>
     <div id="middle">
       <table id="listTable" class="listTable" style="width: 100%;clear: both;">
			<tr>
			        <th class="c1">
						<a href="#" class="sort" >分享编号</a>
					</th>
					<th class="c2">
						<a href="#" class="sort"  >分享名称</a>
					</th>
					
					<th class="c3">
						<a href="#" class="sort"  >分享时间</a>
					</th>
					<th class="c4">
						<a href="#" class="sort"  >分享者</a>
					</th>
					<th class="c5">
						<span style="font-size: 13px;font-family:Microsoft YaHei;color: #666;" >操作</span>
					</th>
			</tr>
			<s:iterator  value="shareList" var ="item" >
	          	
			<tr class="checked">
			 <td class="c1">&nbsp;&nbsp;<s:property value="#item.id"/></td>
			 <td class="c2">&nbsp;&nbsp;<s:property value="#item.name"/></td>
			 <td class="c3">&nbsp;&nbsp;20<s:property value="#item.time"/></td>
			 <td class="c4">&nbsp;&nbsp;<s:property value="#item.student.name"/></td>
			 <td class="c5">
					&nbsp;
					<a href="shareAction_deleteShare?shareId=<s:property value="#item.id"/>" title="删除">[删除]</a>
					<a href="<s:property value="#item.id"/>" target="_blank" title="浏览">[浏览]</a>
			 </td>
			</tr>	
            </s:iterator>	
            	
			
		</table>
     
     </div>
    <div style="height: 37;"></div>
     <div id="bottom" >
       <div style="float:right;margin-top: 5px;">
         <ul class="pages">
          <li>总页数：<s:property value="#request.page.totalPage"/>&nbsp;</li>
          <li ><a href="shareAction_changeSharePage?page.currentPage=1">首页</a></li>
           <s:if test="#request.page.hasPrePage">
               <li ><a  href="shareAction_changeSharePage?page.currentPage=<s:property value="#request.page.currentPage-1"/>"><s:property value="#request.page.currentPage-1"/></a></li>
           </s:if>
           <li style="border: 1px solid #003F7E;"><a href="shareAction_changeSharePage?page.currentPage=<s:property value="#request.page.currentPage"/>"><s:property value="#request.page.currentPage"/></a></li>
          <s:if test="#request.page.hasNextPage">
               <li ><a  href="shareAction_changeSharePage?page.currentPage=<s:property value="#request.page.currentPage+1"/>"><s:property value="#request.page.currentPage+1"/></a></li>
          </s:if>
          <li ><a href="shareAction_changeSharePage?page.currentPage=<s:property value="#request.page.totalPage"/>">末页</a></li>
         </ul>
        		
       </div>			
     </div>
   </div>
  </body>
</html>
