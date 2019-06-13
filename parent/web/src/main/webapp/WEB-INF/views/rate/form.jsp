<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h4 class="header">Edit rate</h4>
<div class="row">

	<form:form class="col s12" method="POST" action="/rate"
		modelAttribute="rateForm">
		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name"><spring:message code="rate.name" /></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="priceLanding" type="text" disabled="${readonly}" />
				<form:errors path="priceLanding" cssClass="red-text" />
				<label for="priceLanding"><spring:message
						code="rate.priceLanding" /></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="priceKilometr" type="text" disabled="${readonly}" />
				<form:errors path="priceKilometr" cssClass="red-text" />
				<label for="width"><spring:message code="rate.priceKilometr" /></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="priceMinuteWait" type="text"
					disabled="${readonly}" />
				<form:errors path="priceMinuteWait" cssClass="red-text" />
				<label for="width"><spring:message
						code="rate.priceMinuteWait" /></label>
			</div>
		</div>
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
				<a class="btn waves-effect waves-light right" href="/rate"> <spring:message
						code="standard.tolist" />ƒ<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>
