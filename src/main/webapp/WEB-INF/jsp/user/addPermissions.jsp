
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
<c:import url="/WEB-INF/jsp/loadcss.jsp" />
</head>


<link href="<c:url value="/resources/css/dataTables/buttons.dataTables.min.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/dataTables/jquery.dataTables.min.css"/>" rel="stylesheet" type="text/css" />


<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsp/header.jsp" />

	<c:import url="/WEB-INF/jsp/sidebar.jsp" />

	<div class="app-content content container-fluid"
		style="margin-top: 40px;">
		<div class="content-wrapper">
			 
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
					<div class="large-12 columns">

							<div class="content-wrapper">
								
								<div>
									<div class="content-body">
										<!-- Basic Tables start -->

										<div class="card">
											
											<div class="card-body collapse in">
												<div class="card-block card-dashboard">
												
												<c:url value="/user/savePermissions" var="createUrl" />	 
												<form:form action="${createUrl}" method="post" modelAttribute="user">
												<%-- <div class="row">
																<div class="col-xs-12 col-sm-6">
																	<c:set var="count" value="0" scope="page" />
																	<c:forEach items="${list}" var="list"  varStatus="loop">
																	  <div><input type="hidden" name="userModulePermission[${count}].module" readonly="readonly"   value="${list.module.id}" />${list.module.moduleName}</div><br>
																		
																		 <c:forEach items="${list.permissions}" var="map1">
																			 	 <div style="float: right;"><input type="checkbox" name="userModulePermission[${count}].permissions"  value="${map1.id}" /> ${map1.permssionName}</div>
																          </c:forEach>
																       <input type="hidden" name="userModulePermission[${count}].user"  value="${userId}" />
																		  <c:set var="count" value="${count + 1}" scope="page" />
																	</c:forEach>
																	<input type="text" value="${id}" name="userId"/>
																</div>
													</div>  --%>
													
													<div class="row">
																<div class="col-xs-12 col-sm-12">
																	<c:set var="count" value="0" scope="page" />
																	<c:forEach items="${ump}" var="map"  varStatus="loop">
																	  
																	  <div class="row bold-text">
																	  <input type="hidden" name="userModulePermission[${count}].module" readonly="readonly"   value="${map.key.id}" />${map.key.moduleName}
																	  </div>
																	  <div class="row bold-value">
																		 <c:forEach items="${map.value}" var="permissions">
																		    <c:choose>
																		    		
																					  <c:when test="${permissions.flag eq 'Checked'}">
																				<div class="float-left">
																				<input type="checkbox" checked="checked"  name="userModulePermission[${count}].permissions"  value="${permissions.id}" />${permissions.permissionName}</div>
																		           </c:when>
																		           <c:otherwise>
																		           
																			    <div class="float-left"><input type="checkbox"    name="userModulePermission[${count}].permissions"  value="${permissions.id}" /> ${permissions.permissionName}</div>
																					  </c:otherwise>
																				</c:choose> 
																				
																				
																		  </c:forEach>
																		  </div>
																		<input type="hidden" name="userModulePermission[${count}].user.userId" readonly="readonly"   value="${id}" />
																		
																		   <c:set var="count" value="${count + 1}" scope="page" />
																	</c:forEach>
																	  <input type="hidden" name="userId" value="${user.userId}">
																	  
																</div>
													</div>  
													
													
													<a href="#" onclick="goBack()"
												class="btn btn-primary float-left mr-1"> Back </a>
												
													<input type="submit"  value="Save"
												class="btn btn-primary" />
												
												
												</form:form>
												</div>
											</div>
										</div>
										
									</div>
									<br>
								</div>
							</div>
					</div>
					</div>
</div></div></div>
			<c:import url="/WEB-INF/jsp/footer.jsp" />

<c:import url="/WEB-INF/jsp/loadJs.jsp" />



</body>
</html>

