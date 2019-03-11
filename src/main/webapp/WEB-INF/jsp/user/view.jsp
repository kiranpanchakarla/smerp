<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>

<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMERP</title>
<c:import url="/WEB-INF/jsp/loadcss.jsp" />
<style>
.table thead tr th {
	text-align: center !important;
}
</style>

</head>

 <script src=<c:url value="/resources/components/bootstrap-validator/js/jquery.min.js"/> type="text/javascript"></script>    
 <script src=<c:url value="/resources/components/bootstrap-validator/js/bootstrap.min.js"/> type="text/javascript"></script>    
 <script src=<c:url value="/resources/components/bootstrap-validator/js/validator.min.js"/> type="text/javascript"></script>   


<body data-open="click" data-menu="vertical-menu" data-col="2-columns" class="vertical-layout vertical-menu 2-columns">
    <c:import url="/WEB-INF/jsp/header.jsp" />
    <c:import url="/WEB-INF/jsp/sidebar.jsp" />

    <div class="app-content content container-fluid" style="margin-top: 40px;">
        <div class="content-wrapper">
            <div class="content-body">
                <div class="row">
                    <div class="large-12 columns">
                        <div class="content-body">
                            <!-- Basic form layout section start -->
                            <c:url value="/user/save" var="createUrl" />
                            <form:form method="POST"  action="${createUrl}" class="commentForm" modelAttribute="user" data-toggle="validator" role="form">
                                <section id="basic-form-layouts">
                                    <div class="row match-height">
                                        <div class="col-md-12">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h2 class="card-title" id="basic-layout-icons">Users</h2>
                                                </div>
                                                
                                                <div class="card-body collapse in create-block">
                                                    <div class="card-block">
                                                        <form class="form">
                                                            <div class="form-body">
                                                                <div class="row">
                                                                    <div class="col-sm-4 form-group has-feedback">
                                                                        <label>First Name</label>: ${user.firstname}

                                                                    </div>
                                                                    <div class="col-sm-4 form-group has-feedback">
                                                                        <label>Last Name</label>: ${user.lastname}
                                                                    </div>
                                                                    <div class="col-sm-4 form-group has-feedback">
                                                                        <label>Image</label>: <img src="${contextPath}/${user.image}" alt="Upload Image" id="output" width="100" height="70"> 
                                                                    </div>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="col-sm-4 form-group has-feedback">
                                                                        <label>Username</label>: ${user.username}
                                                                    </div>
                                                                    <div class="col-sm-4 form-group has-feedback">
                                                                        <label>Email</label>: ${user.userEmail}
                                                                    </div>
                                                                    <div class="col-sm-4 form-group has-feedback">
                                                                        <label>Mobile</label>: ${user.mobileNo}
                                                                    </div>
                                                                    
                                                                </div>
                                                                 
                                                                 
                                                                
                                                                <div class="row">
                                                                    <div class="col-sm-4 form-group has-feedback">
                                                                        <label>Department</label>: ${user.department.name}
                                                                    </div>
                                                                    <div class="col-sm-4 form-group has-feedback">
                                                                        <label>Designation</label>: ${user.desigination.desigination}
                                                                    </div>
                                                                    <div class="col-sm-4 form-group has-feedback">
                                                                        <label>Plant</label>: ${user.plant.plantName}
                                                                    </div>
                                                                </div>
                                                                
                                                                
                                                                <!--  -->

                                                            </div>

                                                        </form>

                                                        <div>
                                                            <a href="#" onClick="goBack()" class="btn btn-primary">Back </a>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </section>
                            </form:form>
                        </div>
                        <br>
                        <br>
                    </div>
                </div>
                <!--/ project charts -->
                <br>
            </div>
        </div>
    </div>
    <c:import url="/WEB-INF/jsp/footer.jsp" />
    <script type="text/javascript">
     

        function goBack() {
            window.history.back();
        }
    </script>
</body>

</html>