<template>
	<view class="container">
		<view class="img">
			<image :src="item.cover" mode=""></image>
		</view>
		<view class="content vertical-center mt-10">
			<p>{{ item.title }}</p>
			<p>简介：{{ item.summary }}</p>
			<p>兑换需要积分：{{ item.price }}</p>
			<p>兑换数：{{ item.buyCount }}</p>
			<view class="pis" v-if="item.downloadUrl">
				<p>查看连接：{{ item.downloadUrl }}</p>
			</view>
			<button v-if="!item.downloadUrl" class="large-btn mt-10" @click="getUrl">兑换</button>
			<button v-else-if="!user" class="large-btn mt-10" @click="login">登录后兑换</button>

			<!-- <uni-popup v-if="!user" ref="popup" type="message">
			    <uni-popup-message type="info" message="请先登录" :duration="2000">请先登录</uni-popup-message>
			</uni-popup> -->
		</view>

	</view>
</template>

<script>
	import uniPopup from '@/components/uni-popup/uni-popup.vue'
	import uniPopupMessage from '@/components/uni-popup/uni-popup-message.vue'
	import {
		request,
		get
	} from '@/utils/request';
	import {
		USER_URL,
		EXCHANGE_URL,
		SHARE_DETAIL_URL
	} from '@/utils/api';
	export default {
		data() {
			return {
				user: null,
				item: {}
			};
		},
		components: {
			uniPopup,
			uniPopupMessage
		},
		onLoad(option) {
			// 传来share详情信息
			this.item = JSON.parse(option.item)
			console.log(this.item)
			// 核对用户是否登录
			if (uni.getStorageSync('user')) {
				this.user = uni.getStorageSync('user');
			}
		},
		onShow() {
		},
		methods: {
			open() {
				this.$refs.popup.open()
			},
			async getUrl() {
				if (!this.user){
					uni.showToast({
						icon: 'none',
						title: '请先登录',
						duration: 2000
					});
				} else{
					// 兑换积分
					let res = await request(
						EXCHANGE_URL,
						'POST', {
							"userId": this.user.id,
							"shareId": this.item.id
						},
					).then(res => {
						console.log(JSON.stringify(res) + '>>>>>>>>>>>>>');
						if (res.succ === true) {
							uni.showToast({
								title: '兑换成功',
								duration: 2000
							});
							//重新请求用户数据
							get(USER_URL + '/' + uni.getStorageSync('user').id).then(res => {
								//移除原有用户缓存数据，存入新的数据
								uni.removeStorageSync('user');
								uni.setStorageSync('user', res.data);
								//跳回首页
								uni.switchTab({
									url: '/pages/tabbar/home/home'
								});
							});
						}
					});
					console.log(res)
				}
				
			},
		}
	}
</script>

<style>
	image {
		height: 100%;
		width: 100%;
	}

	p {
		word-break: break-all;
		word-wrap: break-word;
	}

	.img {
		width: 100%;
		height: 150px;
	}

	.content {
		width: 98%;
	}

	.container p {
		margin-top: 5px;
	}

	.vertical-center {
		display: flex;
		flex-direction: column;
		align-items: center;
	}

	.pis {
		width: 90%;
		/* background-color: #007AFF */
	}

	.mt-10 {
		margin-top: 10px;
	}

	.large-btn {
		width: 98%;
		height: 40px;
		font-size: 16px;
	}
</style>
