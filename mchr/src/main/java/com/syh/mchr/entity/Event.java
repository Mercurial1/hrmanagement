package com.syh.mchr.entity;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final Integer wait=0;
	public static final Integer agree=1;
	public static final Integer refuse=2;
	
	private Integer eventid;
	private String eno;
	private Date vacationBegin;
	private Date vacationEnd;
	private Date submitTime;
	private String vacationReason;
	private String mgr;
	private Integer status;
	
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public Integer getEventid() {
		return eventid;
	}
	public void setEventid(Integer eventid) {
		this.eventid = eventid;
	}
	public String getEno() {
		return eno;
	}
	public void setEno(String eno) {
		this.eno = eno;
	}
	public Date getVacationBegin() {
		return vacationBegin;
	}
	public void setVacationBegin(Date vacationBegin) {
		this.vacationBegin = vacationBegin;
	}
	public Date getVacationEnd() {
		return vacationEnd;
	}
	public void setVacationEnd(Date vacationEnd) {
		this.vacationEnd = vacationEnd;
	}
	public String getVacationReason() {
		return vacationReason;
	}
	public void setVacationReason(String vacationReason) {
		this.vacationReason = vacationReason;
	}
	public String getMgr() {
		return mgr;
	}
	public void setMgr(String mgr) {
		this.mgr = mgr;
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
		result = prime * result + ((eno == null) ? 0 : eno.hashCode());
		result = prime * result + ((eventid == null) ? 0 : eventid.hashCode());
		result = prime * result + ((mgr == null) ? 0 : mgr.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submitTime == null) ? 0 : submitTime.hashCode());
		result = prime * result + ((vacationBegin == null) ? 0 : vacationBegin.hashCode());
		result = prime * result + ((vacationEnd == null) ? 0 : vacationEnd.hashCode());
		result = prime * result + ((vacationReason == null) ? 0 : vacationReason.hashCode());
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
		Event other = (Event) obj;
		if (eno == null) {
			if (other.eno != null)
				return false;
		} else if (!eno.equals(other.eno))
			return false;
		if (eventid == null) {
			if (other.eventid != null)
				return false;
		} else if (!eventid.equals(other.eventid))
			return false;
		if (mgr == null) {
			if (other.mgr != null)
				return false;
		} else if (!mgr.equals(other.mgr))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submitTime == null) {
			if (other.submitTime != null)
				return false;
		} else if (!submitTime.equals(other.submitTime))
			return false;
		if (vacationBegin == null) {
			if (other.vacationBegin != null)
				return false;
		} else if (!vacationBegin.equals(other.vacationBegin))
			return false;
		if (vacationEnd == null) {
			if (other.vacationEnd != null)
				return false;
		} else if (!vacationEnd.equals(other.vacationEnd))
			return false;
		if (vacationReason == null) {
			if (other.vacationReason != null)
				return false;
		} else if (!vacationReason.equals(other.vacationReason))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Event [eventid=" + eventid + ", eno=" + eno + ", vacationBegin=" + vacationBegin + ", vacationEnd="
				+ vacationEnd + ", submitTime=" + submitTime + ", vacationReason=" + vacationReason + ", mgr=" + mgr
				+ ", status=" + status + "]";
	}
	
	
	
}
