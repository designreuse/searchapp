<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
    <title>Search</title>
</head>
<body>
<div style="display: flex; justify-content: center; align-items: center; height: 100vh">
    <form action="search" style="width: 30%">
        <div class="input-group">
            <input class="form-control" name="q"/>
            <span class="input-group-btn">
                <button class="btn btn-default">Search</button>
            </span>
        </div>
    </form>
</div>
</body>
</html>