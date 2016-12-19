<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h3><s:message code="page.system"/></h3>
<hr/>
<s:message code="page.hello"/>!
<hr/>
<h3><s:message code="flight.table"/></h3>
</head>
<table>
    <form action="addflight" method="post">

        <div>
            <label for="flightDate"><s:message code="flight.date"/>:</label><br>
            <input type="date" name="flightDate" value="" required/>
        </div>

        <div>
            <label for="seats"><s:message code="flight.seats"/>:</label><br> <input
                type="text" name="seats" value="" required/>
        </div>
        <div>
            <label for="cost"><s:message code="flight.price"/>:</label><br> <input
                type="text" name="cost" value="" required/>
        </div>
        <div>
            <label for="upCost"><s:message code="flight.priceup"/></label> </br><select name="upCost">
            <option value="0">0 - <s:message code="flight.priceup.cant"/></option>
            <option value="1">1 - <s:message code="flight.priceup.can"/></option>
        </select>
        </div>
        <div>
            <input type="submit" value="<s:message code="flight.add"/>"/>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </div>
    </form>
</table>
<br>
<form method="get" action="">
    <td><s:message code="pagination.linesperpage"/>:</td>
    <td>
        <div>
            <select name="recordsPerPage">
                <option value="${recordsPerPage}"></option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="5">5</option>
            </select>
        </div>
    </td>
    <input type="submit" value="<s:message code="pagination.search"/>"/>
</form>
<table border="1">
    <thead align="center">
    <tr>
        <th><s:message code="flight.id"/></th>
        <th><s:message code="flight.date"/></th>
        <th><s:message code="flight.seats"/></th>
        <th colspan="2"><s:message code="action.action"/></th>
    </tr>
    </thead>
    <tbody align="center">
    <c:forEach items="${flights}" var="flight">
        <tr>
            <td><c:out value="${flight.id}"/></td>
            <td><c:out value="${flight.date}"/></td>
            <td><c:out value="${flight.seats}"/></td>
            <td>
                <form method="get" action="">
                    <input type="hidden" name="flight_id" value="${flight.id}">
                    <input type="hidden" name="currentPage" value="${currentPage}">
                    <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">
                    <input type="submit" value="<s:message code="flight.delete"/>"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<c:choose>
    <c:when test="${currentPage != 1}">
        <td><a href="main?currentPage=${currentPage - 1}&recordsPerPage=${recordsPerPage}"><s:message
                code="pagination.previous"/></a>
        </td>
        <td><a href="main?currentPage=1&recordsPerPage=${recordsPerPage}">1</a></td>
    </c:when>
    <c:when test="${currentPage == 1}">
        <td><s:message code="pagination.previous"/></td>
        <td>1</td>
    </c:when>
    <c:otherwise>
        <td></td>
    </c:otherwise>
</c:choose>
<td>
    <select onchange="location.href=this.value">
        <option>${currentPage}</option>
        <c:forEach begin="2" end="${numberOfPages-0}" var="i">
        <c:choose>
        <c:when test="${currentPage eq i}">
<td>${i}</td>
</c:when>
<c:otherwise>
    <option value="main?currentPage=${i}&recordsPerPage=${recordsPerPage}">${i}</option>
</c:otherwise>
</c:choose>
</c:forEach>
</select>
</td>
<c:choose>
    <c:when test="${currentPage lt numberOfPages}">
        <td>
            <a href="main?currentPage=${numberOfPages}&recordsPerPage=${recordsPerPage}">${numberOfPages}</a>
        </td>
        <td><a href="main?currentPage=${currentPage + 1}&recordsPerPage=${recordsPerPage}"><s:message
                code="pagination.next"/></a>
        </td>
    </c:when>
    <c:when test="${currentPage == numberOfPages}">
        <td>${numberOfPages}</td>
        <td><s:message code="pagination.next"/></td>
    </c:when>
    <c:otherwise>
        <td></td>
    </c:otherwise>
</c:choose>
<br>
<a href="/logout"><s:message code="page.logout"/></a>
</body>
</html>