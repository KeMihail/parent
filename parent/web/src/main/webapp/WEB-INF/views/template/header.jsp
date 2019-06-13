<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<header>
	<nav>
		<sec:authorize access="!isAnonymous()">
			<a class="right" href="/execute_logout"><i class="material-icons"></i></a>
		</sec:authorize>

		<ul id="nav-mobile" class="left hide-on-med-and-down">


			<sec:authorize access="isAnonymous()">
				<li><a href="/login">Войти</a></li>
			</sec:authorize>

			<sec:authorize access="hasRole('ADMIN')">

				<nav>
					<div class="nav-wrapper">
						<a href="#!" class="brand-logo center"></a>
						<ul class="left hide-on-med-and-down">
							<li><a href="/"><spring:message code="menu.home" /></a></li>
							<li><a href="/user"><spring:message code="menu.user" /></a></li>
							<li><a href="/driver"><spring:message code="menu.driver" /></a></li>
							<li><a href="/rate"><spring:message code="menu.rate" /></a></li>
							<li><a href="/carOrder"><spring:message
										code="menu.order" /></a></li>
							<li><a href="/serviceitem"><spring:message
										code="menu.service" /></a></li>
							<li><a class="waves-effect waves-light btn"
								href="/execute_logout">Выход</a></li>
						</ul>
					</div>
				</nav>

				<nav>
					<div class="nav-wrapper">
						<a href="#!" class="brand-logo center"></a>
						<ul class="left hide-on-med-and-down">
							<li><a href="/brand"><spring:message code="menu.brand" /></a></li>
							<li><a href="/model"><spring:message code="menu.model" /></a></li>
							<li><a href="/legalEntity"><spring:message
										code="menu.legalEntity" /></a></li>
							<li><a href="/carOption"><spring:message
										code="menu.carOption" /></a></li>
							<li><a href="/car"><spring:message code="menu.car" /></a></li>
							<li><a href="/orderAssessment"><spring:message
										code="menu.orderAssessment" /></a></li>

						</ul>
					</div>
				</nav>

			</sec:authorize>

			<sec:authorize access="hasRole('DRIVER')">
				<li><a href="/carOrder"><spring:message code="menu.order" /></a></li>
			</sec:authorize>

			<sec:authorize access="hasAnyRole('PASSENGER','DRIVER')">
				<li><a href="/carOrder/history"><spring:message
							code="order.history" /></a></li>
			</sec:authorize>

			<sec:authorize access="hasRole('PASSENGER')">
				<li><a href="/carOrder/add"><spring:message
							code="order.new" /></a></li>
				<li><a class="waves-effect waves-light btn"
					href="/execute_logout">Выход</a></li>
			</sec:authorize>

			<sec:authorize access="hasRole('DRIVER')">
				<li><a href="/serviceitem/expenses"><spring:message
							code="serviceitem.cost" /></a></li>
				<li><a href="/orderAssessment/commentsDriver"><spring:message
							code="assessment.comments" /></a></li>
				<li><a class="waves-effect waves-light btn"
					href="/execute_logout">Выход</a></li>
			</sec:authorize>

		</ul>
	</nav>
	<div class="white-text " style="position: absolute; top: 5; right: 60">
		<sec:authentication property="name" />
	</div>

	<div class="white-text " style="position: absolute; top: 30; right: 60">
		<sec:authentication property="authorities" var="roles" scope="page" />
		<c:forEach var="role" items="${roles}">
			${role}
		</c:forEach>
	</div>


	<div style="position: absolute; top: 20; right: 20">
		<a href="?lang=en">en</a> | <a href="?lang=ru">ru</a>
	</div>


</header>