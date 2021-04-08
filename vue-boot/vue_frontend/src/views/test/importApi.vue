<template>
<div class="container">
	<div class="form-row float-left">
		<h2 class="text-left">importApi Page</h2>
	</div>
	
		<table  class="table table-bordered">
		<tr>
			<th colspan="2">상품</th>
			<td colspan="3"><input type="text" class="form-control" v-model="order.name" placeholder="상품명"></td>
		</tr>
		<tr>
			<th colspan="2">상품 가격</th>
			<td colspan="3"><input type="number" class="form-control" v-model="order.amount" placeholder="상품가격"></td>
		</tr>
		<tr>
			<th colspan="5" style="text-align: center">주문자 정보 입력</th>
		</tr>
			<td><input type="text" class="form-control" v-model="order.buyer_tel" placeholder="전화번호"></td>
			<td><input type="text" class="form-control" v-model="order.buyer_email" placeholder="이메일 "></td>
			<td><input type="text" class="form-control" v-model="order.buyer_name" placeholder="이름"></td>
			<td><input type="text" class="form-control" v-model="order.buyer_addr"  @click="addrApi" @keyup="addrApi" placeholder="주소"></td>
			<td><input type="text" class="form-control" v-model="order.buyer_postcode" placeholder="우편번호" readonly></td>
		</table>
		<div class="row text-center" style="width: 100%">
			<div style="width: 30%; float:none; margin:0 auto">
				<button v-on:click="requestPay" class="btn btn btn-info btn-round" style="width: 100%">결제</button>
			</div>
		</div>
		
</div>
</template>

<script>
export default {
	data(){
			return {
			impCode :'imp36542975', // 아임포트 impCode
            order: {
                name : '', // 상품명
                amount : null, //가격
				buyer_tel : '', //주문자 전화번호
				buyer_email :'', //주문자 이메일
				buyer_name : '', //주문자 이름
				buyer_addr : '', //주문자 주소
				buyer_postcode : '', //주문자 우편번호
			},

		}	 
	},
	methods:{
		//결제 클리시 결제 페이
		requestPay(){
			var IMP = window.IMP;
			IMP.init(this.impCode);
			var msg;
			//3. 결제창 호출
			IMP.request_pay({
                    pg : 'html5_inicis',
                    pay_method : 'card', //
                    merchant_uid : 'merchant_' + new Date().getTime(),
                    name : this.order.name,
                    amount : this.order.amount,
					buyer_tel : this.order.buyer_tel, //주문자 전화번호 필수
					buyer_email : this.order.buyer_email, //주문자 이메일
					buyer_name : this.order.buyer_name, // 주문자 이름
					buyer_addr : this.order.buyer_addr, // 주문자 주소
					buyer_postcode : this.order.buyer_postcode //주문자  우편번호
                }, function(rsp) {
                    if ( rsp.success ) {
					//4. 결제 요청 결과 서버(자사)에 적용하기
					
                    //ajax 서버 통신 구현 -> 5. 서버사이드에서 validation check
					//-----------------결제완료시 서버로 결제 완료값 전송 로직 구현-------------------
					/* var pData = new URLSearchParams();
	
					pData.append("imp_uid", rsp.imp_uid)
					pData.append("orderName", this.order.name)
					pData.append("orderPay", rsp.paid_amount)
					pData.append("orderTel", this.order.buyer_tel)
					pData.append("orderAddr", this.order.buyer_addr)
					pData.append("orderEmail", this.order.buyer_email)
					pData.append("orderZipCode", this.order.buyer_postcode)
					
					console.log(pData);
					this.$http.post('/getPaymentData', pData)
					.then((result) => {
						console.log(result)
					}) */ 
			
					//-------------------------------------------------------------------
					//6. 최종 서버 응답 클라이언트에서 단계 4.에서 보낸 서버사이드 응답 에따라 결제 성공 실패 출력
					msg = '결제가 완료되었습니다.';
					msg += '고유ID : ' + rsp.imp_uid;
					msg += '상점 거래ID : ' + rsp.merchant_uid;
					msg += '결제 금액 : ' + rsp.paid_amount;
					msg += '카드 승인번호 : ' + rsp.apply_num;
                    } else {
					msg = '결제에 실패하였습니다.';
					msg += '에러내용 : ' + rsp.error_msg;
                    }
					alert(msg);
					window.location.reload(true);
                });
			
		},
		//주소창 클릭 및 키업시 주소 api 오픈
		addrApi(){
				new window.daum.Postcode({
				oncomplete: (data) => {

					//도로명 주소의 노출 규칙에 따라 주소를 조합
					//내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로 참고
					let fullRoadAddr = data.roadAddress; //도로명 주소 변수
					let extraRoadAddr = ''; // 도로명 조합형 주소 변수
					
					//법정동명이 있을 경우 추가
					//법정동의 경우 마지막 문자가 동,로,가로 끝나다.
					if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
					extraRoadAddr += data.bname; 
					}
					//건물명이 있고, 공동주택일 경우 추가한다.
					if(data.buildingName !== '' && data.apartment === 'Y'){
					extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
					}
					//도로명, 지번, 조합형 주소가 있을 경우, 괄호까지 추가한 최종문자열을 만든다.
					if(extraRoadAddr !== ''){
					extraRoadAddr = ' (' + extraRoadAddr + ')'; 
					}
					//도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
					if(fullRoadAddr !== ''){ 
					fullRoadAddr += extraRoadAddr; 
					}
					//우편번호와 주소 정보를 해당 필드에 넣는다.
					this.order.buyer_postcode = data.zonecode; //5자리 새우편번호 사용 
					this.order.buyer_addr = fullRoadAddr;

				}
			}).open()
		}
	},
}
</script>