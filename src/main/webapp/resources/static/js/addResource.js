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

document.addEventListener("DOMContentLoaded", () => {
	const fileContainer = document.getElementById("fileContainer");
	const fileList = document.getElementById("resourceFileList");

	if (!fileContainer || !fileList) return;

	fileContainer.addEventListener("change", () => {
		updateResourceFileList();
	});

	updateResourceFileList();
});

function updateResourceFileList() {
	const fileContainer = document.getElementById("fileContainer");
	const fileList = document.getElementById("resourceFileList");

	fileList.innerHTML = "";

	const inputs = fileContainer.querySelectorAll(".file-input");
	const files = [];

	inputs.forEach((input) => {
		if (input.files && input.files.length > 0) {
			for (const f of input.files) files.push(f);
		}
	});

	if (files.length === 0) {
		fileList.innerHTML = `<li class="file-list-empty">선택된 파일 없음</li>`;
		return;
	}

	files.forEach((file) => {
		const sizeKB = Math.round(file.size / 1024);
		const li = document.createElement("li");
		li.className = "file-list-item";
		li.textContent = `${file.name} (${sizeKB} KB)`;
		fileList.appendChild(li);
	});
}
