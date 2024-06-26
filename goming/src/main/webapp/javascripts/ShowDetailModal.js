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
        let imgComment = $(this).data("data-comment");
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
    likeBtn.click(function() {
        let isLiked = likeBtn.hasClass("liked");
        let postIdx = modal.data("post-idx");

        if (!isLiked) {
            $.ajax({
                url: "LikeService",
                type: "POST",
                data: {
                    post_idx: postIdx
                },
                success: function(response) {
                    likeBtnImg.attr("src", "./icons/heart-solid.svg");
                    likeBtn.addClass("liked");
                },
                error: function(error) {
                    console.error("좋아요 실패", error);
                }
            });
        } else {
            // 이미 좋아요를 눌렀다면 좋아요 취소하는 로직 추가 가능
            likeBtnImg.attr("src", "./icons/heart-regular.svg");
            likeBtn.removeClass("liked");
        }
    });


});
