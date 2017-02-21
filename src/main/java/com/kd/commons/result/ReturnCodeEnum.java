package com.kd.commons.result;

public enum ReturnCodeEnum {
	
	PARAM_ERROR(-8,"params error"),
	ERROR(-9,"error"),
	WARN(-1,"warn"),
	FAILED(0,"failed"),
	SUCCESS(200,"success"),
	
	ACCOUNT_FORBIDEN(-203,"account_forbiden"),
	BAD_PROTOCOL(-204,"bad_protocol"),
	ACCOUNT_ERROR(-205,"account_error"),
	DEVICE_FORBIDDEN(-206,"device_forbidden"),
	LOGIN_FAILED(-207,"login_failed"),
	
	_200(200,"success"),
	_500(500,"Service code is wrong "),
	 /** Re-like **/
	_1(-1,"You have already liked this person "),
	_10(-10," You have already liked this person "),
	/** Repeat **/
	_2(-2,"Unable re-add forbidden information "),
	_3(-3,"Unable to find out this account "),
	_4(-4,"Unable to find out this device "),
	_5(-5,"This account is forced offline "),
	
	__202(-202,"login-failed,username or password error"),//Log in failed 
	__203(-203,"deviceid-forbiddened"),//This device have been blocked
	__204(-204,"bad-protocol"),//Protocol is wrong 
	__205(-205,"retryfailed"),//You have inputted wrong password over 5 times	
	__206(-206,"account-forbiddened"),//This device have been blocked
	__207(-207,"not-authorized"),//Invalid password 
	
	/** Change password **/
	_404(404," Unqualified password"),
	_403(403," You haven’t bound custom account yet "),
	_402(402,"Oringinal password is invalid "),

	/** About phone & text   */
	_507(507 ," This phone has been registered already, don't register repeatedly "),
	_508(508 ," SMS verification succeeded"),
	_509(509 ," SMS verification failed"),
	_510(510," Phone number is invalid"),
	_511(511," SMS verification expired "),
	_851(851,"  Number of daily verification code have reached limited  "),
	_852(852,"Number of verification code of this phone number have reached limited "),
	_853(853,"This phone number does not exist "),

	_856(856,"Verification request frequently "),

	/** Search user */

	_300(300 ,"This user is offline "),
	_301(301 ,"This user doesn’t exist "),
	_302(302 ,"This user already blocked "),
	_303(303,"This user does not in blacklist "),
	_803(803,"Unblock this user and start chat"),
	_501(501,"You have favorite this user "),
	_503(503,"Unable to like homosexual user "),
	_504(504,"You have not followed this user yet "),
	
	/** Light up medal  **/
	_601(601,"lighten failed"),
	
	/** Guard **/
	_701(701,"guard failed"),
	_702(702,"can't guard self"),
	
	/***
	 * Service limitation	 */
	_710(710,"no have VIP "),
	_711(711,"no privilege "),
	_712(712,"no auth of peeper "),
	

	/** Search user */
	_901(901,"recommend failed"),//Homepage recommendation failed 
	_902(902,"user search failed"),//Search user failed 
	
	/** List */
	_911(911,"rank list failed"),//Access list failed\
	_912(912,"rank top failed"),//Access the first three list failed	
	
	;
	
	
	public String msg;
	
	public int code;
	
	ReturnCodeEnum(int code,String msg){
		this.msg=msg;
		this.code=code;
	}
}
