// ShowDetailModal.js

$(document).ready(function() {
    let modal = $("#modal");
    let modalUserNick = $("#modal-user-nick");
    let modalImg = $("#modal-img");
    let modalComment = $("#modal-comment");
    let span = $(".close");
    let likeBtn = $("#modal-like-btn");
    let likeBtnImg = $("#modal-like-btn img");
    
    // 좋아요 상태 가져오는 함수
    function fetchLikeStatus(postIdx) {
        $.ajax({
            url: "LikeStatusService",
            type: "GET",
            data: {
                post_idx: postIdx
            },
            success: function(response) {
                let isLiked = response.isLiked;
                if (isLiked) {
                    likeBtnImg.attr("src", "./icons/heart-solid.svg");
                    likeBtn.addClass("liked");
                } else {
                    likeBtnImg.attr("src", "./icons/heart-regular.svg");
                    likeBtn.removeClass("liked");
                }
            },
            error: function(xhr, status, error) {
                console.error("좋아요 상태 로드 실패: ", xhr, status, error);
            }
        });
    }
    

    // 이미지 클릭 시 모달 열기
    $(".gallery").on("click", "img", function() {
        let src = $(this).attr("src");
        let userNick = $(this).data("user-nick");
        let imgComment = $(this).data("comment");
        let postIdx = $(this).data("idx");
        
        fetchLikeStatus(postIdx);
        
        modalUserNick.text(userNick);
        modalImg.attr("src", src).attr("data-idx", postIdx).attr("alt", src);
        modalComment.text(imgComment);
        modal.data("post-idx", postIdx);
        
        let isLiked = $(this).siblings(".like-btn").hasClass("liked");
        if (isLiked) {
            likeBtnImg.attr("src", "./icons/heart-solid.svg");
            likeBtn.addClass("liked");
        } else {
            likeBtnImg.attr("src", "./icons/heart-regular.svg");
            likeBtn.removeClass("liked");
        }
        
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
            dataType: "json",
            success: function(response) {
            
            console.log("여기야??",response);
            

                if (response.result === "success") {
                    if (isLiked) {
                        likeBtnImg.attr("src", "./icons/heart-regular.svg");
                        likeBtn.removeClass("liked");
                        $(`.gallery img[data-idx='${postIdx}']`).siblings(".like-btn").removeClass("liked");
                    } else {
                        likeBtnImg.attr("src", "./icons/heart-solid.svg");
                        likeBtn.addClass("liked");
                        $(`.gallery img[data-idx='${postIdx}']`).siblings(".like-btn").addClass("liked");
                    }
                } else {
                    console.error("좋아요 처리 실패: ", response);
                }
            },
            error: function(xhr, status, error) {
                console.error("AJAX 오류: ", xhr, status, error);
            },
            complete: function() {
                isProcessing = false; // 플래그 초기화
            }
        });
    });
});
