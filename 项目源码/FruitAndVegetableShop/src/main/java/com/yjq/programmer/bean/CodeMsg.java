package com.yjq.programmer.bean;
/**
 * 错误码统一处理类，所有的错误码统一定义在这里
 * @author 82320
 *
 */
public class CodeMsg {

	private Integer code;//错误码
	
	private String msg;//错误信息
	
	/**
	 * 构造函数私有化即单例模式
	 * 该类负责创建自己的对象，同时确保只有单个对象被创建。这个类提供了一种访问其唯一的对象的方式，可以直接访问，不需要实例化该类的对象。
	 * @param code
	 * @param msg
	 */
	private CodeMsg(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public CodeMsg () {
		
	}
	
	public Integer getCode() {
		return code;
	}



	public void setCode(Integer code) {
		this.code = code;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	//通用错误码定义
	//处理成功消息码
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	//通用数据错误码
	public static CodeMsg DATA_ERROR = new CodeMsg(-1, "非法数据！");
	public static CodeMsg VALIDATE_ENTITY_ERROR = new CodeMsg(-2, "");
	public static CodeMsg CPACHA_EMPTY = new CodeMsg(-3, "验证码不能为空!");
	public static CodeMsg SESSION_EXPIRED = new CodeMsg(-4, "会话已失效，请刷新页面重试！");
	public static CodeMsg CPACHA_ERROR = new CodeMsg(-5, "验证码错误！");
	public static CodeMsg USER_SESSION_EXPIRED = new CodeMsg(-6, "还未登录或会话失效，请重新登录！");
	public static CodeMsg UPLOAD_PHOTO_SUFFIX_ERROR = new CodeMsg(-7, "图片格式不正确！");
	public static CodeMsg UPLOAD_PHOTO_ERROR = new CodeMsg(-8, "图片上传失败！");
	public static CodeMsg UPLOAD_ATTACHMENT_ERROR = new CodeMsg(-9, "附件上传失败！");
	public static CodeMsg DOWNLOAD_FILE_ERROR = new CodeMsg(-10, "文件下载失败！");
	public static CodeMsg CPACHA_EXPIRE = new CodeMsg(-11, "验证码已过期，请刷新验证码！");
	public static CodeMsg SYSTEM_ERROR = new CodeMsg(-12, "系统出现了错误，请联系管理员！");
	
	
	//菜单管理错误码
	public static CodeMsg MENU_STATE_CHANGE_ERROR = new CodeMsg(-1000, "菜单状态改变失败！请联系管理员！");
	public static CodeMsg MENU_ADD_ERROR = new CodeMsg(-1001, "菜单添加失败！请联系管理员！");
	public static CodeMsg MENU_EDIT_ERROR = new CodeMsg(-1002, "菜单编辑失败！请联系管理员！");
	public static CodeMsg MENU_DELETE_ERROR = new CodeMsg(-1003, "菜单删除失败！请联系管理员！");
	public static CodeMsg MENU_CHILDREN_EXIST = new CodeMsg(-1004, "删除失败！请先删除该菜单下的子菜单！");
	
	
	//管理员管理错误码
	public static CodeMsg ADMIN_STATE_CHANGE_ERROR = new CodeMsg(-2000, "管理员状态信息改变失败！请联系管理员！");
	public static CodeMsg ADMIN_MOBILE_LENGTH_ERROR = new CodeMsg(-2001, "请输入正确的管理员手机号长度！");
	public static CodeMsg ADMIN_ADD_ERROR = new CodeMsg(-2002, "管理员信息添加失败！请联系管理员！");
	public static CodeMsg ADMIN_NAME_EXIST = new CodeMsg(-2003, "管理员名字重复！请换一个！");
	public static CodeMsg ADMIN_EDIT_ERROR = new CodeMsg(-2004, "管理员信息编辑失败！请联系管理员！");
	public static CodeMsg ADMIN_DELETE_ERROR = new CodeMsg(-2005, "管理员信息删除失败！请联系管理员！");
	public static CodeMsg ADMIN_ROLE_EDIT_ERROR = new CodeMsg(-2006, "管理员对应角色信息编辑失败！请联系管理员！");
	
	
	//角色管理错误码
	public static CodeMsg ROLE_ADD_ERROR = new CodeMsg(-3000, "角色添加失败！请联系管理员！");
	public static CodeMsg ROLE_EDIT_ERROR = new CodeMsg(-3001, "角色编辑失败！请联系管理员！");
	public static CodeMsg ROLE_DELETE_ERROR = new CodeMsg(-3002, "角色删除失败！请联系管理员！");
	public static CodeMsg ROLE_NAME_EXIST = new CodeMsg(-3003, "角色名字重复！请换一个！");
	public static CodeMsg ROLE_AUTHORITY_UPDATE_ERROR = new CodeMsg(-3004, "角色权限保存失败！请联系管理员！");
	public static CodeMsg ROLE_AUTHORITY_DELETE_ERROR = new CodeMsg(-3005, "角色权限删除失败！请联系管理员！");
	
	//邮件管理错误码
	public static CodeMsg MAIL_ATTACHMENT_NO_EXIST = new CodeMsg(-4000, "删除失败！	这个附件已经不存在了！");
	public static CodeMsg MAIL_ATTACHMENT_DELETE_ERROR = new CodeMsg(-4001, "附件删除失败！请联系管理员！");
	public static CodeMsg MAIL_RECEIVER_GET_ERROR = new CodeMsg(-4002, "发送失败！收件人获取异常！请联系管理员！");
	public static CodeMsg MAIL_NO_EXIST = new CodeMsg(-4003, "删除失败！这个邮件已经不存在了！");
	public static CodeMsg MAIL_DELETE_ERROR = new CodeMsg(-4004, "删除失败！请联系管理员！");
	
	//系统管理错误码
	public static CodeMsg USERNAME_OR_PASSWORD_ERROR = new CodeMsg(-5000, "用户名或者密码错误！");
	public static CodeMsg USER_STATE_ERROR = new CodeMsg(-5001, "该用户已被冻结！无法登录！");
	public static CodeMsg USER_AUTHORITY_ERROR = new CodeMsg(-5002, "该用户没有任何权限！无法登录！");
	public static CodeMsg PERSON_INFO_SAVE_ERROR = new CodeMsg(-5003, "个人信息保存失败！请联系管理员！");
	
	//公告管理错误码
	public static CodeMsg ANNOUNCEMENT_ADD_ERROR  = new CodeMsg(-6000, "公告添加失败！请联系管理员！");
	public static CodeMsg ANNOUNCEMENT_NOT_EXIST  = new CodeMsg(-6001, "删除失败！这个公告已经不存在了！");
	public static CodeMsg ANNOUNCEMENT_DELETE_ERROR  = new CodeMsg(-6002, "公告删除失败！请联系管理员！");

	//用户管理错误码
	public static CodeMsg USER_REPASSWORD_EMPTY  = new CodeMsg(-7000, "确认密码不能为空！");
	public static CodeMsg USER_REPASSWORD_ERROR  = new CodeMsg(-7001, "两次密码输入不一致！");
	public static CodeMsg USER_ADD_ERROR  = new CodeMsg(-7002, "用户信息添加失败，请联系管理员！");
	public static CodeMsg USER_USERNAME_ALREADY_EXIST  = new CodeMsg(-7003, "用户名称已经存在，请换一个！");
	public static CodeMsg USER_USERNAME_EMPTY  = new CodeMsg(-7004, "用户名称不能为空！");
	public static CodeMsg USER_PASSWORD_EMPTY  = new CodeMsg(-7005, "用户密码不能为空！");
	public static CodeMsg USER_NOT_EXIST  = new CodeMsg(-7006, "用户不存在！");
	public static CodeMsg USER_INFO_EDIT_ERROR  = new CodeMsg(-7007, "用户个人信息修改失败，请联系管理员！");
	public static CodeMsg USER_PREPASSWORD_EMPTY  = new CodeMsg(-7008, "原密码不能为空！");
	public static CodeMsg USER_NEWPASSWORD_EMPTY  = new CodeMsg(-7009, "新密码不能为空！");
	public static CodeMsg USER_RENEWPASSWORD_EMPTY  = new CodeMsg(-7010, "确认新密码不能为空！");
	public static CodeMsg USER_PREPASSWORD_ERROR  = new CodeMsg(-7011, "原密码错误！");
	public static CodeMsg USER_RENEWPASSWORD_ERROR  = new CodeMsg(-7012, "新密码和确认新密码输入不一致！");
	public static CodeMsg USER_PASSWORD_EDIT_ERROR  = new CodeMsg(-7013, "用户密码修改失败，请联系管理员！");
	public static CodeMsg USER_DELETE_ERROR  = new CodeMsg(-7014, "用户删除失败，请联系管理员！");

	//地址管理错误码
	public static CodeMsg ADDRESS_ADD_ERROR  = new CodeMsg(-8000, "地址添加失败，请联系管理员！");
	public static CodeMsg ADDRESS_NUM_EXCEED_LIMIT  = new CodeMsg(-8001, "存储的地址数量不能超过3个！");
	public static CodeMsg ADDRESS_SET_FIRST_SELECTED_ERROR  = new CodeMsg(-8002, "地址设置为订单首选失败，请联系管理员！");
	public static CodeMsg ADDRESS_DELETE_ERROR  = new CodeMsg(-8003, "地址删除失败，请联系管理员！");

	//商品管理错误码
	public static CodeMsg PRODUCT_CATEGORY_ADD_ERROR  = new CodeMsg(-9000, "商品种类添加失败，请联系管理员！");
	public static CodeMsg PRODUCT_CATEGORY_EDIT_ERROR  = new CodeMsg(-9001, "商品种类修改失败，请联系管理员！");
	public static CodeMsg PRODUCT_CATEGORY_DELETE_ERROR  = new CodeMsg(-9002, "商品种类删除失败，请联系管理员！");
	public static CodeMsg PRODUCT_ADD_ERROR  = new CodeMsg(-9003, "商品添加失败，请联系管理员！");
	public static CodeMsg PRODUCT_EDIT_ERROR  = new CodeMsg(-9004, "商品修改失败，请联系管理员！");
	public static CodeMsg PRODUCT_DELETE_ERROR  = new CodeMsg(-9005, "商品删除失败，请联系管理员！");
	public static CodeMsg PRODUCT_NOT_EXIST  = new CodeMsg(-9006, "该商品已经不存在！");
	public static CodeMsg PRODUCT_STOCK_ERROR  = new CodeMsg(-9007, "该商品库存不够！");

	//收藏管理错误码
	public static CodeMsg COLLECT_ADD_ERROR  = new CodeMsg(-10000, "收藏添加失败，请联系管理员！");
	public static CodeMsg COLLECT_DELETE_ERROR  = new CodeMsg(-10001, "收藏删除失败，请联系管理员！");
	public static CodeMsg COLLECT_ALREADY_EXIST  = new CodeMsg(-10002, "该商品已收藏，请勿重复添加！");

	//订单管理错误码
	public static CodeMsg ORDER_ADD_ERROR  = new CodeMsg(-11000, "订单信息添加失败，请联系管理员！");
	public static CodeMsg ORDER_ITEM_ADD_ERROR  = new CodeMsg(-11001, "订单详情信息添加失败，请联系管理员！");
	public static CodeMsg ORDER_REMARK_EXCEED_LENGTH  = new CodeMsg(-11002, "订单留言不能超过50！");
	public static CodeMsg ORDER_ADDRESS_EMPTY  = new CodeMsg(-11003, "订单配送地址不能为空！");
	public static CodeMsg ORDER_UPDATE_ERROR  = new CodeMsg(-11004, "订单修改失败，请联系管理员！");
	public static CodeMsg ORDER_ERROR  = new CodeMsg(-11005, ""); //订单自定义错误
	public static CodeMsg ORDER_NOT_EXIST = new CodeMsg(-11006, "该订单不存在！");
	public static CodeMsg ORDER_STATE_EDIT_ERROR = new CodeMsg(-11007, "订单状态修改失败，请联系管理员！");
	public static CodeMsg ORDER_DELETE_ERROR = new CodeMsg(-11008, "订单删除失败，请联系管理员！");

	//评论管理错误码
	public static CodeMsg COMMENT_ADD_ERROR = new CodeMsg(-12000, "评论添加失败，请联系管理员！");
	public static CodeMsg COMMENT_NOT_EXIST = new CodeMsg(-12001, "该评论不存在！");
	public static CodeMsg COMMENT_DELETE_ERROR = new CodeMsg(-12002, "评论删除失败，请联系管理员！");
}
