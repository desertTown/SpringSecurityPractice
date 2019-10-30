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

5-4 开发QQ登录（中）
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


5-6 处理QQ注册逻辑

测试QQ登录使用这个url(需要host文件映射 127.0.0.1	www.pinzhi365.com)

http://www.pinzhi365.com/evan-signIn.html


5-8 绑定与解绑处理

1.在登录成功之后, 访问
> http://www.pinzhi365.com/connect

查看该账号与哪些社交账号绑定了

2. 登录成功之后， 访问
> http://www.pinzhi365.com/evan-banding.html

然后跳转到微信扫码界面， 确认登录之后， 使用sql查询， 会发现多一条数据
> SELECT * FROM imooc_userconnection


3. 解绑
 
 手动触发解绑操作 可以使用网页版的restClient  去发请求测试，
 
 DELETE http://www.pinzhi365.com/connect/weixin
 
 发了之后查询DB，SELECT * FROM imooc_userconnection  会发现删除相关结果成功
 
 
 6-6 重构社交登录
 测试：
 1. demo项目依赖browser模块， 以debug模式启动
 2. 在 OAuth2AuthenticationService.getAuthToken(HttpServletRequest request, HttpServletResponse response)打 个断点
 3. http://www.pinzhi365.com/evan-signIn.html  登录微信
 4. F9 开发者模式查看有一条包含授权码code和state的重定向url， 拷贝起来
 5. 进入断点, 停掉IDEA
 6. 拷贝此时的url
 7. demo项目依赖app模块， 启动
 8. 将刚才的url复制到Talend API Tester 工具上(07社交登录)访问(带上Authorization信息)
 9. 这里是访问失败的， 异常信息提示code被用过了， 怀疑刚才IDEA 关闭的时候， 上一次的请求还是发出去了
 
 6-9 使用JWT 替换默认令牌
 
测试：
访问 02密码模式 -> 00获取用户信息  -> 10刷新token


6-11 SSO
测试:启动sso_server, sso_client1,sso_client2
http://127.0.0.1:8080/client1/index.html
账号随便填，  密码是123456

7-2 源码阅读
分别在登录授权之前和之后访问
http://127.0.0.1:8060/user/1

7-3 权限表达式

先登录成功
> http://localhost:8060/evan-signIn.html

访问允许的页面  
> http://localhost:8060/index.html

访问权限不够的页面  
> http://localhost:8060/demo.html

Fianl: 使用Demo项目直接测试访问 
1. http://localhost:8080/evan-signIn.html

账号: 任意
密码: 123456


8-1 测试带有RBAC的模块
http://www.pinzhi365.com/evan-signIn.html
账号是admin
密码是123456

登录成功之后会跳转到admin主页