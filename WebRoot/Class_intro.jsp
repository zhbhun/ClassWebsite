<%@ page language="java" import="java.util.*,com.hibernate.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri = "/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
     <script type='text/javascript' src='js/roller.js' ></script>
    <title>班级简介</title>
    
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/introduction.css" rel="stylesheet" type="text/css" />
<script language=javascript src="js/jquery.min.js"></script>

<jsp:include page="navigation.jsp"></jsp:include>

<style type="text/css">
<!--
.STYLE10 {
	font-size: small;
}
.STYLE11 {

}
-->

</style>
 <script type='text/javascript' src='js/roller.js' ></script>
<script type="text/javascript">

function showIntr(){
 $("#intro").show();
 $("#leader").hide();
 $("#mail").hide();
 $("#contact").hide();
 $("#address").hide();
 
} 
function showleader(){
 $("#leader").show();
 $("#intro").hide();
 $("#mail").hide();
 $("#contact").hide();
 $("#address").hide();
} 
function showmail(){
	$("#mail").show();
    $("#leader").hide();
    $("#intro").hide();
    $("#contact").hide();
   
    $("#address").delay(500).fadeIn(1000);
	
}
function showcontact(){
	$("#mail").hide();
    $("#leader").hide();
    $("#intro").hide();
    $("#contact").show();
    $("#address").hide();
	
	
}

</script>


</head>
  
<!--  <body style="text-align:center"  >-->



<!--<div class="content" id="content">-->
<!--  <table width="100%" height="85%" border="1">-->
<!--    <tr>-->
<!--      <td width="15%" height="50%" align="center">-->
<!--	  	<ul>-->
<!--	  		<li><a href="action/BanjiAction_intro" onclick="showIntr()">班级简介</a></li>-->
<!--	  		<li><a href="action/StudentAction_leader" onclick="showleader()">班委</a></li>-->
<!--	  	</ul>-->
<!--	  </td>-->
<!--      <td width="85%" rowspan="2" background="images/intro.jpg">-->
<!--      <div id="intro">-->
<!--     <s:property value="banji.indroduct"/>-->
<!--     </div>-->
<!--      <div id="leader">-->
<!--     <s:iterator  value= "leader" var = "l" >-->
<!--	<table border="1">-->
<!--	<tr>-->
<!--	    <td><s:property value="#l.job.name"/></td>-->
<!--		<td><s:property value="#l.name"/></td>-->
<!--	</tr>-->
<!--	</table>-->
<!--   </s:iterator>-->
<!--     </div>-->
<!--     -->
<!--	-->
<!--      </td>-->
<!--    </tr>-->
<!--    <tr>-->
<!--      <td height="50%" align="center"><a href="#">联系我们</a></td>-->
<!--    </tr>-->
<!--  </table>-->
<!--</div>-->


