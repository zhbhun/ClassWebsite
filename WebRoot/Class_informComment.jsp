<%@ page language="java" import="java.util.*,com.hibernate.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<InformComment> list=(List)request.getAttribute("list");
Inform inform = (Inform)request.getAttribute("inform");
%>
<%@ taglib uri = "/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <head>
    <base href="<%=basePath%>">
    <title>班级公告</title>
    
<script type="text/javascript" src="js/jquery.min.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<SCRIPT language=javascript src="js/jquery.min.js"></SCRIPT>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
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

  $("#comment").click(function(){
      var oEditor = CKEDITOR.instances.editor;
    
      if(oEditor.getData()==""){
        alert("请输入评论内容");
        return false;
      }
      else
       return true;
  });
  
});

</script>
<script type="text/javascript">
function showComment(){
 $("#comment").show();
} 
function closeComment(){
 $("#comment").hide();
} 
</script>
</head>
  
  <body >
  <jsp:include page="navigation.jsp"></jsp:include><br/><br/><br/><br/>
  <div >
    <h2>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;  
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       通知：</h2>
   <table width="800" height="30" align="center" border="20" style="font-size: large; background-color: #B0C4DE;">
  <tr>
    <td colspan="2" align="center" ><s:property value="inform.name"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"  style="font-size: 100%; font-family: serif;"><%=inform.getContent() %></td>
  </tr>
  <tr style="font-size: small;">
    <td width="0" align="right" style="display: none;"><s:property value="inform.id"/></td>
    <td width="322" align="right"><s:property value="inform.administrator.name"/></td>
    <td width="217" align="right"> <s:property value="inform.time"/></td>
  </tr>
</table>


 	<h2>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;  
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	评论：</h2>
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

	
 	<div align="center" style="font-family: sans-serif;font-size: small;">
  <ul>
  	<li>
  <table width="800" height="50" border="10"  style="border-color: #84C1FF; border-bottom-color: gray; background-color: #84C1FF">
  <tr>
    <td width="150" rowspan="2" align="center" style="font-size: large; "><%=list.get(index).getStudent().getName()%></td>
    <td height="30" colspan="2"  style="font-family: sans-serif;font-size: small; background-color:AFEEEE"><%=list.get(index).getContent()%></td>
  </tr>
  <tr style="font-size: small;">
    <td width="200" colspan="2" align="right"><%=list.get(index).getTime()%></td>
  </tr>
</table>
  	</li>
  </ul>
  </div>
  	  <%
index++;
i++;}%>
<div align="center">
	  共<%=a%>个记录，分<%=intPageCount%>页显示，当前页是：第<%=intPage%>页  
<%if(intPage>1){%>
<a href='action/informAction_comment?inform.id=<s:property value="inform.id"/>&&pageCount=<%=intPage-1%>'>上一页</a>
<%}%>
<%if(intPage<intPageCount){%>
<a href="action/informAction_comment?inform.id=<s:property value="inform.id"/>&&pageCount=<%=intPage+1%>">下一页</a>
<%} %>
  <div>
  <input type="button" value="评论" onclick="showComment()"/>
  <input type="button" value="关闭评论" onclick="closeComment()" align="middle"/>
  </div>
  </div>
   <form action="action/informCommentAction_comment" method="post" enctype="multipart/form-data">
   <div id="comment" style="display: none;"align="center">
   <textarea name="informComment.content" id="editor" ></textarea>
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
         <input type="text" name="informComment.inform.id" value="<s:property value="inform.id"/>" style="display: none;"/>
		   <input type="submit" id="comment"  value="发布"/>
           <input type="reset" />
           </div>
       </form>
 
 
  	 </div>	
	 
		
  </body>
</html>
