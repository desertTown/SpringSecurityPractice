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