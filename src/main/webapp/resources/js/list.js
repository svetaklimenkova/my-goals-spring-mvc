function searchGoalsByUserId() {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: document.location.pathname + "/list",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log(data);
            if (data !== null) {
                viewGoals(data);
            }
        },
        error: function (e) {
            console.log("Error");
        }
    });
}

function viewGoals(goals) {
    for (var i = 0; i < goals.length; i++) {
        $('#goals-list').prepend('<a class="goal list-group-item" href="' + goals[i].id + '"><span class="goal-name">' + goals[i].name +
            '</span><span class="goal-state"> (' + parseState(goals[i].state) + ')</span></a>');
    }
}

function parseState(state){
    switch (state) {
        case 'NOT_STARTED': return $('#not_started').val();
        case 'IN_PROGRESS': return $('#in_progress').val();
        case 'FINISHED': return $('#finished').val();
        default: return $('#in_progress').val();
    }
}

$(document).ready(function () {
    searchGoalsByUserId();
});