package com.syh.mchr.ex;
/**
 * 用户名冲突异常
 * @author SYH
 *
 */
public class DuplicateException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public DuplicateException() {
		// TODO Auto-generated constructor stub
	}

	public DuplicateException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DuplicateException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DuplicateException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public DuplicateException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
