// =====================
// 대표 이미지 드롭존
// =====================

function handleDragOver(e) {
	e.preventDefault();
	document.getElementById('dropzone').classList.add('drag-active');
}

function handleDragLeave(e) {
	e.preventDefault();
	document.getElementById('dropzone').classList.remove('drag-active');
}

function handleDrop(e) {
	e.preventDefault();
	const dropzone = document.getElementById('dropzone');
	dropzone.classList.remove('drag-active');

	const fileInput = document.getElementById('fileInput');
	if (e.dataTransfer.files.length > 0) {
		fileInput.files = e.dataTransfer.files;
		updateImagePreview(fileInput.files[0]);
	}
}

function handleFileChange(e) {
	if (e.target.files.length > 0) {
		updateImagePreview(e.target.files[0]);
	}
}

function updateImagePreview(file) {
	const textSpan = document.getElementById('dropzone-text');
	const previewImg = document.getElementById('imagePreview');

	if (textSpan) textSpan.textContent = file.name;

	// 이미지면 미리보기 처리
	if (file.type && file.type.startsWith('image/')) {
		const reader = new FileReader();
		reader.onload = function(e) {
			previewImg.src = e.target.result;
			previewImg.style.display = 'block';
		};
		reader.readAsDataURL(file);
	} else {
		previewImg.src = '';
		previewImg.style.display = 'none';
	}
}

function clearFile() {
	const fileInput = document.getElementById('fileInput');
	const textSpan = document.getElementById('dropzone-text');
	const previewImg = document.getElementById('imagePreview');

	fileInput.value = "";
	textSpan.textContent = "파일을 선택하거나 여기로 끌어놓으세요";
	previewImg.src = "";
	previewImg.style.display = "none";
}


// =====================
// 리소스 파일 목록 표시
// =====================

// fileContainer 내의 모든 .file-input을 감시해서 파일 목록 업데이트
document.addEventListener("DOMContentLoaded", () => {
	const fileContainer = document.getElementById("fileContainer");
	const fileList = document.getElementById("resourceFileList");

	if (!fileContainer || !fileList) return;

	// input 변화 감지 (동적 추가 input 포함)
	fileContainer.addEventListener("change", () => {
		updateResourceFileList();
	});

	// 초기 표시
	updateResourceFileList();
});

// 실제 파일 리스트 생성
function updateResourceFileList() {
	const fileContainer = document.getElementById("fileContainer");
	const fileList = document.getElementById("resourceFileList");

	fileList.innerHTML = ""; // 초기화

	const inputs = fileContainer.querySelectorAll(".file-input");
	const files = [];

	// 모든 input의 파일을 수집
	inputs.forEach((input) => {
		if (input.files && input.files.length > 0) {
			for (const f of input.files) files.push(f);
		}
	});

	if (files.length === 0) {
		fileList.innerHTML = `<li class="file-list-empty">선택된 파일 없음</li>`;
		return;
	}

	// 수집된 파일 목록 렌더링
	files.forEach((file) => {
		const sizeKB = Math.round(file.size / 1024);
		const li = document.createElement("li");
		li.className = "file-list-item";
		li.textContent = `${file.name} (${sizeKB} KB)`;
		fileList.appendChild(li);
	});
}
