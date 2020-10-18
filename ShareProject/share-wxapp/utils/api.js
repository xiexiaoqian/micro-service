// 后端接口一级基础路径
export const BASE_API_URL = 'https://shareapp.utools.club';
// export const BASE_API_URL = 'http://39.97.253.19:8040';

// 二级基地址
export const SHARE_URL = BASE_API_URL + '/shares';
export const USER_URL = BASE_API_URL + '/users';
export const NOTICE_URL = BASE_API_URL + '/notices';
export const ADMIN_URL = BASE_API_URL + '/admin';



// =================== 首页功能 ===================
// 分享列表
export const SHARE_LIST_URL = SHARE_URL + '/query';
// 最新公告
export const LATEST_NOTICE_URL = NOTICE_URL + '/latest';

// =================== 投稿兑换相关功能 ===================
//投稿
export const CONTRIBUTE_URL = SHARE_URL + '/contribute';
//兑换
export const EXCHANGE_URL = SHARE_URL + '/exchange';



//=================== 我的 ===================
//登录
export const LOGIN_URL = USER_URL + '/login';
//我的兑换
export const MY_EXCHANGE_URL = SHARE_URL + '/my/exchanges';
//积分明细
export const MY_BONUS_LOG_URL = USER_URL + '/my-bonus-log';
//我的投稿
export const MY_CONTRIBUTION_URL = SHARE_URL + '/my/contributions';
//签到
export const SIGN_URL = USER_URL + '/sign';

//=================== 管理员功能 ===================
//管理员查看待审核分享列表
export const NOT_YET_SHARE_URL = ADMIN_URL + '/shares/list';
//管理员审核某个分享
export const AUDIT_URL = ADMIN_URL + '/shares/audit';