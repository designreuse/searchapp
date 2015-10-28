<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Indexing</title>
</head>
<body>
<form action="/index" method="post">
    <input name="q" type="text"/>
    <input type="number" name="depth" value="3"/>
    <button type="submit">Index</button>
</form>
</body>
</html>