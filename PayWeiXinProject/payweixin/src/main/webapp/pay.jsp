<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/27
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/paymentServlet">
    <%--注意：实际开发中这里的内容是在服务器获取的，这里应该给服务器传递一个id让服务器去查找--%>
    购买的内容:<input name="body"><br>
    <input type="submit" value="购买">
</form>

</body>
</html>
