$(document).ready(function () {
    $('#end-time').timepicker({
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
    document.getElementById("end-date").min = minDateISO;

    $("#registration").click(function() {
      let date = $("#end-date").val();
      let time = $("#end-time").val();
      console.log("date: " + date);
      console.log("time: " + time);
      let i = date + "T" + time;
      console.log(i);
      console.log(typeof(i));

      let c = $("#category").val();
      console.log(c);
      console.log(typeof(c));
    });
    
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
