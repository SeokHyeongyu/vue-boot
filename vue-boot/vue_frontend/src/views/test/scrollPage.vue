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
import InfiniteLoading from "vue-infinite-loading";
export default {
    data(){
        return{
          rowData:[],
            limit : 0,
        }
    },
  methods: {
    infiniteHandler($state) {
      this.$http.get("/getScorllPage/"+(this.limit+10))
        .then(result =>{
          setTimeout(()=>{
            if(result.data.length){
              this.rowData=this.rowData.concat(result.data);
              $state.loaded();
              this.limit+=10
              if(this.rowData.length / 10 == 0){
                $state.complete();
              }
            }else{
              $state.complete();
            }
          }, 1000)
        }).catch(error =>{
          console.error(error);
        })
    }
  },
  created(){
     this.$http.get("/getScorllPage/"+this.limit)
     .then((result)=>{
       console.log(result.data.length)
         this.rowData=result.data;
                console.log(this.rowData.length)
     })
  },
    components: {
        InfiniteLoading
    }
}
</script>