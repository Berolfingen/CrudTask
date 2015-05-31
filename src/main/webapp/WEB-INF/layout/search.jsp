<%@include file="taglib.jsp"%>
<%@page isELIgnored="false" %>
<h3>Search</h3>
<form action="/filter" method="POST" >
    <table class="table table-responsive table-condensed table-hover table-responsive">
        <tr>
            <td>Name</td>
            <td><input name="name" class="form-control" type="text"  value="${filter}"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit" name="action" value="Filter" class="btn btn-default btn-block" > <span aria-hidden="true"></span>Start search</button>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit" name="action" value="Clear" class="btn btn-default btn-block" > <span aria-hidden="true"></span>Clear</button>
            </td>
        </tr>
    </table>
</form>
