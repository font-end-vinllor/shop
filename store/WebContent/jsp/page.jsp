<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:50px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
			<!--若为第一页  -->
			<c:if test="${pb.pageNumber == 1 }">
				<li class="disabled">
					<a href="javascript:void(0)" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			</c:if>
			<!--若不为第一页  -->
			<c:if test="${pb.pageNumber != 1 }">
				<li>
					<a href="${pageContext.request.contextPath }/order?method=findMyOrdersByPage&pageNumber=${pb.pageNumber-1}&cid=${cid}" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			</c:if>
			
		<!-- 页码设计 -->
		<!--如果是当前页  -->
			<c:forEach begin="1" end="${pb.totalPage }" var="n">
				<c:if test="${pb.pageNumber == n }">
			   		<li class="active"><a href="${pageContext.request.contextPath }/order?method=findMyOrdersByPage&pageNumber=${n}&cid=${cid}">${n}</a></li>
				</c:if>
				<c:if test="${pb.pageNumber != n }">
			   		<li><a href="${pageContext.request.contextPath }/order?method=findMyOrdersByPage&pageNumber=${n}&cid=${cid}">${n}</a></li>
				</c:if>
			</c:forEach>
				
		<!--若为最后一页  -->
				<c:if test="${pb.pageNumber == pb.totalPage }">
				 <li class="disabled">
					<a href="javascript:void(0)" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				 </li>
				</c:if>
				<!-- 若不是最后一页 -->
					<c:if test="${pb.pageNumber != pb.totalPage }">
				 <li>
					<a href="${pageContext.request.contextPath }/order?method=findMyOrdersByPage&pageNumber=${pb.pageNumber+1}&cid=${cid}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				 </li>
				</c:if>
				
			</ul>
		</div>
		<!-- 分页结束=======================        -->
  
</body>
</html>