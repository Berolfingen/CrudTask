<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@include file="/WEB-INF/layout/taglib.jsp"%>
<h3>Users</h3>
<c:url var="firstUrl" value="/pages/1" />
<c:url var="lastUrl" value="/pages/${pageData.totalPages}" />
<c:url var="prevUrl" value="/pages/${currentIndex - 1}" />
<c:url var="nextUrl" value="/pages/${currentIndex + 1}" />

<table class="table table-bordered table-striped table-condensed table-hover table-responsive">
    <th>Id</th>
    <th>Name</th>
    <th>Age</th>
    <th>Admin</th>
    <th width="80">Date created</th>
    <th>Edit</th>
    <th>Delete</th>
    <c:forEach items="${pageData.content}" var="userInfo">
        <tr>
            <td>${userInfo.id}</td>
            <td>${userInfo.name}</td>
            <td>${userInfo.age}</td>
            <td>${userInfo.isAdmin}</td>
            <td>${userInfo.createdDate}</td>
            <td><a href="/user.do?id=${userInfo.id}&action=Search"><button type="submit"class="btn btn-default btn-block" formaction="user.do?id=${userInfo.id}"> <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span></button></a></td>
            <td><a href="/user.do?id=${userInfo.id}&action=Delete"><button type="submit" class="btn btn-default btn-block" formaction="user.do?id=${userInfo.id}"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></button></a></td>
        </tr>
    </c:forEach>
</table>



<div class="pagination">
    <div class="btn-group" role="group" aria-label="...">
        <c:choose>
            <c:when test="${currentIndex == 1}">
                <a href="#"><button type="button" class="btn btn-default disabled" >&lt;&lt;</button></a>
                <a href="#"><button type="button" class="btn btn-default disabled" >&lt;</button></a>
            </c:when>
            <c:otherwise>
                <a href="${firstUrl}"><button type="button" class="btn btn-default">&lt;&lt;</button></a>
                <a href="${prevUrl}"><button type="button" class="btn btn-default">&lt;</button></a>
            </c:otherwise>
        </c:choose>

        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:url var="pageUrl" value="/pages/${i}" />
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <a href="${pageUrl}"><button type="button" class="btn btn-default active" ><c:out value="${i}" /></button></a>
                </c:when>
                <c:otherwise>
                    <a href="${pageUrl}"><button type="button" class="btn btn-default"><c:out value="${i}" /></button></a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${currentIndex == pageData.totalPages}">
                <a href="#"><button type="button" class="btn btn-default disabled">&gt;</button></a>
                <a href="#"><button type="button" class="btn btn-default disabled">&gt;&gt;</button></a>
            </c:when>
            <c:otherwise>
                <a href="${nextUrl}"><button type="button" class="btn btn-default">&gt;</button></a>
                <a href="${lastUrl}"><button type="button" class="btn btn-default">&gt;&gt;</button></a>
            </c:otherwise>
        </c:choose>
    </div>
</div>