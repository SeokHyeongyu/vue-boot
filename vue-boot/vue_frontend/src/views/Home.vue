
<template>
  <div>
    <h1  class="text-center">{{title}}</h1>
    <div class="row">
     
        <div class="col-sm-9 col-xs-12">
           <div class="form-inline text-middle">
            <input  type="text" class="form-control" v-model="input1" />
            <button type="button"  class="btn btn-success" @click="getData">조회</button>
        <!--<button type="button" @click="setData">set</button> -->
            </div>     
        </div>
   </div>

    <select class="form-control" v-model="region" @change="changeRegion">
        <option v-for="(item, index) in options" :key="index" :value="item.v">{{item.t}}</option>
    </select>
    <table class="table table-bordered" v-show="tableShow">
        <tr  v-for="(item, index) in options" :key="index">
            <td>{{item.v}}</td>
            <td>{{item.t}}</td>
        </tr> 
    </table>
    <table class="table table-bordered"> 
        <thead>
            <tr>
                <th style="text-align: center; width: 80px; background: #6c757d;">NO</th>
                <th style="text-align: center">이름</th>
                <th style="text-align: center">컬러코드</th>
                <th style="text-align: center">코드번호</th>
                <th style="text-align: center">생성일자</th>
            </tr>
        </thead>
        <tbody>
            <tr  v-for="(item, index) in rowData" :key="index"> 
                <td class="text-center" style="background: #6c757d;">{{index}}</td>
                <td class="text-center">{{item.NAME}}</td>
                <td class="text-center">{{item.COLRCD}}</td>
                <td class="text-center">{{item.OBJYKEY}}</td>
                <td class="text-center">{{item.REGDT}}</td>
            </tr>
        </tbody>
    </table>
  </div>
</template>
<script>
export default {
    data(){
        return{
            title : "",
            input1 : "",
            options :[
                {v:"S", t:"Seoul"},
                {v:"J", t:"Jeju"},
                {v:"B", t:"Busan"},
            ],
            rowData :"",
            region : "",
            tableShow : false,
        };
    },
    //watch 메소드는 객체의 이름과 동일한 이름을 함수로 만들어 실시간으로 변화 하는값을 출력한다
    watch:{
        input1(){
            console.log(""+this.input1)
        }
    },
    methods:{
        getData(){
        const pData = new URLSearchParams();
        pData.append("keyWord", this.input1)
        this.$http.post('/getData', pData)
        .then((result) => {
            console.log(result)
            this.rowData = result.data
        })
        },
        setData(){
            this.input1 = "1234"
        },
        changeRegion(){
            alert(this.region)
        },
    },
    beforeCreate(){
        //console.log("beforeCreate");
    },
    created(){
        this.$http.get('/getData')
        .then((result) => {
            console.log(result.data)
            this.title = "List"
            this.rowData = result.data
        })

        //console.log("created 시작과 동시에 동작 ex 디비에서 데이터를 미리 들고와서 바로 뿌려준다");
    },
    beforeMount(){
        //console.log("beforeMount");
    },
    mounted(){
        //console.log("mounted");
    },
    beforeUpdate(){
        //console.log(" beforeUpdate 데이터 변경시 동작");
    },
    updated(){ 
        //console.log(" 데이터 변경시 동작");
    },
    beforeDestroy(){
        //console.log("beforeDestroy");
    },
    destroyed(){
        //console.log("destroyed");
    },
   
};
</script>
