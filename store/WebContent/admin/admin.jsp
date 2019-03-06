<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<style>

body {
	width：100%；
	height: 100%;
	background-image:url(../products/back.jpg);
	/* background-repeat: repeat; */
	/* background-size: 100% 100%; */
}
 .admin {
 margin:0 auto;width:700px;height:100px;
 }
</style>
</head>
<body>
<div class="admin">
 <form class="form-group" action="${pageContext.request.contextPath }/adminUserServlet?method=admin" method="post">
 <div class="col-xs-5">
 <h2>登录</h2>
   用户名：<input type="text" name="username" class="form-control input-lg " placeholder="input your username">    <br>
  密码：<input type="password" name="password" class="form-control input-lg" placeholder="input your password">
  <br><input type="submit" class="btn ">
  </div>
 </form>
 </div>
</body>
</html>