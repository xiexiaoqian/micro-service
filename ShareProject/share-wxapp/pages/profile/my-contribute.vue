<template>
	<view>
		<!-- <uni-list>
			<uni-list-item
				v-for="(share, index) in shares"
				:key="index"
				:title="share.title"
				:note="share.summary"
				:thumb="share.cover"
				:rightText="share.auditStatus"
				@click="gotoDetail(share.auditStatus, share.id)"
			></uni-list-item>
		</uni-list> -->
		
		<view v-for="(share, index) in shares" :key="index">
			<my-card :contribute="true" :share="share" :key="index" @click="gotoDetail(share.auditStatus, share.id)"></my-card>
			</view>
	</view>
</template>

<script>
import { request } from '@/utils/request';
import { MY_CONTRIBUTION_URL } from '@/utils/api';
export default {
	data() {
		return {
			shares: [],
			pageNo: 1,
			pageSize: 7,
			more: true,
			statusInfo: ''
		};
	},
	onLoad() {
		this.getShares(true);
	},
	onReachBottom() {
		if (!this.more) {
			uni.showToast({
				title: '已加载完毕',
				duration: 1000
			});
			return false;
		}
		this.pageNo = this.pageNo + 1;
		uni.showLoading({
			title: '加载中'
		});
		this.getShares();
		setTimeout(() => {
			uni.hideLoading();
		}, 1000);
	},
	onPullDownRefresh() {
		this.getShares(true);
	},
	methods: {
		async getShares(init) {
			if (init) {
				this.pageNo = 1;
				this.more = true;
			}
			uni.showLoading({
				title: '加载中'
			});
			
			let res = await request(MY_CONTRIBUTION_URL, 'POST', {
				userId: uni.getStorageSync('user').id
			});
			
			setTimeout(() => {
				uni.hideLoading();
			}, 100);
			if (init) {
				this.shares = res.data;
				uni.stopPullDownRefresh();
			} else {
				this.shares = this.shares.concat(res.data);
			}
			if (res.data.length < this.pageSize && this.pageNo > 0) {
				this.more = false;
			}
			let shares = res.data;
			this.shares = shares.map(function(item) {
				let res = {};
				switch (item.auditStatus) {
					case 'NOT_YET':
						res.auditStatus = '尚未审核';
						break;
					case 'PASS':
						res.auditStatus = '审核通过';
						break;
					case 'REJECT':
						res.auditStatus = '审核被拒';
						break;
				}
				res.id = item.id;
				res.title = item.title;
				res.cover = item.cover;
				res.summary = item.summary;
				setTimeout(() => {
					uni.hideLoading();
				}, 100);
				return res;
			});
		},
		gotoDetail(auditStatus, id) {
			// switch (auditStatus) {
			// 	case '尚未审核':
			// 		uni.showToast({
			// 			icon: 'none',
			// 			title: '资源尚未审核',
			// 			duration: 1500
			// 		});
			// 		break;
			// 	case '审核通过':
			// 		uni.navigateTo({
			// 			url: `../../home/share-detail/share-detail?id=${id}`
			// 		});
			// 		break;
			// 	case '审核被拒':
			// 		uni.showToast({
			// 			icon: 'none',
			// 			title: '资源审核未通过，请修改后重新提交',
			// 			duration: 1500
			// 		});
			// 		break;
			// }
			console.log(auditStatus)
			if (auditStatus == '审核通过'){
				uni.navigateTo({
							url: `../home/share-detail?id=${id}`,
							fail: (res) => {
								console.log(res)
							}
						});
			}else if (auditStatus == '尚未审核'){
				uni.showToast({
							icon: 'none',
							title: '资源尚未审核',
							duration: 1500
						});
			}else{
				uni.showToast({
							icon: 'none',
							title: '资源审核未通过，请修改后重新提交',
							duration: 1500
						});
			}
		},
		summary() {
			let summary = this.share.summary;
			if (summary && summary.length > 30) {
				summary = summary.substring(0, 30) + '...';
			}
			return summary;
		}
	}
};
</script>

<style lang="scss"></style>