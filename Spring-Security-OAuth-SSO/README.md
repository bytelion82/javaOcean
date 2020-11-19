# 单点登录

### 测试效果
#### 单点登录
先启动认证服务器，任何在启动两个客户端。启动后，访问http://127.0.0.1:9090/app1/index.html：
随便输入用户名，密码123456
跳转到系统二
发现系统二成功登录后，访问http://127.0.0.1:9091/app2/user看是否能成功获取到用户信息：
#### 权限认证
登录成功后，访问http://127.0.0.1:9090/app1/auth/test1：


## 参考地址：
https://mrbird.cc/Spring-Security-OAuth2-SSO.html
https://github.com/wuyouzhuguli/SpringAll/blob/master/readme.md