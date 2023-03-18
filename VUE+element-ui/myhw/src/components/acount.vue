<template>
  <el-tabs type="border-card">
    <el-tab-pane label="账号查询">
        <Search></Search>
    </el-tab-pane>
    <el-tab-pane label="账号注册"><Create></Create></el-tab-pane>
    <el-tab-pane label="账号信息修改"><Edit></Edit></el-tab-pane>
  </el-tabs>
</template>

<script>
import Search from './Acount/search.vue'
import Create from './Acount/create.vue'
import Edit from './Acount/edit.vue'
export default {
  name: "acount",
  components: {Search,Create,Edit}
}
</script>

<style scoped>

</style>