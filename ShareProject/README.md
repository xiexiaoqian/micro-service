# 知识分享小程序

## 项目

知识分享小程序是作为微服务练习，是课堂教学伴生项目，老师带我们从实践中学习，小而精的项目是最好不过的选择

项目整合了多种技术:

持久层 —— tk-mybatis

服务注册与发现 —— Nacos

负载均衡 —— Ribbon

服务调用 —— Feign

服务容错 —— Sentinel

消息驱动 —— RoketMQ

网关 —— Gateway

麻雀虽小五脏俱全，有了这些技术的加权，项目才更加完整和体系

对比以前写项目，实现效果即可，没有框架可言

到现在的逐渐体系化，能小小的体会到架构的感觉，很奇妙

## 项目结构

#### content-center——内容中心

#### user-center——用户中心

#### gateway——网关

#### share-wxapp——前端（uniapp）

## 功能实现

- 我的
  - 微信授权登录
  - 签到并加积分，写入积分日志
  - 我的投稿
  - 我的资源（含兑换）
  - 积分明细（实现后端接口，前端联调未通）
- 首页
  - 资源列表，根据用户情况显示下载或兑换
  - 下拉刷新，触底加载
  - 资源兑换并扣除积分、复制下载地址
- 投稿
  - 投稿
- 待实现
  - 管理员审核

## 页面展示

#### 登录

微信授权登录

<img src="https://uploader.shimo.im/f/qIbqzCCLFKsb0VVe.png!thumbnail" style="zoom: 67%;" /> <img src="https://uploader.shimo.im/f/eDrcNj0KHgELMMoL.png!thumbnail" style="zoom:67%;" />



#### 签到

签到成功，并加相应积分，以及重复签到提示

<img src="https://uploader.shimo.im/f/3nS0uSELRtaA9CB2.png!thumbnail" style="zoom:67%;" /> <img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201019014252917.png" alt="image-20201019014252917" style="zoom:67%;" />



#### 首页：

资源列表

用户未登录时，资源都显示为兑换；登录后，则根据兑换情况显示

<img src="https://uploader.shimo.im/f/0kkY0uSYp6mTa6Nn.png!thumbnail" alt="用户未登录时，资源都显示兑换" style="zoom:67%;" /> <img src="https://uploader.shimo.im/f/U9i6mgwHJcfz3DmS.png!thumbnail" style="zoom:67%;" />



资源详情

已兑换的直接显示链接，点击复制，可粘贴至相关平台下载查看；未兑换的显示兑换界面

<img src="https://uploader.shimo.im/f/zOnyuAeOm3VkxiOX.png!thumbnail" style="zoom:67%;" /> <img src="https://uploader.shimo.im/f/tNcxudmJeGohxweM.png!thumbnail" style="zoom:67%;" />



#### 兑换

判断用户积分是否足够后，进行兑换，点击确定，跳转到该资源详情（含链接），同时扣除相应积分，否则提示积分不足

<img src="https://uploader.shimo.im/f/LdLOdJQVmYe0FuJb.png!thumbnail" style="zoom:67%;" />





#### 投稿

输入投稿信息。点击继续投稿则清空刚才的信息，可继续填写

<img src="https://uploader.shimo.im/f/mw1QFZ68IKZU9yae.png!thumbnail" style="zoom:67%;" /> <img src="https://uploader.shimo.im/f/pdAF6tylaHcYlDOT.png!thumbnail" style="zoom:67%;" /> <img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201019020314347.png" style="zoom:67%;" />



#### 我的兑换

<img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201019022620273.png" style="zoom:67%;" />

#### 我的投稿

点击未审核的稿件则会提示：资源尚未通过审核

<img src="https://uploader.shimo.im/f/Po7sNcbsztiAhQMa.png!thumbnail" style="zoom:67%;" /> <img src="https://uploader.shimo.im/f/DaRig30FpQy6xP3g.png!thumbnail" style="zoom:67%;" />

#### 积分明细（待调试）



