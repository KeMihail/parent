<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib"
	uri="https://journaldev.com/jsp/tlds/mytags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<spring:url value="/orderAssessment" var="pageurl" />

<h4 class="header">
	<spring:message code="standard.comments" />
</h4>

<table class="bordered highlight">
	<tbody>
		<tr>
			<sec:authorize access="hasRole('ADMIN')">
				<th><mytaglib:sort-link column="orderId">
						<spring:message code="assessment.order" />
					</mytaglib:sort-link></th>
				<th></th>
			</sec:authorize>

			<th><mytaglib:sort-link column="assessment">
					<spring:message code="assessment.assessment" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="comment">
					<spring:message code="assessment.comment" />
				</mytaglib:sort-link></th>
		</tr>

		<c:forEach var="orderAssessment" items="${listModel.list}"
			varStatus="loopCounter">
			<tr>
				<sec:authorize access="hasRole('ADMIN')">
					<td><c:out value="${orderAssessment.orderId}" /></td>
				</sec:authorize>

				<td><c:out value="${orderAssessment.assessment}" /></td>
				<td><c:out value="${orderAssessment.comment}" /></td>

				<sec:authorize access="hasRole('ADMIN')">
					<td class="right"><a class="btn-floating"
						href="/orderAssessment/${orderAssessment.orderId}"><i
							class="material-icons">info</i></a> <a class="btn-floating"
						href="/orderAssessment/${orderAssessment.orderId}/edit"><i
							class="material-icons">edit</i></a> <a class="btn-floating red"
						href="/orderAssessment/${orderAssessment.orderId}/delete"><i
							class="material-icons">delete</i></a></td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</tbody>
</table>

<sec:authorize access="hasRole('DRIVER')">
	<h5>
		<spring:message code="assessment.averagemark" />
		${averageMark}
	</h5>
</sec:authorize>


<mytags:paging />

