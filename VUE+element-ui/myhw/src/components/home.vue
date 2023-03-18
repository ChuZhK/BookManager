<template>
  <div>
    <TopBar></TopBar>
    <el-tabs tab-position="left" style="height: 100%; margin-top: 5px">
      <el-tab-pane label="图书管理"><Book></Book></el-tab-pane>
      <el-tab-pane label="账号管理"><Acount></Acount></el-tab-pane>
      <el-tab-pane label="出版社信息管理"><Publish></Publish></el-tab-pane>
      <el-tab-pane label="关于系统"><span>图书管理系统 SSE Chuzhk </span></el-tab-pane>
    </el-tabs>
  </div>

</template>

<script>
import Hello from './HelloWorld.vue'
import Book from './book.vue'
import TopBar from './TopBar.vue'
import Acount from './acount.vue'
import Publish from './publish.vue'
export default {
  name: "home",
  components:{
    Hello,
    Book,
    TopBar,
    Acount,
    Publish
  }
}
</script>

<style scoped>

</style>