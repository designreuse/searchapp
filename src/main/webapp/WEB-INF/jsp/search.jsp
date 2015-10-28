<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Search</title>
</head>
<body>
<form action="search">
  <input name="q"/>
  <button>Search</button>
</form>
<div>
  <c:forEach var="i" items="${result}">
    <a href="${i.url}" target="_blank">${i.title}</a>
    <div>${i.highlight}</div>
    <br />
  </c:forEach>
</div>
</body>
</html>
