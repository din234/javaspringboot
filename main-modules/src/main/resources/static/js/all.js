
$(document).ready(function () {
    $('#normal').on('click', function(event){
        event.preventDefault();

        var requests = (localStorage.getItem("requests") === null) ? 1 : parseInt(localStorage.getItem("requests"));
        for (var i = 0; i < requests; i++){
            request("/test/blocking");
        }
    });

    $('#defer').on('click', function(event){
        event.preventDefault();

        var requests = (localStorage.getItem("requests") === null) ? 1 : parseInt(localStorage.getItem("requests"));
        for (var i = 0; i < requests; i++){
            request("/test/non-blocking");
//            defer();
        }
    });
});

function request(url){
    var form = {}
    form["name"] = $("#name").val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        data: JSON.stringify(form),
        dataType: 'json',
        timeout: 600000,
        async: true,
        success: function (data) {
             console.log("SUCCESS : ", data);
        },
        error: function (err) {
            console.log("ERROR : ", JSON.stringify(err, null, 4));
        }
    });
}

//function defer(){
//    var form = {}
//    form["name"] = $("#name").val();
//
//    $.ajax({
//        type: "POST",
//        contentType: "application/json",
//        url: "/test/defer",
//        data: JSON.stringify(form),
//        dataType: 'json',
//        timeout: 600000,
//        async: true,
//        success: function (data) {
//             console.log("SUCCESS : ", data);
//        },
//        error: function (err) {
//            console.log("ERROR : ", JSON.stringify(err, null, 4));
//        }
//    });
//}
