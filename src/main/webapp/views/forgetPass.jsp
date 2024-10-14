<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>


<div class="container1">
<%String alertMsg = (String) request.getAttribute("alert");%>
				<%if (alertMsg != null) {%>
				<div class="alert alert-danger">
					<%=alertMsg%>
					</div><%}%>
	<h2>Đặt Lại Mật Khẩu</h2>
	<form id="resetForm"
		action="${pageContext.request.contextPath}/forgetPass" method="POST">
		<div class="input-group">
			<input type="text" id="new-password" name="username"
				value="${username }" hidden="hidden">
		</div>
		<div class="input-group">
			<label for="new-password">Mật khẩu mới:</label> <input
				type="password" id="new-password" name="password"
				placeholder="Mật khẩu mới" required>
		</div>
		<div class="input-group">
			<label for="confirm-password">Xác nhận mật khẩu:</label> <input
				type="password" id="confirm-password" name="password2"
				placeholder="Xác nhận mật khẩu" required>
		</div>
		<div class="error-message" id="error-message">Mật khẩu không
			khớp. Vui lòng thử lại.</div>
		<button type="submit" class="btn">Đặt Lại Mật Khẩu</button>
	</form>
</div>
