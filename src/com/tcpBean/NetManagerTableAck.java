package com.tcpBean;

/**
 * app网管巡检作业表应答
 * 
 * cmdtype(netmanagertableack)
 * serialnumber
 * userid
 * ack					-- 0：确认收到；1：其它异常信息
 * 
 * @author 12878
 *
 */

public class NetManagerTableAck {
	private String cmdtype = "netmanagertableack";
	private String serialnumber;
	private String userid;
	private String ack;
	public String getCmdtype() {
		return cmdtype;
	}
	public void setCmdtype(String cmdtype) {
		this.cmdtype = cmdtype;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAck() {
		return ack;
	}
	public void setAck(String ack) {
		this.ack = ack;
	}
	@Override
	public String toString() {
		return "NetManagerTableAck [cmdtype=" + cmdtype + ", serialnumber="
				+ serialnumber + ", userid=" + userid + ", ack=" + ack + "]";
	}
	
}
