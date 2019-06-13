<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:url value="/legalEntity" var="pageurl" />

<h4 class="header">
	<spring:message code="standard.legalEntyti" />
</h4>
<table class="bordered highlight">

	<tbody>

		<tr>
			<th><mytaglib:sort-link column="name">
					<spring:message code="legalEntyti.name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="address">
					<spring:message code="legalEntyti.address" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="phoneNumber">
					<spring:message code="legalEntyti.phoneNumber" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="email">
					<spring:message code="legalEntyti.email" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>

		<c:forEach var="legalEntity" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${legalEntity.name}" /></td>
				<td><c:out value="${legalEntity.address}" /></td>
				<td><c:out value="${legalEntity.phoneNumber}" /></td>
				<td><c:out value="${legalEntity.email}" /></td>

				<td class="right"><a class="btn-floating"
					href="/legalEntity/${legalEntity.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="/legalEntity/${legalEntity.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/legalEntity/${legalEntity.id}/delete"><i
						class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/legalEntity/add"><i
	class="material-icons">add</i></a>
