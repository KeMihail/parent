<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:url value="/rate" var="pageurl" />

<h4 class="header">
	<spring:message code="standard.rate" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link column="name">
					<spring:message code="rate.name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="priceLanding">
					<spring:message code="rate.priceLanding" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="priceKilometr">
					<spring:message code="rate.priceKilometr" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="priceMinuteWait">
					<spring:message code="rate.priceMinuteWait" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="rate" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${rate.name}" /></td>
				<td><c:out value="${rate.priceLanding}" /></td>
				<td><c:out value="${rate.priceKilometr}" /></td>
				<td><c:out value="${rate.priceMinuteWait}" /></td>

				<td class="right"><a class="btn-floating"
					href="/rate/${rate.id}"><i class="material-icons">info</i></a> <a
					class="btn-floating" href="/rate/${rate.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="/rate/${rate.id}/delete"><i class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<mytags:paging />
<a class="waves-effect waves-light btn right" href="/rate/add"><i
	class="material-icons">add</i></a>
