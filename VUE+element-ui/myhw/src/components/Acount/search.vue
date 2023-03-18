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
        <el-table-column label="读者编号" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <el-icon><timer /></el-icon>
              <span style="margin-left: 10px">{{ scope.row.uid }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="密码" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span style="margin-left: 10px">{{ scope.row.pwd }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="读者等级" sortable sort-method="sortFun(a,b)" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span style="margin-left: 10px">{{ scope.row.lev }}</span>
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
        <el-table-column label="待还数量" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span style="margin-left: 10px">{{ scope.row.borrow_num-scope.row.return_num }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="余额" width="80">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span :style="classStatus(scope.row.wage >0 )" >
                {{ scope.row.wage }}
              </span>
            </div>
          </template>
        </el-table-column>

        <el-table-column label="Operations">
          <template #default="scope">
            <el-button size="small" type="primary" @click="handleEdit(scope.row.uid)" :disabled="scope.row.status===2 ? true : false"
            >缴费</el-button
            >
            <el-button
                size="small"
                type="success"
                @click="handleDelete(scope.row.uid)" :disabled="scope.row.status===1 ? true : false"
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
        axios.get('http://localhost:8084/getAllUser')
            .then(res=>{
              console.log(res.data.data);
              this.tableData=res.data.data;
              console.log(this.tableData);
            })
      }
      else{
        //search book by inputs
          axios.get('http://localhost:8084/getUserById?uid='+this.input)
              .then(res=>{
                console.log(res.data.data);
                this.tableData=[res.data.data];
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
    handleDelete(uid){
      console.log("uid ---->>>> "+uid)
      axios.post('http://localhost:8084/deleteUser?uid='+uid)
          .then(res=>{
            if(res.data.data===0){
              this.$message({
                type: 'success',
                message: '成功删除账号'
              });
              this.search();
            }
            else if(res.data.data===-1){
              this.$message({
                type: 'error',
                message: '该用户还有未归还书籍，无法删除'
              });
            }
            else{
              this.$message({
                type: 'error',
                message: '删除账号失败'
              });
            }
          })
    }
  }
}
</script>

<style scoped>

</style>