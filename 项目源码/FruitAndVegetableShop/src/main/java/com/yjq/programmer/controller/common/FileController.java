package com.yjq.programmer.controller.common;

import com.yjq.programmer.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

/**
 * UEditor图片上传
 */
@Controller
@RequestMapping("/ueditor")
public class FileController {
	

	@Value("${yjq.upload.photo.path}")
	private String uploadPhotoPath;//文件保存位置
	
	
    @RequestMapping(value = "/file")
    @ResponseBody
    public String file(HttpServletRequest request) {
        String s = "{\n" +
                "            \"imageActionName\": \"uploadimage\",\n" +
                "                \"imageFieldName\": \"file\", \n" +
                "                \"imageMaxSize\": 2048000, \n" +
                "                \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \n" +
                "                \"imageCompressEnable\": true, \n" +
                "                \"imageCompressBorder\": 1600, \n" +
                "                \"imageInsertAlign\": \"none\", \n" +
                "                \"imageUrlPrefix\": \"\",\n" +
                "                \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\" }";
        return s;
    }

    @RequestMapping(value = "/imgUpdate")
    @ResponseBody
    public String imgUpdate(MultipartFile file, HttpServletRequest request) throws FileNotFoundException {
        if (file.isEmpty()) {
            return "error";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 这里我使用随机字符串来重新命名图片
        fileName = Calendar.getInstance().getTimeInMillis() + suffixName;

        String realPath = request.getSession().getServletContext().getRealPath("images");

        String path = uploadPhotoPath + "/" + StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + fileName;//此处保存在本地了，你也可以保存在图片服务器，或者realPath做临时文件
        File dest = new File(path);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            String config = "{\"state\": \"SUCCESS\"," +
                    "\"url\": \"" + "http://localhost:8080/ueditor/images/" +StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + fileName + "\"," +
                    "\"title\": \"" + fileName + "\"," +
                    "\"original\": \"" + fileName + "\"}";
            return config;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }


    /**
     * 通过url请求返回图像的字节流
     *      
     */
    @RequestMapping("/images/{date}/{fileName}")
    public void getIcon(@PathVariable("fileName") String fileName,@PathVariable("date") String date, HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (StringUtils.isEmpty(fileName)) {
            fileName = "";
        }
        String path = uploadPhotoPath + "/" +date + "/" + fileName;;


        File file = new File(path);
        //判断文件是否存在如果不存在就返回默认图标
        if (!(file.exists() && file.canRead())) {
            file = new File(path);
        }

        FileInputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        int length = inputStream.read(data);
        inputStream.close();
        response.setContentType("image/png");
        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
    }

    @RequestMapping(value = "/uploadContent.action")
    @ResponseBody
    public void uploadContent(HttpServletRequest request) {
        String content = request.getParameter("myContent");
        System.out.println(content);
        return;
    }
}