<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>测试国际化</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
   	<!-- 测试测试自定义参数 对应action 方法ChangeLanguage2() languageName-->
   	<a href="<%=basePath%>Change!ChangeLanguage2?languageName=zw">中文简体</a>  
   	<a href="<%=basePath%>Change!ChangeLanguage2?languageName=en">English</a>
   	
   	<p>
   	<s:label key="language"/><input name="language">
   	<s:label key="name"/><input name="name"> 
   	<s:label key="age"/><input name="age">  
    <s:label key="sex"/><input name="sex">
    <br/>
  </body>
</html>
