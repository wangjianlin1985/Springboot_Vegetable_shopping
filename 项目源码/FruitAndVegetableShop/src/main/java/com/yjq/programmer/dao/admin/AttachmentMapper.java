package com.yjq.programmer.dao.admin;

import com.yjq.programmer.pojo.admin.Attachment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 附件mapper接口
 * @author 82320
 *
 */
@Repository
public interface AttachmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attachment record);

    int insertSelective(Attachment record);

    Attachment selectByPrimaryKey(Integer id);
    
    List<Attachment> selectAll();

    int updateByPrimaryKeySelective(Attachment record);

    int updateByPrimaryKey(Attachment record);
    
    int getTotal();
}