
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMERP</title>
<%@include file="loadcss.jsp"%>
</head>
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<%@include file="topmenubar.jsp"%>

	<%@include file="sidemenubar.jsp"%>

	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-header row">
				<div class="col-md-6">
					<h4>Header</h4>
				</div>
			</div>
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="col-xl-12 col-lg-12">
						<div class="card">
							<div class="card-body">
								 <div>token:${token}</div>
								<input type="text"  id="tokenId"  value="${tokenId}"/> 
								<div>data:${data}</div>
							</div>
							<div class="card-footer">Footer</div>
						</div>
					</div>
				</div>
				<!--/ project charts -->
				<br>
			</div>
		</div>
	</div>

	<footer class="footer footer-static footer-light navbar-border">
		<div class="row">
			<div class="col-md-6">
				<div class="footer_error_validation">validation</div>
				<div class="footer_error_validation">validation</div>
			</div>
			<div class="col-md-5">
				<div class="footer_error_validation">validation</div>
				<div class="footer_error_validation">validation</div>
			</div>
			<div class="col-md-1">
				<div class="footer_logo">
					<img src="/resources/images/footer_logo.png">
				</div>
			</div>
		</div>
	</footer>
	
	<%@include file="loadJs.jsp"%>
</body>
<script>
$(document).ready(function(){
	/*  console.log("hiiiii");
	var x=$('#tokenId').val();
	console.log("token id"+x);
	document.cookie="tokenId=" + x;
	
	console.log("document cookie"+document.cookie);   */
});



</script>
</html>