<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
    <h1>Title : ${title}</h1>   
    <h1>${message}</h1>
    <h2>Options: 
        <a href="<c:url value="/hello" />" >Home</a> | 
        <a href="<c:url value="/admin" />" >Administration</a> | 
        <a href="<c:url value="/super" />" >Super user</a>
        <c:if test="${pageContext.request.userPrincipal.name != null}"> |
            <a href="<c:url value="/logout" />" >Logout</a> 
        </c:if>
    </h2>   
</body>
</html>