package com.liang.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.liang.VO.ResultVO;
import com.liang.util.ResultVOUtil;

@RestController
public class UploadFile {

	/*@RequestMapping(value="/databasefile")
    @ResponseBody
    public ResultVO databasefile(MultipartFile imgFile,HttpServletRequest request,String key) throws IOException {
		System.out.println("key:"+key);
		if(key.equals("383B9E584B54A8EA7819D2B4C0852043")) {
		
		System.out.println("传入的文件参数："+JSON.toJSONString(imgFile, true));
	        //File path = new File(ResourceUtils.getURL("classpath:").getPath());
	        //win路径
	        //String pathImg="C:/eclipseWorkspace/uploadFile/employee/image";
	        //System.out.println(pathImg);
	        String pathImg="/BackupsFile"+File.separator+"/database";
	        String Suffix = imgFile.getOriginalFilename();
	        int pos = Suffix.lastIndexOf(".");
	        String extName = Suffix.substring(pos+1).toLowerCase();
	        System.out.println(extName);
	        if(extName.equals("zip") || extName.equals("7z")) {
	        	String fileName = UUID.randomUUID() + "." + extName;
				
				File targetFile = new File(pathImg);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				File targetFile2 = new File(pathImg+"/"+fileName);
				if (!targetFile2.exists()) {
					targetFile2.createNewFile();
				}
				// 保存
				imgFile.transferTo(targetFile2);
				return ResultVOUtil.success(pathImg+File.separator+fileName);
	        }else {
	        	return ResultVOUtil.error(-1, "格式错误");
	        }
		}else {
			return ResultVOUtil.error(-1, "密钥错误");
		}
    }*/
	
	@RequestMapping(value="/imgfile")
    @ResponseBody
    public ResultVO imgfile(MultipartFile imgFile,HttpServletRequest request) throws IOException {
		//System.out.println("传入的文件参数："+JSON.toJSONString(file, true));
	       // File path = new File(ResourceUtils.getURL("classpath:").getPath());
	        //win路径
	        String pathImg="C:/eclipseWorkspace/uploadFile/image";
	        //System.out.println(pathImg);
	        //String pathImg="/BackupsFile"+File.separator+"/imgfile";
	        String Suffix = imgFile.getOriginalFilename();
	        int pos = Suffix.lastIndexOf(".");
	        String extName = Suffix.substring(pos+1).toLowerCase();
	        System.out.println(extName);
        	String fileName = UUID.randomUUID() + "." + extName;
			
			File targetFile = new File(pathImg);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			File targetFile2 = new File(pathImg+"/"+fileName);
			if (!targetFile2.exists()) {
				targetFile2.createNewFile();
			}
			// 保存
			imgFile.transferTo(targetFile2);
			return ResultVOUtil.success(pathImg+File.separator+fileName);
    }
	
}
