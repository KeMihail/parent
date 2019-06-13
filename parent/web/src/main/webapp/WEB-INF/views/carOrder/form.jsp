<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<h4 class="header">Edit CarOrder</h4>

<div class="row">
	<form:form class="col s12" method="POST" action="/carOrder"
		modelAttribute="carOrderForm">
		<form:input path="id" type="hidden" />

		<sec:authorize access="hasRole('PASSENGER')">
			<form:input path="clientId" type="hidden" />

			<div class="row">
				<div class="input-field col s12">
					<form:input path="departureAddress" type="text"
						disabled="${readonly}" />
					<form:errors path="departureAddress" cssClass="red-text" />
					<label for="departureAddress"><spring:message
							code="order.departureAddress" /></label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:input path="arrivalAddress" type="text"
						disabled="${readonly}" />
					<form:errors path="arrivalAddress" cssClass="red-text" />
					<label for="arrivalAddress"><spring:message
							code="order.arrivalAddress" /></label>
				</div>
			</div>
		</sec:authorize>

		<c:if test="${show}">
			<div class="row">
				<div class="input-field col s12">
					<form:input path="total" type="text" disabled="${readonly}" />
					<form:errors path="total" cssClass="red-text" />
					<label for="arrivalAddress"><spring:message
							code="order.total" /></label>
				</div>
			</div>
		</c:if>


		<sec:authorize access="hasAnyRole('DRIVER','ADMIN')">
			<form:input path="departureAddress" type="hidden" />
			<form:input path="arrivalAddress" type="hidden" />
			<form:input path="clientId" type="hidden" />
			<form:input path="orderBegin" type="hidden" />

			<form:input path="driverId" type="hidden" />

			<div class="row">
				<div class="input-field col s12">
					<form:input path="distanceOrder" type="text" disabled="${readonly}" />
					<form:errors path="distanceOrder" cssClass="red-text" />
					<label for="distanceOrder"><spring:message
							code="order.distanceOrder" /></label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<form:input path="distancePaid" type="text" disabled="${readonly}" />
					<form:errors path="distancePaid" cssClass="red-text" />
					<label for="distancePaid"><spring:message
							code="order.distancePaid" /></label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:input path="inactivityMinutes" type="text"
						disabled="${readonly}" />
					<form:errors path="inactivityMinutes" cssClass="red-text" />
					<label for="inactivityMinutes"><spring:message
							code="order.inactivityMinutes" /></label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:select path="rateId" type="text" disabled="${readonly}">
						<form:options items="${rateChoices}" />
					</form:select>
					<form:errors path="rateId" cssClass="red-text" />
					<label for="rateId"><spring:message code="order.rate" /></label>
				</div>
			</div>

		</sec:authorize>

		<sec:authorize access="hasAnyRole('ADMIN','PASSENGER','DRIVER')">
			<div class="row">
				<div class="col s6"></div>
				<div class="col s3">
					<c:if test="${!readonly}">
						<button class="btn waves-effect waves-light right" type="submit">
							<spring:message code="standard.save" />
						</button>
					</c:if>
				</div>

				<div class="col s3">
					<a class="btn waves-effect waves-light right" href="/carOrder"><spring:message
							code="standard.tolist" /><i class="material-icons right"></i> </a>
				</div>
			</div>
		</sec:authorize>
	</form:form>
</div>
