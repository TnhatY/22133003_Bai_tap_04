<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="contens2">
	<div class="profile-card">
		<div class="social-icons">
		<a href="#"><i class="fa fa-facebook"></i></a>
        <a href="#"><i class="fa fa-twitter"></i></a>
        <a href="#"><i class="fa fa-github"></i></a>
		</div>
		<div class="profile-image">
			<img src="${user.getImage() }" alt="Profile Image">
		</div>
		<h2>${user.getFullName() }</h2>
		<p>Email: ${user.getEmail()}</p>
		<p>SĐT: ${user.getPhone()}</p>
		<div class="buttons">
			<a href="${pageContext.request.contextPath}/updateProfile" class="add-friend">Edit Profile</a>
		</div>
	</div>
</div>

