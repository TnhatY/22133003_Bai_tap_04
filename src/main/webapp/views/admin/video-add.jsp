<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="form-container">

    <form action="<c:url value='/admin/video/add'/>" method="post"
    enctype="multipart/form-data">

        <label for="categoryname">Video Id:</label><br> 
        <input type="text" id="categoryname" name="videoid"><br>
        
        <label for="categoryname">Video Title:</label><br> 
        <input type="text" id="categoryname" name="videoname"><br>
        
        <label for="categoryname">Video Count   :</label><br> 
        <input type="text" id="categoryname" name="videoname"><br>
        
         <label for="images1">Link images:</label><br> 
        <input type="text" id="images1" name="images"><br>

       	<label for="images1">Upload images:</label><br> 
        <input type="file" id="images1" name="images1"><br>
		
		<select name="shoes">
    		<c:forEach items="${category}" var="c">
        		<option >${c.categoryname}</option>
    		</c:forEach>
		</select><br><br>
        <label for="status">Status</label><br> 
        <input type="radio" id="ston" name="status" value="1"> 
        <label for="ston">Hoạt động</label><br>

        <input type="radio" id="stoff" name="status" value="0"> 
        <label for="stoff">Khóa</label><br>

        <br>
        <input type="submit" value="Insert">

    </form>
</div>