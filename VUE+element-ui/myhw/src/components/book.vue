<template>
  <el-tabs type="border-card">
    <el-tab-pane label="图书查询">
      <Search></Search>
    </el-tab-pane>
    <el-tab-pane label="馆藏图书管理"><InBook></InBook></el-tab-pane>
    <el-tab-pane label="图书基本信息管理"><BaseBook></BaseBook></el-tab-pane>
    <el-tab-pane label="借阅记录"><Borrow></Borrow></el-tab-pane>
    <el-tab-pane label="归还记录"><Return></Return></el-tab-pane>
  </el-tabs>
</template>

<script>
import Search from './BookPages/search.vue'
import InBook from './BookPages/inBook.vue'
import BaseBook from './BookPages/baseBook.vue'
import Borrow from './BookPages/borrow.vue'
import Return from './BookPages/return.vue'
export default {
  name: "book",
  components:{
    Search,
    InBook,
    BaseBook,
    Borrow,
    Return
  }
}
</script>

<style scoped>

</style>