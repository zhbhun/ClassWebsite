<%@ page language="java" import="java.util.*,com.hibernate.domain.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<link rel="stylesheet" type="text/css" href="css/login.css"/>
   <script type='text/javascript' src='js/roller.js' ></script>
  
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
	margin-top: 20px;
	}
	
	#nav_container4{
	width:1200px;
	margin-left:60px;
	margin-top:30px;
	float:left;
	}
	#nav_container4 li{
	padding:0px;
	margin-bottom:1px;
	float:left;
	border-right:1px solid #ccc;
	border-left: 1px solid #888;
	}
	#nav_container4 a:link,#nav_container4 a:visited,#nav_container4 a:active{
	width:145px;
	background: url(images/bg5.gif) 0px -70px repeat-x;
	font-weight:bold;
	height:30px;
	padding-top:5px;
	display:block;
	text-align:center;
	border-bottom:1px solid #777;
	text-decoration:none;
	color:#333;
	}
	#nav_container4 a:hover{
	color:#000;
	background: url(images/bg5.gif)  0px 0px repeat-x;
	}
	</style>
	<script>
roller.init('nav_container4','v',-66,0,400,20);
</script>
	
		<div style="float: left;margin-top: -40px;"><img src="images/logo.jpg"></img></div>
	 <div id="login">
 <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Student student = (Student)session.getAttribute("student");
	if(student == null){
%> 
        <a href="#" id="link" class="signin">Sign In</a> 
        <form class="drop" action="action/studentAction_login" method="post" >
            <label for="name">Username :</label>
            <input type="text" name="student.studentNum"  class="login"/>
            <label for="password">Password:</label>
            <input type="password" name="student.password" class="login"/>
            <p class="remember"><input type="checkbox" class="checkbox" />Remember Me</p>
            <input type="submit" class="submit" value="Sign In" class="login"/>
            <p><a href="#" class="tooltip">Forgot Password?<span>Click To Reset Your Password</span></a></p>
        </form>
         <%}else{ %>
         <div style="font-style: oblique;" align="right">
            <a href="action/studentAction_inform" style="font-size: larger;font-family: serif;color: red"><%=student.getName() %></a><br>
            <a href="studentAction_logout" style="font-size: larger;font-family: serif;color: blue">退出</a>
          </div>
         <%;}%>
       </div> 
  
	<div style="clear:both;width:100%;"></div>
		<div id="nav_container4">
			<ul>
				<li>				
					<a href="index.jsp">主页</a>
				</li>
				<li>				
					<a href="action/banjiAction_intro">班级简介</a>
				</li>
				<li>				
					<a href="action/studentAction_cards">班级成员</a>
				</li>
				<li>				
					<a href="action/informAction_inform">班级公告</a>
				</li>
				<li>				
					<a href="albumAction_showAlbum">班级相册</a>
				</li>
				<li>				
					<a href="shareAction_getShareForUser">资源共享</a>
				</li>
				<li>				
					<a href="action/messageAction_message">访客留言</a>
				</li>
				<li>				
					<a href="action/studentAction_inform">个人信息</a>
				</li>
			</ul>
		</div>
      <br/><br/>
