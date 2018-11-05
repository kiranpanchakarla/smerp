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
</head>
<c:import url="/WEB-INF/jsp/loadcss.jsp" />
<body>


    <div style="text-align: center; margin-top: 50px;">
        <h1>Error Page</h1>
    </div>

    <div class="col-md-12">
        <div class="card">
            <div class="card-body collapse in">
                <div class="table-responsive">
                    <table class="table table-bordered mb-0">

                        <tr>
                            <th style="width: 15%;">Error</th>
                            <td>${error}</td>
                        </tr>
                        <tr>
                            <th >Status</th>
                            <td>${status}</td>
                        </tr>
                        <tr>
                            <th>message</th>
                            <td>${message}</td>
                        </tr>
                        <tr>
                            <th>Exception</th>
                            <td>${exception}</td>
                        </tr>
                        <tr>
                            <th>Trace</th>
                            <td>${trace}</td>
                        </tr>

                    </table>
                </div>
            </div>
        </div>
    </div>
   




    <div style="text-align: center; margin-top: 250px;">
        <h1>Contact:</h1>
        <h2>
            <u><font color="red">help@manuhindia.com</font></u>
        </h2>
        <h4>for more Information.</h4>
    </div>

  <footer class="footer footer-static footer-light navbar-border">
			<p class="clearfix text-muted text-sm-center mb-0 px-2">
				<span class="float-md-right d-xs-block d-md-inline-block">Copyright
					&copy; 2018 <a href="#" target="_blank"
					class="text-bold-800 grey darken-2">SMERP </a>, All rights
					reserved.
				</span>
			</p>
		</footer>
</body>
</html>