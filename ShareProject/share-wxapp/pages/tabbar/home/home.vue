<template>
	<view class="container">
		<view>
			<uni-segmented-control :current="current" :values="menus" @clickItem="onClickItem" style-type="text" active-color="#387fff"></uni-segmented-control>
			<view class="content">
				<view v-if="current === 0">
					<p class="notice">{{ notice.content }}</p>
					<uni-search-bar @confirm="handleSearch"></uni-search-bar>
					<uni-list>
						<uni-list-item :title="item.title" :note="item.summary" :thumb="item.cover" thumb-size="lg" :showBadge="item.price + '积分'"
						 :rightText="item.downloadUrl === null ? '兑换' : '下载'" showArrow="false" v-for="(item, index) in shares" :key="index"
						 class="item" @click="toNextPage(item)" />
					</uni-list>
				</view>
				<view v-if="current === 1">
					<uni-card title="微服务技术" mode="title" :is-shadow="true" thumbnail="https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/muwu.jpg"
					 extra="技术没有上限" note="软件_1851">
						使用说明
					</uni-card>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import uniSegmentedControl from '@/components/uni-segmented-control/uni-segmented-control.vue';
	import uniSearchBar from '@/components/uni-search-bar/uni-search-bar.vue';
	import {
		request,
		get
	} from '@/utils/request';
	import {
		SHARE_LIST_URL,
		NOTICE_URL
	} from '@/utils/api';
	export default {
		components: {
			uniSegmentedControl,
			uniSearchBar
		},
		data() {
			return {
				more: true,
				current: 0,
				pageNo: 1,
				pageSize: 6,
				menus: ['发现', '使用说明'],
				shares: [],
				notice: {}
			};
		},
		onLoad() {
			//请求前加载动画
			uni.showLoading({
				title: '加载中'
			});
			this.getNotice();
			this.getShares(true);
			//请求结束延时隐藏加载动画
			setTimeout(() => {
				uni.hideLoading();
			}, 1000);
		},
		onShow() {
			this.getShares(true);
		},
		//触底加载更多
		onReachBottom() {
			//已显示全部数据
			if (!this.more) {
				uni.showToast({
					title: '已加载完毕',
					duration: 1000
				});
				//直接返回，不再继续执行此方法
				return false;
			}
			//正常加载下一页
			this.pageNo = this.pageNo + 1;
			uni.showLoading({
				title: '加载中'
			});
			//不带参数的请求，是正常分页加载
			this.getShares();
			setTimeout(() => {
				uni.hideLoading();
			}, 1000);
		},

		//下拉刷新
		onPullDownRefresh() {
			//参数为true，就是重新开始加载
			this.getShares(true);
		},
		methods: {

			onClickItem(e) {
				if (this.current !== e.currentIndex) {
					this.current = e.currentIndex;
				}
			},
			async getShares(init) {
				//入参为true，从第一页重新开始加载数据
				if (init) {
					this.pageNo = 1;
					this.more = true;
				}
				//请求远程shares列表数据，以await方式代替then
				let res = await request(SHARE_LIST_URL, 'GET', {
					pageNo: this.pageNo,
					pageSize: this.pageSize
				});
				//处理进入首页加载数据和下拉刷新场景
				if (init) {
					//获得新的数据集合，将数据初始化为本次请求返回结果
					this.shares = res.data;
					//请求成功后就停止下拉刷新，让它回到顶部
					uni.stopPullDownRefresh();
				} else {
					//不是下拉刷新，是加载下一页数据，则把新数据追加到后面，不覆盖原值
					this.shares = this.shares.concat(res.data);
				}
				console.log(res.data)
				//加载完所有数据
				if (res.data.length < this.pageSize && this.pageNo > 0) {
					this.more = false;
				}
			},
			async getNotice() {
				// 获取最新公告
				let notice = await get(NOTICE_URL);
				// console.log(notice.data);
				this.notice = notice.data;
			},
			toNextPage(v) {
				// 跳转页面并传递share对象
				uni.navigateTo({
					url: '../../home/share-detail?item=' + JSON.stringify(v),
				})
			},
		}
	};
</script>

<style>
	.container {
		padding: 10px;
		line-height: 24px;
	}

	.item {
		font-size: 20px;
	}

	.notice {
		margin-top: 10px;
		font-size: 16px;
		color: #387fff;
	}
</style>
