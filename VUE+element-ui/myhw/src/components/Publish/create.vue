<template>
  <div>
    <el-form ref="form" :model="form" label-width="100px">
      <el-form-item label="出版社名称">
        <el-input v-model="form.name" placeholder="请输入出版社名称"></el-input>
      </el-form-item>
      <el-form-item label="出版社地址">
        <el-input v-model="form.address" placeholder="请输入出版社地址"></el-input>
      </el-form-item>
      <el-form-item label="负责人">
        <el-input v-model="form.person" placeholder="请输入负责人姓名"></el-input>
      </el-form-item>
      <el-form-item label="联系电话">
        <el-input v-model="form.phone" placeholder="请输入联系电话"></el-input>
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
       name:'',
       address:'',
       person:'',
        phone:''

      }
    }
  },
  methods: {

    onSubmit() {
      console.log('submit!');
      console.log(this.form);
      axios.post('http://localhost:8084/addPub',{
        "id":0,
        "name":this.form.name,
        "address":this.form.address,
        "person":this.form.person,
        "phone":this.form.phone
      })
          .then(res=>{
            if(res.data.data<0){
              this.$message({
                type: 'error',
                message: '该出版社已存在'
              });
            }
            else if( res.data.data>0){
              this.$message({
                type: 'success',
                message: '成功添加出版社'
              });
            }
            else{
              this.$message({
                type: 'error',
                message: '添加失败'
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