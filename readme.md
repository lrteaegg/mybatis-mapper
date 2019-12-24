spring boot + mybatis + mapper 实现简单的curd



### 开发环境

jdk 1.8

mysql 5.7



### FAQ

项目先加载mapper的工具类之后再初始化dao，否者会报错出现类型转换异常
ClassCastException