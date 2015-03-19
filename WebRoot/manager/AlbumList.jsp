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
    
     <title>相册管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    
	<link href="css/base.css" rel="stylesheet" type="text/css" />
	<link href="css/admin.css" rel="stylesheet" type="text/css" />
	<link href="css/AlbumManage.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="jqModal/css/jqModal_gray.css">
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/header.js"></script>
	<script type="text/javascript" src="jqModal/jqModal.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		 //alert("123");
	   $('#addAlbumDiv').jqm({
	      trigger:'#addAlbum',
	      overlay: 0,
	      onShow: function(h) {
	        /* callback executed when a trigger click. Show notice */
	        h.w.css('opacity',0.92).slideDown(); 
	        },
	      onHide: function(h) {
	        /* callback executed on window hide. Hide notice, overlay. */
	        h.w.slideUp("slow",function() { if(h.o) h.o.remove(); }); } 
	      });
	   $('#changeAlbumDiv').jqm({
		      overlay: 0,
		      onShow: function(h) {
		        /* callback executed when a trigger click. Show notice */
		        h.w.css('opacity',0.92).slideDown(); 
		        },
		      onHide: function(h) {
		        /* callback executed on window hide. Hide notice, overlay. */
		        h.w.slideUp("slow",function() { if(h.o) h.o.remove(); }); } 
		      });
	   $("a.editAlbum").click(function(){
		  // alert("hello"+$(this).attr("name"));
		   var url = "/class/json/albumAction_accessChangeAlbum";
		   var params = {
				   albumId:$(this).attr("name")
	       };
		   $.ajax({
		    	url:url,
		    	type:"POST",
		    	data:params,
		       	dataType:"json",
		       	success:function(data){
			    // alert("hello");
			     $("#changeAlbumId").attr("value",data.id);
			     $("#changeAlbumName").attr("value",data.name);
			     $("#changeAlbumIntroduction").html(data.introduction);
			     $("#changeAlbumDiv").jqmShow();
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
  <!--    <div style="height: 100%; width: 1101px; position: fixed; left: 0px; top: 0px; z-index: 2999; opacity: 0.5;margin-left: 135px;margin-top: 59px; " class="jqmOverlay"></div> -->
     <div id="addAlbumDiv" class="jqmNotice" >
      <div class="addAlbumDivTitle">
         <h1 align='center'>添加相册</h1>
      </div>
      <br/>
      <br/>
      <div class="addAlbumDivContent">
        <form action="albumAction_addAlbum" method="post" enctype="multipart/form-data">
           <ul style='list-style: none;margin-left: 10px;'>
            
            <li><label style='display: block;'>相册名:</label><input name='album.name' type="text" size="40" style='width: 150px;'/></li>
            <li><label style='display: block;'>介绍:</label><textarea name='album.introduction'  style='resize: none;width: 450px;height: 130px;'></textarea></li>
            
            <li style='margin-top: 20;'>
              <input type="submit" class="formButton" value='创建'/>
             <input type="reset" class="formButton" />
            </li>
            </ul>
            
        </form>
      </div>
      
      <img src="jqModal/css/images/close_icon.png" alt="close" class="jqmClose" />
      <img src="jqModal/css/images/resize.gif" alt="resize" class="jqResize" />
    </div>
     <div id="changeAlbumDiv" class="jqmNotice" >
      <div class="changeAlbumDivTitle">
         <h1 align='center'>修改相册</h1>
      </div>
      <br/>
      <br/>
      <div class="changeAlbumDivContent">
        <form action="albumAction_changeAlbum" method="post" enctype="multipart/form-data">
           <ul style='list-style: none;margin-left: 10px;'>
           <li>
            <input type="hidden" name="album.id" id="changeAlbumId" />
           <label style='display: block;'>相册名:</label>
           <input name='album.name' id="changeAlbumName" type="text" size="40" style='width: 150px;'/>
           </li>
            <li>
            <label style='display: block;'>介绍:</label>
            <textarea name='album.introduction'  id="changeAlbumIntroduction" style='resize: none;width: 450px;height: 130px;'></textarea>
            </li>
            
            <li style='margin-top: 20;'>
              <input type="submit" class="formButton" value='修改'/>
             <input type="reset" class="formButton" />
            </li>
            </ul>
            
        </form>
      </div>
      
      <img src="jqModal/css/images/close_icon.png" alt="close" class="jqmClose" />
      <img src="jqModal/css/images/resize.gif" alt="resize" class="jqResize" />
    </div>
    <div id="top" class="bar"><span>相册列表&nbsp;总记录数：<s:property value="#request.page.totalSize"/>(共<s:property value="#request.page.totalPage"/>页)</span></div>
     <div id="operate" style >
         <input type="button" class="formButton"  id="addAlbum" value="创建相册" style="float:left;margin-top: 5px;margin-left: 7px;">  
         <form action="albumAction_getAlbumForAdmin" method="post" enctype="multipart/form-data" style='padding-top: 5px;'>
          <input type="hidden" name="page.currentPage" value="1" />
         <span>&nbsp;&nbsp;&nbsp;&nbsp;查找：&nbsp;相册名称:</span>
          <input type="text" name="page.name" value="<s:property value="#request.page.name"/>"/>
         <input type="submit" class="formButton" value="搜索"/>
         </form>
      </div>
       <div id="middle">
          <table id="listTable" class="listTable" style="width: 100%;clear: both;">
			<tr>
			        <th class="c1">
						<a href="#" class="sort" >相册编号</a>
					</th>
					<th class="c2">
						<a href="#" class="sort"  >相册名称</a>
					</th>
					
					<th class="c3">
						<a href="#" class="sort"  >创建时间</a>
					</th>
					<th class="c4">
						<span style="font-size: 13px;font-family:Microsoft YaHei;color: #666;" >操作</span>
					</th>
			</tr>
			
			<s:iterator  value="albumList" var ="item" >
	          	
			<tr class="checked">
			 <td class="c1">&nbsp;&nbsp;<s:property value="#item.id"/></td>
			 <td class="c2">&nbsp;&nbsp;<s:property value="#item.name"/></td>
			 <td class="c3">&nbsp;&nbsp;20<s:property value="#item.time"/></td>
			 <td class="c4">
					&nbsp;<a style="cursor: pointer;" class="editAlbum" name="<s:property value="#item.id"/>"  title="编辑" style="margin-left: 10px;">[编辑]</a>
					<a href="photoAction_getPhotoByAlbumIdForAdmin?album.id=<s:property value="#item.id"/>" title="浏览">[进入相册]</a>
					<a href="albumAction_deleteAlbum?albumId=<s:property value="#item.id"/>" title="删除">[删除]</a>
					<a href="<s:property value="#item.id"/>" target="_blank" title="浏览">[浏览]</a>
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
          <li ><a href="albumAction_changeAlbumpage?page.currentPage=1">首页</a></li>
           <s:if test="#request.page.hasPrePage">
               <li ><a  href="albumAction_changeAlbumpage?page.currentPage=<s:property value="#request.page.currentPage-1"/>"><s:property value="#request.page.currentPage-1"/></a></li>
           </s:if>
           <li style="border: 1px solid #003F7E;"><a href="albumAction_changeAlbumpage?page.currentPage=<s:property value="#request.page.currentPage"/>"><s:property value="#request.page.currentPage"/></a></li>
          <s:if test="#request.page.hasNextPage">
               <li ><a  href="albumAction_changeAlbumpage?page.currentPage=<s:property value="#request.page.currentPage+1"/>"><s:property value="#request.page.currentPage+1"/></a></li>
          </s:if>
          <li ><a href="albumAction_changeAlbumpage?page.currentPage=<s:property value="#request.page.totalPage"/>">末页</a></li>
         </ul>
        		
       </div>			
     </div>
   </div>
   
   
  
  </body>
</html>