<!--	</body>-->

	<style>
   body {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	margin: 0;
	font-size: 80%;
	font-weight: bold;
	}	
	
	ul {
	list-style: none;
	margin: 0;
	padding: 0;
	}
	#nav_container3{
	width:145px;
	margin-left:60px;
	margin-top:30px;
	float:left;
	}
	#nav_container3 li{
	padding:0px;
	margin-bottom:1px;
	}
	#nav_container3 a:link,#nav_container3 a:visited,#nav_container3 a:active{
	width:145px;
	background: url(images/bg4.gif) -200px 0px repeat-x;
	font-weight:bold;
	height:30px;
	padding-top:5px;
	padding-left:50px;
	display:block;
	text-align:left;
	border-bottom:1px solid #777;
	text-decoration:none;
	color:#333;
	}
	#nav_container3 p {
	width:145px;
	background: url(images/bg4.gif) -200px 0px repeat-x;
	font-weight:bold;
	height:30px;
	padding-top:5px;
	padding-left:50px;
	display:block;
	text-align:left;
	border-bottom:1px solid #777;
	text-decoration:none;
	color:#333;
	}
	#nav_container3 a:hover{
	color:#000;
	background: url(images/bg4.gif)  -24px 0px repeat-x;
	}
	#nav_container3 p:hover{
	color:#000;
	background: url(images/bg4.gif)  -24px 0px repeat-x;
	}
	#nav_home{
	background-image: url(images/bg7.gif);
	background-position: 0px 0px;
	}
	#nav_home:hover{
	background-image: url(images/bg7.gif);
	background-position: 0px -45px;
	}
		
	
	#nav_about{
	background-image: url(images/bg8.gif);
	background-position: 0px 0px;
	}
	#nav_about:hover{
	background-image: url(images/bg8.gif);
	background-position: 0px -45px;
	}
		
	
	#nav_port{
	background-image: url(images/bg9.gif);
	background-position: 0px 0px;
	}
	#nav_port:hover{
	background-image: url(images/bg9.gif);
	background-position: 0px -45px;
	}
		
	
	#nav_con{
	background-image: url(images/bg10.gif);
	background-position: 0px 0px;
	}
	#nav_con:hover{
	background-image: url(images/bg10.gif);
	background-position: 0px -45px;
	}
	
.sub_text {

	margin-left: 350px;
	margin-top: 100px;
    width: 700px;
    font-size: 5px;
    text-align: left;
    line-height: 20px;
    overflow: hidden;
<!--    margin: 0px auto;-->
}
.sub_text p {
    text-indent: 2em;
}
</style>
<body>
<div id="nav_container3">
			<ul>
				<li>			
					<a href="action/banjiAction_intro" onclick="showIntr()">班级简介</a>
				</li>
				<li>		<br>		
					<a href="action/studentAction_leader" onclick="showleader()">班委</a>
				</li>
				<li>				
					 <p onclick="showmail()" onmouseover="this.style.cursor='hand'" >班级公共邮箱</p>
				</li>
				<li>				
					<p onclick="showcontact()" onmouseover="this.style.cursor='hand'" >联系我们</p>
				</li>
			</ul>
</div>
		
<script>
roller.init('nav_container3','h',-200,-24,100,20);
</script>
<div class="sub_text" align = "center" style="position: relative;;">
<div id="intro" style="background: url('images/class_intro.jpg');">
<font face="Times New Roman" size="2" color="8B008B">
 <s:property value="banji.indroduct"/></font>    
     </div>
      <div id="leader">
      <table>
      <tr>
       <s:iterator  value= "leader" var = "l" >
       <td>
      	<table border="40" width="100px" height="150px" style="position: !important; background-image: url('images/class_intro.jpg'); border-color: #228B22;border-bottom-style: groove;">
      	<tr>
	    <td><font face="Times New Roman" size="2" color="#008000"><s:property value="#l.job.name"/>	</font></td>
		<td><font face="Times New Roman" size="2" color="#008000"><s:property value="#l.name"/>	</font></td>
		</tr>
		</table> 
		</td>	
       </s:iterator>
       
      </tr>
      </table>
      
     
    
	
     </div>
     <div id="mail" style="display:none">
     <table width="50%" height="50%" align="center" bgcolor="#d3a4ff">
     <tr>
     
     
     <td>
      <div id="address" style="display:none"> 
    <label for="name">Mail address：</label>
    <label for="mail"  class="login">sdu.200903@gmail.com</label>
    <label for="password">Password:</label>
    <label for="pass" class="login">sdu200903</label>
</div> 
     </td>
     <td rowspan="3"><img src="images/mailbox.jpg">
     </td>
     </tr>
     </table>
  </div> 
     <div id="contact" style="display:none;">
    <table width="55%" height="46%" align="center" >
         <tr>
            <td style="background: url('images/contact.jpg');">  
            	
 			</td>
 		</tr>
 		</table>     
      </div>


</div>
</body>
