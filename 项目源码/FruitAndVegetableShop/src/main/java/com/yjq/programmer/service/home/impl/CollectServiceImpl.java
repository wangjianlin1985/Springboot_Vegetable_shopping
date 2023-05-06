package com.yjq.programmer.service.home.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.home.CollectMapper;
import com.yjq.programmer.pojo.home.Collect;
import com.yjq.programmer.service.home.ICollectService;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-14 13:40
 */

/**
 * 收藏service接口实现类
 * @author 82320
 *
 */
@Service
public class CollectServiceImpl implements ICollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public List<Collect> findCollectByUserId(Long userId) {
        return collectMapper.findCollectByUserId(userId);
    }

    @Override
    public ResponseVo<Boolean> add(Long id, HttpServletRequest request) {
        if(id == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        //获取当前登录用户id
        String userId = (String) request.getAttribute("id");
        //判断是否已经添加过收藏
        Collect collectByUserIdAndProductId = collectMapper.findCollectByUserIdAndProductId(Long.valueOf(userId), id);
        if(collectByUserIdAndProductId != null){
            return ResponseVo.errorByMsg(CodeMsg.COLLECT_ALREADY_EXIST);
        }
        //声明一个收藏实体类
        Collect collect = new Collect(Long.valueOf(userId), id);
        //把收藏信息添加到数据库中
        if(collectMapper.insertSelective(collect) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.COLLECT_ADD_ERROR);
        }
        return ResponseVo.successByMsg(true, "添加收藏成功！");
    }

    @Override
    public ResponseVo<Boolean> delete(Long id) {
        if(id == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        //根据收藏id删除数据库信息
        if(collectMapper.deleteByPrimaryKey(id) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.COLLECT_DELETE_ERROR);
        }
        return ResponseVo.successByMsg(true, "删除收藏成功！");
    }

    @Override
    public ResponseVo<PageInfo> getCollectByPageAndUserId(Integer pageNum, Integer pageSize, Long userId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Collect> allCollect = collectMapper.findCollectByUserId(userId);
        PageInfo pageInfo = new PageInfo<>(allCollect);
        pageInfo.setList(allCollect);
        return ResponseVo.success(pageInfo);
    }
}
