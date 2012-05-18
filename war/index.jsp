<%@ page language="java" contentType="text/html;"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<link type="text/css"
		href="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.3/bootstrap.min.js"
		rel="stylesheet">
	<title>Personal Image Repository</title>
</head>
<body>
	<!-- <h1><s:property value="debug" /></h1> -->
	<h1>Welcome to your personal image repository, <s:property value="user.user.nickname" /></h1>
	<div>
		<s:iterator value="user.images">
			<div>
				<h2><s:property value="title" /> (Rating: <s:property value="rating" />/5) (<a href="/s/delete?key=<s:property value="keyString" />">Delete</a>)</h2>
				<img src="/image?key=<s:property value="imageKey" />" />
			</div>
		</s:iterator>
	</div>
	
	<div>
		<h2>Upload new image</h2>
		<form action="<s:property value="submitUrl" />" method="post"
			accept-charset="utf-8" enctype="multipart/form-data">
			
			<p><label for="title">Title</label><input type="text" name="title" value="" id="title">
			</p>
			
			<p><label for="rating">Rating</label>
				<select name="rating" id="rating">
					<option value="5" selected="selected">5</option>
					<option value="4">4</option>
					<option value="3">3</option>
					<option value="2">2</option>
					<option value="1">1</option>
				</select>
			</p>
			
			<p><label for="image">Image</label><input type="file" name="image" value="" id="image">
			</p>

			<p><input type="submit" value="Upload"></p>
		</form>
	</div>
	
	<div><a href="<s:property value="logoutUrl" />">Logout</a></div>
</body>
</html>
