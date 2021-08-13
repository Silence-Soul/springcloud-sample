# springcloud-sample
学习笔记


# 问题
## 1、Did you forget to include spring-cloud-starter-loadbalancer
出现这个问题是没有loadbalanc 但是nacos中ribbon会造成loadbalanc包失效

由于SpringCloud Feign在Hoxton.M2 RELEASED版本之后不再使用Ribbon而是使用spring-cloud-loadbalancer，所以不引入spring-cloud-loadbalancer会报错
在common的pom加入
```bazaar
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-loadbalancer</artifactId>
    <version>2.2.4.RELEASE</version>
</dependency>

```

```bazaar
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    <exclusions>
        <exclusion>
            <groupId> springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```
# 2、解决SpringCloud2020整合Nacos-Bootstrap，配置中心不生效的问题
```bazaar
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```

# VUE 模板 片段代码
```bazaar
{
	"生成vue模板": {
		"prefix": "vue",
		"body": [
			"<!-- $1 -->",
			"<template>",
			"<div></div>",
			"</template>",
			"",
			"<script>",
			"//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）",
			"//例如：import 《组件名称》 from '《组件路径》';",
			"",
			"export default {",
			"//import引入的组件需要注入到对象中才能使用",
			"components: {},",
			"props: {},",
			"data() {",
			"//这里存放数据",
			"return {",
			"",
			"};",
			"},",
			"//监听属性 类似于data概念",
			"computed: {},",
			"//监控data中的数据变化",
			"watch: {},",
			"//方法集合",
			"methods: {",
			"",
			"},",
			"//生命周期 - 创建完成（可以访问当前this实例）",
			"created() {",
			"",
			"},",
			"//生命周期 - 挂载完成（可以访问DOM元素）",
			"mounted() {",
			"",
			"},",
			"beforeCreate() {}, //生命周期 - 创建之前",
			"beforeMount() {}, //生命周期 - 挂载之前",
			"beforeUpdate() {}, //生命周期 - 更新之前",
			"updated() {}, //生命周期 - 更新之后",
			"beforeDestroy() {}, //生命周期 - 销毁之前",
			"destroyed() {}, //生命周期 - 销毁完成",
			"activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发",
			"}",
			"</script>",
			"<style scoped>",
			"",
			"$4",
			"</style>"
		],
		"description": "生成vue模板"
	}
}
```

# VUE HTTP POST GET 模板
## http post
```bazaar
this.$http({
  url: this.$http.adornUrl(''),
  method: 'post',
  data: this.$http.adornData(ids, false)
}).then(({data}) => { })


```
## http get
```bazaar
this.$http({
  url: this.$http.adornUrl(''),
  method: 'get',
  params: this.$http.adornParams({ })
}).then(({data}) => { })
```