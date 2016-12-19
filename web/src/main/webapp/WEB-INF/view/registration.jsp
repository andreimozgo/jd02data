<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title><s:message code="page.login.reg"/></title>
</head>
<body>
<div class="header">
    <div style="float: left;">
        <table>
            <tr>
                <td style="padding: 5px;">
                    <a href="?locale=ru">Ru</a>
                </td>
                <td style="padding: 5px;">
                    <a href="?locale=en">En</a>
                </td>
            </tr>
        </table>
    </div>
</div>
<br>
<h4><s:message code="page.login.regwelcome"/></h4>
<form method="POST" action="addRegistration">
    <div>
        <label for="login"><s:message code="page.login.login"/>:</label><br> <input type="text"
                                                                                    name="login" value="" required/>
    </div>

    <div>
        <label for="password"><s:message code="page.login.password"/>:</label><br> <input type="text"
                                                                                          name="password" value=""
                                                                                          required/>
    </div>
    <div>
        <input type="submit" value="<s:message code="page.login.register"/>"/>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </div>
</form>
</body>
</html>