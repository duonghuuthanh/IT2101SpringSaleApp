<%-- 
    Document   : products
    Created on : Jul 8, 2024, 11:24:42 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-primary mt-1">QUẢN LÝ SẢN PHẨM</h1>
<c:url value="/products" var="action" />
<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>
<form:form method="post" enctype="multipart/form-data" action="${action}" modelAttribute="product">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    
    <div class="mb-3 mt-3">
        <label for="name" class="form-label">Tên sản phẩm:</label>
        <form:input path="name" type="text" class="form-control" id="name" placeholder="Tên sản phẩm..." name="name" />
        
    </div>
    <div class="mb-3 mt-3">
        <label for="price" class="form-label">Gía sản phẩm:</label>
        <form:input path="price" type="number" class="form-control" id="price" placeholder="Gía sản phẩm..." name="price" />
    </div>
    <div class="mb-3 mt-3">
        <label for="file" class="form-label">Ảnh sản phẩm:</label>
        <form:input path="file" type="file" accept=".jpg,.png" class="form-control" id="file" name="file" />
        <c:if test="${product.image != null}">
            <img class="mt-1" src="${product.image}" alt="${product.image}" width="120" />
        </c:if>
    </div>
    <div class="mb-3 mt-3">
        <label for="browser" class="form-label">Danh mục: </label>
        <form:select class="form-select" path="categoryId" >
            <c:forEach items="${categories}" var="c">
                <c:choose>
                    <c:when test="${c.id == product.categoryId.id}">
                        <option value="${c.id}" selected>${c.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.name}</option>
                    </c:otherwise>
                </c:choose>
                
            </c:forEach>
        </form:select>
    </div>
    <div class="mb-3 mt-3">
        <form:hidden path="id" />
        <form:hidden path="image" />
        <button class="btn btn-success" type="submit">
            
            <c:choose>
                <c:when test="${product.id != null}">
                    <option value="${c.id}" selected>Cập nhật sản phẩm</option>
                </c:when>
                <c:otherwise>
                    Thêm sản phẩm
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>

