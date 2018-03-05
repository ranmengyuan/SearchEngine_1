<%@ page language="java" import="java.util.*" %>
<%@ page pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--<link rel="stylesheet" type="text/css" href="styles.css">-->
	    <style type="text/css">
    #input{
      border-radius:40px;
      width:30%;
      height:25px;
      font-size:100%;
    
    }
    #search{
    border-radius:40px;
    height:25px;
    font-size:100%;
    }
    
    </style>
  </head>
  <body>
  <div id="layer3"style="position:absolute;overflow: hidden;top:0;left:0;width: 40%;
	  	height: 20%;z-index: 0">
	  	<img src="234940-13011Z9314435.jpg" style="width:35%">
  </div>
  	<div id="layer1" style="position:absolute;overflow: hidden;top:10%;left:40%;width: 100%;
	  	height: 10%;z-index: 0">
	     <form action="servlet/Manage" method="post">
	        <input type="text" name="keyword" style="width:50%" id="input">
	        <input type="submit" value="Search" id="search">
	      </form> 
  	</div>
  	<div id="layer2" style="position: absolute; overflow: auto;top:20%;left:0;height: 80%;
	  	width: 100%;z-index: 0;background-color:#b4b5a7">
	<%
	       ArrayList<String> url  = (ArrayList<String>)session.getAttribute("url");//接受severlet传入的两个线性表
	       ArrayList<String> title  = ( ArrayList<String>)session.getAttribute("title");
	       
	%>  
	  <%  if(url.size()==0){//判断是否搜到结果
	    %>
	       <h3 align="center">无结果！</h3>
	    <% 
	     }
	     %> 
	      <%
	      for(int i=0;i<url.size();i++) {
	       %>
	        <a href=<%=url.get(i) %>><%=title.get(i) %></a><br/>
	         
	       <%
	       
	       }
	        %>
      </div>
  </body>
</html>
