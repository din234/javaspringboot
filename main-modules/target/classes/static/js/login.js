$(document).ready(function () {
    $('#login-submit').on('click', function(event){
        event.preventDefault();
        login_submit();
    });
});




function login_submit(){
    var login = {};
    login["username"] = $("#username").val();
    login["password"] = $("#password").val();

    $.ajax({
        type: "POST",
//        headers: {
//            Authorization:
//        }
        contentType: "application/json",
        url: "/user/auth/login",
        data: JSON.stringify(login),
        dataType: 'json',
        timeout: 600000,
        async: true,
        success: function (data) {
            localStorage.setItem("token",data["token"]);
            var json = "<p class='alert alert-success'>" + JSON.stringify(data, null, 4) + "</p>";
            $('#feedback').html(json);

            // console.log("SUCCESS : ", data);
            // $("#btn-search").prop("disabled", false);

        },
        error: function (err) {
            var json = "<p class='alert alert-danger'>"+ JSON.stringify(err,null,4) + "</p>";
            $('#feedback').html(json);

            console.log("ERROR : ", err);
//            $("#btn-search").prop("disabled", false);

        }
    });
}


function getLocalStorage(){
    var keys = Object.keys(localStorage)
    console.log(JSON.stringify(localStorage,null,4));
}
