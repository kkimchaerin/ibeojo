<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.sql.*"%>
<%
String id = request.getParameter("id");
String result = "";

if (id != null && !id.trim().isEmpty()) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");

		String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();

		if (rs.next() && rs.getInt(1) > 0) {
	result = "아이디가 이미 사용 중입니다.";
		} else {
	result = "사용 가능한 아이디입니다.";
		}
	} catch (Exception e) {
		e.printStackTrace();
		result = "오류가 발생했습니다.";
	} finally {
		try {
	if (rs != null)
		rs.close();
		} catch (SQLException e) {
	e.printStackTrace();
		}
		try {
	if (pstmt != null)
		pstmt.close();
		} catch (SQLException e) {
	e.printStackTrace();
		}
		try {
	if (conn != null)
		conn.close();
		} catch (SQLException e) {
	e.printStackTrace();
		}
	}
} else {
	result = "아이디를 입력하세요.";
}

out.print(result);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>