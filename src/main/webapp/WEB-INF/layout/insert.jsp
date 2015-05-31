<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp" %>
<h3>Add/Update</h3>
<form:errors path="user.*" class="text-warning"/>
<form:form action="/user.do" method="POST" commandName="user">
    <table class="table table-responsive table-condensed table-hover table-responsive">
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Age</td>
        </tr>
        <tr>
            <td width="20"><form:input path="id" class="form-control disabled" disabled="true"/></td>
            <td width="90"><form:input path="name" class="form-control"/></td>
            <td width="40"><form:input path="age" class="form-control"/></td>
        </tr>
        <tr>
            <td>Admin</td>
            <td>
                <form:radiobutton
                        path="isAdmin"
                        id="true"
                        value="true"
                        />
                <label for="true">yes</label>
                <form:radiobutton
                        path="isAdmin"
                        id="false"
                        value="false"
                        />
                <label for="false">no</label>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <button type="submit" name="action" value="Add" class="btn btn-default btn-block"><span
                        aria-hidden="true"></span> Save to DB
                </button>

            </td>
        </tr>
    </table>
</form:form>