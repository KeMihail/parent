<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
<h2>Login with Username and Password</h2>
<c:if test="${not empty error}">
	<div class="error">${error}</div>
</c:if>
<c:if test="${not empty msg}">
	<div class="msg">${msg}</div>
</c:if>
<div class="row">
	<form class="col s12 loginform" name='loginForm'
		action="<c:url value='login' />" method='POST'>
		
		<p>Admin: email miha@tut.by, password 4038</p>

		<p>Driver: email mihaila4038@yandex.ru, password LU7CNL9BP</p>
		<p>Driver: email mihaila1984@yandex.ru, password 3GVTF</p>

		<p>PASSENGER: email Ivan@tut.by, password ALP1YXA6T</p>
		<p>PASSENGER: email Petrov@tut.by, password 0FCGIEZRU</p>
		

		<div class="row">
			<div class="input-field col s12 center">
				<input type='text' name='username' value=''> <label
					class="active" for="username">User:</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12 center">
				<input type='password' name='password' /><label class="active"
					for="password">Password:</label>
			</div>
		</div>
		<div class="row">
			<div class="col s12 center">
				<button class="btn waves-effect waves-light " type="submit">войти</button>
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />



	</form>