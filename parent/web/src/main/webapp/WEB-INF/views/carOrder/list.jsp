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
	<spring:message code="standard.order" />
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
				<th></th>
			</sec:authorize>

			<sec:authorize access="hasAnyRole('ADMIN','PASSENGER')">
				<th><mytaglib:sort-link column="orderBegin">
						<spring:message code="order.orderBegin" />
					</mytaglib:sort-link></th>
				<th><mytaglib:sort-link column="orderEnd">
						<spring:message code="order.orderEnd" />
					</mytaglib:sort-link></th>
				<th><mytaglib:sort-link column="distanceOrder">
						<spring:message code="order.distanceOrder" />
					</mytaglib:sort-link></th>
				<th><mytaglib:sort-link column="distancePaid">
						<spring:message code="order.distancePaid" />
					</mytaglib:sort-link></th>
				<th><mytaglib:sort-link column="inactivityMinutes">
						<spring:message code="order.inactivityMinutes" />
					</mytaglib:sort-link></th>
				<th><mytaglib:sort-link column="total">
						<spring:message code="order.total" />
					</mytaglib:sort-link></th>
				<th><mytaglib:sort-link column="clientName">
						<spring:message code="order.clientName" />
					</mytaglib:sort-link></th>
				<th></th>
			</sec:authorize>
		</tr>

		<c:forEach var="carOrder" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>

				<sec:authorize access="hasAnyRole('DRIVER','PASSENGER')">
					<c:if test="${carOrder.driverId == null}">
						<td><c:out value="${carOrder.departureAddress}" /></td>
						<td><c:out value="${carOrder.arrivalAddress}" /></td>

						<td><a class="waves-effect waves-light btn"
							href="/carOrder/${carOrder.id}/edit"><i
								class="material-icons">ВЫПОЛНИТЬ</i></a></td>
					</c:if>
				</sec:authorize>

				<sec:authorize access="hasAnyRole('ADMIN','PASSENGER')">
					<td><c:out value="${carOrder.orderBegin}" /></td>
					<td><c:out value="${carOrder.orderEnd}" /></td>
					<td><c:out value="${carOrder.distanceOrder}" /></td>
					<td><c:out value="${carOrder.distancePaid}" /></td>
					<td><c:out value="${carOrder.inactivityMinutes}" /></td>
					<td><c:out value="${carOrder.total}" /></td>
					<td><c:out value="${carOrder.clientName}" /></td>

					<td class="right"><a class="btn-floating"
						href="/carOrder/${carOrder.id}"><i class="material-icons">info</i></a>
						<a class="btn-floating" href="/carOrder/${carOrder.id}/edit"><i
							class="material-icons">edit</i></a> <a class="btn-floating red"
						href="/carOrder/${carOrder.id}/delete"><i
							class="material-icons">delete</i></a>
				</sec:authorize>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<sec:authorize access="hasAnyRole('MANAGER','ADMIN','PASSENGER')">
	<a class="waves-effect waves-light btn right" href="/carOrder/add"><i
		class="material-icons">add</i></a>
</sec:authorize>
