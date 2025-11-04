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
	document.getElementById('dropzone').classList.remove('drag-active');

    const fileInput = document.getElementById('fileInput');
	if (e.dataTransfer.files.length > 0) {
    fileInput.files = e.dataTransfer.files;
    updateFileName(fileInput.files[0]);
    }
}

function handleFileChange(e) {
	if (e.target.files.length > 0) {
		updateFileName(e.target.files[0]);
	}
}

function updateFileName(file) {
	document.getElementById('dropzone-text').textContent = file.name;
}

function clearFile() {
    const fileInput = document.getElementById('fileInput');
    const dropzoneText = document.getElementById('dropzone-text');

    fileInput.value = "";
    dropzoneText.textContent = "파일을 선택하거나 끌어놓으세요";
}