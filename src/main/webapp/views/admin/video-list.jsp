<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglib.jsp"%>
<div class="contens1">

	<a href="<c:url value="/admin/video/add"/>" class="button-add">Add video</a> <br>


	<hr>


	<table border="1" width="100%">


		<tr>
			<th>STT</th>
			<th>video</th>
			<th>Description</th>
			<th>Category name</th>
			<th>Title</th>
			<th>Views</th>
			<th>Status</th>
			<th>Action</th>
		
		</tr>
		<c:forEach items="${listvideo}" var="video" varStatus="STT">
			<tr>
				<td>${STT.index+1 }</td>

				<c:if test="${video.poster.substring(0,5)=='https'}">

					<c:url value="${video.poster }" var="imgUrl"></c:url>

				</c:if>

				<c:if test="${video.poster.substring(0,5)!='https'}">

					<c:url value="/image?fname=${video.poster }" var="imgUrl"></c:url>

				</c:if>

				<td><img height="150" width="200" src="${imgUrl}" /></td>

				<td>${video.description }</td>

				<td>
					${video.getCategory().getCategoryname() }
				</td>
				<td>
					${video.getTitle() }
				</td>
				<td>
					${video.getViews() }
				</td>
				<td>
					<c:if test="${video.active==1 }"> Hoạt động </c:if> 
					<c:if test="${video.active!=1 }"> Khóa </c:if>
				</td>
				<td>
					<a href="<c:url value="/admin/video/edit?id=${video.videoId }"/>">Sửa</a>

					| <a href="<c:url value="/admin/video/delete?id=${video.videoId }"/>">Xóa</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>