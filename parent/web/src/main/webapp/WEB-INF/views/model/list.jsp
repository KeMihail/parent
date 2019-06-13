
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:url value="/model" var="pageurl" />

<h4 class="header">
	<spring:message code="standard.model" />
</h4>
<table class="bordered highlight">

	<tbody>

		<tr>
			<th><mytaglib:sort-link column="name">
					<spring:message code="model.name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="carKit">
					<spring:message code="model.carKit" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="engineType">
					<spring:message code="model.engineType" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="BodyType">
					<spring:message code="model.bodyType" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="brandName">
					<spring:message code="model.brand" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>

		<c:forEach var="model" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${model.name}" /></td>
				<td><c:out value="${model.carKit}" /></td>
				<td><c:out value="${model.engineType}" /></td>
				<td><c:out value="${model.bodyType}" /></td>
				<td><c:out value="${model.brandName}" /></td>

				<td class="right"><a class="btn-floating"
					href="/model/${model.id}"><i class="material-icons">info</i></a> <a
					class="btn-floating" href="/model/${model.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/model/${model.id}/delete"><i class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/model/add"><i
	class="material-icons">add</i></a>
