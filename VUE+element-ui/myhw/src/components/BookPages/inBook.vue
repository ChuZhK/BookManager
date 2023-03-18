<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" style="width: 800px">
      <el-form-item label="索书号" prop="bindex">
        <el-row>
          <el-col :span="10">
            <el-input v-model="ruleForm.bindex"></el-input>
          </el-col>
         <el-col :span="4">
           <el-button type="primary" @click="indexBook()">索引</el-button>

         </el-col>
          <el-col :span="2">
            <el-button  @click="fillIt()">一键填充</el-button>
          </el-col>
        </el-row>

      </el-form-item>
      <el-form-item label="书名" prop="name">
        <el-input v-model="ruleForm.name" :placeholder="this.base.name"></el-input>
      </el-form-item>
      <el-form-item label="作者" prop="auther">
        <el-input v-model="ruleForm.auther" :placeholder="this.base.auther"></el-input>
      </el-form-item>
      <el-form-item label="ISBN" prop="isbn">
        <el-input v-model="ruleForm.isbn" :placeholder="this.base.isbn"></el-input>
      </el-form-item>
      <el-form-item label="出版社" prop="pub">
        <el-input v-model="ruleForm.pub" :placeholder="this.base.pub"></el-input>
      </el-form-item>
      <el-form-item label="价格" >
        <el-input v-model="ruleForm.price" :placeholder="this.base.price"></el-input>
      </el-form-item>

      <el-form-item label="图书状态" prop="status">
        <el-radio-group v-model="ruleForm.status">
          <el-radio label="1" >在架上</el-radio>
          <el-radio label="2">已借出</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">保存修改</el-button>
        <el-button type="success" @click="addNew('ruleForm')">添加图书</el-button>
        <el-button type="danger"  @click="deleteBook()">删除</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
import axios from "axios";
import {left} from "core-js/internals/array-reduce";
import search from "@element-plus/icons/lib/Search";
import loading from "@element-plus/icons/lib/Loading";

export default {
  data() {
    return {
      base:{},
      valiNumberPass1 : (rule, value, callback) => {//包含小数的数字
        let reg = /^[+-]?(0|([1-9]\d*))(\.\d+)?$/g;
        if (value === '') {
          callback(new Error('请输入内容'));
        } else if (!reg.test(value)) {
          callback(new Error('请输入数字'));
        } else {
          callback();
        }
      },
      ruleForm: {
        bindex:'',
        name: '',
        auther:'',
        pub:'',
        isbn: '',
        status: '',
        price:''
      },
      rules: {
        name: [
          { required: true, message: '请输入书籍名称', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        auther: [
          { required: true, message: '请输入作者名称', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        isbn: [
          { required: true, message: '请输入ISBN', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        pub: [
          { required: true, message: '请输入出版社名称', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        price: [
          { required: true, message: '请输入价格', trigger: 'blur' },
          { validator:this.valiNumberPass1, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        type: [
          { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
        ],
        resource: [
          { required: true, message: '请选择活动资源', trigger: 'change' }
        ],
        desc: [
          { required: true, message: '请填写活动形式', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:8084/saveBook',this.ruleForm)
              .then(res=>{
                console.log(res)
                if(res.data.data>0){
                  this.$message({
                    showClose: true,
                    message: '馆藏图书信息修改成功',
                    type: 'success'
                  });
                }
                else {
                  this.$message({
                    showClose: true,
                    message: '馆藏图书信息修改失败',
                    type: 'error'
                  });
                }
              })
        } else {
          console.log('error submit!!');
          this.$message({
            showClose: true,
            message: '修改失败，信息格式不正确',
            type: 'error'
          });
          return false;
        }
      });

    },
    addNew(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:8084/addBook?name='+this.ruleForm.name+'&isbn='+this.ruleForm.isbn+'&price='+this.ruleForm.price
          +'&auther='+this.ruleForm.auther+'&bindex='+this.ruleForm.bindex+'&status='+this.ruleForm.status+'&pub='+this.ruleForm.status)
              .then(res=>{
                console.log(res)
                if(res.data.data>0){
                  this.$message({
                    showClose: true,
                    message: '馆藏图书信息添加成功',
                    type: 'success'
                  });
                  this.base=this.ruleForm;
                }
                else {
                  this.$message({
                    showClose: true,
                    message: '馆藏图书信息添加失败',
                    type: 'error'
                  });
                }
              })
        } else {
          console.log('error submit!!');
          this.$message({
            showClose: true,
            message: '添加失败，信息格式不正确',
            type: 'error'
          });
          return false;
        }
      });
    },
    deleteBook(){
      axios.post('http://localhost:8084/deleteBook?bindex='+this.ruleForm.bindex)
          .then(res=>{
            if(res.data.data===1){
              this.$message({
                showClose: true,
                message: '删除成功',
                type: 'success'
              });
              this.base={};
              this.$refs[this.ruleForm].resetFields();
            }
            if(res.data.data<0){
              if(res.data.data===-2){
                this.$message({
                  showClose: true,
                  message: '删除失败，该图书尚未归还',
                  type: 'error'
                });
              }
              else{
                this.$message({
                  showClose: true,
                  message: '删除失败，不存在该图书',
                  type: 'error'
                });
              }

              this.$refs[this.ruleForm].resetFields();
            }
            else{
              this.$message({
                showClose: true,
                message: '删除失败,请重试',
                type: 'error'
              });
            }

          })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.base={};
    },
    indexBook(){
      axios.get('http://localhost:8084/getIndex?bindex='+this.ruleForm.bindex)
          .then(res=>{
            console.log(res.data.data)
            // this.ruleForm=res.data.data[0];
            this.base=res.data.data[0];
            this.ruleForm.status=this.base.status===1? '1' : '2';
          })
    },
    fillIt(){
      this.ruleForm=this.base;
      this.ruleForm.status=this.base.status===1? '1' : '2';
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