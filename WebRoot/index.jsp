<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*,com.hibernate.domain.*" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script language=javascript src="js/alixixi.js"></script>

<script type="text/javascript" src="js/jquery.min.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" />



<style type="text/css">
<!--
.STYLE10 {
	font-size: small;
	color: #FFFFFF;
}
.STYLE11 {

}
-->
</style>
<style>
a:link {text-decoration: none;}
a:visited {text-decoration: none;}
a:active {text-decoration: none;}
a:hover {text-decoration: none;}
</style>
<Script Language="Javascript">
bname=navigator.appName;
bversion=parseInt(navigator.appVersion)
var z=0;
var rgb=0;
var message= new Array();
var value=0;
var timer1;
var timer2;
var timer3;
var convert = new Array()
var hexbase= new Array("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F");
//Do not edit anything else in the Script except the lines below
var bgcolor="#FF0000"; //Fade color
var color="#0000FF";  //Starting color
var speed=55
//Do not edit anything else in the Script except the lines above
for (x=0; x<16; x++){
for (y=0; y<16; y++){
convert[value]= hexbase[x] + hexbase[y];
value++;
}
}
redx=color.substring(1,3);
greenx=color.substring(3,5);
bluex=color.substring(5,7);
hred=eval(parseInt(redx,16));
hgreen=eval(parseInt(greenx,16));
hblue=eval(parseInt(bluex,16));
eredx=bgcolor.substring(1,3);
egreenx=bgcolor.substring(3,5);
ebluex=bgcolor.substring(5,7);
ered=eval(parseInt(eredx,16));
egreen=eval(parseInt(egreenx,16));
eblue=eval(parseInt(ebluex,16));
red=ered;
green=egreen;
blue=eblue;
function start(){
timer1=window.setInterval('up()',speed)
}
function up(){
if (red<hred){
if ((red+15)<hred){
red+=15;
redx = convert[red]
}
else{
red=hred
redx = convert[red]
}
}
if (red>hred){
if ((red-15)>hred){
red-=15;
redx = convert[red]
}
else{
red=hred
redx = convert[red]
}
}
if (green<hgreen){
if ((green+15)<hgreen){
green+=15;
greenx = convert[green]
}
else{
green=hgreen
greenx = convert[green]
}
}
if (green>hgreen){
if ((green-15)>hgreen){
green-=15;
greenx = convert[green]
}
else{
green=hgreen
greenx = convert[green]
}
}
if (blue<hblue){
if ((blue+15)<hblue){
blue+=15;
bluex = convert[blue]
}
else{
blue=hblue
bluex = convert[blue]
}
}
if (blue>hblue){
if ((blue-15)>hblue){
blue-=15;
bluex = convert[blue]
}
else{
blue=hblue
bluex = convert[blue]
}
}
rgb = "#"+redx+greenx+bluex;
document.linkColor=rgb;
document.vlinkColor=rgb;
if (z>19){
window.clearInterval(timer1);
timer2=window.setInterval('down()',speed)
}
else
z++
}
function down(){
if (red<ered){
if ((red+15)<ered){
red+=15;
redx = convert[red]
}
else{
red=ered
redx = convert[red]
}
}
if (red>ered){
if ((red-15)>ered){
red-=15;
redx = convert[red]
}
else{
red=ered
redx = convert[red]
}
}
if (green<egreen){
if ((green+15)<egreen){
green+=15;
greenx = convert[green]
}
else{
green=egreen
greenx = convert[green]
}
}
if (green>egreen){
if ((green-15)>egreen){
green-=15;
greenx = convert[green]
}
else{
green=egreen
greenx = convert[green]
}
}
if (blue<eblue){
if ((blue+15)<eblue){
blue+=15;
bluex = convert[blue]
}
else{
blue=eblue
bluex = convert[blue]
}
}
if (blue>eblue){
if ((blue-15)>eblue){
blue-=15;
bluex = convert[blue]
}
else{
blue=eblue
bluex = convert[blue]
}
}
rgb = "#"+redx+greenx+bluex;
document.linkColor=rgb;
document.vlinkColor=rgb;
if (z>38){
z=0;
window.clearInterval(timer2);
timer1=window.setInterval('up()',speed);
}
else
z++
}
start()
// done hiding -->
</Script>
</head>

<body style="text-align:center"  >

<br />
<jsp:include page="navigation.jsp"></jsp:include>

<br />
<br />

