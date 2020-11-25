<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>springboot学习</title>
    <link rel=”baiicon” href=”/favicon.ico” mce_href=”/favicon.ico” type=”image/x-icon”>
    <link rel=”shortcut icon” href=”/favicon.ico” mce_href=”/favicon.ico” type=”image/x-icon”>
</head>
<body>
<c:forEach items="${list}" var="item" varStatus="vs">
    <h1>电阻</h1>
    <p>电阻id：${item.id}</p>
    <p>名称：${item.name}</p>
    <a> 图标</a>
    <tr>
        <td>
            <img src="http://localhost:9002/view/picture/12.ico">
        </td>>
    </tr>>
</c:forEach>
</body>
</html>
