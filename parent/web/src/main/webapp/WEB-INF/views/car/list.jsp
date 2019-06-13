<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:url value="/cars" var="pageurl" />

<h4 class="header">
	<spring:message code="standard.car" />
</h4>
<table class="bordered highlight">

	<tbody>
		<tr>
			<th><mytaglib:sort-link column="releaseYear">
					<spring:message code="car.releaseYear" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="modelName">
					<spring:message code="car.model" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="legalEntityName">
					<spring:message code="car.legalEntity" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="status">
					<spring:message code="car.status" />
				</mytaglib:sort-link></th>

			<th></th>
		</tr>

		<c:forEach var="car" items="${listModel.list}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${car.releaseYear}" /></td>
				<td><c:out value="${car.modelName}" /></td>
				<td><c:out value="${car.legalEntityName}" /></td>
				<td><c:out value="${car.status}" /></td>

				<td class="right"><a class="btn-floating" href="/car/${car.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="/car/${car.id}/edit"><i class="material-icons">edit</i></a> <a
					class="btn-floating red" href="/car/${car.id}/delete"><i
						class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/car/add"><i
	class="material-icons">add</i></a>
