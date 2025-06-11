$(document).ready(function () {
    // 초기 로드 시 전체 데이터 표시
    displayAllResources()
    function displayAllResources() {
        console.log("displayAll진입했습니다.")
        $("#resourceFile").empty();

        console.log("상점정보:", shop); // 상점 객체 상태 출력
        if (!shop) {
            $("#resourceFile").html('<p>해당 상품에 대한 정보가 없습니다.</p>');
            return;
        }

        shop.resourceFile.forEach(function (file) {
            var fileName = file.resourceFileName || '';
            if (fileName) {
                fileName = fileName.split(/[\\/]/).pop();
                // 추가 이스케이프 처리
                var originfilename = fileName.replace(/\\/g, '/').replace(/[^a-zA-Z0-9._-]/g, '_');
            }
            console.log("파일명: " + fileName)
            var itemHtml = '<div class="productDetail-card" data-item-id="' + (shop.itemId || '') + '">' +
                '<img src="/upload/images/' + fileName + '" alt="' + (originfilename || '') + '">' +
                '<h3>' + (shop.itemName || '') + '</h3>' +
                '<div class="content">' + (shop.resourceContent || '') + '</div>' +
                '<div class="author">' + (shop.itemWriter || '') + '</div>' +
                '<div class="price">' + (shop.itemPrice || '0') + '</div>' +
                '</div>';
            //빈카드 생성 방지
            if (shop.itemName !== null) {
                $("#resourceFile").append(itemHtml);
            }
        });
    }
});