<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<h4 class="header">Edit model</h4>

<div class="row">
	<form:form class="col s12" method="POST" action="/model"
		modelAttribute="modelForm">
		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name"><spring:message code="model.name" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="carKit" type="text" disabled="${readonly}">
					<form:options items="${carKitChoices}" />
					<form:errors path="carKit" cssClass="red-text" />
				</form:select>
				<label for="carCit"><spring:message code="model.carKit" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="engineType" type="text" disabled="${readonly}">
					<form:options items="${engineTypeChoices}" />
					<form:errors path="engineType" cssClass="red-text" />
				</form:select>
				<label for="engineType"><spring:message
						code="model.engineType" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="bodyType" type="text" disabled="${readonly}">
					<form:options path="bodyType" items="${bodyTypeChoices}"
						cssClass="red-text" />
				</form:select>
				<label for="BodyType"><spring:message code="model.bodyType" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="brandId" disabled="${readonly}">
					<form:options items="${brandChoices}" />
				</form:select>
				<form:errors path="brandId" cssClass="red-text" />
				<label for="brandId"><spring:message code="model.brand" /></label>
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
				<a class="btn waves-effect waves-light right" href="/model"><spring:message
						code="standard.tolist" />ƒ<i class="material-icons right"></i> </a>
			</div>
		</div>
	</form:form>
</div>
