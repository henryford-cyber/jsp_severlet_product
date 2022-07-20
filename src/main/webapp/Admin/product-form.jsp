<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!DOCTYPE html>
<html lang="en"> 
<head> 
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>List User</title>  
  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/Admin/vendors/typicons/typicons.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/Admin/vendors/css/vendor.bundle.base.css"> 
  <link rel="stylesheet" href="<%=request.getContextPath()%>/Admin/css/style.css"> 
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/Admin/images/favicon.png" />
</head>
<body> 
  <div class="container-scroller">  
    <jsp:include page="partials/navbar.jsp"/>
     
    <div class="container-fluid page-body-wrapper">
      
     <jsp:include page="partials/slidebar.jsp"/>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">   
           
			<div class="container col-md-5">
    <div class="card" style="width: 250%;
    right: 75%;">
        <div class="card-body">
            <c:if test="${prd != null}">
            <form action="updateProduct" method="post">
                </c:if>
                <c:if test="${prd == null}">
                <form action="insertProduct" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${prd != null}">
                                Edit User
                            </c:if>
                            <c:if test="${prd == null}">
                                Add New Product
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${prd != null}">
                        <input type="hidden" name="id" value="<c:out value='${prd.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Product Name</label>
                         <input type="text" value="<c:out value='${prd.name}' />" 
                         class="form-control" name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Product price </label> 
                        <input type="number" value="<c:out value='${prd.price}' />" 
                        class="form-control" name="price">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Image</label> 
                        <input type="text" value="<c:out value='${prd.image}' />" 
                        class="form-control" name="image">
                    </fieldset>
                    
                    <fieldset class="form-group">
                        <label>Category</label>
                         <input type="text" value="<c:out value='${prd.category_id}' />" 
                        class="form-control" name="category_id">
                           
                   <%-- <select name="category_id">
                    	<c:forEach var="category" items="${listCategory}"> 
                			<option value="${category.id}">value="${category.id}"</option>
                		 
                	</c:forEach>
                    	 
                    </select>--%> 
                 
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
           

        </div> 
      </div>
      <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  
  <script src="<%=request.getContextPath()%>/Admin/vendors/js/vendor.bundle.base.js"></script>
   
  <script src="<%=request.getContextPath()%>/Admin/vendors/chart.js/Chart.min.js"></script>
   
  <script src="<%=request.getContextPath()%>/Admin/js/off-canvas.js"></script>
  <script src="<%=request.getContextPath()%>/Admin/js/hoverable-collapse.js"></script>
  <script src="<%=request.getContextPath()%>/Admin/js/template.js"></script>
  <script src="<%=request.getContextPath()%>/Admin/js/settings.js"></script>
  <script src="<%=request.getContextPath()%>/Admin/js/todolist.js"></script>
   
  <script src="<%=request.getContextPath()%>/Admin/js/dashboard.js"></script>
</body>

</html>

 