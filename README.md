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