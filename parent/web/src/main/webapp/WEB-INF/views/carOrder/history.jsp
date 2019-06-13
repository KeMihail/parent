<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:url value="/carOrder" var="pageurl" />

<h4 class="header">
	<spring:message code="menu.order" />
</h4>

<table class="bordered highlight">

	<tbody>

		<tr>
			<sec:authorize access="hasAnyRole('DRIVER','PASSENGER')">
				<th><mytaglib:sort-link column="departureAddress">
						<spring:message code="order.departureAddress" />
					</mytaglib:sort-link></th>
				<th><mytaglib:sort-link column="arrivalAddress">
						<spring:message code="order.arrivalAddress" />
					</mytaglib:sort-link></th>
				<th><mytaglib:sort-link column="orderBegin">
						<spring:message code="order.date" />
					</mytaglib:sort-link></th>
				<th><mytaglib:sort-link column="total">
						<spring:message code="order.total" />
					</mytaglib:sort-link></th>
			</sec:authorize>

			<sec:authorize access="hasRole('PASSENGER')">
				<th><mytaglib:sort-link column="driverName">
						<spring:message code="order.driverName" />
					</mytaglib:sort-link></th>
				<th></th>
			</sec:authorize>

			<sec:authorize access="hasRole('DRIVER')">
				<th><mytaglib:sort-link column="clientName">
						<spring:message code="order.clientName" />
					</mytaglib:sort-link></th>
				<th></th>
			</sec:authorize>


			<c:forEach var="carOrder" items="${listModel.list}"
				varStatus="loopCounter">
				<tr>

					<sec:authorize access="hasAnyRole('DRIVER','PASSENGER')">
						<td><c:out value="${carOrder.departureAddress}" /></td>
						<td><c:out value="${carOrder.arrivalAddress}" /></td>
						<td><c:out value="${carOrder.orderBegin}" /></td>
						<td><c:out value="${carOrder.total}" /></td>
					</sec:authorize>

					<sec:authorize access="hasRole('PASSENGER')">
						<td><c:out value="${carOrder.driverName}" /></td>
						<td><a class="waves-effect waves-light btn"
							href="/orderAssessment/${carOrder.id}/orderComment"><i
								class="material-icons">КОМЕНТАРИЙ</i></a></td>
					</sec:authorize>

					<sec:authorize access="hasRole('DRIVER')">
						<td><c:out value="${carOrder.clientName}" /></td>
					</sec:authorize>
				</tr>
			</c:forEach>
	</tbody>
</table>
<sec:authorize access="hasRole('DRIVER')">
	<h5>
		<spring:message code="driver.revenue" />
		${result}
	</h5>
</sec:authorize>
<mytags:paging />
