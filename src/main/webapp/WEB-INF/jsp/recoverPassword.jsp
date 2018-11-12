<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMERP | Home</title>
<%-- <%@include file="loadJs.jsp"%> --%>
<c:import url="/WEB-INF/jsp/loadJs.jsp"/>
</head>
<body data-open="click" data-menu="vertical-menu" data-col="1-column" class="vertical-layout vertical-menu 1-column  blank-page blank-page">
<!-- ////////////////////////////////////////////////////////////////////////////-->
<div class="app-content content container-fluid">
  <div class="content-wrapper">
    <div class="content-header row"> </div>
    <div class="content-body">
      <section class="flexbox-container">
        <div class="col-md-4 offset-md-4 col-xs-10 offset-xs-1 box-shadow-2 p-0">
          <div class="card border-grey border-lighten-3 px-2 py-2 m-0">
            <div class="card-header no-border pb-0">
              <div class="card-title text-xs-center"> <img src="/resources/images/logo.png" style="width: 150px;" alt="branding logo"> </div>
              <h6 class="card-subtitle line-on-side text-muted text-xs-center font-small-3 pt-2"><span>We will send you a link to reset your password.</span></h6>
            </div>
            <div class="card-body collapse in">
              <div class="card-block">
                <form class="form-horizontal" action="index.html" novalidate>
                  <fieldset class="form-group position-relative has-icon-left">
                    <input type="email" class="form-control form-control-lg input-lg" id="user-email" placeholder="Your Email Address" required>
                    <div class="form-control-position"> <i class="icon-mail6"></i> </div>
                  </fieldset>
                  <button type="submit" class="btn btn-primary btn-lg btn-block"><i class="icon-lock4"></i> Recover Password</button>
                </form>
              </div>
            </div>
            <div class="card-footer no-border">
              <p class="float-sm-left text-xs-center"><a href="login-simple.html" class="card-link">Login</a></p>
              <p class="float-sm-right text-xs-center">New to SMERP ? <a href="register-simple.html" class="card-link">Create Account</a></p>
            </div>
          </div>
        </div>
      </section>
    </div>
  </div>
</div>
<!-- ////////////////////////////////////////////////////////////////////////////--> 
</body>
</html>