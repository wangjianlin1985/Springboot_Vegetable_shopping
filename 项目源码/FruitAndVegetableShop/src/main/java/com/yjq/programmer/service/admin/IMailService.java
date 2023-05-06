package com.yjq.programmer.service.admin;

import com.github.pagehelper.PageInfo;
import com.yjq.programmer.pojo.admin.Mail;
import com.yjq.programmer.vo.common.ResponseVo;

/**
 * 邮件service接口
 * @author 82320
 *
 */
public interface IMailService {

	//通过分页获取收件箱邮件列表
	ResponseVo<PageInfo> getReceiveMailsByPage(Integer pageNum, Integer pageSize, Integer id);
		
	//通过分页和查询信息获取收件箱邮件列表
	ResponseVo<PageInfo> getReceiveMailsByPageAndTitle(Integer pageNum, Integer pageSize, Integer id, String title);
	
	//通过分页获取发件箱邮件列表
	ResponseVo<PageInfo> getSendMailsByPage(Integer pageNum, Integer pageSize, Integer id);
		
	//通过分页和查询信息获取发件箱邮件列表
	ResponseVo<PageInfo> getSendMailsByPageAndTitle(Integer pageNum, Integer pageSize, Integer id, String title);
	
	//移除附件
	ResponseVo<Boolean> removeAttachment(Integer Id);
	
	//发送邮件
	ResponseVo<Boolean> sendMail(Mail mail, String receivers, Integer loginedId);
	
	//邮件删除处理
	ResponseVo<Boolean> delete(Integer id, Integer loginedId);
	
	
}
