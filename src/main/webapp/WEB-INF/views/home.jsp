<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//malsup.github.com/jquery.form.js"></script>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	INputArea  
</h1>

<input type="text" name="message" id="message"></input>
			    
	<button id="Click" type="submit">입력</button>
	
	
<script>
$(document).ready(function () {
	$("#Click").click(function() {
    var message=$("#message").val();
    alert(message);
		$.ajax({
            //var Status = $("#url").val();
			url: 'receivemessage',
			type: 'post',
			data : {
					message : message,
				},
			dataType : "json",
			success : function(data) {
				console.log(data.result.orgUrl);
			}   	
    });
 });
});
</script>

