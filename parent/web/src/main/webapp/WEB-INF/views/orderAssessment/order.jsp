<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link column="assessment">
					<spring:message code="assessment.assessment" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="comment">
					<spring:message code="assessment.comment" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>

		<tr>
			<td><c:out value="${orderAssessment.assessment}" /></td>
			<td><c:out value="${orderAssessment.comment}" /></td>

			<c:if test="${orderAssessment.orderId == null}">
				<td><a class="waves-effect waves-light btn right"
					href="/orderAssessment/add"><i class="material-icons">add</i></a></td>
			</c:if>
			<c:if test="${orderAssessment.orderId == null}">
				<td class="right"><a class="btn-floating red"
					href="/orderAssessment/${orderAssessment.orderId}/delete"><i
						class="material-icons">delete</i></a></td>
			</c:if>
		</tr>
	</tbody>
</table>
<mytags:paging />
