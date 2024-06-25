// 이미지 업로드 input 스타일 변경
document.getElementById('photo').addEventListener('change', function() {
	let fileName = this.files[0] ? this.files[0].name : 'Click to add image'; .366666
	document.getElementById('file-name').textContent = fileName;
});

// 선택한 button 스타일 변경
function setValue(hiddenInputId, value, button) {
	document.getElementById(hiddenInputId).value = value;

	// 같은 그룹의 모든 버튼에서 checked 클래스 제거
	const group = button.parentElement.parentElement;
	group.querySelectorAll('.form-btn').forEach(btn => btn.classList.remove('checked'));

	// 클릭된 버튼에 checked 클래스 추가
	button.classList.add('checked');

	console.log(hiddenInputId + ": " + value);
}