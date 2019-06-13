<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div style="margin: 10px;">

	<script src="/resources/js/init-combos.js"></script>


	<script>
		var latestId = <c:out value="${latestCarOrderId}"/>;

		setInterval(function() {
			$.get("/carOrder/lastId", function(lastIdFromServer) {

				if (latestId < lastIdFromServer) {
					Materialize.toast('Someone created a new order!!!', 4000) // simple popup message using Materialize framework
					latestId = lastIdFromServer;
				}
			})
		}, 5 * 1000);
	</script>
</div>