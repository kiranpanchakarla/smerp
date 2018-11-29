<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMERP | Home</title>
<%-- <%@include file="loadcss.jsp"%> --%>
<c:import url="/WEB-INF/jsp/loadcss.jsp"/>
<style>
.form-control-position i{
        line-height: 3.5em;
    }
</style>
</head>
<body data-open="click" data-menu="vertical-menu" data-col="1-column" class="vertical-layout vertical-menu 1-column  blank-page blank-page">
<!-- ////////////////////////////////////////////////////////////////////////////-->
<div class="app-content content container-fluid">
  <div class="content-wrapper">
    <div class="content-header row"> </div>
    <div class="content-body">
      <section class="flexbox-container login-main">
        <div class="col-md-3">
        <div class="card-title text-xs-center">
                <div class="p-1">
                <img style="width: 150px;"  src="<c:url value="/resources/images/logo.png"/>"/>
                <!-- <img src="/resources/images/logo.png" style="width: 150px;" alt="branding logo"> -->
                </div>
              </div>
          <div class="card border-grey border-lighten-3 m-0">
          <c:if test="${not empty errorMsg}">
							<div class="alert alert-danger">
							 <b>${errorMsg}</b>
							</div>
			</c:if>		
            <div class="card-header no-border">
              
              <h6 class="card-subtitle line-on-side text-muted text-xs-center font-small-3 pt-2"><span>Login Here</span></h6>
            </div>
            <div class="card-body collapse in">
              <div class="card-block">
                <c:url value="/login" var="generateToken" />
                  <form name='f' class="login-form" role="form" method="POST" action="<c:url value="login"/>" >
                  <fieldset class="form-group position-relative has-icon-left mb-0">
                    <input type="text" class="form-control form-control-lg input-lg" name="username" id="user-name" placeholder="Your Username" required>
                    <div class="form-control-position"> <i class="icon-head"></i> </div>
                  </fieldset>
                  <fieldset class="form-group position-relative has-icon-left">
                    <input type="password" class="form-control form-control-lg input-lg" name="password"    id="user-password" placeholder="Enter Password" required>
                    <div class="form-control-position"> <i class="icon-key3"></i> </div>
                  </fieldset>
                  <fieldset class="form-group row">
                    <div class="col-md-6 col-xs-12 text-xs-center text-md-left">
                     <!--  <fieldset>
                        <input type="checkbox" id="remember-me" class="chk-remember">
                        <label for="remember-me"> Remember Me</label>
                      </fieldset> -->
                    </div>
                   <!--  <div class="col-md-6 col-xs-12 text-xs-center text-md-right"><a href="#" class="card-link">Forgot Password?</a></div> -->
                  </fieldset>
                  <button type="submit" class="btn btn-primary btn-lg btn-block"><i class="icon-unlock2"></i> Login</button>
                </form>
              </div>
            </div>
           <!--  <div class="card-footer">
              <div class="">
                <p class="float-sm-left text-xs-center m-0"><a href="#" class="card-link">Recover password</a></p>
                <p class="float-sm-right text-xs-center m-0">New to SMERP? <a href="#" class="card-link">Sign Up</a></p>
              </div>
            </div> -->
          </div>
        </div>
      </section>
    </div>
  </div>
</div>
<!-- ////////////////////////////////////////////////////////////////////////////--> 
<%-- <%@include file="loadJs.jsp"%> --%>
<c:import url="/WEB-INF/jsp/loadJs.jsp" />
</body>
</html>