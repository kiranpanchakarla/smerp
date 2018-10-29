
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
<body data-open="click" data-menu="vertical-menu" data-col="2-columns"
	class="vertical-layout vertical-menu 2-columns">
	<c:import url="/WEB-INF/jsp/header.jsp" />
	<c:import url="/WEB-INF/jsp/sidebar.jsp" />

	<div class="app-content content container-fluid" style="margin-top: 40px;">
		<div class="content-wrapper">
			<div class="content-header row">
				<div class="col-md-6">
					<h4>USER</h4>
				</div>
			</div>
			<div class="content-body">
				<!--/ project charts -->
				<div class="row">
				<div class="large-12 columns">
							
				<div class="content-body"><!-- Basic form layout section start -->
		
   <form:form method="POST" action="/currency/save" modelAttribute="user" onsubmit="return register()" >
      <section id="basic-form-layouts">
        <div class="row match-height">
        
          <div class="col-md-6">
            <div class="card">
              <div class="card-header">
                <h4 class="card-title" id="basic-layout-icons">Currency/Create</h4>
                <a class="heading-elements-toggle"><i class="icon-ellipsis font-medium-3"></i></a>
              </div>
              
               <input type="hidden" id="id" class="form-control"  name="id" value="${currencyObj.id}">
              
              <div class="card-body collapse in">
                <div class="card-block">
                  <form class="form">
                    <div class="form-body">
                      <div class="form-group">
                        <label for="timesheetinput1">Currency Name</label>
                        <div class="position-relative has-icon-left">
                         
                          <form:input type="text"  cssClass="form-control" placeholder='Currency Name'   path="name"  value="${currencyObj.name}" />
                          
                          <div class="form-control-position"> <i class="icon-head"></i> </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="timesheetinput2">Currency Description</label>
                        <div class="position-relative has-icon-left">
                        <form:input type="text"  cssClass="form-control" placeholder='Currency Description'   path="description"  value="${currencyObj.description}" />
                          <div class="form-control-position"> <i class="icon-briefcase4"></i> </div>
                        </div>
                      </div>
                    </div>
                    <div class="form-actions right">
                     <a  href="/currency/list"> <button type="button" class="btn btn-warning mr-1"> <i class="icon-cross2"></i> Cancel </button></a>
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                      
                     <c:if test = "${currencyObj.name!=null}">  
                      <button type="submit" class="btn btn-primary"> <i class="icon-check2"></i> Update </button>
                      </c:if>
                      
                       <c:if test = "${currencyObj.name==null}">  
                      <button type="submit" class="btn btn-primary"> <i class="icon-check2"></i> Save </button>
                      </c:if>
                      
                      
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
     </form:form>
     <a class="btn btn-primary" href="/users/4">Back</a>
      <br>
       
      <br>
      
      <!-- // Basic form layout section end --> 
    </div>
    
				
					<%-- <div class="col-xl-12 col-lg-12">
						<div class="card">
							<div class="card-body">
								 <div>token:${token}</div>
								<input type="text"  id="tokenId"  value="${tokenId}"/> 
								<div>data:${data}</div>
							</div>
							<div class="card-footer">Footer</div>
						</div>
					</div> --%>
				</div>
				<!--/ project charts -->
				<br>
			</div>
		</div>
	</div>

	<footer class="footer footer-static footer-light navbar-border">
		<div class="row">
			
			<div class="col-md-12">
				<div class="footer_logo">
					<img src="/resources/images/footer_logo.png">
				</div>
			</div>
		</div>
	</footer>
	

	<c:import url="/WEB-INF/jsp/loadJs.jsp" />
	
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
