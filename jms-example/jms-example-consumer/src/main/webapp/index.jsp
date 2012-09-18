<%@page import="com.blogspot.nikcode.jms.util.ViewHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Messages</title>
    </head>
    <body>
        <h1>Messages</h1>
        <p><%= ViewHelper.getValuesAsString() %></p>
        <a href="javascript:location.reload(true)">Refresh this page</a>
    </body>
</html>
