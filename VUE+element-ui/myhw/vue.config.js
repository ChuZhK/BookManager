const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

module.exports = {
  lintOnSave: false,
  chainWebpack: config => {
    config.resolve.extensions
        .add('ts')
        .add('tsx');
  }
}

