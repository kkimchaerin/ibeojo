// Modal.js

$(document).ready(function() {
    // 모달 관련 요소들
    var modal = $("#modal");
    var modalImg = $("#modal-img");
    var span = $(".close");

    // 이미지 클릭 시 모달 열기
    $(".gallery").on("click", "img", function() {
        var src = $(this).attr("src");
        modalImg.attr("src", src);
        modal.css("display", "block");
    });

    // 모달 닫기
    span.click(function() {
        modal.css("display", "none");
    });

    // 모달 바깥 클릭 시 닫기
    $(window).click(function(event) {
        if (event.target.id == "modal") {
            modal.css("display", "none");
        }
    });
});
