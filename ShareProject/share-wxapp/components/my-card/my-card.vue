<template>
	<view v-if="share.showFlag" class="my-card" @click="onClick">
		<view class="thumb">
			<image :src="share.cover" class="img" mode="aspectFill" />
			<text class="tag origin" v-if="share.isOriginal">原创</text>
			<text v-else class="tag reprint">转载</text>
		</view>
		<view class="detail">
			<view class="mid">
				<view class="title">{{ share.title}}</view>
				<view class="summary">{{ share.summary.length > 30 ? share.summary.substring(0, 30) : share.summary }}</view>
			</view>
			<view v-if="share.price" class="right">
				<view>{{ share.price }}积分</view>
				<view class="right-a" v-if="share.downloadUrl">下载</view>
				<view class="right-b" v-else>兑换</view>
			</view>
			<view class="arrow">
				<image src="http://ww1.sinaimg.cn/large/006yro14gy1gjttkcja1tj301c01c0ea.jpg" class="img" mode="aspectFill" />
			</view>
		</view>
	</view>
</template>
<script>
export default {
	name: 'MyCard',
	props: ['share'],
	// computed: {
	// 	summary() {
	// 		let summary = this.share.summary;
	// 		if (summary && summary.length > 30) {
	// 			summary = summary.substring(0, 30) + '...';
	// 		}
	// 		return summary;
	// 	}
	// },
	methods: {
		onClick() {
			this.$emit('click');
		}
	}
};
</script>
<style lang="scss" scoped>
.my-card {
	padding: 15px 15px;
	border-bottom: 1px dashed #ddd;
	display: flex;
	background-color: #fff;
	font-size: 14px;
	
	.thumb {
		width: 90px;
		height: 70px;
		position: relative;
		// background-color: #F0AD4E;
		
		.img {
			width: 70px;
			height: 60px;
			border-radius: 3px;
			position: absolute;
			top: 5px;
			left: 5px;
		}
		.tag {
			display: flex;
			justify-content: center;
			width: 32px;
			color: #fff;
			padding: 2px;
			position: absolute;
			top: 0;
			left: 0;
			border-radius: 2px;
			font-size: 10px;
		}
		.origin {
			background-color: #7986cb;
		}
		.reprint {
			background-color: #90a4ae;
		}
	}
	.detail {
		padding-left: 20px;
		word-wrap: break-word;
		width: 100%;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		line-height: 170%;
		
		.mid {
			width: 160px;
			margin-top: -7px;
			
			.title {
				// font-weight: bold;
				color: #1a237e;
			}
			
			.summary{
				color: #90a4ae;
			}
		}
		.right {
			margin-top: -7px;
			width: 60px;
			text-align: right;
			color: #90a4ae;
			display: flex;
			flex-direction: column;
			justify-content: space-around;
		}
		.arrow{
			margin-left: 15px;
			width: 20px;
			height: 20px;
		}
	}
}
</style>