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
    
     <title>XXX相册管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    
	<link href="css/base.css" rel="stylesheet" type="text/css" />
	<link href="css/admin.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="jqModal/css/jqModal_gray.css">
	<link href="css/PhotoList.css" rel="stylesheet" type="text/css" />
	 <link href="css/uploadify.css" rel="stylesheet" type="text/css" />
	 <link rel="stylesheet" type="text/css" href="jquery.fancybox/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/header.js"></script>
	<script type="text/javascript" src="jqModal/jqModal.js"></script>
	 <script type="text/javascript" src="js/swfobject.js"></script>
        <script type="text/javascript" src="js/jquery.uploadify.v2.1.4.js"></script>
        <script type="text/javascript" src="jquery.fancybox/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
    <script type="text/javascript" src="jquery.fancybox/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
    
	<script type="text/javascript">
	$(document).ready(function() {
		 $(".fancybox").fancybox({
				'width'				: '100%',
				'height'			: '100%',
				'autoScale'			: false,
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'type'				: 'iframe'
			});
			
	   $('#addDiv').jqm({
	      trigger:'#addPhoto',
	      overlay: 0,
	      onShow: function(h) {
	        /* callback executed when a trigger click. Show notice */
	        h.w.css('opacity',0.92).slideDown(); 
	        },
	      onHide: function(h) {
	        /* callback executed on window hide. Hide notice, overlay. */
	        h.w.slideUp("slow",function() { if(h.o) h.o.remove(); }); } 
	      });

	   $("#uploadify").uploadify({
           'uploader'       : 'uploadify.swf',
          'script'         : 'photoAction_uploadPhoto?album.id=<s:property value="#request.album.id"/>',
          'cancelImg'      : 'images/cancel.png',
           'fileDataName'   : 'photo',
          'queueID'        : 'fileQueue',
          'auto'           : false,
          'multi'          : true,
          'simUploadLimit' : 2,
          'buttonText'     : 'BROWSE',
          'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
           'fileDesc'       : '支持格式:jpg/gif/jpeg/png/bmp.',
          'onAllComplete' : function(event,data) {
              alert(data.filesUploaded+"张相片已上传,"+data.errors+"张上传失败");
              window.location.replace("photoAction_getPhotoByAlbumIdForAdmin?album.id=<s:property value="#request.album.id"/>");
            }
      });
	  
	});
	 function showResult(){
         $("#result").html("");
       }
       function uploadFile(){
        jQuery('#uploadify').uploadifyUpload();
       }
       function clearFile(){
	       
           jQuery('#uploadify').uploadifyClearQueue();
       }
		</script>
  </head>
  
  <body >
  <div class="header" style="width:1300px;height:60px">
   <%@ include file="Header.html" %>
   </div>
  <div id="main">
  <div id="addDiv" class="jqmNotice" >
      <div class="addDivTitle">
         <h1 align='center'>上传相片</h1>
      </div>
      <div class="addDivContent" style="text-align: center">
          <div id="result" align="center"></div><!--显示结果-->
        <input type="file" name="photo" id="uploadify" />
        <p align="center">
        <a href="javascript:uploadFile()">开始上传</a>&nbsp;
        <a href="javascript:clearFile()">取消所有上传</a>
        </p>
         <div id="fileQueue" align="center" style="overflow: auto;height:400px"></div>
       
      </div>
      
      <img src="jqModal/css/images/close_icon.png" alt="close" class="jqmClose" />
      <img src="jqModal/css/images/resize.gif" alt="resize" class="jqResize" />
    </div>
    <div id="top" class="bar"><span><s:property value="#request.album.name"/>--相册列表&nbsp;总记录数：<s:property value="#request.page.totalSize"/>(共<s:property value="#request.page.totalPage"/>页)</span></div>
     <div id="operate" style >
         <input type="button" class="formButton"  id="addPhoto" value="上传相片" style="float:left;margin-top: 5px;margin-left: 7px;">  
         <form action="photoAction_getPhotoByAlbumIdForAdmin" method="post" enctype="multipart/form-data" style='padding-top: 5px;'>
          <input type="hidden" name="page.currentPage" value="1" />
          <input type="hidden" name="album.id" value="<s:property value="#request.album.id"/>" />
         <span>&nbsp;&nbsp;&nbsp;&nbsp;查找：&nbsp;相片名称:</span>
          <input type="text" name="page.name" value="<s:property value="#request.page.name"/>"/>
         <input type="submit" class="formButton" value="搜索"/>
         </form>
      </div>
       <div id="middle">
          <table id="listTable" class="listTable" style="width: 100%;clear: both;">
			<tr>
			        <th class="c1">
						<a href="#" class="sort" >相片编号</a>
					</th>
					<th class="c2">
						<a href="#" class="sort"  >相片名称</a>
					</th>
					
					<th class="c3">
						<a href="#" class="sort"  >创建时间</a>
					</th>
					<th class="c4">
						<span style="font-size: 13px;font-family:Microsoft YaHei;color: #666;" >操作</span>
					</th>
			</tr>
			
			<s:iterator  value="photoList" var ="item" >
	          	
			<tr class="checked">
			 <td class="c1">&nbsp;&nbsp;<s:property value="#item.id"/></td>
			 <td class="c2">&nbsp;&nbsp;<s:property value="#item.name"/></td>
			 <td class="c3">&nbsp;&nbsp;20<s:property value="#item.time"/></td>
			 <td class="c4">
					<a class="fancybox" href="photoAction_getPhotoByIdForAdmin?photoId=<s:property value="#item.id"/>"  title="<s:property value="#item.name"/>">[查看相片]</a>
					<a href="photoAction_deletePhoto?photoId=<s:property value="#item.id"/>" title="删除">[删除]</a>
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
          <li ><a href="photoAction_changePhotoPage?page.currentPage=1">首页</a></li>
           <s:if test="#request.page.hasPrePage">
               <li ><a  href="photoAction_changePhotoPage?page.currentPage=<s:property value="#request.page.currentPage-1"/>"><s:property value="#request.page.currentPage-1"/></a></li>
           </s:if>
           <li style="border: 1px solid #003F7E;"><a href="photoAction_changePhotoPage?page.currentPage=<s:property value="#request.page.currentPage"/>"><s:property value="#request.page.currentPage"/></a></li>
          <s:if test="#request.page.hasNextPage">
               <li ><a  href="photoAction_changePhotoPage?page.currentPage=<s:property value="#request.page.currentPage+1"/>"><s:property value="#request.page.currentPage+1"/></a></li>
          </s:if>
          <li ><a href="photoAction_changePhotoPage?page.currentPage=<s:property value="#request.page.totalPage"/>">末页</a></li>
         </ul>
        		
       </div>			
     </div>
   </div>
  </body>
</html>
