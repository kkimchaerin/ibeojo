// CategoryNav.js

$(document).ready(function() {
    // 스타일 카테고리 버튼 클릭 시
    $(".style-category .category-btn").click(function() {
        $(".style-category .category-btn").removeClass("checked"); // 모든 스타일 카테고리 버튼에서 checked 클래스 제거
        $(this).addClass("checked"); // 현재 클릭된 버튼에 checked 클래스 추가
    });

    // 계절 카테고리 버튼 클릭 시
    $(".season-category .category-btn").click(function() {
        $(".season-category .category-btn").removeClass("checked"); // 모든 계절 카테고리 버튼에서 checked 클래스 제거
        $(this).addClass("checked"); // 현재 클릭된 버튼에 checked 클래스 추가
    });
});
