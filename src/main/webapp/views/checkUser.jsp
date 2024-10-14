<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="container1">
    <h2>Nhập tài khoản của bạn</h2>
    <form action="${pageContext.request.contextPath}/checkUser" method="POST">
        <div class="input-group">
            <label for="username">Nhập Tài Khoản:</label>
            <input type="text" id="username" name="username" placeholder="Tài khoản" required>
        </div>
        <button type="submit" class="btn">Tiếp theo</button>
    </form>
</div>
