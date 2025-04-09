document.addEventListener("DOMContentLoaded", () => {
    let index = 1;
    const container = document.getElementById("subcategory-container");
    const addBtn = document.getElementById("add-btn");

    addBtn.addEventListener("click", () => {
        const newRow = document.createElement("div");
        newRow.className = "row subcategory-row";
        newRow.innerHTML = `
            <label class="col-1">서브 카테고리 명:<span style="color:blue;"> * </span></label>
            <div class="col-2">
                <input type="text" name="resourceSubCategoryList[${index}].resourceSubCategoryName" class="form-control" />
            </div>
        `;
        container.appendChild(newRow);
        index++;
    });
});