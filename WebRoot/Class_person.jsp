<%@ page language="java" import="java.util.*,com.hibernate.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Student s = (Student)session.getAttribute("student");
%>
<%@ taglib uri = "/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>班级简介</title>
    
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/introduction.css" rel="stylesheet" type="text/css" />
   <script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script language=javascript src="js/jquery.min.js"></script>
<style type="text/css">
<!--
.STYLE10 {
	font-size: small;
}
.STYLE11 {

}
-->

</style>
<script language="javascript">
$(document).ready(function(){

  $("#share_submit").click(function(){
      if($("#share_name").val()==""){
        alert("请输入上传的资源标题");
        return false;
      }
      else
       return true;
  });
  
});
</script>
<script type="text/javascript">
function showShare(){
 $("#share1").show();
  $("#intro").hide();
} 
function showIntro(){
 $("#share1").hide();
  $("#intro").show();
} 
</script>
</head>
  <body style="text-align:center" >
   <jsp:include page="navigation.jsp"></jsp:include><br><br><br>
  <table width="100%" height="75%">
  <tr>
    <td width="5%">&nbsp;</td>
    <td valign="top" width="90%" rowspan="2">
    
    <%if(s == null){%>
  	请先登录
   <% }else{%>
   <input type="button" value="个人信息" onclick="showIntro()">
   <input type="button" value="分享资源" onclick="showShare()">
   <table align="center" border="1">
   <tr>
    <td width="280px" height="550px" 

background="images/person_bac.jpg" id="intro">
    <div id="person" align="center" >
  <h3 style="border-color: #777;">基本资料</h3>
   <form action="action/studentAction_modify" method="post">
   <ul style="f">
   <li style="display: none;">
   <label>ID:<input type="text" name="student.banji.id" 

value="<s:property value="student.banji.id"/>"> </label>
   </li>
   <li style="display: none;">
   <label>job:<input type="text" name="student.job.id" 

value="<s:property value="student.job.id"/>"></label>
   </li>
   <li style="display: none;">
   <label>password:<input type="text" name="student.password" 

value="<s:property value="student.password"/>"></label>
   </li>
   <li>
   <label>学号:<input type="text" name="student.studentNum" 

value="<s:property value="student.studentNum"/>"> </label>
   </li>
   <li>
   	<label>姓名:<input type="text" name="student.name" 

value="<s:property value="student.name"/>"></label>
   </li>
   <li>
   	<label>年龄:<input type="text" name="student.age" 

value="<s:property value="student.age"/>"></label>
   </li>
   <li>
   	<label>性别:<input type="text" name="student.sex" 

value="<s:property value="student.sex"/>"></label>
   </li>
   <li>
   	<label>职务:<input type="text" name="student.job.name" 

value="<s:property value="student.job.name"/>" 

disabled="disabled"></label>
   </li>
   <li>
   	<label>电话:<input type="text" 

name="student.phonenumber" value="<s:property 

value="student.phonenumber"/>"></label>
   </li>
   <li>
   <label>QQ:<input type="text" name="student.qq" 

value="<s:property value="student.qq"/>"></label>
   </li>
   <li>
   <label>邮箱:<input type="text" name="student.mail" 

value="<s:property value="student.mail"/>"></label>
   </li>
   <li>
   <label>地址:<input type="text" name="student.address" 

value="<s:property value="student.address"/>"></label>
   </li>
   <li>
   <label>籍贯:<input type="text" name="student.nativePalce" 

value="<s:property value="student.nativePalce"/>"></label>
   </li>
   <li>
   <label>简介:<input type="text" name="student.introduction" 

value="<s:property value="student.introduction"/>"></label>
   </li>
   <li> <input type="submit" value="提交"/></li>
   </ul>
   </form>
  </div>
    </td>
    </tr>
    <tr>
    <td width="40%"  >
  <div id="share" style="border-style: outset; background-

color: window;"  >
  <div id="share1" style="display: none; font-family: serif;" 

align="center">
  	<form action="shareAction_addShare" method="POST" 

enctype="multipart/form-data" >
<label>分享标题：
<input name="share.name"id="share_name" /></label>
<br/><br/>
<label>分享内容：</label>
<textarea name="share.content" id="editor" rows="" cols="" 

></textarea>
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
   <label style="margin-left:200px">附件：<input type="file" 

name="upload" size="50"/></label>
</div>
<br/>
<div align="center">
   <input type="submit" class="formButton" id="share_submit" 

value="提交"/>
   <input type="reset" class="formButton"/>
 </div>
</form>
  </div>
  
  </div>
   </td>
   </tr>
   </table>
  
   <%} %>
    
		</td>
    <td width="5%">&nbsp;</td>
  </tr>
</table>
  </body>
</html>
