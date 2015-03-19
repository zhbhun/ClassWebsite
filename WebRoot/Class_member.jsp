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
    

<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/StudentManager.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="jqModal/css/jqModal_gray.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="jqModal/jqModal.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	 $('#detailDiv').jqm({
	      overlay: 0,
	      onShow: function(h) {
	        /* callback executed when a trigger click. Show notice */
	        h.w.css('opacity',0.92).slideDown(); 
	        },
	      onHide: function(h) {
	        /* callback executed on window hide. Hide notice, overlay. */
	        h.w.slideUp("slow",function() { if(h.o) h.o.remove(); }); } 
	      });
	 $("a.detailStudent").click(function(){
 		  //alert("hello"+$(this).attr("name"));
 		 var url = "/class/json/StudentAction_getStudentByIdToJson";
		   var params = {
				studentId:$(this).attr("name")
	       };
		   $.ajax({
		    	url:url,
		    	type:"POST",
		    	data:params,
		       	dataType:"json",
		       	success:function(data){
			   //  alert("hello");
			              
            $("#studentNum").html(data.studentNum);
			     $("#studentName").html(data.name);
			     $("#studentAge").html(data.age);
			     $("#studentSex").html(data.sex);
			     $("#studentPhone").html(data.phone);
			     $("#studentQQ").html(data.qq);
			     $("#studentEmail").html(data.mail);
			     $("#studentAddress").html(data.address);
			     $("#studentJob").html(data.job);
			     $("#detailDiv").jqmShow();
		       }
		       });
 	 });
});
</script>
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
<br>
<br>
<h2>通讯录</h2><hr>
<div id="detailDiv" class="jqmNotice"  style="height: 500px">
      <div class="detailDivTitle" >
         <h1 align='center' id="studenNameAsTitle">学生信息</h1>
      </div>
      <div  class="detailDivContent" style="text-align: center;">
       <table style="margin-left: 90px;margin-top: 30px;">
            <tr class="newCourseTr">
             <td class="left">学生学号：</td>
             <td><span id="studentNum"></span></td>
            
           </tr>
           <tr class="newCourseTr">
             <td class="left">学生姓名：</td>
             <td><span id="studentName"></span></td>
            
           </tr>
            <tr class="newCourseTr">
             <td class="left">学生年龄：</td>
             <td><span id="studentAge"></span></td>
           </tr>
           <tr class="newCourseTr">
             <td class="left">学生性别：</td>
             <td><span id="studentSex"></span></td>
           </tr>
            <tr class="newCourseTr">
             <td class="left">学生电话：</td>
             <td><span id="studentPhone"></span></td>
           </tr>
            <tr class="newCourseTr">
             <td class="left">学生QQ：</td>
             <td><span id="studentQQ"></span></td>
           </tr>
            <tr class="newCourseTr">
             <td class="left">学生email：</td>
             <td><span id="studentEmail"></span></td>
           </tr>
            <tr class="newCourseTr">
             <td class="left">学生住址：</td>
             <td><span id="studentAddress"></span></td>
           </tr>
            <tr class="newCourseTr">
             <td class="left">学生职务：</td>
             <td><span id="studentJob"></span></td>
           </tr>
           
         </table>
      
      </div>
      
      <img src="jqModal/css/images/close_icon.png" alt="close" class="jqmClose" />
      <img src="jqModal/css/images/resize.gif" alt="resize" class="jqResize" />
    </div>
 <table width="80%" height="70%" border="0" align="center" cellpadding="10" cellspacing="10" >
<% List<Student> cards =(List)request.getAttribute("cards");
int intPageSize;      //一页显示的记录数
int intPageCount;     //总页数
int intPage;         //待显示的页码
String strPage;
int a=cards.size();//获取记录总数
int index=0;            //指针
int i=0;
intPageSize=6; //设置一页显示的记录数
strPage=request.getParameter("page");//取得待显示的页码
if(strPage==null) //判断strPage是否等于null,如果是，显示第一页数据
{
intPage=1;
}else{
intPage=java.lang.Integer.parseInt(strPage); //将字符串转换为整型
}
if(intPage<1)
{
intPage=1;
}



intPageCount=(a+intPageSize-1)/intPageSize; 
if(intPage>intPageCount) intPage=intPageCount; //调整待显示的页码
if(intPageCount>0)
{
index=((intPage-1)*intPageSize); //将记录指针定位到待显示页的第一条记录上

}

