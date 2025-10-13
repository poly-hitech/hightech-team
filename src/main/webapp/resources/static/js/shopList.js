$(document).ready(function () {
    // showButton 값이 정의되어 있지 않으면 false로 설정
    var showButton = (typeof showButton !== "undefined") ? showButton : false;

    // 초기 로드 시 전체 데이터 표시
    displayAllResources();
    
    // 1차 카테고리 버튼 클릭 시
    $(".category-btn").click(function () {
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
        var selectedCategory = categories.find(function (cat) {
            return cat.resourceCategoryId === categoryId;
        });

        if (selectedCategory) {
            selectedCategory.resourceSubCategory.forEach(function (sub) {
                var btn = $('<button class="subcategory-btn" data-id="' + sub.resourceSubCategoryId + '">' + sub.resourceSubCategoryName + '</button>');
                $("#subcategoryButtons").append(btn);
            });
            $("#subcategoryArea").show();

            // 2차 카테고리가 선택되지 않은 경우 선택 문구 표시
            if ($(".subcategory-btn.active").length === 0) {
                $("#resourceGrid").html('<p>2차 카테고리를 선택해주세요</p>');
            }
        }
        $("#loading").hide();
    });

    // 2차 카테고리 버튼 클릭 시
    $(document).on("click", ".subcategory-btn", function () {
        var subcategoryId = parseInt($(this).data("id"));
        if (!subcategoryId) return;

        // 로딩 표시
        $("#loading").show();
        $("#resourceGrid").empty();

        // 선택된 2차 카테고리의 상점 정보 필터링
        var selectedCategory = categories.find(function (cat) {
            return cat.resourceCategoryId === parseInt($(".category-btn.active").data("id"));
        });

        if (selectedCategory && selectedCategory.resourceSubCategory) {
            var selectedSubcategory = selectedCategory.resourceSubCategory.find(function (sub) {
                return sub.resourceSubCategoryId === subcategoryId;
            });

            if (selectedSubcategory) {
                if (!selectedSubcategory.resourceShop || selectedSubcategory.resourceShop.length === 0) {
                    $("#resourceGrid").html('<p>표시할 상점이 없습니다.</p>');
                } else {
                    selectedSubcategory.resourceShop.forEach(function (shop) {
                        var filepath = shop.resourceImage || '';
                        var fileName = '';
                        var originfilename = '';
                        if (filepath) {
                            fileName = filepath.split(/[\\/]/).pop();
                            // 추가 이스케이프 처리
                            originfilename = fileName.replace(/\\/g, '/').replace(/[^a-zA-Z0-9._-]/g, '_');
                        }
                        console.log("파일명: " + fileName)
                        var itemHtml = '<div class="product-card" data-item-id="' + (shop.itemId || '') + '">' +
                            '<img src="' + filepath + '" alt="' + (originfilename || '') + '">' +
                            '<h3>' + (shop.itemName || '') + '</h3>' +
                            '<div class="author">' + (shop.itemWriter || '') + '</div>' +
                            '<div class="price">' + (shop.itemPrice || '0') + '</div>';
                            if(showButton === true){
                            itemHtml += '<div>' + '<a href="../updateMyResource/'+ (shop.itemId || '') +'">수정</a>' + '</div>' +
                            '<div>' + '<a href="../delete/'+ (shop.itemId || '') +'">삭제</a>' + '</div>';
                            }
                            itemHtml += '</div>';
                        //빈카드 생성 방지
                        if (shop.itemName !== null) {
                            $("#resourceGrid").append(itemHtml);
                        } else {
                            $("#resourceGrid").html('<p>표시할 리소스가 없습니다.</p>');
                        }
                    });
                }
            } else {
                $("#resourceGrid").html('<p>표시할 리소스가 없습니다.</p>');
            }
        }
        $("#loading").hide();
    });

    // 상품 카드 클릭 시 상세정보로 이동
    $("#resourceGrid").on("click", ".product-card", function () {
        var itemId = $(this).data("item-id");
        if (itemId) {
            // 상세정보 페이지로 이동
            window.location.href = "/shop/detail/" + itemId;
        } else {
            console.log("itemId가 정의되지 않았습니다.");
            alert("상품 정보를 불러올 수 없습니다.");
            console.log(itemId);
        }
    });

    function displayAllResources() {
        console.log("displayAll진입했습니다.")
        $("#loading").show();
        $("#resourceGrid").empty();
        $("#subcategoryArea").hide();

        console.log("카테고리:", categories); // categories 객체 상태 출력
        if (!categories || categories.length === 0) {
            $("#resourceGrid").html('<p>표시할 리소스가 없습니다.</p>');
            $("#loading").hide();
            return;
        }

        var hasShops = false;
        categories.forEach(function (category) {
            console.log("카테고리 객체: " + category)
            console.log("카테고리" + category.resourceCategoryName + "진입했습니다.")
            console.log("서브카테고리 개수: " + category.resourceSubCategory.length)
            if (category.resourceSubCategory !== null && category.resourceSubCategory.length > 0) {
                console.log("서브카테고리 존재합니다.")
                category.resourceSubCategory.forEach(function (sub) {
                    console.log("서브카테고리 객체: " + sub)
                    console.log("서브카테고리" + sub.resourceSubCategoryName + "진입했습니다.")
                    if (sub.resourceShop !== null && sub.resourceShop.length > 0) {
                        hasShops = true;
                        sub.resourceShop.forEach(function (shop) {
                            console.log("상점 객체: " + shop)
                            console.log("상점" + shop.itemName + "진입했습니다.")
                            if (shop.resourceFile && shop.resourceFile.length > 0) {
                                var filepath = shop.resourceImage || '';
                                console.log("상점 아이템이미지: " + filepath);
                                var fileName = '';
                                var originfilename = '';
                                if (filepath) {
                                    fileName = filepath.split(/[\\/]/).pop();
                                    // 추가 이스케이프 처리
                                    originfilename = fileName.replace(/\\/g, '/').replace(/[^a-zA-Z0-9._-]/g, '_');
                                }
                                console.log("파일명: " + fileName)
                                var itemHtml = '<div class="product-card" data-item-id="' + (shop.itemId || '') + '">' +
                                    '<img src="' + filepath + '" alt="' + (originfilename || '') + '">' +
                                    '<h3>' + (shop.itemName || '') + '</h3>' +
                                    '<div class="author">' + ('작성자 : ') + (shop.itemWriter || '') + '</div>' +
                                    '<div class="author">' + ('판매량 : ') + (shop.counting.totalcount || '0') + '</div>' +
                                    '<div class="author">' + ('랭킹 : ') + (shop.ranking.totalRank || '없음') + '</div>' +
                                    '<div class="price">' + (shop.itemPrice || '0') +('원') + '</div>';
                                    if(showButton === true){
                                    itemHtml += '<div>' + '<a href="../updateMyResource/'+ (shop.itemId || '') +'">수정</a>' + '</div>' +
                                    '<div>' + '<a href="../delete/'+ (shop.itemId || '') +'">삭제</a>' + '</div>';
                                    }
                                    itemHtml += '</div>';
                                //빈카드 생성 방지
                                if (shop.itemName !== null) {
                                    $("#resourceGrid").append(itemHtml);
                                }
                            } else {
                                console.log("상점에 파일이 없습니다.")
                            }
                        });
                    } else {
                        console.log("상점 없음")
                    }
                });
            } else {
                console.log("서브카테고리 없음")
            }
        });

        if (!hasShops) {
            $("#resourceGrid").html('<p>등록된 상점이 없습니다.</p>');
        }
        $("#loading").hide();
    }
});