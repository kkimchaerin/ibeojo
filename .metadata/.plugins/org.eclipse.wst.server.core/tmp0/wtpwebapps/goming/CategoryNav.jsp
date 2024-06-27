<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="category-nav-wrapper">
	<h3 class="sr-only">카테고리 필터링</h3>
	<div class="style-category">
		<h3 class="sr-only">스타일</h3>
		<ul class="category-list">
			<li>
				<button type="button" class="category-btn checked">
					<img src="./icons/minimal.png" alt="미니멀"> <span>미니멀</span>
				</button>
			</li>
			<li>
				<button type="button" class="category-btn">
					<img src="./icons/business.png" alt="비즈니스"> <span
						class="small-text">비즈니스</span>
				</button>
			</li>
			<li>
				<button type="button" class="category-btn">
					<img src="./icons/sporty.png" alt="스포티"> <span>스포티</span>
				</button>
			</li>
			<li>
				<button type="button" class="category-btn">
					<img src="./icons/casual.png" alt="캐주얼"> <span>캐주얼</span>
				</button>
			</li>
		</ul>
	</div>
	<div class="season-category">
		<h3 class="sr-only">계절</h3>
		<ul class="category-list">
			<li>
				<button type="button" class="category-btn">
					<img src="./icons/spring.png" alt="봄"> <span>봄</span>
				</button>
			</li>
			<li>
				<button type="button" class="category-btn checked">
					<img src="./icons/summer.png" alt="여름"> <span>여름</span>
				</button>
			</li>
			<li>
				<button type="button" class="category-btn">
					<img src="./icons/autumn.png" alt="가을"> <span>가을</span>
				</button>
			</li>
			<li>
				<button type="button" class="category-btn">
					<img src="./icons/winter.png" alt="겨울"> <span>겨울</span>
				</button>
			</li>
		</ul>
	</div>
</nav>
