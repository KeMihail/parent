<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<h4 class="header">Edit car</h4>

<div class="row">
	<form:form class="col s12" method="POST" action="/car"
		modelAttribute="carForm">
		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:input path="releaseYear" type="text" disabled="${readonly}" />
				<form:errors path="releaseYear" cssClass="red-text" />
				<label for="releaseYear"><spring:message
						code="car.releaseYear" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="modelId" type="text" disabled="${readonly}">
					<form:options items="${modelChoices}" />
				</form:select>
				<form:errors path="modelId" cssClass="red-text" />
				<label for="modelId"><spring:message code="car.model" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="legalEntityId" type="text" disabled="${readonly}">
					<form:options items="${legalEntityChoices}" />
				</form:select>
				<form:errors path="legalEntityId" cssClass="red-text" />
				<label for="legalEntityId"><spring:message
						code="car.legalEntity" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="status" type="text" disabled="${readonly}">
					<form:options items="${statusChoices}" />
				</form:select>
				<form:errors path="status" cssClass="red-text" />
				<label for="status"><spring:message code="car.status" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="carOptionId" multiple="true" type="text"
					disabled="${readonly}">
					<form:options items="${carOptionChoices}" />
				</form:select>
				<form:errors path="carOptionId" cssClass="red-text" />
				<label for="carOptionId"><spring:message code="car.options" /></label>
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
				<a class="btn waves-effect waves-light right" href="/car"> <spring:message
						code="standard.tolist" /><i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>
