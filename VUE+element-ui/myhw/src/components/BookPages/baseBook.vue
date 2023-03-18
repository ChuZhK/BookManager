<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" style="width: 800px">
      <el-form-item label="ISBN" prop="isbn">
        <el-row>
          <el-col :span="10">
            <el-input v-model="ruleForm.isbn"></el-input>
          </el-col>
          <el-col :span="4">
            <el-button type="primary" @click="isbnBook()">索引</el-button>

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
      <el-form-item label="出版社" prop="pub">
        <el-input v-model="ruleForm.pub" :placeholder="this.base.pub"></el-input>
      </el-form-item>
      <el-form-item label="价格" >
        <el-input v-model="ruleForm.price" :placeholder="this.base.price"></el-input>
      </el-form-item>


      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">保存修改</el-button>
<!--        <el-button type="success" @click="addNew('ruleForm')">添加图书</el-button>-->
        <el-button type="danger"  @click="deleteBook('ruleForm')">删除</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
import axios from "axios";

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
        name: '',
        isbn: '',
        price:'',
        auther:'',
        pub:''
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
        ]
        // price: [
        //   { required: true, message: '请输入价格', trigger: 'blur' },
        //   { validator:this.valiNumberPass1, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        // ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:8084/saveBase',{
            name:this.ruleForm.name,
            isbn:this.ruleForm.isbn,
            price: this.ruleForm.price,
            auther:this.ruleForm.auther,
            pub:this.ruleForm.auther
          })
              .then(res=>{
                console.log(res)
                if(res.data.data===0){
                  this.$message({
                    showClose: true,
                    message: '图书基本信息修改成功',
                    type: 'success'
                  });
                }
                else {
                  this.$message({
                    showClose: true,
                    message: '图书基本信息修改失败',
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
    deleteBook(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:8084/deleteBase',{
            name:this.ruleForm.name,
            isbn:this.ruleForm.isbn,
            price: this.ruleForm.price,
            auther:this.ruleForm.auther,
            pub:this.ruleForm.auther
          })
              .then(res=>{
                console.log(res.data.data);
                if(res.data.data===-1){
                  this.$message({
                    showClose: true,
                    message: '删除失败，没有这条信息',
                    type: 'error'
                  });

                  this.$refs[this.ruleForm].resetFields();
                }
                else if(res.data.data===0){
                  this.$message({
                    showClose: true,
                    message: '删除成功',
                    type: 'success'
                  });
                  this.base={};
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
        } else {
          console.log('error submit!!');
          this.$message({
            showClose: true,
            message: '删除失败，信息格式不正确',
            type: 'error'
          });
          return false;
        }
      });




    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.base={};
    },
    isbnBook(){
      axios.get('http://localhost:8084/getByIsbn?isbn='+this.ruleForm.isbn)
          .then(res=>{
            console.log(res.data.data)
            // this.ruleForm=res.data.data[0];
            this.base=res.data.data[0];
            this.ruleForm.status=this.base.status===1? '1' : '2';
          })
    },
    fillIt(){
      this.ruleForm=this.base;
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