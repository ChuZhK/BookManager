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
              <el-dropdown-item @click.enter="change(1)">索书号</el-dropdown-item>
              <el-dropdown-item @click.enter="change(2)">按读者编号</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-col>
    </el-row>
    <el-table
        :data="tableData"
        style="width: 100%"
        :default-sort = "{prop: 'date', order: 'descending'}"
        height="560"
    >
      <el-table-column
          prop="bindex"
          label="索书号"
          width="180">
      </el-table-column>
      <el-table-column
          prop="bid"
          label="读者编号"
          width="180">
      </el-table-column>
      <el-table-column
          prop="return_time"
          label="归还时间"
          sortable
          :formatter="formatter">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "return",
  data(){
    return{
      btn_txt:'搜索全部',
      search_index:0,
      input:'',
      tableData: [{
        time: '2023-01-29 12:56:20',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }]
    }
  },
  methods:{
    change(index){
      this.search_index=index;
      switch (index){
        case 0:
          this.btn_txt="搜索全部";
          break;
        case 1:
          this.btn_txt="按索书号";
          break;
        case 2:
          this.btn_txt="按读者编号";
          break;
      }
    },
    search(){
      console.log("input---> "+ this.input)
      switch (this.search_index){
        case 0:
          axios.get('http://localhost:8084/ReturnAll')
              .then(res=>{
                    this.tableData=res.data.data;
                    console.log(res.data.data);
                  }
              )
          break;
        case 1:
          axios.get('http://localhost:8084/ReturnInfoByIndex?bindex='+this.input)
              .then(
                  res=>{
                    console.log(res.data.data);
                    this.tableData=res.data.data;
                  }
              )
          break;
        case 2:
          axios.get('http://localhost:8084/ReturnById?bid='+this.input)
              .then(
                  res=>{
                    console.log(res.data.data);
                    this.tableData=res.data.data;
                  }
              )
          break;
      }
    }
  },
  created() {
    this.search();
  }


}
</script>

<style scoped>

</style>