<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>登录</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/sell/css/signin.css" rel="stylesheet">
</head>

<body class="text-center">
<form class="form-signin" method="post" action="/sell/seller/login">
    <img class="mb-4" src="/sell/img/bootstrap-solid.svg" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">请登录</h1>
    <label class="sr-only">用户名</label>
    <input type="text" class="form-control" name="username" required="" autofocus="">
    <label></label>
    <label class="sr-only">密码</label>
    <input type="password" class="form-control" name="password" required="">
    <label></label>

    <#--			<div class="checkbox mb-3">-->
    <#--				<label>-->
    <#--          <input type="checkbox" value="remember-me"> 记住我-->
    <#--        </label>-->
    <#--			</div>-->
    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
</form>

</body>

</html>