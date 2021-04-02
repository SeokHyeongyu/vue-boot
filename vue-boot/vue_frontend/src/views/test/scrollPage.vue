<template>
  <div>
    <h1>scrollPage</h1>
    <table class="table table-bordered">
      <tr>
        <th>INOUT_SEQ</th>
        <th>BODY_NO</th>
        <th>TAG_ID</th>
        <th>PROC_CD</th>
        <th>REGDT</th>
      </tr> 
      <tr  v-for="p in rowData" :key="p.INOUT_SEQ"> 
        <td>{{ p.PROD_PLAN_NO }}</td>
        <td>{{ p.JOB_ORD_CD }}</td>
        <td>{{ p.JOB_CD }}</td>
        <td>{{ p.PROD_CD }}</td>
        <td>{{ p.REG_DT }}</td>
      </tr>
    </table>
    <infinite-loading @infinite="infiniteHandler" spinner="waveDots">
          <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 20px; padding: 10px 0px;">조회 가능 데이터가 없습니다 </div>
    </infinite-loading>
  </div>
</template>

<script>
import InfiniteLoading from "vue-infinite-loading"; //vue-infinite-loading import
export default {
    data(){
        return{
          rowData:[], // 조회 데이터를 담을 배열
            limit : 0, //limit 변수 생성 
        }
    },
  methods: {
    infiniteHandler($state) { 
      this.$http.get("/getScorllPage/"+(this.limit+10)) //url에 limit트 값에 10을 더하여 추가 데이터를 조회
        .then(result =>{
          setTimeout(()=>{
            if(result.data.length){ // 서버에서 전달받은 리스트의 길이확인
              this.rowData=this.rowData.concat(result.data); // 기존 데이터에 이러 붙친다.
              $state.loaded();
              this.limit+=10 // limit값 추가
              if(this.rowData.length / 10 == 0){ // 조회 데이터의 값이 확인
                $state.complete(); // 값이 0이면 조회 종료
              }
            }else{
              $state.complete();
            }
          }, 1000) //1초에 한번 조회
        }).catch(error =>{
          console.error(error);
        })
    }
  },
  created(){ // 시작과 동시에 데이터 조회 (최초 조회 0~10까지만)
     this.$http.get("/getScorllPage/"+this.limit)
     .then((result)=>{
         this.rowData=result.data;
     })
  },
    components: {
        InfiniteLoading
    }
}
</script>
