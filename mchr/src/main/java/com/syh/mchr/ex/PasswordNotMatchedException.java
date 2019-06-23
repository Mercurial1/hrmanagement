package com.syh.mchr.ex;
/**
 * 密码错误异常
 * @author SYH
 *
 */
public class PasswordNotMatchedException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public PasswordNotMatchedException() {
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchedException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchedException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchedException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
