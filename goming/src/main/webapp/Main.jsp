<%@page import="java.util.List"%>
<%@page import="com.goming.post.model.PostDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	List<PostDTO> posts = (List<PostDTO>)request.getAttribute("posts");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>오늘의옷</title>
<link rel="stylesheet" type="text/css" href="./styles/Main.css?ver=2" />
<link rel="stylesheet" type="text/css" href="./styles/BottomNav.css" />
<link rel="stylesheet" type="text/css" href="./styles/Reset.css?ver=2" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="./javascripts/Post.js?ver=2" defer></script>
<script src="./javascripts/Main.js" defer></script>
    <script>
    $(document).ready(function() {
        function loadInitialImages() {
            loadImagesByFilters("M", "미니멀", "봄");
        }

        function loadImagesByFilters(gender, style, season) {
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
                    
                    if(data.length === 0){
                    	console.log("empty!");
                    	$(".gallery").html("<p>게시물이 없습니다.</p>");
                    }else{
                        $.each(data, function(index, post) {
                            let imgTag = $("<img>").attr("src", "post/" + post).attr("alt", data);
                            $(".gallery").append(imgTag);
                        });
                    }

                },
                error: function(xhr, status, error) {
                    console.error("이미지 로딩 실패: " + status + ", " + error); 
                },
                complete: function() {
                    $(".loading-spinner").remove(); // 로딩 스피너 제거
                }
            });
        }

        loadInitialImages();

        $(document).on("click", ".category-btn", function() {
            let gender = $(this).closest(".gallery-wrapper").find(".gender .checked").attr("id");
            let style = $(this).closest(".category-nav-wrapper").find(".style-category .checked img").attr("alt");
            let season = $(this).closest(".category-nav-wrapper").find(".season-category .checked img").attr("alt");
            console.log("gender카테고리버튼: ", gender);
            console.log("style카테고리버튼: ", style);
            console.log("season카테고리버튼: ", season);
            loadImagesByFilters(gender, style, season);
        });
    });

    </script>
</head>
<body>
	<!-- header -->
	<!-- include지시자 파일명(확장자까지) 작성 -->
	<%@ include file="Header.jsp"%>

	<!-- main -->
	<main>
		<section class="weather-wrapper">
			<h2 class="sr-only">날씨 정보</h2>
			<div class="weather-info">
				<img src="./icons/sun.png" alt="맑음"> <span id="temperature">28°C</span>
				<span id="">맑음</span>
			</div>
			<div id="weather-comment">다소 쌀쌀한 날씨가 예상됩니다. 자켓이나 코트같은 두꺼운 외투를 챙기는게 좋겠어요</div>
		</section>
		<section class="gallery-wrapper">
			<h2 id="style-name">미니멀</h2>
			<div class="gender">
				<a href="#" id="M" class="checked">MEN</a> 
				<a href="#" id="F">WOMEN</a>
			</div>
			
			<!-- category nav -->
			<%@ include file="CategoryNav.jsp"%>

			<div class="sort">
				<a href="#" id="new">최신순</a> <a href="#" id="popular">인기순</a>
			</div>
			<div class="gallery">
				<img src="./images/men_winter_minimal_11.png" alt="피드1"> 
				<img src="./images/women_summer_minimal_11.png" alt="피드2"> 
				<img src="./images/men_winter_minimal_12.png" alt="피드3"> 
				<img src="./images/women_spring_minimal_13.png" alt="피드4"> 
				<img src="./images/women_summer_minimal_12.png" alt="피드5"> 
				<img src="./images/women_spring_minimal_14.png" alt="피드6"> 

			</div>
		</section>
		<section class="upload">
			<h3 class="sr-only">게시물 업로드</h3>
			<a href="Upload.jsp"><img src="./icons/upload.png" alt="게시물 업로드"></a>
		</section>
	</main>

	<!-- bottom nav -->
	<%@ include file="BottomNav.jsp"%>

</body>
</html>