<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<h4 class="header">Edit serviceitem</h4>

<div class="row">
	<form:form class="col s12" method="POST" action="/serviceitem"
		modelAttribute="serviceitemForm">
		<form:input path="id" type="hidden" />


		<sec:authorize access="hasAnyRole('PASSENGER','ADMIN')">
			<div class="row">
				<div class="input-field col s12">
					<form:select path="driverId" type="text" disabled="${readonly}">
						<form:options items="${driverdChoices}" />
					</form:select>
					<form:errors path="driverId" cssClass="red-text" />
					<label for="driverId"><spring:message
							code="serviceitem.car" /></label>
				</div>
			</div>
		</sec:authorize>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="item" type="text" disabled="${readonly}">
					<form:options items="${serviceItemChoices}" />
				</form:select>
				<form:errors path="item" cssClass="red-text" />
				<label for="item"><spring:message code="serviceitem.item" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="summa" type="text" disabled="${readonly}" />
				<form:errors path="summa" cssClass="red-text" />
				<label for="summa"><spring:message code="serviceitem.summa" />»</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="comment" type="text" disabled="${readonly}" />
				<form:errors path="comment" cssClass="red-text" />
				<label for="comment"><spring:message
						code="serviceitem.comment" />»</label>
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
				<a class="btn waves-effect waves-light right" href="/serviceitem">
					<spring:message code="standard.tolist" />ƒƒ<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>
