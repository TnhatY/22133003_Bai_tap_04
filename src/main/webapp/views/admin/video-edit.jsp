<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="form-container">

    <form action="<c:url value='/admin/video/edit'/>" method="post"
    enctype="multipart/form-data">

        <label for="categoryname">Video Id:</label><br> 
        <input type="text" id="categoryname" name="videoid" value="${video.videoId }"><br>
        
        <label for="categoryname">Video Title:</label><br> 
        <input type="text" id="categoryname" name="videoname" value="${video.title }"><br>
        
        <label for="categoryname">Video Description   :</label><br> 
        <input type="text" id="categoryname" name="videoname" value="${video.description }"><br>
        
         <label for="images1">Link images:</label><br> 
        <input type="text" id="images1" name="images" value="${video.poster }"><br>

       	<label for="images1">Upload images:</label><br> 
        <input type="file" id="images1" name="images1" value="${video.poster }"><br>
        
	<select name="shoes">
    <c:forEach items="${category}" var="c">
        <option value="${c.categoryname}" ${c.categoryname == video.getCategory().getCategoryname() ? 'selected' : '' }>
            ${c.categoryname}
        </option>
    </c:forEach>
	</select>

		<br><br>
       <label for="status">Status</label> 
		<input type="radio" id="ston" name="status" value="1" ${video.active == 1 ? 'checked' : '' }> 
		<label for="ston">Hoạt động</label>
		<input type="radio" id="stoff" name="status" value="0" ${video.active != 1 ? 'checked' : '' }> 
		<label for="stoff">Khóa</label>

        <br>
        <input type="submit" value="Update ">

    </form>
</div>