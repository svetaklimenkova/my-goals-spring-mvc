<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title><spring:message code="message.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/static/icon.png" type="image/png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Project CSS -->
    <link href="https://fonts.googleapis.com/css?family=Caveat" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login_form.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
<div class="container">
    <!-- Navigation Bar -->
    <header>
        <nav class="navbar mynav">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                        <spring:message code="message.brand"/>
                    </a>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <spring:message code="message.language"/>
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="?lang=ru">RU</a></li>
                            <li><a href="?lang=en">EN</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <!-- Main Block -->
    <div id="main">
        <div class="row main col-md-offset-3 col-md-6">
            <div class="main-login main-center">
                    <div class="form-group">
                        <label for="login" class="cols-sm-2 control-label">
                            <spring:message code="message.username"/>
                        </label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon invalid input-check">
                                    <i class="glyphicon glyphicon-remove" data-toggle="tooltip" data-placement="auto left"
                                       title="<spring:message code="message.valid.incorrect_login"/>"></i>
                                </span>
                                <input type="text" class="form-control" name="name" maxlength="254" id="login"
                                       placeholder="<spring:message code="message.username"/>"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="cols-sm-2 control-label">
                            <spring:message code="message.mail"/>
                        </label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon invalid input-check">
                                    <i class="glyphicon glyphicon-remove" data-toggle="tooltip" data-placement="auto left"
                                       title="<spring:message code="message.valid.incorrect_mail"/>"></i>
                                </span>
                                <input type="text" class="form-control" name="email" id="email" maxlength="254"
                                       placeholder="<spring:message code="message.mail"/>"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="cols-sm-2 control-label">
                            <spring:message code="message.password"/>
                        </label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon invalid input-check">
                                    <i class="glyphicon glyphicon-remove" data-toggle="tooltip" data-placement="auto left"
                                       title="<spring:message code="message.valid.incorrect_password"/>"></i>
                                </span>
                                <input type="password" class="form-control" name="password" id="password" maxlength="36"
                                       placeholder="<spring:message code="message.password"/>"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="confirm" class="cols-sm-2 control-label">
                            <spring:message code="message.confirm_password"/>
                        </label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon invalid input-check">
                                    <i class="glyphicon glyphicon-remove" data-toggle="tooltip" data-placement="auto left"
                                       title="<spring:message code="message.valid.incorrect_password_confirm"/>"></i>
                                </span>
                                <input type="password" class="form-control" name="confirm" id="confirm" maxlength="36"
                                       placeholder="<spring:message code="message.confirm_password"/>"/>
                            </div>
                        </div>
                    </div>

                    <h5 class="error" style="display: none"><spring:message code="message.valid.used_mail"/></h5>
                    <div class="form-group ">
                        <button type="button" disabled="true" id="btn_sign_up" class="btn btn-primary btn-lg btn-block login-button">
                            <spring:message code="message.sign_up"/>
                        </button>
                    </div>
            </div>
        </div>
    </div>

</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/js/signUp.js"></script>
</body>
</html>

