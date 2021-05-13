<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>login</h1>

<c:if test="${requestScope.errorMessage != null}">
<div class="error">${requestScope.errorMessage}</div>
</c:if>

<form:form modelAttribute="loginForm">
    <div>
        <div>Login</div>
        <div><form:input path="username"/></div>
        <div>Password</div>
        <div><form:password path="password" name="password"/></div>
        <div><input type="submit" value="Sign in"/></div>
    </div>
</form:form>