<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerPort()+ path + "/";
%>
<html>
<head>
    <base href="<%=path%>">
    <title>上传文件</title>
</head>
<body>
<!--利用form表单提交文件的上传-->
<%--<form action="upload" method="post" enctype="multipart/form-data">--%>
<form enctype="multipart/form-data" id="batchUpload"  action="upload" method="post" class="form-horizontal">
    <input type="file" name="file">
    <input type="text" name="name">
    <input type="submit" value="上传">
</form>
</body>
</html>