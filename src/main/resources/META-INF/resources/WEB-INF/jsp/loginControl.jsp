<html>
<head>
    <link href  ="webjars\bootstrap\5.1.3\css\bootstrap.min.css" rel = "stylesheet">
    <title>Login Controller Web page</title>
</head>
<body>
<div class="container">
    <h1>Login</h1>
    <pre>${errorMessage}</pre> <!-- displaying the message before going back to login page-->
    <form method="post">
        Name: <input type="text" name="name">
        Password: <input type="password" name="password">
        <input type="submit">
    </form>
</div>
<script src = "webjars\bootstrap\5.1.3\js\bootstrap.min.js"></script>
<script src = "webjars\jquery\3.6.0\jquery.min.js"></script>

</body>
</html>