<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title><spring:message code="message.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/static/icon.png" type="image/png">

    <link href="https://fonts.googleapis.com/css?family=Caveat" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
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
                            <span class="glyphicon glyphicon-cog"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a id="a-delete-goal" href="#"><spring:message code="message.goal.delete"/> </a></li>
                        </ul>
                    </li>
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

    <div id="main">
        <div id="goal-name">
        </div>
        <div>
            <a id="a-edit-page">
                <span class="glyphicon glyphicon-pencil"></span>
                <spring:message code="message.goal.edit"/>
            </a>
        </div>
        <div id="goal-description">
            <h2><spring:message code="message.goals.description"/></h2>
        </div>
        <div id="stage-list">
            <h2><spring:message code="message.goals.stages"/> </h2>
            <div id="no_stages" style="display: none">
                <h3><spring:message code="message.goals.no_stages"/></h3>
            </div>
            <div class="no_tasks" style="display: none">
                <h3><spring:message code="message.goal.no_tasks"/> </h3>
            </div>
            <ol id="ol-stages-list">
            </ol>
        </div>
    </div>
</div>

<input id="goal-id" type="hidden">
<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}">
<input id="not_started" type="hidden" value="<spring:message code="message.goal.not_started"/>">
<input id="in_progress" type="hidden" value="<spring:message code="message.goal.in_progress"/>">
<input id="finished" type="hidden" value="<spring:message code="message.goal.finished"/>">
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/resources/js/show.js"></script>
</body>
</html>