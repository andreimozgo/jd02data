<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
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

    <form method="get" action="">
        <tr>
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
            <td>
                <input type="hidden" name="recordsPerPage" value="${recordsPerPage}"/>
                <input type="submit" value="<s:message code="pagination.search"/>"/>
            </td>
        </tr>
    </form>
    <form method="get" action="">
        <tr>
            <td>
                <div>
                    <label for="flightDate"><s:message code="flight.date"/>:</label><br>
                    <input type="date" name="flightDate"/>
                </div>
            </td>
            <td>
                <input type="hidden" name="flightDate" value="${flightDate}"/>
                <input type="submit" value="<s:message code="pagination.search"/>"/>
            </td>
        </tr>
    </form>
</table>
<br>
<table border="1">
    <thead align="center">
    <tr>
        <th><s:message code="flight.id"/></th>
        <th><s:message code="flight.date"/></th>
        <th><s:message code="flight.seats"/></th>
        <th><s:message code="flight.price"/></th>
        <th><s:message code="action.action"/></th>
    </tr>
    </thead>
    <tbody align="center">
    <c:forEach items="${flights}" var="flight">
        <tr>
            <td><c:out value="${flight.id}"/></td>
            <td><c:out value="${flight.date}"/></td>
            <td><c:out value="${flight.seats}"/></td>
            <td><c:out value="${flight.cost}"/></td>
            <td>
                <form method="post" action="buyticket">
                    <input type="hidden" name="flight_id" value="${flight.id}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" value="<s:message code="ticket.buy"/>"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:choose>
    <c:when test="${currentPage != 1}">
        <td><a href="user?currentPage=${currentPage - 1}&recordsPerPage=${recordsPerPage}"><s:message
                code="pagination.previous"/></a>
        </td>
        <td><a href="user?currentPage=1&recordsPerPage=${recordsPerPage}">1</a></td>
    </c:when>
    <c:when test="${currentPage == 1}">
        <td><s:message code="pagination.previous"/></td>
        <td>1</td>
    </c:when>
</c:choose>
<td>
    <select onchange="location.href=this.value">
        <option>${currentPage}</option>
        <c:forEach begin="2" end="${numberOfPages-1}" var="i">
        <c:choose>
        <c:when test="${currentPage eq i}">
<td>${i}</td>
</c:when>
<c:otherwise>
    <option value="user?currentPage=${i}&recordsPerPage=${recordsPerPage}">${i}</option>
</c:otherwise>
</c:choose>
</c:forEach>
</select>

</td>
<c:choose>
    <c:when test="${currentPage lt numberOfPages}">
        <td>
            <a href="user?currentPage=${numberOfPages}&recordsPerPage=${recordsPerPage}">${numberOfPages}</a>
        </td>
        <td><a href="user?currentPage=${currentPage + 1}&recordsPerPage=${recordsPerPage}"><s:message
                code="pagination.next"/></a>
        </td>
    </c:when>
    <c:when test="${currentPage == numberOfPages}">
        <td>${numberOfPages}</td>
        <td><s:message code="pagination.next"/></td>
    </c:when>
</c:choose>
<table border="1">
    <thead align="center">
    <tr>
        <th><s:message code="ticket.id"/></th>
        <th><s:message code="flight.id"/></th>
        <th><s:message code="ticket.baggage"/> (+35)</th>
        <th><s:message code="ticket.registration"/> (+10)</th>
        <th><s:message code="ticket.boarding"/> (+12)</th>
        <th><s:message code="ticket.price"/></th>
        <th><s:message code="ticket.state"/></th>
        <th><s:message code="action.action"/></th>
    </tr>
    </thead>
    <tbody align="center">
    <c:forEach items="${tickets}" var="ticket">
        <tr>
            <form method="post" action="recalculate">
                <td><c:out value="${ticket.id}"/></td>
                <td><c:out value="${ticket.flight.id}"/></td>
                <td>
                    <div>
                        <select name="baggage">
                            <option value="0"><s:message code="ticket.no"/></option>
                            <option value="1"><s:message code="ticket.yes"/></option>
                        </select>
                    </div>
                </td>
                <td>
                    <div>
                        <select name="priorityregistration">
                            <option value="0"><s:message code="ticket.no"/></option>
                            <option value="1"><s:message code="ticket.yes"/></option>
                        </select>
                    </div>
                </td>
                <td>
                    <div>
                        <select name="priorityboarding">
                            <option value="0"><s:message code="ticket.no"/></option>
                            <option value="1"><s:message code="ticket.yes"/></option>
                        </select>
                    </div>
                </td>
                <td><c:out value="${ticket.cost}"/></td>
                <c:choose>
                    <c:when test="${ticket.paid == 0}">
                        <td><font color="red"><s:message code="ticket.unpayed"/></font></td>
                    </c:when>
                    <c:when test="${ticket.paid == 1}">
                        <td><font color="green"><s:message code="ticket.payed"/></font></td>
                    </c:when>
                </c:choose>
                <td>
                    <input type="hidden" name="ticket_id" value="${ticket.id}">
                    <input type="hidden" name="baggage" value=value="${baggage}">
                    <input type="hidden" name="priorityregistration" value=value="${priorityregistration}">
                    <input type="hidden" name="priorityboarding" value=value="${priorityboarding}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" value="<s:message code="ticket.recalculate"/>"/>
            </form>

            <form method="post" action="payticket">
                <input type="hidden" name="ticket_id" value="${ticket.id}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="<s:message code="ticket.pay"/>"/>
            </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<a href="/logout"><s:message code="page.logout"/></a>

</body>
</html>