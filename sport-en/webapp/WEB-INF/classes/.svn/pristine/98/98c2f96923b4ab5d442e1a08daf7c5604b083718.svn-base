package com.appscomm.sport.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.appscomm.sport.service.SendMailForPassword;
import com.appscomm.sport.utils.MailSenderInfo;
import com.appscomm.sport.utils.SimpleMailSender;
import com.appscomm.sport.utils.Tools;
import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;

@Service("sendMailForPassword")
public class SendMailForPasswordImpl implements SendMailForPassword{
	private static Log logger = LogFactory.getLog(SendMailForPasswordImpl.class);
	private MandrillApi mandrillApi = new MandrillApi("pgDX9llb9OdJtws6dCqP_A");
	
	@SuppressWarnings("static-access")
	@Override
	public boolean sendMail(String encryType, String subject, String emailServerHost, String emailServerPort,  String companyMail, String companyMailPassword, String userMail, String contents) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(emailServerHost);
		mailInfo.setMailServerPort(emailServerPort);
		mailInfo.setValidate(true);
		mailInfo.setUserName(companyMail);
		mailInfo.setPassword(companyMailPassword);// 您的邮箱密码
		mailInfo.setFromAddress(companyMail);
		mailInfo.setToAddress(userMail);
		mailInfo.setSubject(subject);
		mailInfo.setContent(contents);
		mailInfo.setEncryType(encryType);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		return sms.sendHtmlMail(mailInfo);
	//	return true;
	}

	public boolean sendMail(String companyMail, String subject, String userMail, String contents){
		// create your message
		MandrillMessage message = new MandrillMessage();
		message.setSubject(subject);
		message.setHtml(contents);
		message.setAutoText(true);
		message.setFromEmail(companyMail);
		message.setFromName(companyMail);
		// add recipients
		ArrayList<Recipient> recipients = new ArrayList<Recipient>();
		Recipient recipient = new Recipient();
		recipient.setEmail(userMail);
		recipient.setName(userMail);
		recipients.add(recipient);
		message.setTo(recipients);
		message.setPreserveRecipients(true);
	
		try {
			MandrillMessageStatus[] statusReports = mandrillApi.messages().send(message, false);
//			logger.info("Send email :" + userMail + ",subject:" + subject + ", status report:"+ statusReports[0].getEmail() +",return:" + statusReports[0].getStatus());
			if (statusReports[0].getStatus().equals("sent")){
				return true;
			}
		} catch (MandrillApiError e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}	
		return false;
	}
	/*
	@Override
	public String getMailFindPwdContents(String rootPath, String userMail, String verCode, String staticFilePath,  String clientType, String companyMail, String watchName) {
		String name = "";
		if(userMail != null && userMail.indexOf("@")!=-1){
			name = userMail.substring(0, userMail.indexOf("@"));
		}
		String resetUrl = rootPath+"/forgetPassword_resetinput?email="+userMail+"&date="+new Date().getTime()+"&verCode="+verCode;
		//客户端的来源
		if(StringUtils.isBlank(verCode)){
			resetUrl= rootPath+"/forgetPassword_resetinput?email="+userMail+"&date="+new Date().getTime()+"&type="+clientType;
		}
		
		if(watchName.indexOf("2")!=-1){
			watchName = watchName.substring(0, watchName.length()-1)+"<sup>2</sup>";
		}
		StringBuffer sbr = new StringBuffer("<html><head><!-- NAME: 1 COLUMN --><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		sbr.append("<title>Welcome to "+watchName+"!</title><style type=\"text/css\">body{background:#eee;color:#333;font-weight:normal;font-family:Helvetica, sans-serif;padding:0;margin:0;}");
		sbr.append("img{ border:0;}td{ padding:0 20px;}table{font-size:16px;}a{ text-decoration:none;}p{color:#666; font-family:'Trebuchet MS','Lucida Grande','Lucida Sans Unicode','Lucida Sans',Tahoma,sans-serif;}");
		sbr.append("</style></head><body><div style=\"background: none repeat scroll 0 0 #FFF6F7;\">");
		sbr.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"580\" align=\"center\" style=\"background:#fff; margin-top:20px;\">");
		sbr.append("<tbody><tr><td style=\"padding-top:20px; padding-bottom:20px;\"><img align=\"left\" alt=\"\" src=\""+staticFilePath+"/images/ZeFit_band.jpg\" style=\"max-width:600px;\"></td>");
		sbr.append("</tr><tr><td><p>Hi,</p><p>We received a password reset request for your "+ watchName +" account. To reset your password, use the link below :</p>");
		sbr.append("<p><strong><a href=\""+resetUrl+"\" target=\"_blank\"><span style=\"color:#FFA07A\">reset password</span></a></strong></p>");
		sbr.append("<p>If you didn't request a password reset, you can ignore this message and your password will not be changed.</p><p>MyKronoz Team</p>");               
		sbr.append("</td></tr><tr><td style=\"text-align:center; padding-top:20px; padding-bottom:20px;\"><a href=\"http://www.mykronoz.com\"  target=\"_blank\"><img alt=\"\" src=\""+staticFilePath+"/images/mykronoz.jpg\"></a></td>");
		sbr.append("</tr><tr><td style=\"font-size:12px; text-align:center;\"><p><strong>Copyright © 2014 KRONOZ LLC, All rights reserved</strong></p>");
		sbr.append("<p>This email was sent to: <a target=\"_blank\" href=\"mailto:"+userMail+"\"><span style=\"background-color: #E2F4FE;color: " +
				"#4F4F4F;font-family: helvetica neue,helvetica,arial,verdana,sans-serif;font-size: 12px;line-height: 18px;\">"+userMail+"</span></a></p>");
		
		sbr.append("<p style=\"padding-bottom:20px\">KRONOZ LLC, Route de Valavran 96, 1294 Genthod, Suisse</p></td></tr></tbody></table></body></html>");
		return sbr.toString();
		
	}
	
	
	@Override
	public String getMailRegistContents(String rootPath, String userMail, String verCode, String staticFilePath,  String clientType, String companyMail, String watchName) { 
		String name = "";
		if(userMail != null && userMail.indexOf("@")!=-1){
			name = userMail.substring(0, userMail.indexOf("@"));
		}
		
		if(watchName.indexOf("2")!=-1){
			watchName = watchName.substring(0, watchName.length()-1)+"<sup>2</sup>";
		}
		
		StringBuffer sbb = new StringBuffer("<html><head><!-- NAME: 1 COLUMN --><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		sbb.append("<title>Welcome to "+watchName+"!</title><style type=\"text/css\">body{background:#eee;color:#333;font-weight:normal;font-family:Helvetica, sans-serif;padding:0;margin:0;}");
		sbb.append("td{ padding:0 20px;}table{font-size:16px;}img{ border:0;}p{color:#666; font-family:'Trebuchet MS','Lucida Grande','Lucida Sans Unicode','Lucida Sans',Tahoma,sans-serif;}");
		sbb.append("</style></head><body><div style=\"background: none repeat scroll 0 0 #FFF6F7;\">");
		sbb.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"580\" align=\"center\" style=\"background:#fff; margin-top:20px;\">");
		sbb.append("<tbody><tr class=\"h20\"></tr><tr><td style=\"padding-top:20px; padding-bottom:20px;\"><img align=\"left\" alt=\"\" src='"+staticFilePath+"/images/ZeFit_band.jpg\' style=\"max-width:600px;\"></td></tr>");
	    sbb.append("<tr class=\"h20\"></tr><tr><td><h3>Welcome to "+watchName+" and thanks for joining MyKronoz community!</h3><p>"+watchName+" is a smart wristband that tells time, tracks steps, distance traveled, calories burned and hours slept.</p>");
		sbb.append("<p>Use "+watchName+" mobile app to sync your activity data, set your daily goals and reminders, view your stats in real-time and track your progress!</p>");
		sbb.append("<p>For more information, visit <a href=\"http://www.mykronoz.com/collections/"+watchName.toLowerCase()+"/\" target=\"_blank\"><span style=\"color:#FFA07A\">"+watchName+"</span></a> page!</p>");
		sbb.append("</td></tr><tr class=\"h20\"></tr><tr><td style=\"text-align:center; padding-top:20px; padding-bottom:20px;\"><a href=\"http://www.mykronoz.com\"  target=\"_blank\"><img alt=\"\" src='"+staticFilePath+"/images/mykronoz.jpg\'></a></td>");
		sbb.append("</tr><tr class=\"h20\"></tr><tr><td style=\"font-size:12px; text-align:center;\"><p><strong>Copyright © 2014 KRONOZ LLC, All rights reserved</strong></p>");
		sbb.append("<p>This email was sent to: <a target=\"_blank\" href=\"mailto:"+userMail+"\"><span style=\"background-color: #E2F4FE;color: #4F4F4F;font-family: helvetica neue,helvetica,arial,verdana,sans-serif;font-size: 12px;line-height: 18px;\">"+userMail+"</span></a></p>");
		sbb.append("<p style=\"padding-bottom:20px\">KRONOZ LLC, Route de Valavran 96, 1294 Genthod, Suisse</p>");
		sbb.append("</td></tr><tr class=\"h20\"></tr></tbody></table></body></html>");
		return sbb.toString();    
		
	}
	
	@Override
	public String getMailGetNewPwdContents(String userMail, String staticFilePath, String verCode, String newPassword, String watchName) {
		
		if(watchName.indexOf("2")!=-1){
			watchName = watchName.substring(0, watchName.length()-1)+"<sup>2</sup>";
		}
		
		StringBuffer sbr = new StringBuffer("<html><head><!-- NAME: 1 COLUMN --><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		sbr.append("<title>Welcome to "+watchName+"!</title><style type=\"text/css\">body{background:#eee;color:#333;font-weight:normal;font-family:Helvetica, sans-serif;padding:0;margin:0;}");
		sbr.append("img{ border:0;}td{ padding:0 20px;}table{font-size:16px;}a{ text-decoration:none;}p{color:#666; font-family:'Trebuchet MS','Lucida Grande','Lucida Sans Unicode','Lucida Sans',Tahoma,sans-serif;}");
		sbr.append("</style></head><body><div style=\"background: none repeat scroll 0 0 #FFF6F7;\">");
		sbr.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"580\" align=\"center\" style=\"background:#fff; margin-top:20px;\">");
		sbr.append("<tbody><tr><td style=\"padding-top:20px; padding-bottom:20px;\"><img align=\"left\" alt=\"\" src=\""+staticFilePath+"/images/ZeFit_band.jpg\" style=\"max-width:600px;\"></td>");
		sbr.append("</tr><tr><td><p>Hi,</p><p>We received a get new password request for your "+watchName+" account. The new password is <span style=\"color:#FFA07A\">" + newPassword+ ".</span></p>");
		sbr.append("<p>MyKronoz Team</p>");               
		sbr.append("</td></tr><tr><td style=\"text-align:center; padding-top:20px; padding-bottom:20px;\"><a href=\"http://www.mykronoz.com\"  target=\"_blank\"><img alt=\"\" src=\""+staticFilePath+"/images/mykronoz.jpg\"></a></td>");
		sbr.append("</tr><tr><td style=\"font-size:12px; text-align:center;\"><p><strong>Copyright © 2014 KRONOZ LLC, All rights reserved</strong></p>");
		sbr.append("<p>This email was sent to: <a target=\"_blank\" href=\"mailto:"+userMail+"\"><span style=\"background-color: #E2F4FE;color: " +
				"#4F4F4F;font-family: helvetica neue,helvetica,arial,verdana,sans-serif;font-size: 12px;line-height: 18px;\">"+userMail+"</span></a></p>");
		
		sbr.append("<p style=\"padding-bottom:20px\">KRONOZ LLC, Route de Valavran 96, 1294 Genthod, Suisse</p></td></tr></tbody></table></body></html>");
		return sbr.toString();
		
	}
*/
	@Override
	public String getMailFindPwdContents(String rootPath, String userMail, String verCode, String clientType, String emailTemplate) {
		String resetUrl = rootPath+"/forgetPassword_resetinput?email="+userMail+"&date="+new Date().getTime()+"&verCode="+verCode;
		if(StringUtils.isBlank(verCode)){
			resetUrl= rootPath+"/forgetPassword_resetinput?email="+userMail+"&date="+new Date().getTime()+"&type="+clientType;
		}
		if(Tools.templateEmailForgotPwd == null){
			try {
				Tools.templateEmailForgotPwd  = Tools.readFile(emailTemplate);
			} catch (IOException e) {
				logger.error(e);
			}
		}
		
		emailTemplate = Tools.templateEmailForgotPwd ;
		emailTemplate = emailTemplate.replaceAll("\\{\\{URL\\}\\}", resetUrl);
		emailTemplate = emailTemplate.replaceAll("\\{\\{email\\}\\}", userMail);
		return emailTemplate;
	}

	@Override
	public String getMailRegistContents(String rootPath, String userMail, String verCode, String emailTemplate) {
		if(Tools.templateEmailRegister == null){
			try {
				Tools.templateEmailRegister = Tools.readFile(emailTemplate);
			} catch (IOException e) {
				logger.error(e);
			}
		}
		
		emailTemplate = Tools.templateEmailRegister;
		emailTemplate = emailTemplate.replaceAll("\\{\\{email\\}\\}", userMail);
		return emailTemplate;
	}

	@Override
	public String getMailGetNewPwdContents(String rootPath, String userMail, String verCode, String newPwd,  String emailTemplate) {
		if(Tools.templateEmailNewPwd == null){
			try {
				Tools.templateEmailNewPwd = Tools.readFile(emailTemplate);
			} catch (IOException e) {
				logger.error(e);
			}
		}
		
		emailTemplate = Tools.templateEmailNewPwd;
		emailTemplate = emailTemplate.replaceAll("\\{\\{newPwd\\}\\}", newPwd);
		emailTemplate = emailTemplate.replaceAll("\\{\\{email\\}\\}", userMail);
		return emailTemplate;
	}

}
