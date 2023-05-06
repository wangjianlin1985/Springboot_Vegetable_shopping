package com.yjq.programmer.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.admin.AttachmentMapper;
import com.yjq.programmer.dao.admin.MailMapper;
import com.yjq.programmer.enums.MailDeleteStateEnum;
import com.yjq.programmer.pojo.admin.Mail;
import com.yjq.programmer.service.admin.IMailService;
import com.yjq.programmer.util.ValidateEntityUtil;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件service接口实现类
 * @author 82320
 *
 */
@Service 
public class MailServiceImpl implements IMailService {

	@Autowired
	private MailMapper mailMapper;
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	
	@Override
	public ResponseVo<PageInfo> getReceiveMailsByPage(Integer pageNum, Integer pageSize, Integer id) {
		//pageNum:页号，pageSize:每页个数（插件作用）
		//举例：页号1页个数2：取获取数据的前两条；页号2页个数2：取前面两条数据的后两条
		PageHelper.startPage(pageNum, pageSize);
		List<Mail> receiveMails = mailMapper.selectByReceiverIdAndDeleteState(id, MailDeleteStateEnum.RECEIVER_DELETE.getCode());
		PageInfo pageInfo = new PageInfo<>(receiveMails);
		pageInfo.setList(receiveMails);
		return ResponseVo.success(pageInfo);
	}

	@Override
	public ResponseVo<PageInfo> getReceiveMailsByPageAndTitle(Integer pageNum, Integer pageSize,Integer id, String title) {
		//pageNum:页号，pageSize:每页个数（插件作用）
		//举例：页号1页个数2：取获取数据的前两条；页号2页个数2：取前面两条数据的后两条
		PageHelper.startPage(pageNum, pageSize);
		List<Mail> selectBySearchTitle = mailMapper.selectBySearchTitleAndReceiverIdAndDeleteState(title, id, MailDeleteStateEnum.RECEIVER_DELETE.getCode());
		PageInfo pageInfo = new PageInfo<>(selectBySearchTitle);
		pageInfo.setList(selectBySearchTitle);
		return ResponseVo.success(pageInfo);
	}

	@Override
	public ResponseVo<PageInfo> getSendMailsByPage(Integer pageNum, Integer pageSize, Integer id) {
		//pageNum:页号，pageSize:每页个数（插件作用）
		//举例：页号1页个数2：取获取数据的前两条；页号2页个数2：取前面两条数据的后两条
		PageHelper.startPage(pageNum, pageSize);
		List<Mail> sendMails = mailMapper.selectBySenderIdAndDeleteState(id, MailDeleteStateEnum.SENDER_DELETE.getCode());
		PageInfo pageInfo = new PageInfo<>(sendMails);
		pageInfo.setList(sendMails);
		return ResponseVo.success(pageInfo);
	}
	
	@Override
	public ResponseVo<PageInfo> getSendMailsByPageAndTitle(Integer pageNum, Integer pageSize, Integer id, String title) {
		//pageNum:页号，pageSize:每页个数（插件作用）
		//举例：页号1页个数2：取获取数据的前两条；页号2页个数2：取前面两条数据的后两条
		PageHelper.startPage(pageNum, pageSize);
		List<Mail> selectBySearchTitle = mailMapper.selectBySearchTitleAndSenderIdAndDeleteState(title, id, MailDeleteStateEnum.SENDER_DELETE.getCode());
		PageInfo pageInfo = new PageInfo<>(selectBySearchTitle);
		pageInfo.setList(selectBySearchTitle);
		return ResponseVo.success(pageInfo);
	}

	@Override
	public ResponseVo<Boolean> removeAttachment(Integer Id) {
		if(attachmentMapper.selectByPrimaryKey(Id) == null) {
			return ResponseVo.errorByMsg(CodeMsg.MAIL_ATTACHMENT_NO_EXIST);
		}
		if(attachmentMapper.deleteByPrimaryKey(Id) <= 0) {
			return ResponseVo.errorByMsg(CodeMsg.MAIL_ATTACHMENT_DELETE_ERROR);
		}
		return ResponseVo.successByMsg(true, "删除成功！");
	}

	@Override
	public ResponseVo<Boolean> sendMail(Mail mail, String receivers, Integer loginedId) {
		if(mail == null || receivers == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		mail.setSenderId(loginedId);
		String[] split = receivers.split(",");
		List<Mail> mailList = new ArrayList<>();
		for(int i=0;i<split.length;i++) {
			Mail saveMail = new Mail();
			BeanUtils.copyProperties(mail, saveMail, "id","createTime","updateTime");
			try {
				saveMail.setReceiverId(Integer.parseInt(split[i]));
			}catch(Exception e) {
				return ResponseVo.errorByMsg(CodeMsg.MAIL_RECEIVER_GET_ERROR);
			}
			//进行统一表单验证
			CodeMsg validate = ValidateEntityUtil.validate(saveMail);
			if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
				return ResponseVo.errorByMsg(validate);
			}
			mailList.add(saveMail);
		}
		//插入数据库
		mailMapper.batchInsert(mailList);
		return ResponseVo.successByMsg(true, "发送成功！");
	}

	@Override
	public ResponseVo<Boolean> delete(Integer id, Integer loginedId) {
		Mail selectByPrimaryKey = mailMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey == null) {
			return ResponseVo.errorByMsg(CodeMsg.MAIL_NO_EXIST);
		}
		if(selectByPrimaryKey.getReceiverId().equals(loginedId) && selectByPrimaryKey.getSenderId().equals(loginedId)) {
			if(mailMapper.deleteByPrimaryKey(id) <= 0) {
				return ResponseVo.errorByMsg(CodeMsg.MAIL_DELETE_ERROR);
			}
		}else if(selectByPrimaryKey.getReceiverId().equals(loginedId)) {
			//如果是接收者的删除
			if(selectByPrimaryKey.getDeleteState().intValue() == MailDeleteStateEnum.SENDER_DELETE.getCode().intValue()) {
				//如果发送者已经删除了，那就彻底删掉
				if(mailMapper.deleteByPrimaryKey(id) <= 0) {
					return ResponseVo.errorByMsg(CodeMsg.MAIL_DELETE_ERROR);
				}
			}else {
				//否则，就把状态改成接收者删除
				selectByPrimaryKey.setDeleteState(MailDeleteStateEnum.RECEIVER_DELETE.getCode());
				if(mailMapper.updateByPrimaryKeySelective(selectByPrimaryKey) <= 0) {
					return ResponseVo.errorByMsg(CodeMsg.MAIL_DELETE_ERROR);
				}
			}
		}else if(selectByPrimaryKey.getSenderId().equals(loginedId)) {
			//如果是发送者的删除
			if(selectByPrimaryKey.getDeleteState().intValue() == MailDeleteStateEnum.RECEIVER_DELETE.getCode().intValue()) {
				//如果接收者已经删除了，那就彻底删掉
				if(mailMapper.deleteByPrimaryKey(id) <= 0) {
					return ResponseVo.errorByMsg(CodeMsg.MAIL_DELETE_ERROR);
				}
			}else {
				//否则，就把状态改成接收者删除
				selectByPrimaryKey.setDeleteState(MailDeleteStateEnum.SENDER_DELETE.getCode());
				if(mailMapper.updateByPrimaryKeySelective(selectByPrimaryKey) <= 0) {
					return ResponseVo.errorByMsg(CodeMsg.MAIL_DELETE_ERROR);
				}
			}
		}
		
		return ResponseVo.successByMsg(true, "删除成功！");
	}
}
