<%-- 
    Document   : header
    Created on : Jul 15, 2024, 7:39:42 AM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/" />">e-Commerce Admin</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/" />">Trang chủ</a>
                </li>
                
                <c:forEach items="${categories}" var="c">
                    <c:url value="/" var="cPath">
                        <c:param name="cateId" value="${c.id}" />
                    </c:url>
                    <li class="nav-item">
                        <a class="nav-link" href="${cPath}">${c.name}</a>
                    </li>
                </c:forEach>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/stats" />">
                        Thống kê
                    </a>
                </li>
                <s:authorize access="hasAnyRole('USER', 'ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/" />">
                            <s:authentication property="principal.username" />
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-danger" href="<c:url value="/logout" />">
                           Đăng xuất
                        </a>
                    </li>
                </s:authorize>
            </ul>
        </div>
    </div>
</nav>
