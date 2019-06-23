package com.syh.mchr.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 签到信息
 * @author SYH
 *
 */
public class Sign implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final Integer late = 1;
	public static final Integer early = 2;
	public static final Integer common = 3;
	public static final Integer ignore = 4;
	public static final Integer lateAndEarly = 5;
	
	public static final Integer SIGNIN = 1;
	public static final Integer SIGNOUT = 2;
	
	private String eno;
	private Date date;
	private Date signInTime;
	private Date signOutTime;
	private Integer signState;//签到类型SIGNIN/SIGNOUT
	private Integer status;//签到情况
	public String getEno() {
		return eno;
	}
	public void setEno(String eno) {
		this.eno = eno;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getSignInTime() {
		return signInTime;
	}
	public void setSignInTime(Date signInTime) {
		this.signInTime = signInTime;
	}
	public Date getSignOutTime() {
		return signOutTime;
	}
	public void setSignOutTime(Date signOutTime) {
		this.signOutTime = signOutTime;
	}
	public Integer getSignState() {
		return signState;
	}
	public void setSignState(Integer signState) {
		this.signState = signState;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((eno == null) ? 0 : eno.hashCode());
		result = prime * result + ((signInTime == null) ? 0 : signInTime.hashCode());
		result = prime * result + ((signOutTime == null) ? 0 : signOutTime.hashCode());
		result = prime * result + ((signState == null) ? 0 : signState.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sign other = (Sign) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (eno == null) {
			if (other.eno != null)
				return false;
		} else if (!eno.equals(other.eno))
			return false;
		if (signInTime == null) {
			if (other.signInTime != null)
				return false;
		} else if (!signInTime.equals(other.signInTime))
			return false;
		if (signOutTime == null) {
			if (other.signOutTime != null)
				return false;
		} else if (!signOutTime.equals(other.signOutTime))
			return false;
		if (signState == null) {
			if (other.signState != null)
				return false;
		} else if (!signState.equals(other.signState))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Sign [eno=" + eno + ", date=" + date + ", signInTime=" + signInTime + ", signOutTime=" + signOutTime
				+ ", signState=" + signState + ", status=" + status + "]";
	}
	
	
	
	
	
}
