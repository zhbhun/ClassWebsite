<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri = "/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   <title>班级相册</title>
   
<script type="text/javascript" src="js/jquery.min.js"></script>
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  <link href="css/style.css" rel="stylesheet" type="text/css" />
  <style type="text/css">
	table .c1{width:400px;text-align: left;}
	table .c2{width:150px;text-align: left;}
	table .c3{width:250px;text-align: left;}
	table .c4{width:100px;text-align: left;}
	table .checked{height:40px;}
	#page li{float: left;}
	</style>
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

<jsp:include page="navigation.jsp"></jsp:include>
    <br/>
    <br/>
    <div align="center" style="width: 1150px;margin-left:70px;text-align:left;margin-top: 20px;padding: 0px;">
     <div style="height:100%;width: 200px;float: right;border: solid;padding: 10px;border-color: gray;border-width: 1px 1px 0px 1px;text-align: center;">
         <form action="shareAction_getShareForUser" method="post">
         <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查找</span><br/><br/>
         查找名称：<input type="text" name="page.name" value="<s:property value="#request.page.name"/>" style="width: 100px;"/><br/><br/>
         <input type="submit" class="formButton" value="搜索"/>
         </form>
      </div>
     
      <div style="width:920px;border: solid;padding: 0px;height: 100%;border-color: gray;border-width: 1px 0px 0px 1px;">
      <table >
			<tr style="height: 30px;background: url(images/admin_bg.gif) 0px -60px repeat-x;">
			        <th class="c1">
						<a href="#" class="sort" >标题</a>
					</th>
					<th class="c2">
						<a href="#" class="sort" >附件</a>
					</th>
					
					<th class="c3">
						<a href="#" class="sort"  >分享时间</a>
					</th>
					<th class="c4">
						<a href="#" class="sort"  >分享者</a>
					</th>
			</tr>
			<s:iterator  value="shareList" var ="item" >
	          	
			<tr class="checked">
			 <td class="c1">&nbsp;&nbsp;<s:property value="#item.name"/></td>
			     <s:if test="#item.attachment==null||#item.attachment.equals(\"\")">
			      <td class="c2">&nbsp;无</td>
			         
                 </s:if>
                 <s:else>
			        <td class="c2">&nbsp;有，<a href="<s:property value="#item.attachment"/>">点击下载</a></td>
                 </s:else>
			 <td class="c3">&nbsp;20<s:property value="#item.time"/></td>
			 <td class="c4">&nbsp;<s:property value="#item.student.name"/></td>
			</tr>	
            </s:iterator>	
        </table>
        
       <div style="position: absolute;height:40px;bottom: -10px;right: 350px;">
         <ul id="page" style="list-style-type: none;float: right;">
          <li>总页数：<s:property value="#request.page.totalPage"/>&nbsp;</li>
          <li ><a href="shareAction_changeSharePageForUser?page.currentPage=1">首页</a></li>
           <s:if test="#request.page.hasPrePage">
               <li >&nbsp;&nbsp;&nbsp;&nbsp;<a  href="shareAction_changeSharePageForUser?page.currentPage=<s:property value="#request.page.currentPage-1"/>"><s:property value="#request.page.currentPage-1"/></a></li>
           </s:if>
           <li style="border: 1px solid #003F7E;">&nbsp;<a href="shareAction_changeSharePageForUser?page.currentPage=<s:property value="#request.page.currentPage"/>"><s:property value="#request.page.currentPage"/></a></li>
          <s:if test="#request.page.hasNextPage">
               <li >&nbsp;<a  href="shareAction_changeSharePageForUser?page.currentPage=<s:property value="#request.page.currentPage+1"/>"><s:property value="#request.page.currentPage+1"/></a></li>
          </s:if>
          <li >&nbsp;<a href="shareAction_changeSharePageForUser?page.currentPage=<s:property value="#request.page.totalPage"/>">末页</a></li>
         </ul>
        		
         </div>		
       </div>
    </div>
  </body>
</html>
