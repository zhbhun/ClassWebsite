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
    
     <title>留言管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    
	<link href="css/base.css" rel="stylesheet" type="text/css" />
	<link href="css/admin.css" rel="stylesheet" type="text/css" />
	<link href="css/Message.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript" src="js/admin.js"></script>
	<script type="text/javascript" src="js/header.js"></script>
	
  </head>
  
  <body >
  <s:if test="#request.isShowTip">
   <script type="text/javascript">
     alert("<s:property value="#request.operationState"/>");
   </script>
  </s:if>
  <div class="header" style="width:1300px;height:60px">
   <%@ include file="Header.html" %>
   </div>
   <div id="main">
     <div id="top" class="bar"><span>留言列表&nbsp;总记录数：<s:property value="#request.page.totalSize"/>(共<s:property value="#request.page.totalPage"/>页)</span></div>
    <div id="middle">
       <s:iterator  value="messageList" var ="item" >
	         <div class="message">
                 <div class="messageTop">
                  <a href="messageAction_deleteMessageForAdmin?messageId=<s:property value="#item.id"/>" style="float:right">删除</a>
                 <span>留言者：<s:property value="#item.person"/>（联系方式：<s:property value="#item.telephone"/>）&nbsp;&nbsp;留言时间：20<s:property value="#item.time"/></span>
               </div>
               <div class="messageContent" style="margin-top: 6px;"><s:property value="#item.content"/></div>
            </div>
         </s:iterator>		
       
     </div>
     <div id="bottom" >
       <div style="float:right;margin-top: 5px;">
         <ul class="pages">
          <li>总页数：<s:property value="#request.page.totalPage"/>&nbsp;</li>
          <li ><a href="messageAction_changeMessagePage?page.currentPage=1">首页</a></li>
           <s:if test="#request.page.hasPrePage">
               <li ><a  href="messageAction_changeMessagePage?page.currentPage=<s:property value="#request.page.currentPage-1"/>"><s:property value="#request.page.currentPage-1"/></a></li>
           </s:if>
           <li style="border: 1px solid #003F7E;"><a href="messageAction_changeMessagePage?page.currentPage=<s:property value="#request.page.currentPage"/>"><s:property value="#request.page.currentPage"/></a></li>
          <s:if test="#request.page.hasNextPage">
               <li ><a  href="messageAction_changeMessagePage?page.currentPage=<s:property value="#request.page.currentPage+1"/>"><s:property value="#request.page.currentPage+1"/></a></li>
          </s:if>
          <li ><a href="messageAction_changeMessagePage?page.currentPage=<s:property value="#request.page.totalPage"/>">末页</a></li>
         </ul>
        		
       </div>			
     </div>
   </div>
  </body>
</html>
