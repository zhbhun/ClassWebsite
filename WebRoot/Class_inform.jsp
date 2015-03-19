<%@ page language="java" import="java.util.*,com.hibernate.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Inform> list=(List)request.getAttribute("list");
%>
<%@ taglib uri = "/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>班级公告</title>
    
<script type="text/javascript" src="js/jquery.min.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" />

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
<br>
<br>
<table width="100%" height="75%">
  <tr>
    <td width="5%">&nbsp;</td>
    <td valign="top" width="90%" rowspan="2" background="images/inform.gif">
					
		<table height="100%">
			<tr>
				<td width="300px" background="images/inform_left.jpg"></td>
				<td width="50px"></td>
				<td valign="top"  width="700px">
					<div style="font-family: sans-serif;font-size: small;">
					<ul>
<%					
int intPageSize;      //一页显示的记录数
int intPageCount;     //总页数
int intPage;         //待显示的页码
String strPage;
int a=list.size();//获取记录总数
int index=0;            //指针
int i=0;
intPageSize=15; //设置一页显示的记录数
strPage=request.getParameter("pageCount");//取得待显示的页码
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
while(i<intPageSize&&index!=a){
%>

<li>
<a href="action/informAction_comment?inform.id=<%=list.get(index).getId()%>" ><%=list.get(index).getName()%>
</a>
【<%=list.get(index).getTime()%>】</li>
  <%
index++;
i++;}%>
</ul>	
<div align="center">
共<%=a%>个记录，分<%=intPageCount%>页显示，当前页是：第<%=intPage%>页  
<%if(intPage>1){%>
<a href='action/informAction_inform?pageCount=<%=intPage-1%>'>上一页</a>
<%}
for(int j=1;j<=intPageCount;j++)
{
out.print("  <a href='action/informAction_inform?pageCount="+j+"'>"+j+"</a>");
}%>
<%if(intPage<intPageCount){%>
<a href='action/informAction_inform?pageCount=<%=intPage+1%>'>下一页</a>
<%} %>
</div>
	</div>
				</td>
			</tr>
		</table>
	</td>
    <td width="5%">&nbsp;</td>
  </tr>
</table>



<br>
  </body>
</html>
