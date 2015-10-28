<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Search</title>
</head>
<body>
<form action="search">
  <input name="q" value="${q}"/>
  <select name="sort">
      <c:forEach var="st" items="${sortTypes}">
          <option ${st.name() == sort ? 'selected' : ''} value="${st.name()}">${st.name()}</option>
      </c:forEach>
  </select>
  <button>Search</button>
</form>
<div>
  <c:forEach var="i" items="${result.pages}">
    <a href="${i.url}" target="_blank">${i.title}</a>
    <div>${i.highlight}</div>
    <br />
  </c:forEach>
    <ul>
        <c:forEach var="i" begin="1" end="${result.maxPages}">
            <li><a href="?q=${q}&skip=${(i - 1) * result.perPage}">${i}</a></li>
        </c:forEach>
    </ul>

</div>
</body>
</html>
