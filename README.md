# SpringSecurityPractice

4-4  个性化用户认证流程(一)

http://localhost:8060/user
```
{
"content": "访问的服务需要身份认证，请引导用户到登录页"
}

由前台代码处理，重定向到登录页
```

http://localhost:8060/index.html

如果app有配置属性： evan.security.browser.loginPage = /demo-signIn.html
系统将自动定位到demo-signIn.html  这个作为登录页， 如果没配， 则使用默认的evan-signIn.html

4-5  
可以根据配置去选择登录以后的处理方式(重定向或者返回JSON数据)

案例：
在DEMO模块中添加
evan.security.browser.loginType = REDIRECT
系统将会有重定向的行为
例如访问
localhost:8060/index.html   ->  首先会跳转到登录页， 登录成功之后会重定向到index.html

如果配置的是
evan.security.browser.loginType = JSON   （Browser模块默认是返回JSON）
访问 localhost:8060/index.html  无论成功还是失败， 都只会放回JSON 信息， 逻辑需要前端去处理

4-6 获取认证用户信息
http://localhost:8060/evan-signIn.html  登录
http://localhost:8060/user/me   获取认证用户信息
http://localhost:8060/user/meWithAuthenticationInfo

4-8 图片验证码重构
http://localhost:8060/evan-signIn.html
登录成功之后访问
http://localhost:8060/user   能正常访问
访问这个会报错 -> 验证码的值不能为空。 因为我们配置了url user/* 需要验证码验证
http://localhost:8060/user/1

4-9 添加记住我功能
测试
1.  http://localhost:8060/user   会提示没权限
2. http://localhost:8060/evan-signIn.html   正确输入登录信息， 并且勾上记住我选项
3. SELECT * FROM persistent_logins   查看数据发现会多出刚才登录的信息
4. 重启服务
5. http://localhost:8060/user    发现不用登录也能访问

5-4 
建表语句
```sql
create table UserConnection (userId varchar(255) not null,
	providerId varchar(255) not null,
	providerUserId varchar(255),
	rank int not null,
	displayName varchar(255),
	profileUrl varchar(512),
	imageUrl varchar(512),
	accessToken varchar(512) not null,
	secret varchar(512),
	refreshToken varchar(512),
	expireTime bigint,
	primary key (userId, providerId, providerUserId));
create unique index UserConnectionRank on UserConnection(userId, providerId, rank);
```


5-6

测试QQ登录使用这个url(需要host文件映射 127.0.0.1	www.pinzhi365.com)

http://www.pinzhi365.com/evan-signIn.html