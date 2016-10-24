package com.appscomm.sport.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.appscomm.sport.action.BaseAction;
import com.appscomm.sport.model.PersonVO;
import com.appscomm.sport.model.UserVO;
import com.appscomm.sport.service.UserService;

/**
 * 系统公用Action
 * 
 * 包含文件上传等功能。
 * 
 * @author qindf
 * 
 */
public class MediaUtils extends BaseAction {
	private static final long serialVersionUID = 2196669409327347676L;

	private static final Logger log = LoggerFactory
			.getLogger(MediaUtils.class);
	@Autowired
	private UserService userService;
	
	private final String NOT_UPLOAD_SUFFIX = "exe,js,jsp,php"; //不可上传的文件后缀
//	private final String DOWN_SUFFIX = "xls,xlsx,doc,docs"; //可下载的文件后缀
	private UserVO user;
	private String width = "500";
	private String height = "500";

	private PersonVO person;
	
	public PersonVO getPerson() {
		return person;
	}

	public void setPerson(PersonVO person) {
		this.person = person;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	
	/**
	 * 上传文件
	 * @return
	 */
	public void uploadFile_json() {
		user = super.getUsers();
		// 进入上传页面
		log.debug("上传文件：" + uploadFileFileName);
		if (uploadFileFileName == null) {
			error = "Failed to upload upload file not found！";
			log.info(error);
		}
		int suffixIndex = uploadFileFileName.lastIndexOf('.');
		if (suffixIndex == -1) {
			error = "Failed to upload the file name suffix! No, not allowed to upload！";
			log.info(error);
		}
		String name = uploadFileFileName.substring(0, suffixIndex);
		String suffix = uploadFileFileName.substring(suffixIndex + 1)
				.toLowerCase();
		if(NOT_UPLOAD_SUFFIX.contains(suffix)){
			error = "Failed to upload the file type isn't upload！";
			log.info(error);
		}
		if (StringUtils.isBlank(name)) {
			error = "Failed to upload the file name! No file name, not allowed to upload！";
			log.info(error);
		}
		File toSave;
//		String filePath = getText("file.upload.path").replaceAll("\\/", "\\\\");
		String filePath = getText("file.upload.path");
		toSave = new File(filePath);
		if(!toSave.exists())
			toSave.mkdirs();
//		String newFileName = uploadFileFileName;
//		uploadPath = filePath + "\\" +newFileName;
		uploadPath = ImageUtil.getUUIDPath(suffix, filePath);
		String tempPath = uploadPath;
		uploadPath = filePath + tempPath;
		try {
//			toSave = new File(uploadPath+new Date().getTime());
//			FileUtils.copyFile(uploadFile, toSave);
			//上传之前先压缩图片
			log.info("输入文件路径： " + uploadFile.getAbsolutePath());
			ImageUtil.compressImage(
					uploadFile.getAbsolutePath(), uploadPath,
					Integer.parseInt(width), Integer.parseInt(height));
			if (user != null){
				//修改路径
				person = userService.getPerson(user.getId());
				person.setImgUrl(tempPath);
				userService.editPerson(person);
				error = "Image update success!";
				//替换session
				UserVO userVO = (UserVO)getSession().get("loginUsers");
				userVO.setImgUrl(tempPath);
				getSession().put("loginUsers", userVO);
				////groupService.flushUser(person.getRegisterId()+"");
			}
			log.info("上传文件成功：{}", uploadPath);
		} catch (Exception e) {
			log.error("上传失败！", e);
		}
		super.writeJson(error);
	}
	
	
	
	/**
	 * 上传文件
	 * 
	 * @return
	 */
	public String uploadFile() {
		user = super.getUsers();
		// 进入上传页面
		log.debug("上传文件：" + uploadFileFileName);
		if (uploadFileFileName == null) {
			error = "上传失败！没有找到上传文件！";
			log.info(error);
			return SUCCESS;
		}
		int suffixIndex = uploadFileFileName.lastIndexOf('.');
		if (suffixIndex == -1) {
			error = "上传失败！文件没有后缀名，不允许上传！";
			log.info(error);
			return SUCCESS;
		}
		String name = uploadFileFileName.substring(0, suffixIndex);
		String suffix = uploadFileFileName.substring(suffixIndex + 1)
				.toLowerCase();
		if(NOT_UPLOAD_SUFFIX.contains(suffix)){
			error = "上传失败！该文件类型不可上传！";
			log.info(error);
			return SUCCESS;
		}
		if (StringUtils.isBlank(name)) {
			error = "上传失败！该文件名没有文件名，不允许上传！";
			log.info(error);
			return SUCCESS;
		}
		File toSave;
//		String filePath = getText("file.upload.path").replaceAll("\\/", "\\\\");
		String filePath = getText("file.upload.path");
		toSave = new File(filePath);
		if(!toSave.exists())
			toSave.mkdirs();
		String newFileName = uploadFileFileName;
		uploadPath = filePath + File.separator +newFileName;
		try {
			toSave = new File(uploadPath+new Date().getTime());
//			FileUtils.copyFile(uploadFile, toSave);
			//上传之前先压缩图片
			log.info("输入文件路径： " + uploadFile.getAbsolutePath());
			ImageUtil.compressImage(
					uploadFile.getAbsolutePath(), uploadPath,
					Integer.parseInt(width), Integer.parseInt(height));
			/*
			FileUtils.copyFile(ImageUtil.compressImage(
					uploadFile.getAbsolutePath(), uploadPath,
					Integer.parseInt(width), Integer.parseInt(height)), toSave);
					*/
			error = "上传文件成功";
			log.info("上传文件成功：{}", uploadPath);
			return SUCCESS;
		} catch (Exception e) {
			error = "上传失败！信息:" + e.getMessage();
			log.error("上传失败！", e);
			return SUCCESS;
		}
	}

	/**
	 * 下载文件
	 * @return
	 */
	public String downloadFile(){
		if(downloadPath == null) return NONE;
		int suffixIndex = downloadPath.lastIndexOf('.');
		if (suffixIndex == -1) {
			return NONE;
		}
		/*String suffix = downloadPath.substring(suffixIndex + 1);				
		if(downloadPath.contains("../")|| !DOWN_SUFFIX.contains(suffix)){
			return NONE;
		}*/
		
		String realPath = downloadPath;	
		File f = new File(realPath);
		HttpServletResponse response = ServletActionContext.getResponse();
        if (!f.exists()) {
            try {
				response.sendError(404, "File not found!");
			} catch (IOException e) {
				e.printStackTrace();
			}
            return NONE;
        }
		String filedisplay = downloadPath.substring(downloadPath.lastIndexOf("/")+1);
		try {
			filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setContentType("application/zip");
		response.addHeader("Content-Disposition",
				"attachment;filename=" + filedisplay);
		OutputStream outp = null;
		FileInputStream in = null;
		try {
			outp = response.getOutputStream();
			in = new FileInputStream(f);

			byte[] b = new byte[1024];
			int i = 0;

			while ((i = in.read(b)) > 0) {
				outp.write(b, 0, i);
			}
			outp.flush();
		} catch (Exception e) {
			log.error("", e);
		} finally {
			if (in != null) {
				try {
					in.close();
					in = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outp != null) {
				try {
					outp.close();
					outp = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return NONE;
	}
	
	
	private java.io.File uploadFile;
	private String uploadFileContentType;
	private String uploadFileFileName;

	private String uploadPath;
	private String downloadPath;
	private String error;


	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public java.io.File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(java.io.File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}
	
}
