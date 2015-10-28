<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
    <title>Indexing</title>
</head>
<body>
<div style="display: flex; justify-content: center; align-items: center; height: 100vh">
    <form action="search" style="width: 30%">
        <div>
            <input class="form-control" style="width: 70%; display: inline" name="q"/>
            <input class="form-control" style="width: 10%; display: inline" type="number" name="depth" value="3"/>
            <button class="form-control" style="width: 10%; display: inline" type="submit">Index</button>
        </div>
    </form>
</div>
</body>
</html>