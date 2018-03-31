function editGoal() {
    var goal = {};

    goal["id"] = stripTags($("#idGoal").val());
    goal["name"] = stripTags($("#name").val());
    goal["description"] = stripTags($("#description").val());

    var stages = [];
    var stageItems = $('.ol-stages');
    var countStages = 0;

    $.each(stageItems[0].children, function (key, value) {
        if (stripTags($(value).find('.stage').val()) !== "") {
            var stage = {};

            stage['id'] = stripTags($(value).find('.stage-id').val());
            stage['name'] = stripTags($(value).find('.stage').val());
            stage['order'] = countStages;

            var tasks = [];
            var countTasks = 0;

            $.each($(value).find('.li-task'), function (key, value) {
                if (stripTags($(value).find('.task').val()) !== "") {
                    var task = {};
                    task['id'] = stripTags($(value).find('.task-id').val());
                    task['name'] = stripTags($(value).find('.task').val());
                    tasks[countTasks] = task;
                    countTasks++;
                }
            });

            stage['tasks'] = tasks;
            stages[countStages] = stage;
            countStages++;
        }
    });

    goal['stages'] = stages;
    console.log(goal);


    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: $('#contextPath').val() + '/goal/' + goal.id,
        data: JSON.stringify(goal),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            if (data !== null) {
                window.location.href = $('#contextPath').val() + '/goal/' + goal.id;
            }
        },
        error: function (e) {
            console.log(e.toString());
        }
    });
}

function searchGoalById(path) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: path + '/get',
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log(data);
            if (data !== null) {
                viewGoal(data);
            }
        },
        error: function (e) {
            console.log(e.toString());
        }
    });
}

function viewGoal(goal) {
    $('#idGoal').val(goal.id);
    $('#name').val(goal.name);
    $('#description').val(goal.description);
    $('#idOwner').val(goal.idOwner);

    var stages = goal.stages;

    for (var i = 0; i < stages.length; i++) {
        $('.ol-stages').append($($('.li-stage')[0]).clone(true));
    }

    var stageNum = 0;
    $('.ol-stages').children().each(function () {
        if (stageNum >= stages.length) {
            return false;
        }

        $(this).find('.stage-id').val(stages[stageNum].id);
        $(this).find('.stage').val(stages[stageNum].name);

        var tasks = stages[stageNum].tasks;

        var liTask = $(this).find('.li-task').clone(true);
        for (var i = 0; i < tasks.length; i++) {
            console.log("append");
            $(this).find('.ul-tasks').append($($(this).find('.li-task')[0]).clone(true));
        }

        var taskNum = 0;
        $(this).find('.li-task').each(function () {
            if (taskNum >= tasks.length) {
                return false;
            }

            $(this).find('.task-id').val(tasks[taskNum].id);
            $(this).find('.task').val(tasks[taskNum].name);
            taskNum++;
        });

        stageNum++;
    });
}

$(document).ready(function () {
    var path = document.location.pathname;
    searchGoalById(path);

    $('#btnSaveGoal').click(function (event) {
        editGoal();
    });

    $('#name, #description').on("change paste keyup", function () {
        if ($('#name').val() === "" || $('#description').val() === '') {
            $('#btnSaveGoal').prop('disabled', true);
        } else {
            $('#btnSaveGoal').prop('disabled', false);
        }
    })

    $('.ul-tasks').on("change paste keyup", '.li-task', function () {
        if (stripTags($(this).find('.task').val()) !== "") {
            if ($(this).next('.li-task').length === 0) {
                var li = $(this).clone(true);
                li.find('.task-id').val(-1);
                li.find('.task').val("");
                $(this).after(li);
            }
        } else {
            while ($(this).next('.li-task').length !== 0
            && stripTags($(this).next('.li-task').find('.task').val()) === "") {
                $(this).next('li').remove();
            }
        }
    });

    $('.ol-stages').on("change paste keyup", '.stage', function () {
        if (stripTags($(this).val()) !== "") {
            if ($(this).parent().next('.li-stage').length === 0) {
                var li = $(this).parent().clone(true);

                li.children().val("");
                while(li.find('.li-task').length !== 1) {
                    li.find('.li-task')[0].remove();
                }

                li.find('.stage-id').val(-1);
                li.find('.task').val("");
                $(this).parent().after(li);
            }
        } else {
            while ($(this).parent().next('.li-stage').length !== 0
            && stripTags($(this).parent().next('.li-stage').find('.stage').val()) === "") {
                if ($(this).parent().next('.li-stage').find('.task').length === 1) {
                    $(this).parent().next('.li-stage').remove();
                } else {
                    break;
                }
            }
        }
    });
});

function stripTags(str, allow) {
    allow = (((allow || "") + "").toLowerCase().match(/<[a-z][a-z0-9]*>/g) || []).join('');

    var tags = /<\/?([a-z][a-z0-9]*)\b[^>]*>/gi;
    var commentsAndPhpTags = /<!--[\s\S]*?-->|<\?(?:php)?[\s\S]*?\?>/gi;
    return str.replace(commentsAndPhpTags, '').replace(tags, function ($0, $1) {
        return allow.indexOf('<' + $1.toLowerCase() + '>') > -1 ? $0 : '';
    });
}