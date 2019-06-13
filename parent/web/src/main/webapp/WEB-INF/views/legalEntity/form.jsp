<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<h4 class="header">Edit legalEntyti</h4>

<div class="row">

	<form:form class="col s12" method="POST" action="/legalEntity"
		modelAttribute="legalEntityForm">
		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name"><spring:message code="legalEntyti.name" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="address" type="text" disabled="${readonly}" />
				<form:errors path="address" cssClass="red-text" />
				<label for="address"><spring:message
						code="legalEntyti.address" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="phoneNumber" type="text" disabled="${readonly}" />
				<form:errors path="phoneNumber" cssClass="red-text" />
				<label for="phoneNumber"><spring:message
						code="legalEntyti.phoneNumber" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="email" type="text" disabled="${readonly}" />
				<form:errors path="email" cssClass="red-text" />
				<label for="email"><spring:message code="legalEntyti.email" /></label>
			</div>
		</div>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">
						<spring:message code="standard.save" />
						Œ
					</button>
				</c:if>
			</div>

			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="/legalEntity">
					<spring:message code="standard.tolist" /><i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>
