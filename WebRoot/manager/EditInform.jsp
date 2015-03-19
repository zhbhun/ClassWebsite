<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改通知</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/base.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>

  </head>
  
  <body>
  <s:if test="#request.isShowTip">
   <script type="text/javascript">
     alert("<s:property value="#request.operationState"/>");
   </script>"
  </s:if>
  <div align='center'>
    <form action="informAction_changeInform" method="post" enctype="multipart/form-data">
       <div align="center">
       <input type="hidden" name="inform.id" value="<s:property value="#request.inform.id"/>"/>
       <label style="font-family: Microsoft YaHei;font-size: 14px;">标题:&nbsp;&nbsp;&nbsp;</label>
       <input type="text" name="inform.name" value="<s:property value="#request.inform.name"/>"/>
       </div>
       <div>
       <textarea name="inform.content" id="editor" rows="" cols=""><s:property value="#request.inform.content"/></textarea>
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
          <div align="center">
           <input type="submit" class="formButton" value="发布"/>
           <input type="reset" class="formButton"/>
         </div>
      </form>
      </div>
  </body>
</html>
