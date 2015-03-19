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
    
    <title>My JSP 'InformCommend.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/InformComment.css" rel="stylesheet" type="text/css" />

  </head>
  
  <body>
  <s:if test="#request.isShowTip">
   <script type="text/javascript">
     alert("<s:property value="#request.operationState"/>");
   </script>
  </s:if>
  <h3 align="center"><s:property value="#request.operationState"/></h3>
   <s:iterator  value="informCommentList" var ="item" >
	      <div class="comment" >
              <div >
                <a href="informCommentAction_deleteInformComment?informCommentId=<s:property value="#item.id"/>" style="float:right">删除</a>
                <span><s:property value="#item.student.name"/>：</span>
                <span>20<s:property value="#item.time"/></span>
              </div>
              <div class="content" style="margin-top: 5px;">
                  <s:property value="#item.content"/>
               </div>
         </div>
    </s:iterator>
    
   
  </body>
</html>
