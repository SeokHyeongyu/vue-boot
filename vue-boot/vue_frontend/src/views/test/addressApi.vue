<template>
  <div>
	<h1>우편번호: <span>{{ zip }}</span></h1>
	<h1>기본주소: <span>{{ addr1 }}</span></h1>
	<h1>상세주소: <span>{{ addr2 }}</span></h1>
	<button @click="api">주소검색</button>
</div>
</template>

<script>
export default {
	data(){
		return{
			zip : "",
			addr1 : "",
			addr2 : ""
		}
	},
	methods : {
		api(){
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
					this.zip = data.zonecode; //5자리 새우편번호 사용 
					this.addr1 = fullRoadAddr;

				}
			//}).embed(this.$refs.embed)
			}).open()
		}
	}
}
</script>