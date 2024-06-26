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
        let postIdx = $(this).data("idx");
        
        modalImg.attr("src", src).attr("data-idx", postIdx);
        modalComment.text(imgComment);
        modal.data("post-idx", postIdx);
        
        modal.css("display", "block");
    });

    // 모달 닫기
    span.click(function() {
        modal.css("display", "none");
    });
    
    // 좋아요 버튼 클릭 시 아이콘 변경 및 서버에 저장
    let isProcessing = false; // 중복 클릭 방지를 위한 플래그

    likeBtn.click(function() {
        if (isProcessing) {
            return; // 이미 처리 중이면 중복 클릭 방지
        }

        isProcessing = true; // 처리 중으로 플래그 설정

        let isLiked = likeBtn.hasClass("liked");
        let postIdx = modal.data("post-idx");
        let url = isLiked ? "UnlikeService" : "LikeService"; // 좋아요와 취소를 구분하여 서비스 URL 지정

        $.ajax({
            url: url,
            type: "POST",
            data: {
                post_idx: postIdx
            },
            success: function(response) {
                console.log("AJAX 응답: ", response); // 응답 로그 추가
       
                    if (isLiked) {
                        likeBtnImg.attr("src", "./icons/heart-regular.svg");
                        likeBtn.removeClass("liked");
                    } else {
                        likeBtnImg.attr("src", "./icons/heart-solid.svg");
                        likeBtn.addClass("liked");
                    }

            },
            error: function(xhr, status, error) {
                console.error("AJAX 오류: ", xhr, status, error); // 오류 로그 추가
            },
            complete: function() {
                isProcessing = false; // 처리 완료 후 플래그 초기화
            }
        });
    });
});
