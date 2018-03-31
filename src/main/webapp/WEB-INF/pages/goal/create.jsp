<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title><spring:message code="message.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Caveat" rel="stylesheet">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/static/icon.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
<div class="container">
    <!-- Navigation Bar -->
    <header>
        <nav class="navbar mynav">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/goal/">Just Do It</a>
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
                    <li><a href="${pageContext.request.contextPath}/j_spring_security_logout"><span class="glyphicon glyphicon-user"></span>
                        <spring:message code="message.exit"/>
                    </a></li>
                </ul>
            </div>
        </nav>
    </header>

    <!-- Main block -->
    <div id="main">
        <h1><spring:message code="message.goals.create_goal"/></h1>

        <div class="row main col-md-offset-2 col-md-8">
            <div class="main-login main-center">
                <div class="form-group">
                    <label for="name" class="cols-sm-2 control-label">
                        <spring:message code="message.goals.goal_name"/>
                    </label>
                    <div class="cols-sm-10">
                        <input type="text" class="form-control" id="name" max="255"
                               placeholder="<spring:message code="message.goal_name_example"/>"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="description" class="cols-sm-2 control-label">
                        <spring:message code="message.goals.description"/>
                    </label>
                    <div class="cols-sm-10">
                        <input type="text" class="form-control" id="description" maxlength="1000"
                               placeholder="<spring:message code="message.goal_description_example"/>"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="stage1" class="cols-sm-2 control-label">
                        <spring:message code="message.goals.stages"/>
                    </label>
                    <div class="cols-sm-10" id="stage1" >
                        <ol class="ol-stages">
                            <li class="li-stage">
                                <input type="text" class="form-control col-sm-9 stage" maxlength="255"
                                       placeholder="<spring:message code="message.stage.name"/>"/>
                                <ul class="ul-tasks">
                                    <input type="text" class="form-control col-sm-8 task" maxlength="255"
                                           placeholder="<spring:message code="message.task.name"/>"/>
                                </ul>
                            </li>
                        </ol>
                    </div>
                </div>

                <div class="error" style="display: none"><h5><spring:message code="message.valid.goal_create_error"/></h5></div>

                <div class="form-group ">
                    <button type="button" id="btnAddGoal" class="btn btn-primary btn-lg btn-block login-button"
                        disabled="true" >
                        <spring:message code="message.goal.add"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}">
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/resources/js/create.js"></script>
</body>
</html>