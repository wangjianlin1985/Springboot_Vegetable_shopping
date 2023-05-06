package com.yjq.programmer.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.admin.AnnouncementMapper;
import com.yjq.programmer.pojo.admin.Announcement;
import com.yjq.programmer.service.admin.IAnnouncementService;
import com.yjq.programmer.util.ValidateEntityUtil;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告service接口实现类
 * @author 82320
 *
 */
@Service 
public class AnnouncementServiceImpl implements IAnnouncementService {

	@Autowired
	private AnnouncementMapper announcementMapper;
	
	@Override
	public ResponseVo<PageInfo> getAnnouncementByPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Announcement> allAnnouncements = announcementMapper.selectAll();
		PageInfo pageInfo = new PageInfo<>(allAnnouncements);
		pageInfo.setList(allAnnouncements);
		return ResponseVo.success(pageInfo);
	}

	@Override
	public ResponseVo<PageInfo> getAnnouncementByPageAndContent(Integer pageNum, Integer pageSize, String content) {
		PageHelper.startPage(pageNum, pageSize);
		List<Announcement> selectBySearchContent = announcementMapper.selectBySearchContent(content);
		PageInfo pageInfo = new PageInfo<>(selectBySearchContent);
		pageInfo.setList(selectBySearchContent);
		return ResponseVo.success(pageInfo);
	}

	@Override
	public ResponseVo<Boolean> add(Announcement announcement,Integer loginedId) {
		if(announcement == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		announcement.setAdminId(loginedId);
		//进行统一表单验证
		CodeMsg validate = ValidateEntityUtil.validate(announcement);
		if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
			return ResponseVo.errorByMsg(validate);
		}
		//添加数据库
		if(announcementMapper.insertSelective(announcement) <= 0){
			return ResponseVo.errorByMsg(CodeMsg.ANNOUNCEMENT_ADD_ERROR);
		}
		return ResponseVo.successByMsg(true, "发布成功！");
	}

	@Override
	public ResponseVo<Boolean> delete(Integer id) {
		if(announcementMapper.selectByPrimaryKey(id) == null) {
			return ResponseVo.errorByMsg(CodeMsg.ANNOUNCEMENT_NOT_EXIST);
		}
		//删除数据
		if(announcementMapper.deleteByPrimaryKey(id) <= 0){
			return ResponseVo.errorByMsg(CodeMsg.ANNOUNCEMENT_DELETE_ERROR);
		}
		return ResponseVo.successByMsg(true, "删除成功！");
	}

	
}
