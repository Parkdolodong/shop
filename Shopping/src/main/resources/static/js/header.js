$(function () {
    $("#signOut").click(function () {
    console.log("실행확인");
        $.ajax({
            type: "POST",
            url: "/sign/logout",
            success: function () {
                console.log("성공");
                document.location.reload();
            }
        });
    });
});