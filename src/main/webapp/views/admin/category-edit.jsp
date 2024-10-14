<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="form-container">
	<form action="<c:url value='/admin/category/update'/>" method="post"
		enctype="multipart/form-data">

		<input type="text" name="categoryid" value="${cate.categoryId}"
			hidden="hidden"> <label for="categoryname">Category
			name:</label> <input type="text" id="categoryname" name="categoryname"
			value="${cate.categoryname}"> 
			<label for="images">Link images:</label> 
			<input type="text" id="images" name="images" value="${cate.images}">

		<c:if test="${cate.images.substring(0,5)=='https'}">
			<c:url value="${cate.images }" var="imgUrl"></c:url>
		</c:if>

		<c:if test="${cate.images.substring(0,5)!='https'}">
			<c:url value="/image?fname=${cate.images }" var="imgUrl"></c:url>
		</c:if>

		<img height="150" width="200" src="${imgUrl}" /> 
		<label for="images1">Upload images:</label> 
		<input type="file" id="images1" name="images1"> 
		<label for="status">Status</label> 
		<input type="radio" id="ston" name="status" value="1" ${cate.status == 1 ? 'checked' : '' }> 
		<label for="ston">Hoạt động</label>
		<input type="radio" id="stoff" name="status" value="0" ${cate.status != 1 ? 'checked' : '' }> 
		<label for="stoff">Khóa</label>
		<input type="submit" value="Update">

	</form>
</div>
