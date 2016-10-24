package com.appscomm.sport.service;

public interface SendMailForPassword {

	/**
	 * 发送密码重置的邮件
	* @description:
	* @param emailAddr
	* @param verCode
	* @param contents
	* @return(设定参数)
	* @return boolean(返回值说明)
	* @author Administrator  2014-4-3
	 */
	public boolean sendMail(String encryType,String subject, String emailServerHost, String emailServerPort,  String companyMail, String companyMailPassword, String userMail, String contents);
	public boolean sendMail(String companyMail, String subject, String userMail, String contents);
	/**
	 * 获取密码重置的邮件内容
	* @description:
	* @return(设定参数)
	* @return String(返回值说明)
	* @author 陈林  2014-4-3
	 */
//	public String getMailFindPwdContents(String rootPath, String userMail, String verCode, String staticFilePath,  String clientType, String companyMail, String watchName);
	public String getMailFindPwdContents(String rootPath, String userMail, String verCode, String clientType, String emailTemplate);
	/**
	 * 获取注册的邮件内容
	* @description:
	* @return(设定参数)
	* @return String(返回值说明)
	* @author 陈林  2014-5-12
	 */
//	public String getMailRegistContents(String rootPath, String userMail, String verCode, String staticFilePath,  String clientType, String companyMail, String watchName);
	public String getMailRegistContents(String rootPath, String userMail, String verCode, String emailTemplate);
	/**
	 *  获取新密码邮件内容
	* @description:
	* @param userMail
	* @param staticFilePath
	* @param newPassword
	* @return(设定参数)
	* @return String(返回值说明)
	* @author Administrator  2014-7-22
	 */
//	public String getMailGetNewPwdContents(String userMail, String staticFilePath,  String verCode, String newPassword, String watchName);
	public String getMailGetNewPwdContents(String rootPath, String userMail, String verCode, String newPwd, String emailTemplate);
}
