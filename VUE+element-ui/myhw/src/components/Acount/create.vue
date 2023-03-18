<template>
<div>
  <el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="读者编号">
      <el-input v-model="form.uid" placeholder="请输入读者编号"></el-input>
    </el-form-item>
    <el-form-item label="用户密码">
      <el-input v-model="form.pwd" placeholder="请输入用户密码"></el-input>
    </el-form-item>
    <el-form-item label="读者等级">
      <el-input v-model="form.lev" placeholder="请输入读者等级"></el-input>
    </el-form-item>
    <el-form-item label="联系电话">
      <el-input v-model="form.phone" placeholder="请输入联系电话"></el-input>
    </el-form-item>




    <el-form-item label="性别">
      <el-radio-group v-model="form.sex">
        <el-radio label=1>男</el-radio>
        <el-radio label=0>女</el-radio>
      </el-radio-group>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSubmit">立即创建</el-button>
      <el-button @click="cancle()">取消</el-button>
    </el-form-item>
  </el-form>
</div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      form: {
        uid:'',
        pwd:'',
        lev:'',
        sex:'',
        phone:'',
        borrow_num: 0,
        return_num: 0,
        wage: 0.0
      }
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!');
      console.log(this.form);
      axios.post('http://localhost:8084/addUser',{
        "uid":this.form.uid,
        "pwd":this.form.pwd,
        "lev":this.form.lev,
        "sex":this.form.sex,
        "phone":this.form.phone,
        "borrow_num": 0,
        "return_num": 0,
        "wage": 0.0
      })
          .then(res=>{
            if(res.data.data<0){
              this.$message({
                type: 'error',
                message: '该账号已存在'
              });
            }
            else if( res.data.data>0){
              this.$message({
                type: 'success',
                message: '成功注册账号'
              });
            }
            else{
              this.$message({
                type: 'error',
                message: '注册账号失败'
              });
            }
          })
    },
    cancle(){
      this.form=''
    }
  }
}
</script>

<style scoped>

</style>