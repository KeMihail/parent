<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<spring:url value="/serviceitem" var="pageurl" />

<h4 class="header">
	<spring:message code="standard.cost" />
</h4>
<table class="bordered highlight">

	<tbody>
		<tr>

			<sec:authorize access="hasRole('ADMIN')">
				<th><mytaglib:sort-link column="carName">
						<spring:message code="serviceitem.car" />
					</mytaglib:sort-link></th>
			</sec:authorize>

			<th><mytaglib:sort-link column="item">
					<spring:message code="serviceitem.item" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="summa">
					<spring:message code="serviceitem.summa" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="comment">
					<spring:message code="serviceitem.comment" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>

		<c:forEach var="serviceitem" items="${listModel.list}"
			varStatus="loopCounter">

			<tr>
				<sec:authorize access="hasRole('ADMIN')">
					<td><c:out value="${serviceitem.carName}" /></td>
				</sec:authorize>

				<td><c:out value="${serviceitem.item}" /></td>
				<td><c:out value="${serviceitem.summa}" /></td>
				<td><c:out value="${serviceitem.comment}" /></td>

				<td class="right"><a class="btn-floating"
					href="/serviceitem/${serviceitem.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="/serviceitem/${serviceitem.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/serviceitem/${serviceitem.id}/delete"><i
						class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<sec:authorize access="hasRole('DRIVER')">
	<h5>
		<spring:message code="serviceitem.all" />
		${allExpenses}
	</h5>
</sec:authorize>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/serviceitem/add"><i
	class="material-icons">add</i></a>
