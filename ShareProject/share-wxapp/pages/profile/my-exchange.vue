<template>
	<view class="container">
		<p>hh </p>
		<!-- <uni-list>
			<uni-list-item :title="item.title" :note="item.summary" :thumb="item.cover" thumb-size="lg" :showBadge="item.price + '积分'"
			 :rightText="下载" showArrow="false" v-for="(item, index) in items" :key="index"
			 class="item" @click="toNextPage(item)" />
		</uni-list> -->
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
		EXCHANGE_URL,
		SHARE_DETAIL_URL
	} from '@/utils/api';
	export default {
		data() {
			return {
				myExchage: []
			};
		},
		components: {},
		onLoad(option) {},
		onShow() {},
		methods: {
			getExchange(){
				
			}
		}
	}
</script>

<style>

</style>
