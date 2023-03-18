<template>
  <div>
    <el-row>
      <el-col :span="10">
        <el-input v-model="input" placeholder="请输入内容"></el-input>
      </el-col>
      <el-col :span="2">
        <el-button type="primary" @click="search()">
          <el-icon color="838B83" class="inherit">
            <Search />
          </el-icon>
        </el-button>
      </el-col>
    </el-row>
    <div class="table">
      <el-table  :data="tableData" style="width: 100%" height="560">
        <el-table-column  label="出版社编号" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <el-icon><timer /></el-icon>
              <span style="margin-left: 10px">{{ scope.row.id }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="出版社名称" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span style="margin-left: 10px">{{ scope.row.name }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="出版社地址"  sort-method="sortFun(a,b)" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span style="margin-left: 10px">{{ scope.row.address }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="负责人" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span style="margin-left: 10px">{{ scope.row.person }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="联系电话" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span style="margin-left: 10px">{{ scope.row.phone }}</span>
            </div>
          </template>
        </el-table-column>


        <el-table-column label="Operations">
          <template #default="scope">
            <el-button
                size="small"
                type="success"
                @click="handleDelete(scope.row.id,scope.row.name)"
            >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

    </div>


  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "search",
  data(){
    return {
      btn_txt:'搜索全部',
      input:'',
      tableData:[]
    }

  },
  created() {
    this.search();
  },
  methods:{
    search(){
      console.log("inpout====>>>"+this.input);
      console.log("this is in search count fun")
      if (this.input ==='') {
        //search all books
        axios.get('http://localhost:8084/allPub')
            .then(res=>{
              console.log(res.data.data);
              this.tableData=res.data.data;
              console.log(this.tableData);
            })
      }
      else{
        //search book by inputs
        axios.get('http://localhost:8084/pubByName?name='+this.input)
            .then(res=>{
              console.log(res.data.data);
              this.tableData=res.data.data;
            })

      }
    },
    classStatus(status){
      if(status===false){
        return 'color:red'
      }
      else{
        return 'color:blue'
      }
    },

    sortFun(a,b){
      return a<b;
    },
    handleDelete(uid,name){
      console.log("uid ---->>>> "+uid)
      axios.post('http://localhost:8084/deletePub?name='+name+'&id='+uid)
          .then(res=>{
            if(res.data.data===0){
              this.$message({
                type: 'success',
                message: '成功删除出版社信息'
              });
              this.search();
            }
            else if(res.data.data===-1){
              this.$message({
                type: 'error',
                message: '删除失败，存在该出版社出版的图书'
              });
            }
            else{
              this.$message({
                type: 'error',
                message: '删除信息失败'
              });
            }
          })
    }
  }
}
</script>

<style scoped>

</style>