<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Доктор: ${sessionScope.currentDoctor.fullName}</h1>
<c:url value="/doctor/presctiptions" var="prescriptionsUrl" />
<c:url value="/doctor/presctiptions/new" var="newPrescriptionUrl" />
<ul>

    <li><a href="${newPrescriptionUrl}">Новый рецепт</a></li>
    <li><a href="${prescriptionsUrl}">Мои рецепты</a></li>
</ul>