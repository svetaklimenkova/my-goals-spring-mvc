function searchGoalById(path) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: path + '/get',
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            if (data !== null) {
                viewGoal(data);
            }
        },
        error: function (e) {
            console.log("Error");
        }
    });
}

function deleteGoalById(path) {
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: path,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            if (data !== null) {
                window.location.href = $('#contextPath').val() + '/goal/';
            }
        },
        error: function (e) {
            console.log(e.toString());
        }
    });
}

function updateStage(stage) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: $('#contextPath').val() + '/stage/' + stage.id,
        data: JSON.stringify(stage),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("Stage state is updated.")
        },
        error: function (e) {
            console.log(e.toString());
        }
    });
}

function updateTask(task) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: $('#contextPath').val() + '/task/' + task.id,
        data: JSON.stringify(task),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("Task state is updated.")
        },
        error: function (e) {
            console.log(e.toString());
        }
    });
}

function updateGoalState(goal) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: $('#contextPath').val() + '/goal/' + goal.id + '/state',
        data: JSON.stringify(goal),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("Goal state is updated.")
        },
        error: function (e) {
            console.log(e.toString());
        }
    });
}

function viewGoal(goal) {
    console.log(goal);
    $('#goal-name').append('<h1>' + goal.name + '</h1>');
    $('#goal-description').append('<p>' + goal.description + '</p>');

    $('#goal-id').val(goal.id);

    var stages = goal.stages;

    if (stages.length === 0) {
        if ($('#no_stages').css('display') === 'none') {
            $('#no_stages').animate({height: 'show'}, 1000);
        }
    } else {
        for (var i = 0; i < stages.length; i++) {
            var tasks = stages[i].tasks;

            var li = $('<li name="' + stages[i].id + '" class="stage ' + stages[i].state.toLowerCase()
                + '">' + stages[i].name + '</li>');
            var ul = $('<ul></ul>');
            for (var j = 0; j < tasks.length; j++) {
                ul.append($('<li name="' + tasks[j].id + '" class="task ' + tasks[j].state.toLowerCase()
                    + '">' + tasks[j].name + '</li>'));
            }

            if (tasks.length === 0) {
                ul.append($('.no_tasks').clone(true));
                ul.find('.no_tasks').fadeIn();
            }

            li.append(ul);
            $('#ol-stages-list').append(li);
        }
    }
}

$(document).ready(function () {
    var path = document.location.pathname;
    searchGoalById(path);

    $('#a-edit-page').attr('href', path + '/edit');
    $('#a-delete-goal').click(function (event) {
        deleteGoalById(path);
    });

    $('#ol-stages-list').on('click', '.task', function (event) {
        var liStage = $($($(this).parent()).parent());

        var task = {};
        task['id'] = $(this).attr('name');

        var stage = {};
        stage['id'] = liStage.attr('name');

        if ($(this).hasClass('not_started')) {
            $(this).removeClass('not_started').addClass('in_progress');
            task['state'] = 'IN_PROGRESS';
        } else if ($(this).hasClass('in_progress')) {
            $(this).removeClass('in_progress').addClass('finished');
            task['state'] = 'FINISHED';
        } else if ($(this).hasClass('finished')) {
            $(this).removeClass('finished').addClass('not_started');
            task['state'] = 'NOT_STARTED';
        }

        updateTask(task);

        if(liStage.find('.in_progress').length === 0 && liStage.find('.finished').length === 0) {
            stage['state'] = 'NOT_STARTED';
        } else if(liStage.find('.in_progress').length === 0 && liStage.find('.not_started').length === 0) {
            stage['state'] = 'FINISHED';
        } else {
            stage['state'] = 'IN_PROGRESS';
        }

        if(liStage.hasClass('not_started')) {
            liStage.removeClass('not_started');
        } else if (liStage.hasClass('in_progress')) {
            liStage.removeClass('in_progress');
        } else if (liStage.hasClass('finished')) {
            liStage.removeClass('finished');
        }
        liStage.addClass(stage.state.toLowerCase());

        updateStage(stage);

        var goal = {};
        goal['id'] = $('#goal-id').val();

        if($('#ol-stages-list').find('.in_progress').length === 0 && $('#ol-stages-list').find('.finished').length === 0) {
            goal['state'] = 'NOT_STARTED';
        } else if($('#ol-stages-list').find('.in_progress').length === 0 && $('#ol-stages-list').find('.not_started').length === 0) {
            goal['state'] ='FINISHED';
        } else {
            goal['state'] = 'IN_PROGRESS';
        }

        updateGoalState(goal);
    });
});