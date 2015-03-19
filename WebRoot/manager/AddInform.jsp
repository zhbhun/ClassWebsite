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
    
    <title>发布通知</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/base.css" rel="stylesheet" type="text/css" />
	<link href="css/admin.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/header.js"></script>
     <script type="text/javascript" src="ckeditor/ckeditor.js"></script>
    
  </head>
  
  <body>
  <s:if test="#request.isShowTip">
   <script type="text/javascript">
     alert("<s:property value="#request.operationState"/>");
   </script>
  </s:if>
    <div class="header" style="width:1300px;height:60px">
   <%@ include file="Header.html" %>
   </div>
   
   <div id="main" style="background-color: #E9F0F4;">
      <div style="height: 30px;line-height: 30px;margin-left: 20px">
        <s:if test="#request.inform!=null">
            <s:if test="#request.inform.id!=null">
              <span style='font-size: 15px;'><s:property value="#request.operationState"/>&nbsp;</span>
              <a style='font-size: 15px;' href="<s:property value="#request.inform.id"/>">查看该通知</a>
            </s:if>
        </s:if>
       </div>
      <form action="informAction_addInform" method="post" enctype="multipart/form-data">
       <div align="center">
       <label style="font-family: Microsoft YaHei;font-size: 14px;">标题:&nbsp;&nbsp;&nbsp;</label>
       <input type="text" name="inform.name"/>
       </div>
       <div>
       <textarea name="inform.content" id="editor" rows="" cols="" ></textarea>
        <script type="text/javascript"> 
           CKEDITOR.replace( 'editor', 
           { 
             skin : 'kama', 
             width: '1100',
             height: '300',
             resize_enabled: 'false',
             language : 'zh-cn' 
           }); 
          </script>
          </div>
          <br />
          <br />
          <div align="center">
           <input type="submit" class="formButton" value="发布"/>
           <input type="reset" class="formButton"/>
         </div>
       </form>
   </div>
  </body>
</html>
