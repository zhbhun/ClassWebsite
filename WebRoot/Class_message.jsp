<%@ page language="java" import="java.util.*,com.hibernate.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Message> list=(List)request.getAttribute("messageList");
%>
<%@ taglib uri = "/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <head>
    <base href="<%=basePath%>">
    <title>班级留言</title>
    
<script type="text/javascript" src="js/jquery.min.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<SCRIPT language=javascript src="js/jquery.min.js"></SCRIPT>
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

  $("#message").click(function(){
 
     var oEditor = CKEDITOR.instances.editor;
     
      if(oEditor.getData()==""){
        alert("请输入留言内容");
        return false;
      }
      else
       return true;
  });
  
});
</script>
<script type="text/javascript">
function showMsg(){
 $("#msg").show();
} 
function closeMsg(){
 $("#msg").hide();
} 
</script>
</head>
  
  <body style="text-align:center;"  >

<jsp:include page="navigation.jsp"></jsp:include><br/><br/><br/><br/>
<div align="center">
			<div style="font-family: serif; color: red; font-size: 200%">班级寄语</div> <br>
			<div style="color: #cccc00; font-size: 150%">
				 曾经青涩的感受，曾经单纯的表达，<br/>
  				曾经真诚的体会，曾经无法割舍的留恋……<br/>
  				正在经历的幸福，正在憧憬的美好，<br/>
				  正在成长的兴奋与惶恐……走过的年轻，<br/>
				  渐渐地长大，在你我共同的记忆里，<br/>
  				有那些在校园中传唱着的动听的歌。<br/><br/><br/>
			</div>
<%
int intPageSize;      //一页显示的记录数
int intPageCount;     //总页数
int intPage;         //待显示的页码
String strPage;
int a=list.size();//获取记录总数
int index=0;            //指针
int i=0;
intPageSize=6; //设置一页显示的记录数
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

	
			<div  style="font-family: sans-serif;font-size: small;">
		<ul>
		<li>
			 <table width="60%" height="15%" border="10" align="center" style="border-color: #84C1FF; border-bottom-color: gray; background-color: #84C1FF">
  				<tr >
  				  <td width="10%" rowspan="2" style="font-size: large;" align="center">
  				  <%=list.get(index).getPerson()%>
  				  <br/><p style="font-size: small;"> <%=list.get(index).getTelephone()%></p></td>
  				  <td width="20%" height="3%"> <%=intPageCount*index+1%>楼</td>
   			   	  
   				  <td width="25%"  align="right"> <%=list.get(index).getTime()%></td>
 			    </tr>
  				<tr>
   					 <td colspan="3" style="font-family: sans-serif;font-size: small; background-color:AFEEEE"> <%=list.get(index).getContent()%></td>
  				</tr>
			  </table>
	
		</li>
	</ul></div>
	
	  <%
index++;
i++;}%>
	
	
  
  共<%=a%>个记录，分<%=intPageCount%>页显示，当前页是：第<%=intPage%>页  
<%if(intPage>1){%>
<a href='action/messageAction_message?pageCount=<%=intPage-1%>'>上一页</a>
<%}
if(intPage<intPageCount){%>
<a href='action/messageAction_message?pageCount=<%=intPage+1%>'>下一页</a>
<%} %>
<div align="center">
<input type="button" value="访客留言" onclick="showMsg()"/>
  <input type="button" value="关闭留言" onclick="closeMsg()"/>
</div>
  <div id="msg" style="display: none;">
  <form action="action/messageAction_liuyan" method="post" enctype="multipart/form-data">
   <textarea  name="message.content" id="editor" ></textarea>
        <script type="text/javascript"> 
           CKEDITOR.replace( 'editor', 
           { 
		     toolbar : 'Basic',  
             skin : 'kama', 
             width: '60%',
             height: '50%',
             resize_enabled: 'false',
             language : 'zh-cn' 
           }); 
          </script>
         
          留言人：	<input type="text" name="message.person"/>
        联系电话：	<input type="text" name="message.telephone"/> 
		   <input type="submit"  id="message" value="发布"/>
           <input type="reset" />
       </form>
       </div>
     
       </div>
  </body>
</html>
