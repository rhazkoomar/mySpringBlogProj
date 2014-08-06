<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layouts/taglib.jsp" %>
<%-- <h1><c:out value="${user.name}"></c:out></h1> --%>

<!-- Button trigger modal -->
<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Add New Blog
</button>


<form:form commandName="blog" cssClass="form-horizontal blogForm" >
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Add Blog</h4>
      </div>
      <div class="modal-body">
        <div class="form-group">
    		<label for="name" class="col-sm-2 control-label">Name:</label>
    	<div class="col-sm-10">
     		 <form:input path="name" class="form-control"/>
     		 <form:errors path="name"/>
     		 
    </div>
      </div>
      <div class="form-group">
    <label for="email" class="col-sm-2 control-label">URL:</label>
    <div class="col-sm-10">
      <form:input path="url" class="form-control"/>
      <form:errors path="url"/>
      
    </div>
  </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <input type="submit" value="save" class="btn btn-primary">
      </div>
    </div>
  </div>
</div>
</div>
</form:form>
</br>
</br>

<script type="text/javascript">
$(document).ready(function() {
	$('.nav-tabs a:first').tab('show'); 
	$(".triggerRemove").click(function(e) {
		e.preventDefault();
		$("#modalRemove .removeBtn").attr("href",$(this).attr("href"));
		$("#modalRemove").modal();
	});
	$(".blogForm").validate(
 			{
 				rules:{
 					name:{
 						required:true,
 						minlength:3
 					},
 					url:{
 						required:true,
 						url:true
 					}
 				},
 				highlight:function(element){
 					$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
 				},
 				unhighlight:function(element){
 					$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
 				}
 			}
	
 	);	
	
	
});



</script>
<ul class="nav nav-tabs" role="tablist">
	<c:forEach items="${user.blogs }" var="blog">
 	 <li><a href="#blog_${blog.id}" role="tab" data-toggle="tab">${blog.name}</a></li>
	</c:forEach>
</ul>

<!-- Tab panes -->
<div class="tab-content">
<c:forEach items="${user.blogs }" var="blog">
  <div class="tab-pane" id="blog_${blog.id}">
  
  <h1>${blog.name}</h1>
  <p>
  		<a href="<spring:url value="/blog/remove/${blog.id}.html"></spring:url>" class="btn btn-danger triggerRemove">Remove Blog</a>
  		${blog.url }
  </p>
  		<table class="table table-hover table-hover table-striped">
		<thead>
			<tr>
				<th>date</th>
				<th>item</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${blog.items}" var="item">
				<tr>
					<td>${item.publishedDate }</td>
					
					<td><strong>
					<a href="<c:out value="${item.link }" />" target="_blank">
						<c:out value="${item.title}"></c:out>
					</a>
					
					</strong>
					<br/>
					${item.description}
					</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
  	
  </div>
 </c:forEach>
</div>
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Remove Blog</h4>
      </div>
      <div class="modal-body">
        Really Remove?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		<a href="" class="btn btn-danger removeBtn">Remove</a>
      </div>
    </div>
  </div>
</div>