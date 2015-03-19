<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>无标题文档</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    
     <script type="text/javascript" src="ckeditor/ckeditor.js"></script>
</head>
<body>
<div align="center">
<form action="shareAction_addShare" method="POST" enctype="multipart/form-data" >

<label>分享标题：</label>
<input name="share.name"/>
<br/><br/>
<label>分享内容：</label><br/>
<textarea name="share.content" id="editor" rows="" cols="" ></textarea>
 <script type="text/javascript"> 
           CKEDITOR.replace( 'editor', 
           { 
             skin : 'kama', 
             width: '600',
             height: '100',
             resize_enabled: 'false',
             language : 'zh-cn' 
           }); 
   </script>
   <br/>
   <div align="center">
   <label style="margin-left:200px">附件：</label>
<input type="file" name="upload" size="50"/>
</div>
<br/>
<div align="center">
   <input type="submit" class="formButton" value="提交"/>
   <input type="reset" class="formButton"/>
 </div>
</form>
</div></body>
</html>