// Main.js

// 성별 카테고리 클릭 시 CSS 변경
$(document).ready(function() { 
    $(".gender a").click(function() {
        $(".gender a").removeClass("checked");
        $(this).addClass("checked");
    });
});
