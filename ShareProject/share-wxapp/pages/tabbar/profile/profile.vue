<template>
	<view class="container">
		<view v-if="!user" class="box">
			<image src="https://i.loli.net/2020/10/10/mFxrTZNwPG6CBta.png" class="avatar" />
			<text class="info">登录，享受技术之旅吧！</text>
			<button open-type="getUserInfo" @getuserinfo="bindGetUserInfo">微信账号登录</button>
		</view>
		<view v-else>
			<view class="center">
				<view>
					<image :src="user.avatarUrl" class="avatar" />
				</view>
				<!-- <button @click="logout" class="logout">退出</button> -->
				<view>
					<view class="line">
						<text>{{ user.wxNickname }}</text>
					</view>
					<view class="line">
						<text>当前积分：{{ user.bonus }}</text>
					</view>
				</view>
				<view class="btnbar">
					<view class="btn-a" @click="sign">签到</view>
					<view class="btn-a" @click="logout">退出</view>
				</view>
			</view>

			<uni-list>
				<uni-list-item title="我的兑换" showArrow="true" is-link @click="toNextPage('exchange')"></uni-list-item>
				<uni-list-item title="积分明细" showArrow="true" is-link @click="toNextPage('bonus')"></uni-list-item>
				<uni-list-item title="我的投稿" showArrow="true" is-link @click="toNextPage('contribute')"></uni-list-item>
				<uni-list-item title="审核投稿" showArrow="true" is-link @click="toNextPage('audit')" v-if="user.roles === 'admin'"></uni-list-item>
				
			</uni-list>

		</view>
	</view>
</template>

<script>
	import {
		request,
		get
	} from '@/utils/request';
	import {
		LOGIN_URL,
		USER_URL,
		SIGN_URL,
	} from '@/utils/api';
	export default {
		data() {
			return {
				user: null
			};
		},
		onLoad() {
			if (uni.getStorageSync('user')) {
				this.user = uni.getStorageSync('user');
			}
		},
		onShow() {
			if (uni.getStorageSync('user')) {
				this.user = uni.getStorageSync('user');
			}
		},
		methods: {
			// 跳转页面
			toNextPage(v) {
				switch(v){
					case 'exchange':
					uni.navigateTo({
						url: '../../profile/my-exchange',
					})
					case 'bonus':
					uni.navigateTo({
						url: '../../profile/my-bonus',
					})
					case 'contribute':
					uni.navigateTo({
						url: '../../profile/my-contribute',
					})
					case 'audit':
					uni.navigateTo({
						url: '../../profile/audit',
					})
					
				}
				
			},
			// 判断用户是否授权微信登录
			bindGetUserInfo(e) {
				let platform = uni.getSystemInfoSync().platform;
				//此处e.mp事件适用于mini program小程序
				if (e.mp.detail.rawData) {
					//用户按了允许授权按钮
					this.wxLogin(e);
				} else {
					//用户按了拒绝按钮
					uni.showToast({
						title: '授权失败'
					});
				}
			},
			// 走wx登录接口，并构建loginDTO，向后端发送登录请求
			wxLogin(e) {
				let self = this;
				let userInfo = e.mp.detail.userInfo;
				// console.log(userInfo);
				wx.login({
					success(res) {
						// console.log(res);
						if (res.errMsg === 'login:ok') {
							let loginDTO = {
								loginCode: res.code,
								wxNickname: userInfo.nickName,
								avatarUrl: userInfo.avatarUrl
							};
							//统一调用登录方法
							self.userLogin(loginDTO);
						} else {
							console.log('登录失败！' + res.errMsg);
						}
					}
				});
			},
			appLogin() {
				let self = this;
				uni.login({
					provider: 'weixin',
					success: function(loginRes) {
						uni.getUserInfo({
							provider: 'weixin',
							success: infoRes => {
								//统一的登录请求参数
								let loginDTO = {
									openId: infoRes.userInfo.openId,
									wxNickname: infoRes.userInfo.nickName,
									avatarUrl: infoRes.userInfo.avatarUrl
								};
								//统一调用登录方法
								self.userLogin(loginDTO);
							}
						});
					}
				});
			},
			// 发送登录请求
			userLogin(loginDTO) {
				console.log(loginDTO);
				request(LOGIN_URL, 'POST', loginDTO).then(res => {
					if (res.succ === true) {
						uni.showToast({
							title: '登录成功'
						});
					}
					console.log(res);
					this.user = res.data.user;
					uni.setStorageSync('user', res.data.user);
					uni.setStorageSync('token', res.data.token);
				});
			},
			// 签到
			async sign() {
				let res = await request(SIGN_URL, 'POST', {
					"userId": this.user.id
				})
				console.log(res.data)
				let code = res.data.code
				if (code == "200") {
					uni.showToast({
						icon: 'none',
						title: '签到成功！',
						duration: 2000
					});
				} else if (code == "201") {
					uni.showToast({
						icon: 'none',
						title: '今天签过啦~',
						duration: 2000
					});
				} else {
					uni.showToast({
						icon: 'none',
						title: '未知错误(⋟﹏⋞)',
						duration: 2000
					});
				}
			},
			logout() {
				this.user = null;
				uni.clearStorageSync();
			}
		}
	};
</script>

<style lang="scss">
	uni-list-item {
		margin-left: -250px;
	}

	.container {
		padding-top: 40px;
		text-align: center;

		.box {
			margin-top: 20px;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;

			.avatar {
				width: 150px;
				height: 150px;
				border-radius: 50%;
			}

			.info {
				font-size: 16px;
				margin-top: 20px;
				margin-bottom: 30px;
			}


		}

		.center {
			display: flex;
			flex-direction: column;
			align-items: center;

			.avatar {
				width: 80px;
				height: 80px;
				border-radius: 50%;
			}

			.line {
				margin-top: 5px;
				margin-bottom: 5px;
			}

			.btnbar {
				display: flex;

				.btn-a {
					width: 45px;
					height: 15px;
					padding: 5px;
					margin: 2px 15px 2px 5px;
					font-size: 10px;
					color: #FFFFFF;
					border-radius: 2px;
					background-color: #6190E8;
					// background: linear-gradient(to bottom, #9796f0, #fbc7d4);
				}
			}


		}
	}
</style>
