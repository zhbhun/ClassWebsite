<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
     <title>友情链接管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    
	<link href="css/base.css" rel="stylesheet" type="text/css" />
	<link href="css/admin.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="jqModal/css/jqModal_gray.css">
	<link href="css/LinkManager.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/header.js"></script>
	<script type="text/javascript" src="jqModal/jqModal.js"></script>
	 <script type="text/javascript">
              $(document).ready(function(){
            	  $('#addDiv').jqm({
            	      trigger:'#addLink',
            	      overlay: 0,
            	      onShow: function(h) {
            	        /* callback executed when a trigger click. Show notice */
            	        h.w.css('opacity',0.92).slideDown(); 
            	        },
            	      onHide: function(h) {
            	        /* callback executed on window hide. Hide notice, overlay. */
            	        h.w.slideUp("slow",function() { if(h.o) h.o.remove(); }); } 
            	      });
        	     // alert("123");
            	  $('#changeDiv').jqm({
            	      overlay: 0,
            	      onShow: function(h) {
            	        /* callback executed when a trigger click. Show notice */
            	        h.w.css('opacity',0.92).slideDown(); 
            	        },
            	      onHide: function(h) {
            	        /* callback executed on window hide. Hide notice, overlay. */
            	        h.w.slideUp("slow",function() { if(h.o) h.o.remove(); }); } 
            	      });
            	  $("a.editCourse").click(function(){
            		 //  alert("hello"+$(this).attr("name"));
            		   var url = "/class/json/linkAction_accessChangeLink";
            		   var params = {
            				   linkId:$(this).attr("name")
            	       };
            		   $.ajax({
            		    	url:url,
            		    	type:"POST",
            		    	data:params,
            		       	dataType:"json",
            		       	success:function(data){
            			   //  alert("hello");
            			     $("#linkId").attr("value",data.id);
            			     $("#linkName").attr("value",data.name);
            			     $("#linkAddress").html(data.address);
            			     $("#changeDiv").jqmShow();
            		       }
            		       });
            	 });
            	 
            	 
              });
    </script>
  </head>
  
  <body >
  <div class="header" style="width:1300px;height:60px">
   <%@ include file="Header.html" %>
   </div>
   <div id="main">
   
  <div id="addDiv" class="jqmNotice" >
      <div class="addDivTitle">
         <h1 align='center'>添加链接</h1>
      </div>
      <div  class="addDivContent" style="text-align: center">
      <form action="linkAction_addLink" method="post" enctype="multipart/form-data">
         <table style="margin-left: 50px;margin-top: 100px;">
           <tr class="newCourseTr">
             <td class="left">链接名：</td>
             <td><input style="width: 300px;" type="text" name="link.name"/></td>
            
           </tr>
           <tr class="newCourseTr">
             <td class="left">链接地址：</td>
             <td><textarea style="resize: none" name="link.address" rows=5px" cols="40px"></textarea></td>
           </tr>
         </table>
         <br/>
         <br/>
         <input type="submit" class="formButton" value="提交"/>
         <input type="reset" class="formButton"/>
         </form>
      </div>
      
      <img src="jqModal/css/images/close_icon.png" alt="close" class="jqmClose" />
      <img src="jqModal/css/images/resize.gif" alt="resize" class="jqResize" />
    </div>
     <div id="changeDiv" class="jqmNotice" >
      <div class="changeDivTitle">
         <h1 align='center'>修改链接信息</h1>
      </div>
      <div  class="changeDivContent" style="text-align: center">
      <form action="linkAction_changeLink" method="post" enctype="multipart/form-data">
          <input type="hidden" name="link.id" id="linkId"/>
         <table style="margin-left: 50px;margin-top: 100px;">
           <tr class="newCourseTr">
             <td class="left">链接名：</td>
             <td><input style="width: 300px;" type="text" name="link.name" id="linkName"/></td>
            
           </tr>
           <tr class="newCourseTr">
             <td class="left">链接地址：</td>
             <td><textarea style="resize: none" name="link.address" id="linkAddress" rows=5px" cols="40px"></textarea></td>
           </tr>
         </table>
         <br/>
         <br/>
         <input type="submit" class="formButton" id="changeButton" value="修改"/>
         <input type="reset" class="formButton"/>
         </form>
      </div>
      
      <img src="jqModal/css/images/close_icon.png" alt="close" class="jqmClose" />
      <img src="jqModal/css/images/resize.gif" alt="resize" class="jqResize" />
    </div>
     <div id="top" class="bar"><span>友情链接&nbsp;总记录数：<s:property value="#request.page.totalSize"/>((共<s:property value="#request.page.totalPage"/>页)</span></div>
     <div id="operate" style="height: 25px">
         <a   class="formButton"  id="addLink" style="float:left;">添加链接</a>
       <!--  <form action="" method="post">
         <span>&nbsp;&nbsp;&nbsp;&nbsp;查找：&nbsp;课程名称</span>
         <input type="text" name="page.name"/>
         <input type="submit" class="formButton" value="搜索"/>
         </form>  -->
      </div>
     <div id="middle">
       <table id="listTable" class="listTable" style="width: 100%;clear: both;">
			<tr>
			        <th class="c1">
						<a href="#" class="sort" >链接编号</a>
					</th>
					<th class="c2">
						<a href="#" class="sort"  >链接名称</a>
					</th>
					<th class="c3">
						<a href="#" class="sort"  >链接地址</a>
					</th>
					<th class="c4">
						<span style="font-size: 13px;font-family:Microsoft YaHei;color: #666;" >操作</span>
					</th>
			</tr>
			<s:iterator  value="linklist" var ="item" >
	          	
			<tr class="checked">
			 <td class="c1">&nbsp;&nbsp;<s:property value="#item.id"/></td>
			 <td class="c2">&nbsp;&nbsp;<s:property value="#item.name"/></td>
			 <td class="c3">&nbsp;&nbsp;地址:<a target="_blank" href="<s:property value="#item.address"/>"><s:property value="#item.name"/></a></td>
			 <td class="c4">
					<a class="editCourse"  name="<s:property value="#item.id"/>" title="编辑" style="margin-left: 10px;cursor: pointer;">[编辑]</a>
					<a href="linkAction_deleteLink?linkId=<s:property value="#item.id"/>" title="删除">[删除]</a>
			 </td>
			</tr>	
            </s:iterator>		
				
		</table>
     
     </div>
     <div style="height: 37;"></div>
     <div id="bottom" >
       <div style="float:right;margin-top: 5px;">
         <ul class="pages">
          <li>总页数：<s:property value="#request.page.totalPage"/>&nbsp;</li>
          <li ><a href="linkAction_changeLinkPage?page.currentPage=1">首页</a></li>
           <s:if test="#request.page.hasPrePage">
               <li ><a  href="linkAction_changeLinkPage?page.currentPage=<s:property value="#request.page.currentPage-1"/>"><s:property value="#request.page.currentPage-1"/></a></li>
           </s:if>
           <li style="border: 1px solid #003F7E;"><a href="linkAction_changeLinkPage?page.currentPage=<s:property value="#request.page.currentPage"/>"><s:property value="#request.page.currentPage"/></a></li>
          <s:if test="#request.page.hasNextPage">
               <li ><a  href="linkAction_changeLinkPage?page.currentPage=<s:property value="#request.page.currentPage+1"/>"><s:property value="#request.page.currentPage+1"/></a></li>
          </s:if>
          <li ><a href="linkAction_changeLinkPage?page.currentPage=<s:property value="#request.page.totalPage"/>">末页</a></li>
         </ul>
        		
       </div>			
     </div>
   </div>
  </body>
</html>
