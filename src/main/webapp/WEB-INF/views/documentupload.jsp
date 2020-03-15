<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Document Upload</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
	// Add the following code if you want the name of the file appear on select
	$(".custom-file-input").on(
			"change",
			function() {
				var fileName = $(this).val().split("\\").pop();
				$(this).siblings(".custom-file-label").addClass("selected")
						.html(fileName);
			});
</script>
</head>
<body>
	<div class="container">
		<br />
		<h3>Upload your document..</h3>
		<br />
		<form action="upload" method="post" enctype="multipart/form-data">

			<div class="form-group">
				<label>Enter ID:</label> <input type="text" class="form-control"
					name="id" required />
			</div>

			<div class="form-group">
				<label>Document :</label> <input
					type="file" class="form-control" name="document" required />
			</div>  
            <br />
			<div>
				<button type="submit" class="btn btn-success">Upload</button>
			</div>

		</form>
		<br/>
		
		<table class="table table-striped">
			<thead>
				<tr>
				    <th>ID</th>
					<th>NAME</th>
					<th>Link</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${documents}" var="document">

					<tr>
						<td>${document.id}</td>
						<td>${document.name}</td>
						<td><a href="/uploaddocument/download?id=${document.id}" class="btn btn-success">Download</a></td> 
					</tr>

				</c:forEach>

			</tbody>

		</table>
		

	</div>
</body>
</html>