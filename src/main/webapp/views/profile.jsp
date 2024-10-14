<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


	<div class="container1">
        <h2>Cập nhật thông tin</h2>
        <form action="${pageContext.request.contextPath}/updateProfile" method="POST" enctype="multipart/form-data">
        	<input type="text" name="userid" value="${sessionScope.account.getUserId()}" >
            <input type="text" name="fullname" value="${sessionScope.account.getFullName()}" placeholder="Họ tên" required>
            <input type="email" name="email" value="${sessionScope.account.getEmail()}" placeholder="Email" required>
            <input type="tel" name="phone" value="${sessionScope.account.getPhone()}" placeholder="Số điện thoại" required>
            <h3>Chọn ảnh</h3> 
         
            <input type="file" name="image" value="${sessionScope.account.getImage()}" /><br/>
            <button type="submit" name="submit" class="btn">Cập nhật</button>
        </form>
       
    </div>
