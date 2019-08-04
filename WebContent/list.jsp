<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script type="text/javascript">
    	function delUser(id){
    		var flag = confirm("确认删除？");
    		if(flag){
    			location.href = "${pageContext.request.contextPath}/DelServlet?id="+id;
    		}
    		
    	}
    	
    	window.onload = function(){
    		//获取删除选中按钮   绑定单击事件 提交form表单
    		document.getElementById("delSelected").onclick = function(){
    			if(confirm("确认删除？")){
    				$("form").submit();
    			}
    		}
    	}
      
      
    </script>
    
</head>
<body>
	<%-- ${list } --%>

<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    
    <div style="float: left; margin: 5px">
         <a class="btn btn-primary" href="add.jsp">添加联系人</a>
         <a class="btn btn-primary" href="javascript:void(0)" id="delSelected">删除选中</a>
    </div>
	<form action="${pageContext.request.contextPath }/DelSelectServlet" method="get">   
	    <table border="1" class="table table-bordered table-hover">
	        <tr class="success">
	        	<th><input type="checkbox" id="firstCb"></th> 
	            <th>编号</th>
	            <th>姓名</th>
	            <th>性别</th>
	            <th>年龄</th>
	            <th>籍贯</th>
	            <th>QQ</th>
	            <th>邮箱</th>
	            <th>操作</th>
	        </tr>
	     
	       <c:forEach items="${pb.list }" var="user" varStatus="vs" >
		        <tr>
		        	<td><input type="checkbox" name="uid" value="${user.id }"></td>
		        	
		            <td>${vs.count }</td>
		            <td>${user.name }</td>
		            <td>${user.gender }</td>
		            <td>${user.age }</td>
		            <td>${user.address }</td>
		            <td>${user.qq }</td>
		            <td>${user.email }</td>
		            
		            <td><!-- 根据id查询要修改的记录,并回显到页面  update.jsp -->
		            	<a class="btn btn-default btn-sm" href="${pageContext.request.contextPath }/FindUserServlet?id=${user.id}">修改</a>&nbsp;
		            	<a class="btn btn-default btn-sm" href="javascript:delUser('${user.id }')">删除</a>
		            	<!--  <a href="javascript:refreshCode()"> -->
		            </td>
		        </tr>
	        </c:forEach>
	    </table>
    </form> 
    
    <div>
    	<nav aria-label="Page navigation">
		  <ul class="pagination">
		 	 <c:if test="${pb.currentPage == 1 }">
		    	<li class="disable">
		     </c:if>
		    
		    <c:if test="${pb.currentPage != 1 }">
		    	<li >
		    </c:if>
		      <a href="${pageContext.request.contextPath }/FindUserByPageServlet?currentPage=${pb.currentPage-1}&rows=5" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    
		    <c:forEach begin="1" end="${pb.totalPage }" var="i">
			    <c:if test="${pb.currentPage == i }">
			   		 <li class="active"><a href="${pageContext.request.contextPath }/FindUserByPageServlet?currentPage=${i}&rows=5">${i }</a></li>
			    </c:if>
			    
			     <c:if test="${pb.currentPage != i }">
			   		 <li><a href="${pageContext.request.contextPath }/FindUserByPageServlet?currentPage=${i}&rows=5">${i }</a></li>
			    </c:if>
		    </c:forEach>
		    
		   <c:if test="${pb.currentPage != pb.totalPage  }">
		   	 <li>
		      <a href="${pageContext.request.contextPath }/FindUserByPageServlet?currentPage=${pb.currentPage+1}&rows=5" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		    </c:if>
		    
		     <c:if test="${pb.currentPage == pb.totalPage  }">
		   	 <li class="disable">
		      <a href="${pageContext.request.contextPath }/FindUserByPageServlet?currentPage=${pb.totalPage}&rows=5" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		    </c:if>
		    <span style="font-size: 25px;margin-left: 5px;">
		    	共${pb.totalCount }条记录,共${pb.totalPage }页
		    </span>
		  </ul>
		</nav>
    </div>
</div>

</body>
</html>
