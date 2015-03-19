<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
     <title>学生管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    
	<link href="css/base.css" rel="stylesheet" type="text/css" />
	<link href="css/admin.css" rel="stylesheet" type="text/css" />
	<link href="css/StudentManager.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="jqModal/css/jqModal_gray.css">
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/header.js"></script>
	<script type="text/javascript" src="jqModal/jqModal.js"></script>
	 <script type="text/javascript">
	$(document).ready(function(){
            	  $('#addDiv').jqm({
            	      trigger:'#addStudent',
            	      overlay: 0,
            	      onShow: function(h) {
            	        /* callback executed when a trigger click. Show notice */
            	        h.w.css('opacity',0.92).slideDown(); 
            	        },
            	      onHide: function(h) {
            	        /* callback executed on window hide. Hide notice, overlay. */
            	        h.w.slideUp("slow",function() { if(h.o) h.o.remove(); }); } 
            	      });
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
           			     $("#studentQQ").html(data.studentNum);
           			     $("#studentEmail").html(data.mail);
           			     $("#studentAddress").html(data.address);
           			     $("#studentJob").html(data.job);
           			     $("#detailDiv").jqmShow();
           		       }
           		       });
              	 });
            	  $("input.changeStudent").click(function(){
                       //  alert("123");
                         var url = "/class/json/StudentAction_changeJob";
                 		 var job_id = "stuJob_"+$(this).attr("name");
                 		// alert($("#"+job_id).val());
                 		var params = {
                  				 studentId:$(this).attr("name"),
                  				 jobId:$("#"+job_id).val()
                  	       };
                  		   $.ajax({
                  		    	url:url,
                  		    	type:"POST",
                  		    	data:params,
                  		       	dataType:"json",
                  		       	success:function(data){
                  			     alert(data.state);
                  			    
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
         <h1 align='center'>添加学生</h1>
      </div>
      <div  class="addDivContent" style="text-align: center">
      <form action="studentAction_addStudent" method="post" enctype="multipart/form-data">
         <table style="margin-left: 90px;margin-top: 30px;">
            <tr class="newCourseTr">
             <td class="left">学生学号：</td>
             <td><input style="width: 200px;" type="text" name="student.studentNum"/></td>
            
           </tr>
           <tr class="newCourseTr">
             <td class="left">学生姓名：</td>
             <td><input style="width: 200px;" type="text" name="student.name"/></td>
            
           </tr>
           <tr class="newCourseTr">
             <td class="left">学生性别：</td>
             <td>
                <select name="student.sex">
                   <option value="男">男</option>
                   <option value="女">女</option>
                </select>
             </td>
           </tr>
           <tr class="newCourseTr">
             <td class="left">学生职务：</td>
             <td>
                <select name="student.job.id">
                 <s:iterator  value="jobList" var ="item" >
                   <option value ="<s:property value="#item.id"/>"><s:property value="#item.name"/></option>
                
	              </s:iterator>		
                </select>
             </td>
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
    
    
    
    <div id="detailDiv" class="jqmNotice"  style="height: 500px">
      <div class="detailDivTitle">
         <h1 align='center' id="studenNameAsTitle">学生信息</h1>
      </div>
      <div  class="detailDivContent" style="text-align: center">
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
      <div id="top" class="bar"><span>学生列表&nbsp;总记录数：<s:property value="#request.page.totalSize"/>(共<s:property value="#request.page.totalPage"/>页)</span></div>
     <div id="operate" >
         <input type="button" class="formButton" id="addStudent" value="添加学生 " style="float:left;">
         <form action="studentAction_getStudentForAdmin" method="post" enctype="multipart/form-data">
          <input type="hidden" name="page.currentPage" value="1" />
         
          <span>&nbsp;&nbsp;&nbsp;&nbsp;查找：&nbsp;学生职务</span>
            
           <select name="page.jobId">
             <option value ="0" >所有学生</option>
             <s:iterator  value="jobList" var ="item" >
                <s:if test="#item.id==#request.page.jobId">
                  <option selected="selected" value ="<s:property value="#item.id"/>"><s:property value="#item.name"/></option>
                </s:if>
                <s:else>
                  <option value ="<s:property value="#item.id"/>"><s:property value="#item.name"/></option>
                </s:else>
	            
            </s:iterator>		
          </select>
          <span>&nbsp;学生姓名:</span>
          <input type="text" name="page.name" value="<s:property value="#request.page.name"/>"/>
          <input type="submit" class="formButton" value="搜索"/>
         </form>
      </div>
      <div id="middle">
      
			<s:iterator  value="studentList" var ="item" >
	          <div class="student">
	            <p>学号:<s:property value="#item.studentNum"/>&nbsp;&nbsp;姓名：<s:property value="#item.name"/>&nbsp;&nbsp;性别：<s:property value="#item.sex"/></p>  
	            <br/> 
	             <div>
	             <form>
	                                    职务：
	              <select id="stuJob_<s:property value="#item.id"/>">
	               <s:iterator  value="jobList" var ="item1" >
	                <s:if test="#item.job.id==#item1.id">
                       <option selected="selected" value ="<s:property value="#item1.id"/>"><s:property value="#item1.name"/></option>
                    </s:if>
                     <s:else>
                     <option value ="<s:property value="#item1.id"/>"><s:property value="#item1.name"/></option>
                     </s:else>
                   </s:iterator>
	              </select>
	            <input type="button" value="修改" class="changeStudent" name="<s:property value="#item.id"/>"/>
	            </form>
	            <br/>
	            <a class="detailStudent"  name="<s:property value="#item.id"/>" title="查 看" style="cursor: pointer;">更多个人信息</a>
				<a style="float:right" href="studentAction_deleteStudent?studentId=<s:property value="#item.id"/>">删除</a>
	         </div>
	          </div>
            </s:iterator>		
		   
     </div>
     <div style="height: 37;"></div>
     <div id="bottom" >
       <div style="float:right;margin-top: 5px;">
         <ul class="pages">
          <li>总页数：<s:property value="#request.page.totalPage"/>&nbsp;</li>
          <li ><a href="studentAction_changeStudentPage?page.currentPage=1">首页</a></li>
           <s:if test="#request.page.hasPrePage">
               <li ><a  href="studentAction_changeStudentPage?page.currentPage=<s:property value="#request.page.currentPage-1"/>"><s:property value="#request.page.currentPage-1"/></a></li>
           </s:if>
           <li style="border: 1px solid #003F7E;"><a href="studentAction_changeStudentPage?page.currentPage=<s:property value="#request.page.currentPage"/>"><s:property value="#request.page.currentPage"/></a></li>
          <s:if test="#request.page.hasNextPage">
               <li ><a  href="studentAction_changeStudentPage?page.currentPage=<s:property value="#request.page.currentPage+1"/>"><s:property value="#request.page.currentPage+1"/></a></li>
          </s:if>
          <li ><a href="studentAction_changeStudentPage?page.currentPage=<s:property value="#request.page.totalPage"/>">末页</a></li>
         </ul>
        		
       </div>			
     </div>
   </div>
  </body>
</html>
