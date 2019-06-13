<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:url value="/brand" var="pageurl" />

<h4 class="header">
	<spring:message code="brand.list" />
</h4>

<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link column="id">
					<spring:message code="brand.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="name">
					<spring:message code="brand.name" />
				</mytaglib:sort-link></th>

			<th></th>
		</tr>

		<c:forEach var="brand" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${brand.id}" /></td>
				<td><c:out value="${brand.name}" /></td>

				<td class="right"><a class="btn-floating"
					href="/brand/${brand.id}"><i class="material-icons">info</i></a> <a
					class="btn-floating" href="/brand/${brand.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/brand/${brand.id}/delete"><i class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/brand/add"><i
	class="material-icons">add</i></a>
