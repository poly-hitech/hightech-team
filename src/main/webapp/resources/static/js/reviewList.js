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
	    // 원하는 기능 추가
	});
})