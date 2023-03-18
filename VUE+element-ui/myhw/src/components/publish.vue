<template>
  <el-tabs type="border-card">
    <el-tab-pane label="出版社查询">
      <Search></Search>
    </el-tab-pane>
    <el-tab-pane label="新增出版社"><Create></Create></el-tab-pane>
    <el-tab-pane label="出版社信息修改"><Edit></Edit></el-tab-pane>
  </el-tabs>
</template>

<script>
import Search from './Publish/search.vue'
import Create from './Publish/create.vue'
import Edit from './Publish/edit.vue'
export default {
  name: "publish",
  components:{
    Search,
    Create,
    Edit
  }
}
</script>

<style scoped>

</style>