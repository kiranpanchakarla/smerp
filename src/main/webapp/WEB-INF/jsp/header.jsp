<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.security.Principal"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- END Custom CSS-->
<div class="first_strip">
	<div>

		<ul class="main_menu">
			<li class="nav-item hidden-sm-down toggle_sidemenu_icon"><a
				class="nav-link nav-menu-main menu-toggle hidden-xs"><i
					class="icon-menu5"> </i></a></li>
			<li class="logo-box text-xs-center"><sec:authorize
					access="isAuthenticated()">
					<img src="${contextPath}/<sec:authentication property="principal.company.logo" />"
						class="logo-left-main" width="135px" height="43px" />
				</sec:authorize></li>


			<%-- <li><a>Module</a>
				<ul class="sub_menu">

					<li class="has_sub"><a href="<c:url value ="/dashboard"/>">Administration<i
							class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">

							 <li class="has_sub"><a>Admin Settings<i
									class="icon-ios-arrow-right right"></i></a>
								  <ul class="super_sub">
									<li ><a href="<c:url value ="/company/list"/>">Company</a></li>
									<li><a href="<c:url value ="/user/list"/>">Users</a></li>
									<li><a href="<c:url value ="/vendor/list"/>">Vendors</a></li>
									<li><a href="<c:url value =""/>">Roles</a></li>
									<li><a href="<c:url value ="/department/list"/>">Departments</a></li>
									<li><a href="<c:url value ="/designation/list"/>">Designation</a></li>
								</ul> </li>  
								
							  <li class="has_sub"><a>Global Settings<i
									class="icon-ios-arrow-right right"></i></a>
								<ul class="super_sub">
									<li><a href="<c:url value ="/country/list"/>">Countries</a></li>
									<li><a href="<c:url value ="/currency/list"/>">Currencies</a></li>
									<li><a href="<c:url value ="/languages/list"/>">Languages</a></li>
									<li><a href="<c:url value ="/timezone/list"/>">Timezones</a></li>
									<li><a href="<c:url value ="/states/list"/>">States</a></li>
									<li><a href="<c:url value ="/hsncode/list"/>">Hsn Code</a></li>
									<li><a href="<c:url value ="/saccode/list"/>">Sac Code</a></li>
								</ul></li>
						</ul></li>  
					 
					<li class="has_sub"><a href="<c:url value =""/>">Purchase<i
							class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<!-- <li><a>Sub item 1</a></li>
							<li><a>Sub item 1</a></li>
							<li class="has_sub"><a>Sub item 1<i
									class="icon-ios-arrow-right right"></i></a>
								<ul class="super_sub">
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
								</ul></li> -->
						</ul></li>
					 
					<li class="has_sub"><a>Inventory<i
							class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<!-- <li><a>Sub item 1</a></li>
							<li><a>Sub item 1</a></li>
							<li class="has_sub"><a>Sub item 1<i
									class="icon-ios-arrow-right right"></i></a>
								<ul class="super_sub">
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
								</ul></li> -->
								
						</ul></li>
					  
				</ul></li> --%>
			<!-- <li><a>Tools</a> -->
			<!-- <ul class="sub_menu">
					<li><a>item 1</a></li>
					<li class="has_sub"><a>item 1<i
							class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<li><a>Sub item 1</a></li>
							<li><a>Sub item 1</a></li>
							<li class="has_sub"><a>Sub item 1<i
									class="icon-ios-arrow-right right"></i></a>
								<ul class="super_sub">
									<li><a>Super Sub item 1</a></li>
									<li class="has_sub"><a>Super Sub item 1<i
											class="icon-ios-arrow-right right"></i></a>
										<ul class="super_sub">
											<li><a>Super Sub item 1</a></li>
										</ul></li>
									<li><a>Super Sub item 1</a></li>
								</ul></li>
						</ul></li>
					<li><a>item 1</a></li>
					<li class="has_sub"><a>item 1<i
							class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<li><a>Sub item 1</a></li>
							<li><a>Sub item 1</a></li>
							<li class="has_sub"><a>Sub item 1<i
									class="icon-ios-arrow-right right"></i></a>
								<ul class="super_sub">
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
								</ul></li>
						</ul></li>
					<li><a>item 1</a></li>
					<li class="has_sub"><a>item 1<i
							class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<li><a>Sub item 1</a></li>
							<li><a>Sub item 1</a></li>
							<li class="has_sub"><a>Sub item 1<i
									class="icon-ios-arrow-right right"></i></a>
								<ul class="super_sub">
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
								</ul></li>
						</ul></li>
					<li><a>item 1</a></li>
					<li class="has_sub"><a>item 1<i
							class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<li><a>Sub item 1</a></li>
							<li><a>Sub item 1</a></li>
							<li class="has_sub"><a>Sub item 1<i
									class="icon-ios-arrow-right right"></i></a>
								<ul class="super_sub">
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
								</ul></li>
						</ul></li>
				</ul> -->
			</li>
			<!-- <li><a>Window</a> -->
			<!-- <ul class="sub_menu">
					<li><a>item 1</a></li>
					<li class="has_sub"><a>item 1<i
							class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<li><a>Sub item 1</a></li>
							<li><a>Sub item 1</a></li>
							<li class="has_sub"><a>Sub item 1<i
									class="icon-ios-arrow-right right"></i></a>
								<ul class="super_sub">
									<li><a>Super Sub item 1</a></li>
									<li class="has_sub"><a>Super Sub item 1<i
											class="icon-ios-arrow-right right"></i></a>
										<ul class="super_sub">
											<li><a>Super Sub item 1</a></li>
										</ul></li>
									<li><a>Super Sub item 1</a></li>
								</ul></li>
						</ul></li>
					<li><a>item 1</a></li>
					<li class="has_sub"><a>item 1<i
							class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<li><a>Sub item 1</a></li>
							<li><a>Sub item 1</a></li>
							<li class="has_sub"><a>Sub item 1<i
									class="icon-ios-arrow-right right"></i></a>
								<ul class="super_sub">
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
								</ul></li>
						</ul></li>
					<li><a>item 1</a></li>
					<li class="has_sub"><a>item 1<i
							class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<li><a>Sub item 1</a></li>
							<li><a>Sub item 1</a></li>
							<li class="has_sub"><a>Sub item 1<i
									class="icon-ios-arrow-right right"></i></a>
								<ul class="super_sub">
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
								</ul></li>
						</ul></li>
					<li><a>item 1</a></li>
					<li class="has_sub"><a>item 1<i
							class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<li><a>Sub item 1</a></li>
							<li><a>Sub item 1</a></li>
							<li class="has_sub"><a>Sub item 1<i
									class="icon-ios-arrow-right right"></i></a>
								<ul class="super_sub">
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
								</ul></li>
						</ul></li>
				</ul></li>
			<li><a>Help</a>
				<ul class="sub_menu">
					<li><a>item 1</a></li>
					<li class="has_sub"><a>item 1<i
							class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<li><a>Sub item 1</a></li>
							<li><a>Sub item 1</a></li>
							<li class="has_sub"><a>Sub item 1<i
									class="icon-ios-arrow-right right"></i></a>
								<ul class="super_sub">
									<li><a>Super Sub item 1</a></li>
									<li class="has_sub"><a>Super Sub item 1<i
											class="icon-ios-arrow-right right"></i></a>
										<ul class="super_sub">
											<li><a>Super Sub item 1</a></li>
										</ul></li>
									<li><a>Super Sub item 1</a></li>
								</ul></li>
						</ul></li>
					<li><a>item 1</a></li>
					<li class="has_sub"><a>item 1<i
							class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<li><a>Sub item 1</a></li>
							<li><a>Sub item 1</a></li>
							<li class="has_sub"><a>Sub item 1<i
									class="icon-ios-arrow-right right"></i></a>
								<ul class="super_sub">
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
								</ul></li>
						</ul></li>
					<li><a>item 1</a></li>
					<li class="has_sub"><a>item 1<i
							class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<li><a>Sub item 1</a></li>
							<li><a>Sub item 1</a></li>
							<li class="has_sub"><a>Sub item 1<i
									class="icon-ios-arrow-right right"></i></a>
								<ul class="super_sub">
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
								</ul></li>
						</ul></li>
					<li><a>item 1</a></li>
					<li class="has_sub"><a>item 1<i
							class="icon-ios-arrow-right right"></i></a>
						<ul class="super_sub">
							<li><a>Sub item 1</a></li>
							<li><a>Sub item 1</a></li>
							<li class="has_sub"><a>Sub item 1<i
									class="icon-ios-arrow-right right"></i></a>
								<ul class="super_sub">
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
									<li><a>Super Sub item 1</a></li>
								</ul></li>
						</ul></li>
				</ul> </li>-->
		   
		   <li class="float-right dashboard-logo-wrap"><img src=<c:url value="/resources/images/dashboard-logo.png"/> class="logo-right-main" /></li>
		   
			<li class="dropdown dropdown-user nav-item edit-user-dropdown">
				<a href="#" data-toggle="dropdown"
				class="dropdown-toggle nav-link dropdown-user-link"> <span
					class="avatar avatar-online"> <%-- <img src="${contextPath}/${user.image}"/> --%>
						<sec:authorize access="isAuthenticated()">
							<img
								src="${contextPath}/<sec:authentication property="principal.image" />" />

						</sec:authorize> <!-- <img
						src="/resources/images/portrait/logo/manuh_logo.jpg" alt="avatar"> -->
						<i></i>
				</span> <%-- <span class="user-name">${pageContext.request.userPrincipal.principal.username}</span>  --%>
					<label> <sec:authorize access="isAuthenticated()">
							<sec:authentication property="principal.username" />
							</span>
						</sec:authorize>
				</label>

			</a>
				<div class="dropdown-menu dropdown-menu-right">
				<sec:authentication property="principal.userId" var="userId" />
				<c:url value="/user/edit?id=${userId}&pwd=true" var="createUrl" />
                <a href="${createUrl}" class="dropdown-item"><i class="icon-head"></i> View Profile</a>
					<!-- <a href="#" class="dropdown-item"><i class="icon-head"></i>
						Edit Profile</a> <a href="#" class="dropdown-item"><i
						class="icon-mail6"></i> My Inbox</a> <a href="#" class="dropdown-item"><i
						class="icon-clipboard2"></i> Task</a> <a href="#"
						class="dropdown-item"><i class="icon-calendar5"></i> Calender</a> -->
					<!-- <div class="dropdown-divider"></div> -->
					<a href="<c:url value ='/logout'/>" class="dropdown-item"><i
						class="icon-power3"></i> Logout</a>
				</div>
			</li>
			
			

		</ul>
		<div style="clear: both;"></div>
	</div>
</div>