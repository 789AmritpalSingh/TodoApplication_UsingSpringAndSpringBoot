<%@ include file = "common/navigation.jspf" %>
<%@ include file = "common/header.jspf" %>
<div class = "container">
    <h1> Welcome ${name}</h1>
    <a href="list-todos">Manage</a> your todos

    <!--<div>Your name is : ${name}</div> -->
    <!--<div>Your password is : ${password}</div>-->
    <!-- we are not putting password in welcome page as it is not a good practice-->
</div>
<%@ include file = "common/footer.jspf" %>
