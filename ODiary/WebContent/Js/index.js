$(window).resize(function () {
    $(".Main_body").height($(window).height() - 88);
});

function loadLogin() {
    $("#Login").load("Pages/login.html");
    $("#SignUp").load("Pages/SignUp.html");
    $(".Main_body").height($(window).height() - 88);
}

function dispcreateMem() {
	$("#display").show();
	$("#display").load("Pages/createMem.html");
	$("#timeline").hide();
}

function dispsearchUser() {
	$("#display").show();
	$("#display").load("Pages/searchUser.html");
	$("#timeline").hide();
}

function dispTimeline() {
	$("#timeline").show();
	$("#display").hide();
}