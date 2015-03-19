<%@ page language="java" import="java.util.*,com.hibernate.domain.*" 
 pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri = "/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>班级成员</title>
    
<link href="css/style.css" rel="stylesheet" type="text/css" />

<style type="text/css">
<!--
.STYLE10 {
	font-size: small;
}
.STYLE11 {

}
-->
</style>
</head>
  
  <body style="text-align:center"  >

<jsp:include page="navigation.jsp"></jsp:include><br><br><br><br>
  
  <div id="main">

       <div id="middle">
          <table id="listTable" class="listTable" style="width: 100%;clear: both;">
			<tr>
			        <th class="c1">
						相片编号
					</th>
					<th class="c2">
					相片名称
					</th>
					<th class="c3">
					创建时间
					</th>
					
			</tr>
			
			<s:iterator  value="photoList" var ="item" >
	          	
			<tr class="checked">
			 <td class="c1">&nbsp;&nbsp;<s:property value="#item.id"/></td>
			 <td class="c2">&nbsp;&nbsp;<s:property value="#item.name"/></td>
			 <td class="c3">&nbsp;&nbsp;20<s:property value="#item.time"/></td>
			 <td class="c4">
					<a class="fancybox" href="photoAction_getPhotoById?photoId=<s:property value="#item.id"/>"  title="<s:property value="#item.name"/>">[查看相片]</a>
			 </td>
			</tr>	
            </s:iterator>		
			
			
		</table>
     
     </div>
     
     
     <!--
          <div align="center">
        总页数：<s:property value="#request.page.totalPage"/>&nbsp;
  <a href="albumAction_changeAlbumpageForUser?page.currentPage=1">首页</a>
           <s:if test="#request.page.hasPrePage">
               <li ><a  href="albumAction_changeAlbumpageForUser?page.currentPage=<s:property value="#request.page.currentPage-1"/>"><s:property value="#request.page.currentPage-1"/></a>
           </s:if>
<a href="albumAction_changeAlbumpageForUser?page.currentPage=<s:property value="#request.page.currentPage"/>"><s:property value="#request.page.currentPage"/></a>
          <s:if test="#request.page.hasNextPage">
               <li ><a  href="albumAction_changeAlbumpageForUser?page.currentPage=<s:property value="#request.page.currentPage+1"/>"><s:property value="#request.page.currentPage+1"/></a>
          </s:if>
 <a href="albumAction_changeAlbumpageForUser?page.currentPage=<s:property value="#request.page.totalPage"/>">末页</a>
 
        	-->			
     </div>	
     </div>
  </body>
</html>
