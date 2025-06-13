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
            var itemHtml =
                '<div class="productDetail-card" data-item-id="' + (shop.itemId || '') + '">' +
                '<form>' +
                '<img src="/upload/images/' + fileName + '" alt="' + (originfilename || '') + '">' +
                '<h3>' + (shop.itemName || '') + '</h3>' +
                '<div class="content">' + (shop.resourceContent || '') + '</div>' +
                '<div class="author">' + (shop.itemWriter || '') + '</div>' +
                '<div class="price">' + (shop.itemPrice || '0') + '</div>' +
                '<div>' +
                '<button type="button" class="btn btn-secondary" Id="buyButton">바로 구매</button>' +
                '</div>' +
                '</form>' +
                '</div>';
            //빈카드 생성 방지
            if (shop.itemName !== null) {
                $("#resourceFile").append(itemHtml);
            }
        });
    }

    // 구매 버튼 클릭 이벤트 핸들러
    $(document).on('click', '#buyButton', function () {
        var itemId = $(this).closest('.productDetail-card').data('item-id');
        //아이템 구매 확인 메시지
        let buycheck = confirm("정말로 구매하시겠습니까?");

        if (!buycheck) {
            alert("구매를 취소하셨습니다.");
            return; // 구매가 취소된 경우 함수 종료
        }
        if (itemId) {
            console.log("구매 버튼 클릭, 아이템 ID:", itemId);
            // 구매 로직을 여기에 추가
            // 예시: 서버에 구매 요청을 보내는 AJAX 호출
            $.ajax({
                url: '/shop/detail/' + username + '/itemId=' + shop.itemId, // 구매 요청을 처리하는 서버 엔드포인트
                type: 'POST',
                data: { itemId: itemId },
                success: function (response) {
                    console.log("구매 성공:", response);
                    alert("아이템 구매가 완료되었습니다.");
                    // 추가적인 성공 처리 로직을 여기에 작성할 수 있습니다.
                },
                error: function (xhr, status, error, username) {
                    console.error("구매 실패:", error);
                    //url:에 들어가는 값 확인하기
                    console.error("요청 URL:", '/shop/detail/' + username + '/itemId=' + shop.itemId);
                    alert("아이템 구매에 실패했습니다. 나중에 다시 시도해주세요.");
                }
            });
        } else {
            console.error("아이템 ID를 찾을 수 없습니다.");
        }

        // 구매 후 페이지 새로고침
        location.reload();
    });
});