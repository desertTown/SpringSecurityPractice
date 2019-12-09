1.引入依赖(pom.xml)
<dependency>
	<groupId>com.imooc.security</groupId>
	<artifactId>imooc-security-browser</artifactId>
	<version>1.0.0-SNAPSHOT</version>
</dependency>

2.配置系统(参见 application-example.properties)

3.增加UserDetailsService接口实现

4.如果需要记住我功能，需要创建数据库表(参见 db.sql)

5.如果需要社交登录功能，需要以下额外的步骤
1).配置appId和appSecret
2).创建并配置用户注册页面，并实现注册服务(需要配置访问权限)，注意在服务中要调用ProviderSignInUtils的doPostSignUp方法。
3).添加SocialUserDetailsService接口实现
4).创建社交登录用的表 (参见 db.sql)


Mysql 8.x using issue
> 在使用mysql8.x的时候， 在执行QQ或者微信登录的时候会遇到rank函数冲突的问题， 可以使用上面文件JdbcConnectionRepository去替换本地jar包对应的文件

> 参考自: https://github.com/spring-projects/spring-social/pull/262/commits/48d49d806571385fd0b68777376b77fadfcb23c6