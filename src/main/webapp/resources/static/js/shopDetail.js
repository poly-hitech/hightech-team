$(document).ready(function () {
    // 구매 버튼 클릭 이벤트 핸들러
    $(document).on('click', '#buy', function () {
        const userId = $(this).data('userId');
        const itemId = $(this).data('itemId');
		if (userId == 0) {
        	alert("로그인이 필요한 서비스입니다.");
        	window.location.href = "/login"; // 로그인 경로로 이동
        	return;
    	}
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
                url: `/shop/detail/${userId}/${itemId}`, // 구매 요청을 처리하는 서버 엔드포인트
                type: 'POST',
                contentType: 'application/json',
    			data: JSON.stringify([shop]),
                success: function (response) {
                    console.log("구매 성공:", response);
                    alert("아이템 구매가 완료되었습니다.");
                    //구매 후 페이지 리디렉션
                    window.location.href = "/shop/detail" + itemId;
                },
                error: function (xhr, status, error) {
                    console.error("구매 실패:", error);
                    alert("아이템 구매에 실패했습니다. 나중에 다시 시도해주세요.");
                }
            });
        } else {
            console.error("아이템 ID를 찾을 수 없습니다.");
        }
    });

    // 리뷰 등록 버튼 클릭 핸들러
    $(document).on('click', '#addReview', function (event) {
        event.preventDefault(); // 폼 제출 방지
        const userId = $(this).data('userId');
        const itemId = $(this).data('itemId');
        const reviewWriter = $(this).data('reviewWriter');
        const reviewContent = $('#reviewContent').val();
        const ordersDetailsId = $(this).data('ordersDetailsId');
        const reviewRating = $('input[name="reviewRating"]:checked').val();

        if (userId == 0) {
            alert("로그인이 필요한 서비스입니다.");
            window.location.href = "/login"; // 로그인 경로로 이동
            return;
        }

        if(!itemId) {
            alert("아이템 ID가 없습니다.");
            return;
        }

        if(!ordersDetailsId) {
            alert("리소스를 구매한 경우에만 리뷰를 작성할 수 있습니다.");
            return;
        }

        if (!reviewContent) {
            alert("리뷰 내용을 입력해주세요.");
            return;
        }

        if (!reviewRating) {
            alert("별점을 선택해주세요.");
            return;
        }

        // 리뷰 등록 요청
        $.ajax({
            url: `/shopReview/review`,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                userId: userId,
                itemId: itemId,
                reviewContent: reviewContent,
                reviewCount: parseInt(reviewRating),
                reviewWriter: reviewWriter,
                ordersDetailsId: ordersDetailsId
            }),
            success: function (response) {
                console.log("리뷰 등록 성공:", response);
                alert("리뷰가 등록되었습니다.");
                // 리뷰 등록 후 페이지 새로고침
                window.location.reload();
            },
            error: function (xhr, status, error) {
                console.error("리뷰 등록 실패:", error);
                alert("리뷰 등록에 실패했습니다. 나중에 다시 시도해주세요.");
            }
        });
    });

   $(document).ready(function () {
       $('input[name="ratingSort"]').on('change', function () {
           const sortValue = $(this).val();
           const itemId = $('#addReview').data('itemId'); // 등록버튼에 있는 값 활용

           $.ajax({
               url: '/shopReview/sorted/' + itemId, // 정렬된 리뷰를 가져오는 엔드포인트
               method: 'GET',
               data: {
                   sortType: sortValue
               },
               success: function (data) {
                   $('.review-list').html(data); // 부분 교체
               },
               error: function () {
                   alert("리뷰 정렬에 실패했습니다.");
               }
            });
        });
    });

    $(document).on('click', '.page-btn', function(e) {
        e.preventDefault();
        const page = $(this).data('page');
        const sortType = $(this).data('sort') || 'latest';
        const itemId = $('#addReview').data('itemId');

        $.ajax({
            url: '/shopReview/sorted/' + itemId,
            type: 'GET',
            data: {
                sortType: sortType,
                page: page
            },
            success: function(data) {
                $('.review-list').html(data);
            },
            error: function() {
                alert("페이지 이동에 실패했습니다.");
            }
        });
    });
});