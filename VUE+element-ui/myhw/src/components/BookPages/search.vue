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
      <el-col :span="3" :pull="1">
        <el-dropdown>
          <el-button >
            {{this.btn_txt}}<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <template v-slot:dropdown>
            <el-dropdown-menu  >
              <el-dropdown-item @click.enter="change(0)">搜索全部</el-dropdown-item>
              <el-dropdown-item @click.enter="change(1)">按书名</el-dropdown-item>
              <el-dropdown-item @click.enter="change(2)">按作者</el-dropdown-item>
              <el-dropdown-item @click.enter="change(3)">索书号</el-dropdown-item>
              <el-dropdown-item @click.enter="change(4)">ISBN</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-col>
    </el-row>
    <div class="table">
      <el-table  :data="tableData" style="width: 100%" height="560">
        <el-table-column label="书名" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <el-icon><timer /></el-icon>
              <span style="margin-left: 10px">{{ scope.row.name }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="作者" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span style="margin-left: 10px">{{ scope.row.auther }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="索书号" width="180">
          <template #default="scope">
            <el-popover effect="light" trigger="hover" placement="top" width="auto">
              <template #default>
                <div>ISBN: {{ scope.row.ISBN }}</div>
                <div>价格: {{ scope.row.price }}</div>
              </template>
              <template #reference>
                <el-tag>{{ scope.row.bindex }}</el-tag>
              </template>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="ISBN" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span style="margin-left: 10px">{{ scope.row.isbn }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="出版社" width="180">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span style="margin-left: 10px">{{ scope.row.name }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="状态" width="80">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span :style="classStatus(scope.row.status)" >
                {{ scope.row.status===1 ? this.rtn: this.borrow  }}
              </span>
            </div>
          </template>
        </el-table-column>

        <el-table-column label="Operations">
          <template #default="scope">
            <el-button size="small" type="primary" @click="handleEdit(scope.row.bindex)" :disabled="scope.row.status===2 ? true : false"
            >借阅</el-button
            >
            <el-button
                size="small"
                type="success"
                @click="handleDelete(scope.row.bindex)" :disabled="scope.row.status===1 ? true : false"
            >归还</el-button
            >
          </template>
        </el-table-column>
      </el-table>

    </div>


  </div>

</template>

<script>
import axios from "axios";
import {left} from "core-js/internals/array-reduce";
import search from "@element-plus/icons/lib/Search";
import loading from "@element-plus/icons/lib/Loading";

export default {
  name: "search",
  data() {
    return {
      input:'',
      return_success:false,
      btn_txt:"搜索全部",
      search_index:0,
      borrow:"外借中",
      rtn:"在架上",
      tableData: []
    }
  },
  created() {
    this.search();
  },
  methods:{
    loading() {
      return loading
    },
    left,
    change(index){
      this.search_index=index;
      switch (index){
        case 0:
          this.btn_txt="搜索全部";
          break;
        case 1:
          this.btn_txt="按书名";
          break;
        case 2:
          this.btn_txt="按作者";
          break;
        case 3:
          this.btn_txt="索书号";
          break;
        case 4:
          this.btn_txt="ISBN";
          break;
      }
    },
    handleEdit(index) {
      console.log(index);
      this.$prompt('请输入读者编号', '图书借阅', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        // inputPattern: /[\u4e00-\u9fa5_a-zA-Z0-9_]{3,10}/,
        // inputErrorMessage: '读者编号不正确'
      }).then(({ value }) => {
        axios.post('http://localhost:8084/lendBook',{
          "bindex":index,
          "uid":value
        }).then(res=>{
          console.log("this is res.data"+ res.data);
          console.log("this is res.data.data" + res.data.data);
          if(res.data.data>0){
            this.$message({
              type: 'success',
              message: '读者  ' + value+" 成功借阅"
            });
            this.search();
          }
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },
    handleDelete(index, row) {
      console.log(index, row);
      this.$prompt('请输入图书破损罚款金额，单位:元', '图书归还', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPlaceholder:'无需罚款请输入0',
        inputPattern: /^[0-9]+\.{0,1}[0-9]{0,2}$/,
        inputErrorMessage: '请输入小数点后两位以内的小数'
      }).then(({ value }) => {
        axios.post('http://localhost:8084/returnBook',{
          "bindex":index,
          "wage":value
        }).then(res=>{
          console.log(res);
          if(res.data.data>0){
            this.$message({
              type: 'success',
              message: '归还成功！ '
            });
            this.search();
            this.return_success=true;
          }
          else  if(res.data.data<0){
            this.$notify({
              title: '警告',
              message: '图书逾期! 已罚款'+ res.data.data +'元',
              type: 'warning'
            });
          }
          else {
            this.$message({
              type: 'error',
              message: '归还失败！ '
            });
          }
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });

    },
    classStatus(status){
      if(status===2){
        return 'color:red'
      }
      else{
        return 'color:blue'
      }
    },
    search(){

      if (this.input ==='') {
        //search all books
        axios.get('http://localhost:8084/getall')
            .then(res=>{
              this.tableData=res.data.data;
              console.log(this.tableData);
            })
      }
      else{
        //search book by inputs
        switch (this.search_index){
          case 0:
            axios.get('http://localhost:8084/getall')
                .then(res=>{
                  this.tableData=res.data.data;
                })
            break;
          case 1:
            axios.get('http://localhost:8084/getName?'+'name='+this.input)
                .then(res=>{
                  this.tableData=res.data.data;
                })
            break;
          case 2:
            axios.get('http://localhost:8084/getWriter?'+'auther='+this.input)
                .then(res=>{
                  this.tableData=res.data.data;
                })
            break;
          case 3:
            axios.get('http://localhost:8084/getIndex?'+'bindex='+this.input)
                .then(res=>{
                  this.tableData=res.data.data;
                })
            break;
          case 4:
            axios.get('http://localhost:8084/getByIsbn?'+'isbn='+this.input)
                .then(res=>{
                  this.tableData=res.data.data;
                })
            break;
        }
      }
    }
  }
}
</script>

<style scoped>
.el-dropdown {
  vertical-align: top;
}
.el-dropdown + .el-dropdown {
  margin-left: 15px;
}
.el-icon-arrow-down {
  font-size: 12px;
}
.table{
  margin-top: 10px;
}
</style>