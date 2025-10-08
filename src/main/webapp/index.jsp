<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">

		<form class="form-horizontal" action="/transport-management/login"
			method="post">
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-6">
					<h2>Login form</h2>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="email">Email:</label>
				<div class="col-sm-6">
					<input type="email" class="form-control" id="email"
						placeholder="Enter email" name="email">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="pwd">Password:</label>
				<div class="col-sm-6">
					<input type="password" class="form-control" id="pwd"
						placeholder="Enter password" name="pwd">
				</div>
			</div>
			<div class="form-group">
				<div class="control-label col-sm-4">
					<button type="submit" class="btn btn-default">Submit</button>
				</div>
				<div class="col-sm-6">
					<a href="/transport-managenent/registration" class="form-control">user registration</a>
				</div>
			</div>
		</form>
	</div>

</body>
</html>
