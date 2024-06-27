// ShowDetailModal.js

$(document).ready(function() {
    let modal = $("#modal");
    let modalImg = $("#modal-img");
    let modalComment = $("#modal-comment");
    let span = $(".close");
    let likeBtn = $("#modal-like-btn");
    let likeBtnImg = $("#modal-like-btn img");

    // 이미지 클릭 시 모달 열기
    $(".gallery").on("click", "img", function() {
        let src = $(this).attr("src");
        let imgComment = $(this).data("comment");
        
        modalImg.attr("src", src);
        modalComment.text(imgComment);
        
        modal.css("display", "block");
    });

    // 모달 닫기
    span.click(function() {
        modal.css("display", "none");
    });
    
    // 좋아요 버튼 클릭 시 아이콘 변경
    likeBtn.click(function() {
        let isLiked = likeBtn.hasClass("liked");
        if (isLiked) {
            likeBtnImg.attr("src", "./icons/heart-regular.svg");
        } else {
            likeBtnImg.attr("src", "./icons/heart-solid.svg");
        }
        likeBtn.toggleClass("liked");
    });


});
