<template>
  <div>
    <h1>TABLE PAGINATION</h1>
    <paginated-list :list-array="pageArray" />
  </div>
</template>

<script>
import PaginatedList from "/src/components/PaginatedList.vue";
export default {
  name: 'simple-pagination',
  components: {
    PaginatedList
  },
  data () {
    return {
      pageArray: []
    }
  },
  created () {
    this.$http.get('/getData')
    .then(response => {
      console.log(response);
      this.pageArray = response.data;
    })
    .catch(err => {
      console.log(err);
    });
  }
}
</script>

<style>
h1 {
  color: #454545;
  text-align: center;
}
</style>