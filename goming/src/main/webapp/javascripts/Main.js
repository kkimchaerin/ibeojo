// Main.js

// 성별 카테고리 클릭 시 CSS 변경
$(document).ready(function() { 
    $(".gender-category a").click(function() {
        $(".gender-category a").removeClass("checked");
        $(this).addClass("checked");
    });
});


// 최신순/인기순 클릭 시 CSS 변경
$(document).ready(function() { 
    $(".sort a").click(function() {
        $(".sort a").removeClass("checked");
        $(this).addClass("checked");
    });
});


 // 카테고리 필터링
$(document).ready(function() {    
    let gender = "M";
    let style = "미니멀";
    let season = "봄";
    
    // 페이지 로드 시 초기 이미지 로드
    loadImagesByFilters(gender, style, season);

    // 이미지 로드 함수
    function loadImagesByFilters(gender, style, season) {
		
		console.log("왜!!", { gender, style, season });
		
        $.ajax({
            type: "GET",
            url: "PostLoaderService",
            data: {
                gender: gender,
                style: style,
                season: season
            },
            success: function(data) {
                $("#style-name").text(style); // #style-name의 내용을 선택된 스타일로 변경
                
                console.log("Data received: ", data);
                
                $(".gallery").empty();
                $(".gallery-wrapper").find(".empty-message").remove();
                
                if(data.length === 0){
                    console.log("empty!");                    
                    $(".gallery-wrapper").append('<div class="empty-message"></div>');
                    $(".empty-message").load('EmptyImages.html');
                }else{
					$(".gallery-wrapper").find(".empty-message").remove();
                    $.each(data, function(index, post) {
                        let imgTag = $("<img>").attr("src", "post/" + post).attr("alt", data);
                        $(".gallery").append(imgTag);
                    });
                }
            },
            error: function(xhr, status, error) {
                console.error("이미지 로딩 실패: " + status + ", " + error); 
				$(".gallery-wrapper").append('<div class="empty-message"></div>');
                $(".empty-message").load('EmptyImages.html');
            },
            complete: function() {
                $(".loading-spinner").remove(); // 로딩 스피너 제거
            }
        });
    }

    $(document).on("click", ".get-category", function() {
        gender = $(".gender-category .checked").attr("id");       
        style = $(".category-nav-wrapper").find(".style-category .checked img").attr("alt");
        season = $(".category-nav-wrapper").find(".season-category .checked img").attr("alt");
        
        // console.log("gender카테고리: ", gender);
        // console.log("style카테고리: ", style);
        // console.log("season카테고리: ", season);

        loadImagesByFilters(gender, style, season);
    });
});
