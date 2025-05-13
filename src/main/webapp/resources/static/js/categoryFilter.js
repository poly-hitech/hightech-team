document.addEventListener('DOMContentLoaded', function() {
    const primaryCategorySelect = document.querySelector('#category');
    const secondCategorySelect = document.querySelector('#secondCategory');

    primaryCategorySelect.addEventListener('change', function() {
        const selectedPrimaryId = parseInt(this.value);
        secondCategorySelect.innerHTML = '<option value="">리소스 2차 카테고리</option>';

        if (selectedPrimaryId) {
            // 선택된 1차 카테고리 찾기
            const selectedCategory = category.find(category => 
                category.categoryId === selectedPrimaryId
            );

            // 해당 1차 카테고리의 2차 카테고리 추가
            if (selectedCategory && selectedCategory.resourcesubCategory) {
                selectedCategory.resourcesubCategory.forEach(subCategory => {
                    const option = document.createElement('option');
                    option.value = subCategory.resourceSubCategoryId;
                    option.textContent = subCategory.resourceSubCategoryName;
                    secondCategorySelect.appendChild(option);
                });
            }
        }
    });
});