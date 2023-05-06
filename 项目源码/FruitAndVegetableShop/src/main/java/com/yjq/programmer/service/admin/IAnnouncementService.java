package com.yjq.programmer.service.admin;

import com.github.pagehelper.PageInfo;
import com.yjq.programmer.pojo.admin.Announcement;
import com.yjq.programmer.vo.common.ResponseVo;

/**
 * 公告service接口
 * @author 82320
 *
 */
public interface IAnnouncementService {

	//通过分页获取公告列表
	ResponseVo<PageInfo> getAnnouncementByPage(Integer pageNum, Integer pageSize);
	
	//通过分页和搜索内容获取公告列表
	ResponseVo<PageInfo> getAnnouncementByPageAndContent(Integer pageNum, Integer pageSize, String content);
	
	//发布公告
	ResponseVo<Boolean> add(Announcement announcement, Integer loginedId);
	
	//删除公告
	 ResponseVo<Boolean> delete(Integer id);
}
