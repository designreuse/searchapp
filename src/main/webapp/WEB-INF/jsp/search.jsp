<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
          integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
          crossorigin="anonymous">
    <title>Search</title>
</head>
<body>
<div class="container-fluid">
    <div class="col-md-12">
        <div class="row page-header">
            <form action="search">
                <div class="col-md-3">
                    <input class="form-control" name="q" value="${q}" placeholder="Search for..."/>
                </div>
                <div class="col-md-1">
                    <select name="sort" class="form-control">
                        <c:forEach var="st" items="${sortTypes}">
                            <option ${st.name() == sort ? 'selected' : ''}
                                    value="${st.name()}">${st.name()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-1">
                    <button class="form-control btn btn-default">Search</button>
                </div>
            </form>
        </div>
        <div class="row">
            <ul class="list-group">
                <c:forEach var="i" items="${result.pages}">
                    <li class="list-group-item" style="border: none;">
                        <h4><a href="${i.url}" target="_blank">${i.title}</a></h4>
                        <div>${i.highlight}</div>
                    </li>
                </c:forEach>
            </ul>

        </div>
        <div class="row">
            <ul class="pagination">
                <c:forEach var="i" begin="1" end="${result.maxPages}">
                    <li ${(i - 1) * result.perPage == result.skip ? 'class="active"' : ''}><a
                            href="?q=${q}&skip=${(i - 1) * result.perPage}">${i}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

</body>
</html>
