<template>
  <div>
    <div class="header">
      <div class="box">
        <h1>
          <img src="../assets/logo.png">
          <span style="color: #608bd2">图书管理系统</span>
        </h1>
        <nav>
          <router-link active-class="active" to="/" style="color: #608bd2">首页</router-link>
          <router-link active-class="active" to="/" style="color: #608bd2">问答</router-link>
          <router-link active-class="active" to="/" style="color: #608bd2">社区</router-link>
        </nav>
      </div>
      <div class="box">
        <nav>
          <a style="color: #608bd2">我的</a>
        </nav>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TopBar"
}
</script>

<style scoped>
.header{
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 50px;
  background-color:#FFFAF0;
  color: #ffffff;

  /*吸顶效果*/
  /* position: sticky;
   position: -webkit-sticky;    !*兼容 -webkit 内核的浏览器*!
   top: 0px;                    !*必须设一个值，否则不生效*!*/

}
.box{
  display: flex;
  align-items: center;
}
h1{
  display: flex;
  align-items: center;
  font: normal 28px Cookie, Arial, Helvetica, sans-serif;
  padding: 0px 20px;
}
img{
  width: 40px;
  height: 40px;
}

nav {
  display: flex;
  align-items: center;
  margin: 0px 40px;
  font:16px Arial, Helvetica, sans-serif;
}
nav a{
  padding: 0 15px;
  width: 32px;
  text-decoration:none;
  color: #ffffff;
  font-size: 16px;
  font-weight: normal;
  opacity: 0.9;
}

nav a:hover {
  opacity: 1;
}

.active {
  color: #608bd2;
  pointer-events: none;
  opacity: 1;
}


</style>
