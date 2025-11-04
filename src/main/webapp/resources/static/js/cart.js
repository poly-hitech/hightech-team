//이것저것 테스트 해보는 코드
window.addEventListener("load", () => {
    // 장바구니 페이지에서 수량 변경 버튼 클릭 시
    document.querySelectorAll(".apply").forEach(item => {
        // 각 버튼에 클릭 이벤트 리스너를 추가
        // item: 각 버튼 요소
        item.addEventListener("click", e => {
            // e.target: 이벤트가 발생한 요소, closest: 부모 요소 중에서 가장 가까운 tr 태그를 찾음
            // dataset: data- 속성의 값을 가져옴
            const tr = e.target.closest("tr");
            const id = tr.dataset.id;  //data-id 속성의 값을 가져옴
            const { id2 } = tr.dataset;  //data-id 속성의 값을 가져옴
            const amount = tr.querySelector(".input").value;  //input 태그의 값을 가져옴
            const cart = { id, id2, amount };  //장바구니 정보를 객체로 생성

            const url = `/update/${id}/${id2}/${amount}`;  //URL을 생성
            // fetch API를 사용하여 서버에 POST 요청을 보냄
            // fetch: 네트워크 요청을 보내는 함수
            fetch(url, {    // URL: 서버의 엔드포인트, 요청 옵션: method, headers 등
                body: JSON.stringify(cart),  // 요청 본문에 장바구니 정보를 JSON 문자열로 변환하여 포함(직렬화-텍스트로 변환)
                method: "PUT",  // HTTP 메서드: PUT
                headers: {
                    "Content-Type": "application/json"
                }
            }).then(resp => resp.text())  // 응답을 텍스트로 변환
                .then(result => {
                    alert(result.message);  // 서버로부터 응답을 받으면 메시지를 알림으로 표시
                }).catch(error => {
                    // 네트워크 오류가 발생하면 에러 메시지를 출력
                    console.error("Network error:", error);
                });

            // preventDefault: 기본 동작을 방지
            e.preventDefault();

            // axios를 사용하여 서버에 POST 요청을 보냄
            // axios: Promise 기반의 HTTP 클라이언트 라이브러리
            // axios.post(url, { amount })
            //     .then(response => {
            //         // 요청이 성공하면 페이지를 새로고침
            //         window.location.reload();
            //     })
            //     .catch(error => {
            //         // 요청이 실패하면 에러 메시지를 출력
            //         console.error("Error updating cart:", error);
            //     });

        });
    });
});