# 知识分享小程序 

[Github地址](https://github.com/xiexiaoqian/micro-service)

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
    - token认证鉴权
  - 签到并加积分
    - 同时写入积分日志
    - 重复签到提醒
  - 我的投稿
    - 包括所有审核状态：通过、未通过、审核中
  - 我的资源
    - 包括已兑换和自己的发布
  - 积分明细
    - 根据不同积分事件搭配不同图标和事件标签
  - 管理员审核（已实现后端接口，前端未实现）
    - 待审核列表
    - 审核资源：给出通过与否以及原因
- 首页
  - 资源列表
    - 根据用户是否拥有该资源，显示下载或兑换
    - 根据资源类型，显示原创与转载标签
    - 下拉刷新，触底加载
  - 资源兑换
    - 判断用户积分是否足够，如果足够，扣除相应积分
    - 点击复制按钮，将地址复制到系统剪切板
  - 网络请求中，Loading加载提示
- 投稿

  - 投稿

  

## 页面展示

#### 登录

- 微信授权登录


<img src="https://uploader.shimo.im/f/qIbqzCCLFKsb0VVe.png!thumbnail" style="zoom: 50%;" />  <img src="https://uploader.shimo.im/f/8qLDzxR0mNQSLXhF.png!thumbnail" style="zoom:50%;" /> <img src="https://uploader.shimo.im/f/eDrcNj0KHgELMMoL.png!thumbnail" style="zoom:50%;" />

#### 签到

- 签到成功，并加相应积分，以及重复签到提示


<img src="https://uploader.shimo.im/f/3nS0uSELRtaA9CB2.png!thumbnail" style="zoom: 50%;" /> <img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201019014252917.png" alt="image-20201019014252917" style="zoom: 50%;" />



#### 首页：

- 资源列表
  - 用户未登录时，资源都显示为兑换；登录后，则根据用户拥有的资源情况显示下载或兑换

  - 根据资源类型匹配原创或转载标签

<img src="https://uploader.shimo.im/f/0kkY0uSYp6mTa6Nn.png!thumbnail" alt="用户未登录时，资源都显示兑换" style="zoom: 50%;" /> <img src="https://uploader.shimo.im/f/U9i6mgwHJcfz3DmS.png!thumbnail" style="zoom: 50%;" />



- 下拉刷新，触底加载

 <img src="https://uploader.shimo.im/f/QhVnKPyq1k4BVEm6.png!thumbnail" style="zoom: 53%;" /><img src="https://uploader.shimo.im/f/4Zfj6B1kRYDNBuEZ.png!thumbnail" style="zoom:50%;" /> 

- 资源详情
  - 已兑换的直接显示链接，点击复制，可直接复制到系统剪切板；
  - 未兑换的显示兑换界面

<img src="https://uploader.shimo.im/f/zOnyuAeOm3VkxiOX.png!thumbnail" style="zoom: 50%;" /> <img src="https://uploader.shimo.im/f/tNcxudmJeGohxweM.png!thumbnail" style="zoom: 50%;" />



#### 兑换

判断用户积分是否足够后，进行兑换，点击确定，跳转到该资源详情（含链接）

同时扣除相应积分，否则提示积分不足

跳回到首页，资源标签从兑换变为下载

<img src="https://uploader.shimo.im/f/LdLOdJQVmYe0FuJb.png!thumbnail" style="zoom: 50%;" /> <img src="https://uploader.shimo.im/f/Q0GUnxOaX0My0H8n.png!thumbnail" style="zoom: 63%;" />



#### 投稿

输入投稿信息。点击继续投稿则清空刚才的信息，可继续填写

投稿成功后，稿件会显示在我的投稿中，状态为尚未审核，不会直接出现在首页资源列表

<img src="https://uploader.shimo.im/f/mw1QFZ68IKZU9yae.png!thumbnail" style="zoom: 50%;" /> <img src="https://uploader.shimo.im/f/pdAF6tylaHcYlDOT.png!thumbnail" style="zoom: 50%;" /> <img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201019020314347.png" style="zoom: 50%;" />



#### 我的兑换

显示用户拥有的资源，包括通过兑换获取以及用户发布

按照获取时间降序，最新的在最上

<img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201019022620273.png" style="zoom: 50%;" />

#### 我的投稿

显示每篇投稿的审核状态

点击未审核的稿件则会提示：资源尚未通过审核

 <img src="https://uploader.shimo.im/f/JDrJ1eR0A2amYRil.png!thumbnail" style="zoom:50%;" /> <img src="https://uploader.shimo.im/f/WTIECmGY173D8dkB.png!thumbnail" style="zoom:50%;" />

#### 积分明细

显示所有积分日志，根据不同事件展示搭配不同图标和事件

<img src="https://uploader.shimo.im/f/aKqSvDO4ZqVTGrjP.png!thumbnail" style="zoom:50%;" />

#### 管理员审核（已实现后端接口，前端未实现）

- 审核列表

<img src="https://uploader.shimo.im/f/q0so31vK788fba5I.png!thumbnail" style="zoom:50%;" />

- 审核资源（RoketMQ问题，调试未通过）



### 遇到的小问题

- image标签不解析图片
  - 未解决

