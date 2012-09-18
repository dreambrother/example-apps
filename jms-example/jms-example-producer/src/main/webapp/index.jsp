<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Send JMS message</title>
    </head>
    <body>
        <h1>Message text</h1>
        <form method="POST" action="send">
            <input type="text" name="msg"/>
            <select name="type">
                <option>Queue</option>
                <option>Topic</option>
            </select>
            <input type="submit"/>
        </form>
    </body>
</html>
