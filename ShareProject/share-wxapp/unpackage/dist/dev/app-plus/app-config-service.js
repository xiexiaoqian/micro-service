
var isReady=false;var onReadyCallbacks=[];
var isServiceReady=false;var onServiceReadyCallbacks=[];
var __uniConfig = {"pages":["pages/tabbar/home/home","pages/tabbar/profile/profile","pages/tabbar/post/post","pages/home/exchange","pages/home/share-detail","pages/profile/my-exchange","pages/profile/my-bonus","pages/profile/my-contribute"],"window":{"navigationBarTextStyle":"white","navigationBarTitleText":"share-app","navigationBarBackgroundColor":"#5c6bc0","backgroundColor":"#F8F8F8"},"tabBar":{"borderStyle":"white","backgroundColor":"#f7f3f9","color":"#8F8F94","selectedColor":"#5c6bc0","list":[{"pagePath":"pages/tabbar/home/home","iconPath":"static/img/tabbar/home.png","selectedIconPath":"static/img/tabbar/homeactive.png","text":"首页"},{"pagePath":"pages/tabbar/post/post","iconPath":"static/img/tabbar/post.png","selectedIconPath":"static/img/tabbar/postactive.png","text":"投稿"},{"pagePath":"pages/tabbar/profile/profile","iconPath":"static/img/tabbar/me.png","selectedIconPath":"static/img/tabbar/meactive.png","text":"我的"}]},"nvueCompiler":"uni-app","renderer":"auto","splashscreen":{"alwaysShowBeforeRender":true,"autoclose":false},"appname":"share-wxapp","compilerVersion":"2.8.13","entryPagePath":"pages/tabbar/home/home","networkTimeout":{"request":60000,"connectSocket":60000,"uploadFile":60000,"downloadFile":60000}};
var __uniRoutes = [{"path":"/pages/tabbar/home/home","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"首页","enablePullDownRefresh":true}},{"path":"/pages/tabbar/profile/profile","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"我的"}},{"path":"/pages/tabbar/post/post","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"投稿"}},{"path":"/pages/home/exchange","meta":{},"window":{"navigationBarTitleText":"兑换资源"}},{"path":"/pages/home/share-detail","meta":{},"window":{"navigationBarTitleText":"分享详情"}},{"path":"/pages/profile/my-exchange","meta":{},"window":{"navigationBarTitleText":"我的兑换"}},{"path":"/pages/profile/my-bonus","meta":{},"window":{"navigationBarTitleText":"积分明细"}},{"path":"/pages/profile/my-contribute","meta":{},"window":{"navigationBarTitleText":"我的投稿"}}];
__uniConfig.onReady=function(callback){if(__uniConfig.ready){callback()}else{onReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"ready",{get:function(){return isReady},set:function(val){isReady=val;if(!isReady){return}const callbacks=onReadyCallbacks.slice(0);onReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
__uniConfig.onServiceReady=function(callback){if(__uniConfig.serviceReady){callback()}else{onServiceReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"serviceReady",{get:function(){return isServiceReady},set:function(val){isServiceReady=val;if(!isServiceReady){return}const callbacks=onServiceReadyCallbacks.slice(0);onServiceReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
service.register("uni-app-config",{create(a,b,c){if(!__uniConfig.viewport){var d=b.weex.config.env.scale,e=b.weex.config.env.deviceWidth,f=Math.ceil(e/d);Object.assign(__uniConfig,{viewport:f,defaultFontSize:Math.round(f/20)})}return{instance:{__uniConfig:__uniConfig,__uniRoutes:__uniRoutes,global:void 0,window:void 0,document:void 0,frames:void 0,self:void 0,location:void 0,navigator:void 0,localStorage:void 0,history:void 0,Caches:void 0,screen:void 0,alert:void 0,confirm:void 0,prompt:void 0,fetch:void 0,XMLHttpRequest:void 0,WebSocket:void 0,webkit:void 0,print:void 0}}}});
