const addFileBtn = document.getElementById('addFileBtn');
const removeLastFileBtn = document.getElementById('removeLastFileBtn');
const fileContainer = document.getElementById('fileContainer');

addFileBtn.addEventListener('click', () => {
    const newDiv = document.createElement('div');
    newDiv.classList.add('file-input-wrapper');

    const newInput = document.createElement('input');
    newInput.type = 'file';
    newInput.name = 'resourceFile';
    newInput.accept = true;

    newDiv.appendChild(newInput);
    fileContainer.appendChild(newDiv);
});
removeLastFileBtn.addEventListener('click', () => {
    const wrappers = fileContainer.getElementsByClassName('file-input-wrapper');
    if (wrappers.length > 1) {
        fileContainer.removeChild(wrappers[wrappers.length - 1]);
    }
});