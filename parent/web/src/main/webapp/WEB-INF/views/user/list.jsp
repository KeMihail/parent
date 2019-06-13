<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:url value="/user" var="pageurl" />

<h4 class="header">
	<spring:message code="standard.people" />
</h4>
<table class="bordered highlight">

	<tbody>

		<tr>
			<th><mytaglib:sort-link column="name">
					<spring:message code="driver.name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="lastName">
					<spring:message code="driver.lastName" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="birthday">
					<spring:message code="driver.birthday" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="address">
					<spring:message code="driver.address" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="phoneNumber">
					<spring:message code="driver.phoneNumber" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="email">
					<spring:message code="driver.email" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>

		<c:forEach var="user" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${user.name}" /></td>
				<td><c:out value="${user.lastName}" /></td>
				<td><c:out value="${user.birthday}" /></td>
				<td><c:out value="${user.address}" /></td>
				<td><c:out value="${user.phoneNumber}" /></td>
				<td><c:out value="${user.email}" /></td>

				<td class="right"><a class="btn-floating"
					href="/user/${user.id}"><i class="material-icons">info</i></a> <a
					class="btn-floating" href="/user/${user.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/user/${user.id}/delete"><i class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/user/add"><i
	class="material-icons">add</i></a>
