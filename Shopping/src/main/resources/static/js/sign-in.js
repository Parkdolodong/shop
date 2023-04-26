//$(document).ready(function () {
//  document.getElementById("id").focus();
//  $("form").submit(function (event) {
//    // 서버로 전송할 데이터를 객체 형태로 생성합니다.
//    event.preventDefault();
//    var memberDto = {
//      id: $("#id").val(),
//      password: $("#password").val(),
//    };
//    console.log(memberDto);
//    // 서버로 ajax 요청을 보냅니다.
//    $.ajax({
//      url: "/sign/login",
//      type: "POST",
//      contentType: "application/json",
//      data: JSON.stringify(memberDto),
//      dataType: "json",
//      success: function (response) {
//        window.location.href = "/";
//        console.log("성공");
//      },
//      error: function (xhr, equest, status, error) {
//        console.log(error);
//        console.log("실패");
//        window.location.href = "/sign/sign-in";
//        alert(xhr.responseText);
//      },
//    });
//  });
//});
