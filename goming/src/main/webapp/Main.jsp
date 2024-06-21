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
<link rel="stylesheet" type="text/css" href="./styles/Main.css?ver=1" />
<link rel="stylesheet" type="text/css" href="./styles/Reset.css?ver=2" />
<script src="./javascripts/Post.js" defer></script>
<script>
$(document).ready(function() {
    // 페이지 로딩 시 초기 이미지 로딩
    loadInitialImages();

    // 카테고리 변경 시 이미지 로딩
    $(".category-btn").click(function() {
        var gender = $(this).closest(".category-nav-wrapper").find(".gender .checked").attr("id");
        var style = $(this).closest(".category-nav-wrapper").find(".style-category .checked img").attr("alt");
        var season = $(this).closest(".category-nav-wrapper").find(".season-category .checked img").attr("alt");
        
        loadImagesByFilters(gender, style, season);
    });

    // 초기 이미지 로딩 함수
    function loadInitialImages() {
        loadImagesByFilters("men", "미니멀", "여름");
    }

    // Ajax를 이용한 이미지 로딩 함수
    function loadImagesByFilters(gender, style, season) {
        $.ajax({
            type: "GET",
            url: "PostLoaderService", // 이미지 로딩을 처리할 서블릿 URL
            data: {
                gender: gender,
                style: style,
                season: season
            },
            success: function(data) {
                $(".gallery").empty(); // 기존 이미지 모두 제거
                $.each(data, function(index, post) {
                    var imgTag = $("<img>").attr("src", post.postImg).attr("alt", "게시물 이미지");
                    $(".gallery").append(imgTag);
                });
            },
            error: function(xhr, status, error) {
                console.error("이미지 로딩 실패: " + status + ", " + error);
            }
        });
    }
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
				<a href="#" id="men" class="">MEN</a> 
				<a href="#" id="women">WOMEN</a>
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
			<a href="#"><img src="./icons/upload.png" alt="게시물 업로드"></a>
		</section>
	</main>

	<!-- bottom nav -->
	<%@ include file="BottomNav.jsp"%>

</body>
</html>