<table >
<tr>
<td>
<div align = "left">
<h2>山大要闻</h2>
<ul>
<li><a href = "http://www.view.sdu.edu.cn/new/2011/1201/34239.html">山东大学承办2012届毕业生就业供需见面会</a></li>
<li><a href ="http://www.view.sdu.edu.cn/new/history/">山东大学校史上的今天（12月3日）</a></li>
<li><a href = "http://www.sc.sdu.edu.cn/getNewsDetail.do?newsId=5351" >软件学院2009级本科生赴北京实习活动圆满结束</a></li>
<li><a href = "http://www.sc.sdu.edu.cn/getNewsDetail.do?newsId=5292">软件园校区名师名家论坛活动暨“系统分析与软件设计”创新平台挂牌仪成功举行</a></li>
<li> <a href ="http://www.sc.sdu.edu.cn/getNewsDetail.do?newsId=5302">软件学院成功举行庆祝“建院十周年音乐会”</a></li>
<li> <a href ="http://www.sc.sdu.edu.cn/getNewsDetail.do?newsId=5272">感谢关注与支持我院教育事业发展的广大校友及社会各界”</a></li>
<li> <a href ="http://www.sc.sdu.edu.cn/getNewsDetail.do?newsId=5212">山东省软件工程重点实验室召开学术委员会会议在我校召开</a></li>
<li> <a href ="http://www.sc.sdu.edu.cn/getNewsDetail.do?newsId=5250">第七届全国信息检索学术会议在山东大学举行</a></li>
</ul>
</div>
</td>
<td>
<div align ="right">
<br/>
  <script>FlashInsert('myResize', 'mainflash/kor_main.swf', '980', '300', 'always', 'transparent', '#000000', 'high', 'middle');</script>
</div>
</td>
</tr>
</table>


<br />


<h2><font color = "red">音乐盒</font></h2>
<OBJECT codeBase=http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0 height=30 width=400 classid=clsid:D27CDB6E-AE6D-11cf-96B8-444553540000><PARAM NAME="_cx" VALUE="10583"><PARAM NAME="_cy" VALUE="794"><PARAM NAME="FlashVars" VALUE=""><PARAM NAME="Movie" VALUE="http://www.zhouwenqi.com/mp3.swf?mp3=http://home.anet.net.tw/kelly_311231/jack/stupidmistake.mp3 "><PARAM NAME="Src" VALUE="http://www.zhouwenqi.com/mp3.swf?mp3=http://home.anet.net.tw/kelly_311231/jack/stupidmistake.mp3 "><PARAM NAME="WMode" VALUE="Window"><PARAM NAME="Play" VALUE="0"><PARAM NAME="Loop" VALUE="-1"><PARAM NAME="Quality" VALUE="High"><PARAM NAME="SAlign" VALUE=""><PARAM NAME="Menu" VALUE="-1"><PARAM NAME="Base" VALUE=""><PARAM NAME="AllowScriptAccess" VALUE=""><PARAM NAME="Scale" VALUE="ShowAll"><PARAM NAME="DeviceFont" VALUE="0"><PARAM NAME="EmbedMovie" VALUE="0"><PARAM NAME="BGColor" VALUE=""><PARAM NAME="SWRemote" VALUE=""><PARAM NAME="MovieData" VALUE=""><PARAM NAME="SeamlessTabbing" VALUE="1"><PARAM NAME="Profile" VALUE="0"><PARAM NAME="ProfileAddress" VALUE=""><PARAM NAME="ProfilePort" VALUE="0"><PARAM NAME="AllowNetworking" VALUE="all"><PARAM NAME="AllowFullScreen" VALUE="false">  <embed src="http://www.zhouwenqi.com/mp3.swf?mp3=http://home.anet.net.tw/kelly_311231/jack/stupidmistake.mp3 " quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="400" height="30"></embed> </OBJECT>
<br/>
<hr class="STYLE11"/>
<div align="center"/>
<span class="STYLE10" style="color: black">All Rights Reserved </span>

<div align = "left">
友情链接 
<a href = "http://www.baidu.com">百度&nbsp;&nbsp;&nbsp;&nbsp;</a>
<a href = "http://www.google.com.hk">Google&nbsp;&nbsp;&nbsp;&nbsp;		</a>
<a href =" http://www.sdu.edu.cn">山东大学	&nbsp;&nbsp;&nbsp;&nbsp;	</a>
<a href = "http://www.sc.sdu.edu.cn">山东大学齐鲁软件学院&nbsp;&nbsp;&nbsp;&nbsp;</a>
<a href = "http://bt.neu6.edu.cn/"> 六维空间 &nbsp;&nbsp;&nbsp;&nbsp;   </a>
<a href = "http://www.renren.com">人人网 &nbsp;&nbsp;&nbsp;&nbsp;</a>
<a href = "http://www.csdn.net/"> CSDN&nbsp;&nbsp;&nbsp;&nbsp;</a>
<a href = "http://www.zhaopin.com/">智联招聘 &nbsp;&nbsp;&nbsp;&nbsp;</a>
<a href = "http://www.chinahr.com/index.htm"> 中华英才网&nbsp;&nbsp;&nbsp;&nbsp;</a>
 </div>
</body>
</html>