  $(document).ready(function() {
    let gender = "M";
    
    if(style_tag == null){
		style_tag = "미니멀";
	}
 
//    let style_tag = sessionStorage.getItem("user_preference") || "미니멀"; // 세션 스토리지에서 가져오거나 기본값으로 설정
     // 세션 스토리지에서 가져오거나 기본값으로 설정
    let season = "봄";
    let sortBy = "new"; // 초기 정렬 기준 설정
    
    // 초기 세션 스토리지에서 가져온 style_tag에 해당하는 라디오 버튼에 checked 클래스 추가
    $(".category-list .category-btn").each(function() {
        let buttonStyleTag = $(this).find("img").attr("alt");
        if (buttonStyleTag === style_tag) {
            $(this).addClass("checked");
        }
    });

    // 성별 카테고리 클릭 시 CSS 변경
    $(".gender-category a").click(function() {
        $(".gender-category a").removeClass("checked");
        $(this).addClass("checked");
        gender = $(this).attr("id"); // 성별 업데이트
        loadImagesByFilters(gender, style_tag, season, sortBy);
    });

    // 최신순/인기순 클릭 시
    $(document).on("click", ".sort a", function() {
        $(".sort a").removeClass("checked");
        $(this).addClass("checked");
        sortBy = $(this).attr("id"); // 정렬 기준 업데이트
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
                $(".gallery").empty();
                $(".gallery-wrapper").find(".empty-message").remove();
                if (data.length === 0) {
                    $(".gallery-wrapper").append('<div class="empty-message"></div>');
                    $(".empty-message").load('EmptyImages.html');
                } else {
                    $(".gallery-wrapper").find(".empty-message").remove();
                    $.each(data, function(index, post) {
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
