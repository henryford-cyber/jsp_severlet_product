<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <title>List Product</title>
  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
        <link type="text/css" rel="stylesheet" href="css/pro.css">
</head>
<body>

<header>
  <nav class="navbar navbar-expand-md navbar-dark">  
    <ul class="navbar-nav">
      <li><a class="btn btn-success" href=" " >Users</a></li>
    </ul>
  </nav>
</header>
<br>

<div class="row">
  <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

  <div class="container">
    <h3 id="ok" class="text-center ok" >List of Product</h3>
    <hr>
    <div class="container text-left">

      <a href="/Pro-Admin/ProductController?action=new" class="btn btn-success">Add
        New Product</a>
    </div>
    <br>
    <table class="table table-bordered">
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
          
          <td>
          			<c:forEach var="category" items="${listCategory}">
                		<c:if test="${product.category_id == category.getId()}">
                			<c:out value="${category.getName()}"></c:out>
                		</c:if>
                	</c:forEach></td>
          <td>
          	<a href="/Pro-Admin/ProductController?action=edit&id=${product.id}">Edit</a> 
          </td>
          <td>
          	<a href="/Pro-Admin/ProductController?action=delete&id=${product.id}">Delete</a>
          	</td>
        </tr>
      </c:forEach>
      <!-- } -->
      </tbody>

    </table>
  </div>
</div>
</body>
</html>
