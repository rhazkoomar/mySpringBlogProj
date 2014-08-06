<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layouts/taglib.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	$('.nav-tabs a:first').tab('show'); 
	$(".btnUserRemove").click(function(e) {
		e.preventDefault();
		$("#modalRemove .removeBtn").attr("href",$(this).attr("href"));
		$("#modalRemove").modal();
	});
	
});
</script>

<table class="table table-hover table-hover table-striped">
	<thead>
		<tr>
			<th>user id</th>
			<th>user name</th>
			<th>Operation</th>

		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>
					${user.id}
				</td>
				<td>
					<a href='<spring:url value="/users/${user.id}.html"/>'>
					<c:out value="${user.name}"></c:out>
					</a>
				</td>
				<td>
					<a href='<spring:url value="/users/remove/${user.id}.html" />' class="btn btn-danger btnUserRemove">
					delete
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Remove User</h4>
      </div>
      <div class="modal-body">
        Really Remove the User?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		<a href="" class="btn btn-danger removeBtn">Remove</a>
      </div>
    </div>
  </div>
</div>