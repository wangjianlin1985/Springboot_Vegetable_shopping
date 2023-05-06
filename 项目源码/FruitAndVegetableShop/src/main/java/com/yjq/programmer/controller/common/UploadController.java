package com.yjq.programmer.controller.common;

import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.constant.SessionConstant;
import com.yjq.programmer.dao.admin.AttachmentMapper;
import com.yjq.programmer.pojo.admin.Admin;
import com.yjq.programmer.pojo.admin.Attachment;
import com.yjq.programmer.util.StringUtil;
import com.yjq.programmer.util.ValidateEntityUtil;
import com.yjq.programmer.vo.common.ResponseVo;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 公用的上传类
 * @author 82320
 *
 */
@RequestMapping("/upload")
@Controller
public class UploadController {

	@Value("${yjq.upload.photo.sufix}")
	private String uploadPhotoSufix;
	
	@Value("${yjq.upload.photo.maxsize}")
	private long uploadPhotoMaxSize;   //大小1024KB
	
	@Value("${yjq.upload.photo.path}")
	private String uploadPhotoPath;//图片保存位置
	
	@Value("${yjq.upload.attachment.maxsize}")
	private long uploadAttachmentMaxSize;   //大小204800KB
	
	@Value("${yjq.upload.attachment.path}")
	private String uploadAttachmentPath;//附件保存位置
	
	private Logger log = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	/**
	 * 图片统一上传类
	 * @param photo
	 * @return
	 */
	@RequestMapping(value="/upload_photo",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<String> uploadPhoto(@RequestParam(name="photo",required=true)MultipartFile photo){
		//判断文件类型是否是图片
		String originalFilename = photo.getOriginalFilename();
		//获取文件后缀
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
		if(!uploadPhotoSufix.contains(suffix.toLowerCase())){
			return ResponseVo.errorByMsg(CodeMsg.UPLOAD_PHOTO_SUFFIX_ERROR);
		}
		//photo.getSize()单位是B
		if(photo.getSize()/1024 > uploadPhotoMaxSize){
			CodeMsg codeMsg = CodeMsg.UPLOAD_PHOTO_ERROR;
			codeMsg.setMsg("图片大小不能超过" + (uploadPhotoMaxSize/1024) + "M");
			return ResponseVo.errorByMsg(codeMsg);
		}
		//准备保存文件
		File filePath = new File(uploadPhotoPath);
		if(!filePath.exists()){
			//若不存在文件夹，则创建一个文件夹
			filePath.mkdir();
		}
		filePath = new File(uploadPhotoPath + "/" + StringUtil.getFormatterDate(new Date(), "yyyyMMdd"));
		//判断当天日期的文件夹是否存在，若不存在，则创建
		if(!filePath.exists()){
			//若不存在文件夹，则创建一个文件夹
			filePath.mkdir();
		}
		String filename = StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + suffix;
		try {
			photo.transferTo(new File(uploadPhotoPath+"/"+filename));   //把文件上传
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("图片上传成功，保存位置：" + uploadPhotoPath +filename);
		return ResponseVo.success(filename);
		
	}
	
	/**
	 * 附件统一上传类
	 * @param attachment
	 * @return
	 */
	@RequestMapping(value="/upload_attachment",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Attachment> uploadAttachment(@RequestParam(name="attachment",required=true)MultipartFile attachment,HttpServletRequest request){
		Admin loginedAdmin = (Admin) request.getSession().getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
		String originalFilename = attachment.getOriginalFilename(); //附件名字
		//获取文件后缀
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
		//attachment.getSize()单位是B
		if(attachment.getSize()/1024 > uploadAttachmentMaxSize){
			CodeMsg codeMsg = CodeMsg.UPLOAD_ATTACHMENT_ERROR;
			codeMsg.setMsg("附件大小不能超过" + (uploadAttachmentMaxSize/1024) + "M");
			return ResponseVo.errorByMsg(codeMsg);
		}
		//准备保存文件
		File filePath = new File(uploadAttachmentPath);
		if(!filePath.exists()){
			//若不存在文件夹，则创建一个文件夹
			filePath.mkdir();
		}
		filePath = new File(uploadAttachmentPath + "/" + StringUtil.getFormatterDate(new Date(), "yyyyMMdd"));
		//判断当天日期的文件夹是否存在，若不存在，则创建
		if(!filePath.exists()){
			//若不存在文件夹，则创建一个文件夹
			filePath.mkdir();
		}
		String filename = StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + suffix;
		try {
			attachment.transferTo(new File(uploadAttachmentPath+"/"+filename));   //把文件上传
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//把附件信息写入数据库
		BigDecimal size = new BigDecimal((double)attachment.getSize()/1024);
		size = size.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		Attachment saveAttachment = new Attachment(null,loginedAdmin.getId(),filename,originalFilename,size); //id,senderid,url,name
		CodeMsg validate = ValidateEntityUtil.validate(saveAttachment);
		if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
			return  ResponseVo.errorByMsg(validate);
		}
		if(attachmentMapper.insertSelective(saveAttachment) <= 0) {
			return ResponseVo.errorByMsg(CodeMsg.UPLOAD_ATTACHMENT_ERROR);
		}
		log.info("附件上传成功，保存位置：" + uploadAttachmentPath +filename);
		return ResponseVo.success(saveAttachment);
		
	}

	
	/**
	 * 文件统一下载类
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/download_file",method=RequestMethod.GET)
	public void downloadFile(HttpServletResponse response,Integer id){
		Attachment selectByPrimaryKey = attachmentMapper.selectByPrimaryKey(id);
		try {
			File file = new File(uploadAttachmentPath,selectByPrimaryKey.getUrl());
			response.setHeader("Content-Disposition","attachment;filename=" + new String(selectByPrimaryKey.getName().getBytes("UTF-8"), "ISO8859-1"));
			writefile(response, file);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 打印到浏览器上下载
	 * @param response
	 * @param file
	 */
	public void writefile(HttpServletResponse response, File file) {
		ServletOutputStream sos = null;
		FileInputStream aa = null;
		try {
			aa = new FileInputStream(file);
			sos = response.getOutputStream();
			// 读取文件问字节码
			byte[] data = new byte[(int) file.length()];
			IOUtils.readFully(aa, data);
			// 将文件流输出到浏览器
			IOUtils.write(data, sos);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				sos.close();
				aa.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
