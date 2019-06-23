package com.syh.mchr.entity;

public class Job extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private Integer jobno;
	private Integer deptno;
	private String jobname;
	
	public Integer getJobno() {
		return jobno;
	}
	public void setJobno(Integer jobno) {
		this.jobno = jobno;
	}
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deptno == null) ? 0 : deptno.hashCode());
		result = prime * result + ((jobname == null) ? 0 : jobname.hashCode());
		result = prime * result + ((jobno == null) ? 0 : jobno.hashCode());
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
		Job other = (Job) obj;
		if (deptno == null) {
			if (other.deptno != null)
				return false;
		} else if (!deptno.equals(other.deptno))
			return false;
		if (jobname == null) {
			if (other.jobname != null)
				return false;
		} else if (!jobname.equals(other.jobname))
			return false;
		if (jobno == null) {
			if (other.jobno != null)
				return false;
		} else if (!jobno.equals(other.jobno))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Job [jobno=" + jobno + ", deptno=" + deptno + ", jobname=" + jobname + "]";
	}
	
	
	
}
