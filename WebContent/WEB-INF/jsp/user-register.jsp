<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layouts/taglib.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$(".registrationForm").validate(
 			{
 				rules:{
 					name:{
 						required:true,
 						minlength:3,
 						remote:{
 							url:"<spring:url value='/register/available.html' />",
 							type:"get",
 							data:{
 								username:function(){
 									return $("#name").val();
 								}
 							}
 						}
 					},
 					email:{
 						required:true,
 						email:true
 					},
 					password:{
 						required:true,
 						minlength:3
 					},
 					password_again:{
 						required:true,
 						minlength:3,
 						equalTo:"#password"
 					}
 				},
 				highlight:function(element){
 					$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
 				},
 				unhighlight:function(element){
 					$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
 				},
 				messages:{
 					name:{
 						remote:"Such name already exists!"
 					}
 				}
 			}
	
 	);	
	
 });
</script>



<form:form commandName="user" cssClass="form-horizontal registrationForm">

	<c:if test="${param.sucess eq true}">
		<div class="alert alert-success">Registration sucessfull</div>
	</c:if>
	
	<div class="form-group">
    <label for="name" class="col-sm-2 control-label">Name:</label>
    <div class="col-sm-10">
      <form:input path="name" class="form-control"/>
      <form:errors path="name"/>
    </div>
  </div>
  <div class="form-group">
    <label for="email" class="col-sm-2 control-label">Email:</label>
    <div class="col-sm-10">
      <form:input path="email" class="form-control"/>
      <form:errors path="email"/>
    </div>
  </div>
   <div class="form-group">
    <label for="password" class="col-sm-2 control-label">Password:</label>
    <div class="col-sm-10">
      <form:password path="password" class="form-control"/>
          <form:errors path="password"/>
    
    </div>
  </div>
  
  <div class="form-group">
    <label for="password" class="col-sm-2 control-label">Password again:</label>
    <div class="col-sm-10">
		<input type="password" name="password_again" class="form-control">
    
    </div>
  </div>
     <div class="form-group">
    <div class="col-sm-10">
      <input type="submit" value="Submit" class="btn btn-default"/>
    </div>
  </div>

</form:form>