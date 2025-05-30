$(document).ready(function() {
    // 초기 로드 시 전체 데이터 표시
    displayAllResources();

    // 1차 카테고리 버튼 클릭 시
    $(".category-btn").click(function() {
        $(".category-btn").removeClass("active");
        $(this).addClass("active");

        var categoryId = $(this).data("id");
        if (categoryId === "all") {
            displayAllResources();
            $("#subcategoryArea").hide();
            return;
        }

        // 로딩 표시
        $("#loading").show();
        $("#resourceGrid").empty();
        $("#subcategoryButtons").empty();

        // 선택된 1차 카테고리의 2차 카테고리 필터링
        var selectedCategory = categories.find(function(cat) {
            return cat.resourceCategoryId === categoryId;
        });

        if (selectedCategory) {
            selectedCategory.resourceSubCategory.forEach(function(sub) {
                var btn = $('<button class="subcategory-btn" data-id="' + sub.resourceSubCategoryId + '">' + sub.resourceSubCategoryName + '</button>');
                $("#subcategoryButtons").append(btn);
            });
            $("#subcategoryArea").show();
        }
        $("#loading").hide();
    });

    // 2차 카테고리 버튼 클릭 시
    $(document).on("click", ".subcategory-btn", function() {
        var subcategoryId = parseInt($(this).data("id"));
        if (!subcategoryId) return;

        // 로딩 표시
        $("#loading").show();
        $("#resourceGrid").empty();

        // 선택된 2차 카테고리의 상점 정보 필터링
        var selectedCategory = categories.find(function(cat) {
            return cat.resourceCategoryId === parseInt($(".category-btn.active").data("id"));
        });

        if (selectedCategory) {
            var selectedSubcategory = selectedCategory.resourceSubCategory.find(function(sub) {
                return sub.resourceSubCategoryId === subcategoryId;
            });

            if (selectedSubcategory) {
                selectedSubcategory.resourceShop.forEach(function(shop) {
                    shop.resourceFile.forEach(function(file) {
                        var fileName = file.resourceFileName;
                        if (fileName && fileName.includes("_")) {
                            fileName = fileName.split("_").pop();
                        }
                        var itemHtml = '<div class="product-card">' +
                            '<img src="/upload/' + fileName + '" alt="' + shop.itemName + '">' +
                            '<h3>' + shop.itemName + '</h3>' +
                            '<div class="author">' + shop.itemWriter + '</div>' +
                            '<div class="price">' + shop.itemPrice + '</div>' +
                            '</div>';
                        $("#resourceGrid").append(itemHtml);
                    });
                });
            } else {
                $("#resourceGrid").html('<p>표시할 리소스가 없습니다.</p>');
            }
        }
        $("#loading").hide();
    });

function displayAllResources() {
        $("#loading").show();
        $("#resourceGrid").empty();
        $("#subcategoryArea").hide();

        if (!categories || categories.length === 0) {
            $("#resourceGrid").html('<p>표시할 리소스가 없습니다.</p>');
            $("#loading").hide();
            return;
        }

        categories.forEach(function(category) {
            category.resourceSubCategory.forEach(function(subcategory) {
                subcategory.resourceShop.forEach(function(shop) {
                    shop.resourceFile.forEach(function(file) {
                        var fileName = file.resourceFileName;
                        if (fileName && fileName.includes("_")) {
                            var filename = fileName.split("_").pop();
                        }
                        // 추가 이스케이프 처리
                        var filename = fileName.replace(/\\/g, '/').replace(/[^a-zA-Z0-9._-]/g, '');
                        var itemHtml = '<div class="product-card">' +
                            '<img src="filename" alt="fileName">' +
                            '<h3>' + shop.itemName + '</h3>' +
                            '<div class="author">' + shop.itemWriter + '</div>' +
                            '<div class="price">' + shop.itemPrice + '</div>' +
                            '</div>';
                        $("#resourceGrid").append(itemHtml);
                    });
                });
            });
        });
        $("#loading").hide();
    }
});