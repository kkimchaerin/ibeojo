<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.goming.user.model.user_DTO"%>

<%
List<user_DTO> members = (List) request.getAttribute("selectMembers");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" type="text/css" href="styles/Admin.css?ver=4" />
<link rel="stylesheet" type="text/css" href="styles/Reset.css?ver=2" />
</head>
<body>
	<h1 class="title">관리자 페이지</h1>
	<table class="user_information">
		<tr>
			<th scope="col">아이디</th>
			<th scope="col">닉네임</th>
			<th scope="col">성별</th>
			<th scope="col">선호 스타일</th>
		</tr>

		<%
		for (int i = 0; i < members.size(); i++) {
			user_DTO member = members.get(i);

			if (!member.getUser_email().equals("admin")) {
				out.print("<tr>");
				out.print("<td>" + member.getUser_email() + "</td>");
				out.print("<td>" + member.getUser_nick() + "</td>");
				out.print("<td>" + member.getUser_gender() + "</td>");
				out.print("<td>" + member.getUser_preference() + "</td>");
				out.print("</tr>");
			}
		}
		%>

	</table>
	<a href="First.jsp" class="button-next-scrolly">첫 페이지로 가기</a>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
	<script src="assets/js/util.js"></script>
	<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="assets/js/main.js"></script>
</body>
</html>