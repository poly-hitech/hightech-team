$(document).ready(function () {
	$(document).on('click', '.more-menu-btn', function (e) {
	    e.stopPropagation();
	    const reviewId = $(this).data('reviewId');
	    $('.more-menu').hide(); // 다른 메뉴 닫기
	    $('#moreMenu-' + reviewId).toggle();
	});

	$(document).on('click', function () {
	    $('.more-menu').hide();
	});

	$(document).on('click', '.review-edit, .review-delete', function(e){
	    e.preventDefault();
	    $('.more-menu').hide();
	});

//	$(document).on('click', '.review-edit', function(e) {
//        const reviewId = $(this).data('reviewId');
//        const reviewContent = $('#reviewContent-' + reviewId).text();
//        $('#editReviewId').val(reviewId);
//        $('#editReviewContent').val(reviewContent);
//        $('#editReviewModal').modal('show');
//    });

    $(document).on('click', '#review-delete', function(e) {
        e.preventDefault();
        const reviewId = $(this).data('reviewId');
        const itemId = $(this).data('itemId');
        // 리뷰 삭제 확인
        if (!reviewId || !itemId) {
            alert('리뷰 ID 또는 아이템 ID가 잘못되었습니다.');
            return;
        }
        // 리뷰 삭제 요청 전에 확인
        let isConfirm = confirm('리뷰를 정말 삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다.');
        if (!isConfirm) {
            return; // 사용자가 취소를 선택한 경우 함수 종료
        }
        // 리뷰 삭제 요청
        if (isConfirm) {
            $.ajax({
                url: '/shopReview/delete/' + reviewId + '/' + itemId,
                type: 'POST',
                data: {},
                success: function(response) {
                    if (response == "success") {
                        alert('리뷰가 삭제되었습니다.');
                        location.reload();
                    } else {
                        alert('리뷰 삭제에 실패했습니다. 다시 시도해주세요.');
                    }
                },
                error: function() {
                    alert('서버 오류가 발생했습니다. 나중에 다시 시도해주세요.');
                }
            });
        }
    });
})