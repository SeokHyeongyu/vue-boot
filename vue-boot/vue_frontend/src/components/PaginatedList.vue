<template>
  <div>
    <table class="table table-bordered">
      <tr>
        <th>NO</th>
        <th>NAME</th>
        <th>COLORCODE</th>
        <th>CODENUMBER</th>
        <th>REGDT</th>
      </tr> 
      <tr  v-for="p in paginatedData" :key="p.ROWNUM"> 
        <td>{{ p.ROWNUM }}</td>
        <td>{{ p.NAME }}</td>
        <td>{{ p.COLRCD }}</td>
        <td>{{ p.OBJYKEY }}</td>
        <td>{{ p.REGDT }}</td>
      </tr>
    </table>

    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="btn btn-default">
        이전
      </button>
      <span class="page-count" v-for="(n,index) in pageCount" :key="index" ><a  href="#" @click="pageIndex(index)">{{index+1}}</a></span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="btn btn-default">
        다음
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'paginated-list',
  data () {
    return {
      pageNum: 0
    }
  },
  props: {
    //부모 컨퍼넌트로 부터 전달 받은 array / totalCount 대신 arrat.length로 대체 
    listArray: {
      type: Array,
      required: true
    },
    //한페이지에 보여줄 게시물 수
    pageSize: {
      type: Number,
      required: false,
      default: 10
    }
  },
  methods: {
    //이후 
    nextPage () {
      this.pageNum += 1;
    },
    //이전
    prevPage () {
      this.pageNum -= 1;
    },
    //페이지 번호 이동
    pageIndex(index){
      this.pageNum = index
    }
  },
  computed: {
    pageCount () {
      let listLeng = this.listArray.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      
      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
     var listNum = [];
     for (var i=1; i<page; i++){
       listNum[i]=i;
     }
      return listNum;
    },
    paginatedData () {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;
      return this.listArray.slice(start, end);
    }
  }
}
</script>
<style>
table {
  width: 100%;
  border-collapse: collapse;
}
table th {
  font-size: 1.2rem;
}
table tr {
  height: 2rem;
  text-align: center;
  border-bottom: 1px solid #505050;
}
table tr:first-of-type {
  border-top: 2px solid #404040;
}
table tr td {
  padding: 1rem 0;
  font-size: 1.1rem;
}
.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
</style>
