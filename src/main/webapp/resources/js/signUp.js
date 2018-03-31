$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();

    $("[data-toggle='tooltip']").on("mouseover", function(){
        if($(this).parent().hasClass("invalid")){
            $(".tooltip").addClass("tooltip-invalid").removeClass("tooltip-valid");
        } else if($(this).parent().hasClass("valid")){
            $(".tooltip").addClass("tooltip-valid").removeClass("tooltip-invalid").hide();

        } else {
            $(".tooltip").removeClass("tooltip-invalid tooltip-valid");
        }
    });

    $('#login').on("change paste keyup", function () {
        var elem = $(this);
        if(isEmpty($(this))) {
            return;
        }

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "register?login=" + stripTags($(this).val()),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
                switchValid(elem, data);
            },
            error: function (e) {
                console.log(JSON.stringify(e.responseText));
            }
        });
    });


    $('#email').on("change paste keyup", function () {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        switchValid(this, re.test(stripTags($(this).val())));
    });

    $('#password').on("change paste keyup", function () {
        switchValid(this, stripTags($(this).val()).length > 7);
    });

    $('#confirm').on("change paste keyup", function () {
        switchValid(this, stripTags($(this).val()) === stripTags($('#password').val()));
    });

    $("#btn_sign_up").click(function (event) {
        event.preventDefault();
        registerUser();
    });
});

function registerUser() {
    var user = {};
    user["login"] = stripTags($("#login").val());
    user["mail"] = stripTags($("#email").val());
    user["password"] = stripTags($("#password").val());
    user["creationDate"] = new Date();

    $("#btn_sign_up").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "register",
        data: JSON.stringify(user),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            if (data !== null) {
                if (data.id !== -1) {
                    window.location.href = 'goal/';
                }
            } else {
                $('.error').css('display', 'block');
            }

            $("#btn_sign_up").prop("disabled", false);
        },
        error: function (e) {
            $('.error').css('display', 'block');
            $("#btn_sign_up").prop("disabled", false);
        }
    });
}

function switchValid(elem, result) {
    if (isEmpty(elem)) {
        return;
    }
    var spanCheck = $(elem).prev('.input-check');
    if (result) {
        if (spanCheck.hasClass('invalid')) {
            setValid(spanCheck);
            if ($('.main-login').find('.valid').length === 4) {
                $('#btn_sign_up').prop("disabled", false);
            } else {
                $('#btn_sign_up').prop("disabled", true);
            }
        }
    } else {
        if (spanCheck.hasClass('valid')) {
            setInvalid(spanCheck);
        }
    }
}

function isEmpty(elem) {
    var spanCheck = $(elem).prev('.input-check');

    if (stripTags($(elem).val()) === "") {
        if (spanCheck.hasClass('valid')) {
            setInvalid(spanCheck);
        }
        return true;
    }
    return false;
}

function setValid(elem) {
    elem.removeClass('invalid').addClass('valid');
    elem.children('i').removeClass('glyphicon-remove').addClass('glyphicon-ok');
}

function setInvalid(elem) {
    elem.removeClass('valid').addClass('invalid');
    elem.children('i').removeClass('glyphicon-ok').addClass('glyphicon-remove');
}


function stripTags(str, allow) {
    allow = (((allow || "") + "").toLowerCase().match(/<[a-z][a-z0-9]*>/g) || []).join('');

    var tags = /<\/?([a-z][a-z0-9]*)\b[^>]*>/gi;
    var commentsAndPhpTags = /<!--[\s\S]*?-->|<\?(?:php)?[\s\S]*?\?>/gi;
    return str.replace(commentsAndPhpTags, '').replace(tags, function ($0, $1) {
        return allow.indexOf('<' + $1.toLowerCase() + '>') > -1 ? $0 : '';
    });
}