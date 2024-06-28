$(document).ready(function() {
    let gender = "M";
    let style_tag = "미니멀";
    let season = "봄";
    let sortBy = "new"; // 초기 정렬 기준 설정

    // 성별 카테고리 클릭 시 CSS 변경
    $(".gender-category a").click(function() {
        $(".gender-category a").removeClass("checked");
        $(this).addClass("checked");
        gender = $(this).attr("id"); // 성별 업데이트
        console.log("성별 변경:", gender);
        loadImagesByFilters(gender, style_tag, season, sortBy);
    });

    // 최신순/인기순 클릭 시
    $(document).on("click", ".sort a", function() {
        $(".sort a").removeClass("checked");
        $(this).addClass("checked");
        sortBy = $(this).attr("id"); // 정렬 기준 업데이트
        console.log("정렬 기준 변경:", sortBy);
        loadImagesByFilters(gender, style_tag, season, sortBy);
    });

    // 페이지 로드 시 초기 이미지 로드
    loadImagesByFilters(gender, style_tag, season, sortBy);

    // 이미지 로드 함수 정의
    function loadImagesByFilters(gender, style_tag, season, sortBy) {
        console.log("필터링 및 정렬 요청:", { gender, style_tag, season, sortBy });
        $.ajax({
            type: "GET",
            url: "PostLoaderService",
            data: {
                gender: gender,
                style_tag: style_tag,
                season: season,
                sortBy: sortBy
            },
            success: function(data) {
                $("#style-name").text(style_tag); // #style-name의 내용을 선택된 스타일로 변경
                console.log("데이터 수신:", data);
                $(".gallery").empty();
                $(".gallery-wrapper").find(".empty-message").remove();
                if (data.length === 0) {
                    console.log("이미지 없음!");
                    $(".gallery-wrapper").append('<div class="empty-message"></div>');
                    $(".empty-message").load('EmptyImages.html');
                } else {
                    $(".gallery-wrapper").find(".empty-message").remove();
                    $.each(data, function(index, post) {
                        console.log("이미지 추가:", post);
                        let imgTag = $("<img>").attr("src", "post/" + post.post_img)
                            .attr("alt", post.post_img)
                            .attr("data-idx", post.post_idx)
                            .attr("data-user-nick", post.user_nick)
                            .attr("data-comment", post.comment);
                        $(".gallery").append(imgTag);
                    });
                }
            },
            error: function(xhr, status, error) {
                console.error("이미지 로딩 실패:", status, error);
                $(".gallery-wrapper").append('<div class="empty-message"></div>');
                $(".empty-message").load('EmptyImages.html');
            },
            complete: function() {
                console.log("이미지 로딩 완료!");
                $(".loading-spinner").remove(); // 로딩 스피너 제거
            }
        });
    }

    // 카테고리 필터링
    $(document).on("click", ".get-category", function() {
        style_tag = $(".category-nav-wrapper").find(".style-category .checked img").attr("alt");
        season = $(".category-nav-wrapper").find(".season-category .checked img").attr("alt");
        console.log("카테고리 필터링:", { style_tag, season });
        loadImagesByFilters(gender, style_tag, season, sortBy);
    });

});
