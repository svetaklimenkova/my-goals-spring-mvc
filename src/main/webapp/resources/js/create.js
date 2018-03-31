function addGoal() {
    var goal = {};

    goal["name"] = stripTags($("#name").val());
    goal["description"] = stripTags($("#description").val());
    goal['creationDate'] = new Date();

    var stages = [];
    var stageItems = $('.ol-stages');
    var countStages = 0;

    $.each(stageItems[0].children, function (key, value) {
        if (stripTags($(value).find('.stage').val()) !== "") {
            var stage = {};
            stage['name'] = stripTags($(value).find('.stage').val());
            stage['order'] = countStages;

            var tasks = [];
            var countTasks = 0;

            $.each($(value).find('.task'), function (key, value) {
                if (stripTags($(value).val()) !== "") {
                    var task = {};
                    task['name'] = stripTags($(value).val());
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

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: $('#contextPath').val() + '/goal',
        data: JSON.stringify(goal),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            if (data !== null) {
                window.location.href = $('#contextPath').val() + '/goal/' + data;
            } else {
                $('.error').css('display', 'block');
                console.log("Error creating the goal!");
            }
        },
        error: function (e) {
            $('.error').css('display', 'block');
            console.log("Error creating the goal!");
        }
    });
}

$(document).ready(function () {

    $('#btnAddGoal').click(function (event) {
        addGoal();
    });

    $('#name, #description').on("change paste keyup", function () {
        if ($('#name').val() === "" || $('#description').val() === '') {
            $('#btnAddGoal').prop('disabled', true);
        } else {
            $('#btnAddGoal').prop('disabled', false);
        }
    });

    $('.ul-tasks').on("change paste keyup", '.task', function () {
        if (stripTags($(this).val()) !== "") {
            if ($(this).next('.task').length === 0) {
                var i = $(this).clone();
                i.addClass('task');
                i.val("");
                $(this).after(i);
            }
        } else {
            while ($(this).next('.task').length !== 0 && stripTags($(this).next('.task').val()) === "") {
                $(this).next('.task').remove();
            }
        }
    });

    $('.ol-stages').on("change paste keyup", '.stage', function () {
        if (stripTags($(this).val()) !== "") {
            if ($(this).parent().next('.li-stage').length === 0) {
                var i = $(this).parent().clone(true, true);

                i.children().val("");

                while(i.find('.task').length !== 1) {
                    i.find('.task')[0].remove();
                }

                i.find('.task').val("");
                $(this).parent().after(i);
            }
        } else {
            while ($(this).parent().next('.li-stage').length !== 0 &&
                stripTags($(this).parent().next('.li-stage').children().val()) === "") {
                $(this).parent().next('.li-stage').remove();
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