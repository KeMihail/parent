<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:url value="/carOption" var="pageurl" />

<h4 class="header">
	<spring:message code="standard.options" />
</h4>

<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link column="id">
					<spring:message code="carOption.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="name">
					<spring:message code="carOption.name" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>

		<c:forEach var="carOption" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${carOption.id}" /></td>
				<td><c:out value="${carOption.name}" /></td>

				<td class="right"><a class="btn-floating"
					href="/carOption/${carOption.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="/carOption/${carOption.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/carOption/${carOption.id}/delete"><i
						class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/carOption/add"><i
	class="material-icons">add</i></a>
