/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-10-11
 */
package com.appscomm.sport.common;

/**
 *  运动项目api公共结果代码 
 *	
 *  qindf create by 2013-10-11
 *
 */
public enum SportApiCode {
	
	SUCCESS("0","The success of the operation"),
	ERROR_9999("9999","program exception"),
	ERROR_1000("1000","records is not exits!"),
	ERROR_1001("1001","Token Authorization failed！"),
	ERROR_1002("1002","Header Not null！"),
	ERROR_2001("2001","param Not null！"),
	ERROR_2002("2002","Verification code cannot be empty！"),
	ERROR_2003("2003","Verification code error！"),
	ERROR_2004("2004","param is null or includes invalid charactor！"),
	
	ERROR_1003("1003","Synchronization device is not connected！"),
	ERROR_1101("1101","The user name or password is incorrect！"),
	ERROR_1102("1102","Account does not exist, please re-enter！"),
	ERROR_1103("1103","Please fill in the registration e-mail address or mobile phone number！"),
	ERROR_1104("1104","Please fill in the password！"),
	ERROR_1105("1105","Enter your registered mailbox already exists！"),
	ERROR_1106("1106","Enter your registered phone already exists！"),
	ERROR_1107("1107","Enter your registered nickname already exists！"),
	ERROR_1108("1108","Does not support password！"),
	ERROR_1109("1109","The Baidu unregistered users in the music, please register！"),
	ERROR_1110("1110","This user has registered！"),
	ERROR_1111("1111","Account  or Password erro！"),
	ERROR_1112("1112","Change the password failed, please try again！"),
	ERROR_1113("1113","Add a new user database operation failed, abnorma！"),
	ERROR_1114("1114","user is not exist！"),
	ERROR_1115("1115","only support email account！"),
	
	ERROR_3001("3001", "The user basic information is not complete, please fill in the complete data!"),
	ERROR_3002("3002", "Modify the database operation failed to remind the state, abnormal!"),
	
	ERROR_4000("4000", "Failed to send email and try again!"),
	ERROR_4001("4001", "You fill out the registration mailbox does not exist, please check it and try again!"),
	
	ERROR_7777("7777","Login failed, please re login！"),
	ERROR_7778("7778","illegal operation！"),
	ERROR_7779("7779","Data synchronization failed！"),
	ERROR_7780("7780","Please initialize！"),
	ERROR_7781("7781","Remind add！"),
	ERROR_7782("7782","The current user watch machine number has been bind！"),
	//ERROR_7783("7783", "绑定腕表操作失败！"),
	ERROR_7784("7784", "Unbound watch operation failed, watch does not exist or has been tied to the solution！"),
	ERROR_7785("7785","Binding of SIM operation failed！"),
	ERROR_7786("7786","Unbound SIM operation failed, watch does not exist or has been tied to the solution！"),
	ERROR_7787("7787","Device settings information save failed！"),
	ERROR_7788("7788","Cannot find the specified regional information！"),
	ERROR_7789("7789","The current device, unbound！"),
	ERROR_7790("7790","Failed to update database！"),
	ERROR_7791("7791","Client transfer incomplete parameters！"),
	ERROR_7792("7792","The record does not exist！"),
	ERROR_7793("7793","The user and device binding login account corresponding to the user is not consistent！"),
	ERROR_7794("7794","The nickname is already registered, please change！"),
	ERROR_7795("7795", "Binding to watch the operation fails, the account has been bound with the same type equipment, please unbind after the operation!"),
	ERROR_7796("7796", "Binding watch operation failed, clear user data failed!"),
	ERROR_7797("7797", "user id can not be null!"),
	ERROR_7798("7798", "id can not be null!"),
	ERROR_8000("8000", "Advanced setting is null"),

	ERROR_8089("8089", "kronoz token is null"),
	ERROR_8099("8099", "kronoz token is invalid"),
	ERROR_8109("8109", "kronoz token is Expired"),
	ERROR_9000("9000", "Please check you system time.");
	
	
	private String code;
	private String desc;
	
	private SportApiCode(String code,String desc){
		this.code = code;
		this.desc = desc;
		
	}
	public static SportApiCode getSportApiCode(String code){
		if(code.equals("8089")){
			return ERROR_8089;
		}else if(code.equals("8099")){
			return ERROR_8099;
		}else if(code.equals("8109")){
			return ERROR_8109;
		}
		return null;
	}
	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
