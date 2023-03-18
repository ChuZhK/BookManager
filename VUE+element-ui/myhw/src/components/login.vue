<template>
  <div class="login">
      <div class="loginPart">
        <h2>图书管理系统</h2>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
          <el-form-item  prop="name">
            <el-input v-model="ruleForm.name" placeholder="用户名（3-15个字符）"></el-input>
          </el-form-item>
          <el-form-item  prop="pwd">
            <el-input v-model="ruleForm.pwd" type="password" placeholder="密码（3-15个字符）"></el-input>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="myLoad()">登录</el-button>
        <el-button type="info" plain>注册</el-button>
    </div>



  </div>

</template>
<script>
import axios from 'axios'
export default {
  data() {
    return {
      ruleForm: {
        name: '',
        pwd: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        pwd: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    myLoad(){
      console.log(this.ruleForm.name);
      console.log(this.ruleForm.pwd);
      axios.get('http://localhost:8084/load?'+'uid='+this.ruleForm.name+'&pwd='+this.ruleForm.pwd)
          .then(res=>{
            console.log(res);
            if(res.data.data<0){
              this.$alert('用户名或密码错误', '登录失败', {
                confirmButtonText: '确定',
                callback: action => {
                  this.$message({
                    type: 'info',
                    message: `action: ${ action }`
                  });
                }
              });
            }
            else{
              this.$router.push('/home')
            }
          })
    }
  }
}
</script>
<style scoped>
.loginPart{
  position:absolute;
  /*定位方式绝对定位absolute*/
  top:50%;
  left:50%;
  /*顶和高同时设置50%实现的是同时水平垂直居中效果*/
  transform:translate(-50%,-50%);
  /*实现块元素百分比下居中*/
  width:450px;
  padding:50px;
  background: rgba(0,0,0,.5);
  /*背景颜色为黑色，透明度为0.8*/
  box-sizing:border-box;
  /*box-sizing设置盒子模型的解析模式为怪异盒模型，
  将border和padding划归到width范围内*/
  box-shadow: 0px 15px 25px rgba(0,0,0,.5);
  /*边框阴影  水平阴影0 垂直阴影15px 模糊25px 颜色黑色透明度0.5*/
  border-radius:15px;
  /*边框圆角，四个角均为15px*/
}
.loginPart h2{
  margin:0 0 30px;
  padding:0;
  color: #fff;
  text-align:center;
  /*文字居中*/
}
.loginPart .inputbox{
  position:relative;
}
.loginPart .inputElement input{
  width: 100%;
  padding:10px 0;
  font-size:16px;
  color:#fff;
  letter-spacing: 1px;
  /*字符间的间距1px*/
  margin-bottom: 30px;
  border:none;
  border-bottom: 1px solid #fff;
  outline:none;
  /*outline用于绘制元素周围的线
  outline：none在这里用途是将输入框的边框的线条使其消失*/
  background: transparent;
  /*背景颜色为透明*/
}

.login{

  width: 100%;
  height: 100vh;
  /*background: url("https://img-blog.csdnimg.cn/ef2cb7d5405348c098777f2188d40228.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAamFja3pqYmFieQ==,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center") center center no-repeat;*/
  background: url("https://webstatic.mihoyo.com/upload/static-resource/2021/11/08/c1b22da8a3281c1bf0a338ea666623e3_944901787911909039.jpg") center center no-repeat;


  background-size: 100% 100%;
  position: fixed;
  /*margin-top: -60px;*/


}
</style>