while(i<intPageSize &&index!=a)
{if(i%3==0){
%>

<tr valign="top">
   <td valign="top">
   <table align="left" width="250px" height="150px" border="1"  rules=cols cellspacing=0>
   <tr bgcolor="#84C1FF"><td></td> </tr>
   <%if(cards.get(index).getSex().equals("男")){%>
   <tr  bgcolor=#CECEFF>
   <td><img src="images/boy.jpg">
   </td>
   </tr>
   <%}else{ %>
     <tr  bgcolor=#CECEFF>
   <td><img src="images/girl.jpg"><br>
   </td>
   </tr>
   <%} %>
   <tr bgcolor=#CECEFF>
   <td rowspan="10"><table style="font-family: sans-serif;font-size: small;">
   <tr> 
   <td><%=cards.get(index).getName()%></td>
   <td><%=cards.get(index).getJob().getName()%></td>
   </tr>
   <tr>
   <td colspan="2">QQ：<%=cards.get(index).getQq()%></td>
   </tr>
   <tr><td colspan="2">邮箱：<%=cards.get(index).getMail()%></td></tr>
   <tr>
   	<td colspan="2"><%=cards.get(index).getIntroduction()%></td>
   </tr>
    <tr>
  	<td colspan="2"><a class="detailStudent"  name="<%=cards.get(index).getId()%>" title="查看" style="cursor: pointer;">更多信息</a></td>
   </tr>
   </table><br>
   </td>
   </table>
   </td>
   <%}else if(i%3==1){%>
<td>
   <table width="250px" height="150px" border="1" rules=cols cellspacing=0>
   <tr bgcolor="#84C1FF"><td></td> </tr>
    <%if(cards.get(index).getSex().equals("男")){%>
   <tr  bgcolor=#CECEFF>
   <td><img src="images/boy.jpg">
   </td>
   </tr>
   <%}else{ %>
     <tr  bgcolor=#CECEFF>
   <td><img src="images/girl.jpg">
   </td>
   </tr>
   <%} %>
   <tr bgcolor="#CECEFF">
  <td rowspan="10"><table style="font-family: sans-serif;font-size: small;">
   <tr> 
   <td><%=cards.get(index).getName()%></td>
   <td><%=cards.get(index).getJob().getName()%></td>
   </tr>
   <tr>
   <td colspan="2">QQ：<%=cards.get(index).getQq()%></td>
   </tr>
   <tr><td colspan="2">邮箱：<%=cards.get(index).getMail()%></td></tr>
   <tr>
   	<td colspan="2"><%=cards.get(index).getIntroduction()%></td>
   </tr>
     <tr>
  	<td colspan="2"><a class="detailStudent"  name="<%=cards.get(index).getId()%>" title="查看" style="cursor: pointer;">更多信息</a></td>
   </tr>
   </table><br>
   </td>
   </table>
   </td>
<%}else{%>
<td>
   <table width="250px" height="150px" border="1" rules=cols cellspacing=0>
   <tr bgcolor="#84C1FF"><td></td> </tr>
    <%if(cards.get(index).getSex().equals("男")){%>
   <tr  bgcolor=#CECEFF>
   <td><img src="images/boy.jpg">
   </td>
   </tr>
   <%}else{ %>
     <tr  bgcolor=#CECEFF>
   <td><img src="images/girl.jpg">
   </td>
   </tr>
   <%} %>
   <tr bgcolor="#CECEFF">
   <td rowspan="10"><table style="font-family: sans-serif;font-size: small;">
   <tr> 
   <td><%=cards.get(index).getName()%></td>
   <td><%=cards.get(index).getJob().getName()%></td>
   </tr>
   <tr>
   <td colspan="2">QQ：<%=cards.get(index).getQq()%></td>
   </tr>
   <tr><td colspan="2">邮箱：<%=cards.get(index).getMail()%></td></tr>
   <tr>
   	<td colspan="2"><%=cards.get(index).getIntroduction()%></td>
   </tr>
    <tr>
   	<td colspan="2"><a class="detailStudent"  name="<%=cards.get(index).getId()%>" title="查看" style="cursor: pointer;">更多信息</a></td>
   </tr>
   </table><br>
   </td>
   </table>
   </td>
   <tr>
  <%}
index++;
i++;}%>
  
</table>
<table width="80%"  cellspacing="0" cellpadding="4" align="center" 

bordercolor="#A6CAF0" border=1>
</table>
<br>
<div align="center">
共<%=a%>个记录，分<%=intPageCount%>页显示，当前页是：第<%=intPage%>页  
<%if(intPage>1){%>
<a href='studentAction_cards?page=<%=intPage-1%>'>上一页</a>
<%}
for(int j=1;j<=intPageCount;j++)
{
out.print("  <a href='studentAction_cards'>"+j+"</a>");
}%>
<%if(intPage<intPageCount){%>
<a href='studentAction_cards?page=<%=intPage+1%>'>下一页</a>
<%}%>
<br>
  </body>
</html>
