$(document).ready(function () {
    $('#endtime').timepicker({
        timeFormat: 'HH:mm:mm',
        interval: 60,
        startTime: '00:00',
        dynamic: false,
        dropdown: true,
        scrollbar: true
    });

    const today = new Date();
    const minDate = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 2);
    const minDateISO = minDate.toISOString().split("T")[0];

    // input 요소의 min 속성 값을 현재 날짜로 설정합니다.
    document.getElementById("enddate").min = minDateISO;
});

function previewImage(event) {
    const preview = document.getElementById('image');
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.onloadend = () => {
      preview.src = reader.result;
    }
    if (file) {
      reader.readAsDataURL(file);
    } else {
      preview.src = "";
    }
}

$("#registration").click(function() {
    var userDto = {
        "name": $("#name").val(),
        "category": $("#category").val(),
        "name": $("#name").val(),
        "email": $("#email").val(),
        "phoneNumber": $("#phonenumber").val(),
        "userAddressDto": {
            "address": $("#address").val(),
            "zonecode": $("#postcode").val(),
            "detailedaddress": $("#detailAddress").val()
        }
    };
    $.ajax({
        url: "/item/register",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(userDto),
        success: function(response) {
            console.log("요청성공");
//            window.location.href = "/sign/sign-in";
            alert("물품등록이 완료되었습니다.");
            $('form :input').val('');
        },
        error: function(xhr, textStatus, errorThrown) {
            console.log("에러발생", errorThrown);
            alert(xhr.responseText);
        }
    });
});