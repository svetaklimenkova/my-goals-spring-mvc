$(document).ready(function () {
    var result = getUrlVar();

    if (result['error'] === "true") {
        $('#error').css('display', 'block');
    }

    $("input").on('change paste keyup', function () {
        $(this).val((stripTags($(this).val())));
    })
});

function getUrlVar(){
    var urlVar = window.location.search;
    var valueAndKey = [];
    var resultArray = [];
    arrayVar = (urlVar.substr(1)).split('&');
    if(arrayVar[0] === "") return [];
    for (i = 0; i < arrayVar.length; i ++) {
        valueAndKey = arrayVar[i].split('=');
        resultArray[valueAndKey[0]] = valueAndKey[1];
    }
    return resultArray;
}

function stripTags(str, allow) {
    allow = (((allow || "") + "").toLowerCase().match(/<[a-z][a-z0-9]*>/g) || []).join('');

    var tags = /<\/?([a-z][a-z0-9]*)\b[^>]*>/gi;
    var commentsAndPhpTags = /<!--[\s\S]*?-->|<\?(?:php)?[\s\S]*?\?>/gi;
    return str.replace(commentsAndPhpTags, '').replace(tags, function ($0, $1) {
        return allow.indexOf('<' + $1.toLowerCase() + '>') > -1 ? $0 : '';
    });
}