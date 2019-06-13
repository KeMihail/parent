<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="pagination">
	<c:choose>
		<c:when test="${listModel.firstPage}">
			<li class="disabled"><a class="material-icons"><i>chevron_left</i></a></li>
		</c:when>
		<c:otherwise>
			<li class="waves-effect"><a class="material-icons"
				href="${pageurl}?page=${listModel.page-1}"><i>chevron_left</i></a></li>
		</c:otherwise>
	</c:choose>
	<c:forEach begin="1" end="${listModel.pageCount}" varStatus="loop">
		<c:choose>
			<c:when test="${loop.index == listModel.page}">
				<li class="active"><a>${loop.index}</a></li>
			</c:when>
			<c:otherwise>
				<li class="waves-effect"><a
					href="${pageurl}?page=${loop.index}">${loop.index}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${listModel.lastPage}">
			<li class="disabled"><a class="material-icons"><i>chevron_right</i></a></li>
		</c:when>
		<c:otherwise>
			<li class="waves-effect"><a class="material-icons"
				href="${pageurl}?page=${listModel.page+1}"><i>chevron_right</i></a></li>
		</c:otherwise>
	</c:choose>
</ul>
