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
    
    <title>班级信息修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
   <link href="css/base.css" rel="stylesheet" type="text/css" />

  </head>
  
  <body>
   <s:if test="#request.isShowTip">
   <script type="text/javascript">
     alert("<s:property value="#request.operationState"/>");
   </script>
  </s:if>
    <form action="banjiAction_changeBanji" method="post" enctype="multipart/form-data">
       <div align="center">
          <input type="hidden" name="banji.id" value="<s:property value="#request.banji.id"/>"/>
          <label>学校:</label>
          <input type="text" name="banji.school" value="<s:property value="#request.banji.school"/>"/>
           
            <label>专业:</label>
          <input type="text" name="banji.major" value="<s:property value="#request.banji.major"/>"/>
          
           <label>年级:</label>
          <input type="text" name="banji.grade" value="<s:property value="#request.banji.grade"/>"/>
          
           <label>班级:</label>
          <input type="text" name="banji.classNum" value="<s:property value="#request.banji.classNum"/>"/>
          <br/>
          
           
           <br/>
           <textarea rows="" cols="" id="editor" name="banji.indroduct"><s:property value="#request.banji.indroduct"/></textarea>
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
          <br/>
           <div align="center">
           <input type="submit" class="formButton" value="修改"/>
           <input type="reset" class="formButton"/>
         </div>
       </div>
    </form>
  </body>
</html>
