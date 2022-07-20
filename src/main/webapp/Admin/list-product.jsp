<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>List User</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Admin/vendors/typicons/typicons.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Admin/vendors/css/vendor.bundle.base.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Admin/css/style.css">
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/Admin/images/favicon.png" />
</head>
<body>
	<div class="container-scroller">
		<jsp:include page="partials/navbar.jsp" />

		<div class="container-fluid page-body-wrapper">

			<jsp:include page="partials/slidebar.jsp" />
			<!-- partial -->
			<div class="main-panel">
				<div class="content-wrapper">


					<div class="row">
						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Product list</h4>
									<div class="table-responsive">
										<table class="table table-striped table-bordered ">
											<thead>
												<tr>
													<th>ID</th>
													<th>Name</th>
													<th>Price</th>
													<th>Image</th>
													<th>Category</th>
													<th>Actions</th>
													<th>Actions</th>
												</tr>
											</thead>
											<tbody>

												<c:forEach var="product" items="${listProduct}">

													<tr>
														<td><c:out value="${product.id}" /></td>
														<td><c:out value="${product.name}" /></td>
														<td><c:out value="${product.price}" /></td>
														<td><c:out value="${product.image}" /></td>

														<td><c:forEach var="category" items="${listCategory}">
																<c:if test="${product.category_id == category.getId()}">
																	<c:out value="${category.getName()}"></c:out>
																</c:if>
															</c:forEach></td>
														<td><a class="btn btn-primary"
															href="editProduct?id=${product.id}">Edit</a>
															 &nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														<td><a class="btn btn-danger"
															href="deleteProduct?id=${product.id}">Delete</a>
														</td>
													</tr>
												</c:forEach>
												<!-- } -->
											</tbody>
											<a href="<%=request.getContextPath()%>/newProduct"
												class="btn btn-success">Add New Product</a>
										</table>

									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			 
		</div>
		 
	</div>

	<script
		src="<%=request.getContextPath()%>/Admin/vendors/js/vendor.bundle.base.js"></script>

	<script
		src="<%=request.getContextPath()%>/Admin/vendors/chart.js/Chart.min.js"></script>

	<script src="<%=request.getContextPath()%>/Admin/js/off-canvas.js"></script>
	<script
		src="<%=request.getContextPath()%>/Admin/js/hoverable-collapse.js"></script>
	<script src="<%=request.getContextPath()%>/Admin/js/template.js"></script>
	<script src="<%=request.getContextPath()%>/Admin/js/settings.js"></script>
	<script src="<%=request.getContextPath()%>/Admin/js/todolist.js"></script>

	<script src="<%=request.getContextPath()%>/Admin/js/dashboard.js"></script>
</body>

</html>

