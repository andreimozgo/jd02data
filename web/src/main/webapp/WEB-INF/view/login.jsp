<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Login</title>
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
<h3><s:message code="page.login.welcome1"/></h3>
<h4><s:message code="page.login.welcome2"/></h4>
<form name="loginForm" method="POST" action="<c:url value='/j_spring_security_check' />">
    <td><s:message code="page.login.login"/>:</td>
    <br/>
    <input type='text' name='login' value=""/>
    <br/>
    <td><s:message code="page.login.password"/>:</td>
    <br/>
    <input type="password" name="password" value=""/>
    <br/><br/>
    <input type="submit" value="<s:message code="page.login"/>"/>
    <br/>
    <a href="/registration"><s:message code="page.login.reg"/></a>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>
<hr/>
</body>
</html>