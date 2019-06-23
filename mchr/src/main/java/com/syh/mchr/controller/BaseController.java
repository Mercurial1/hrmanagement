package com.syh.mchr.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.syh.mchr.ex.DuplicateException;
import com.syh.mchr.ex.EventEmptyException;
import com.syh.mchr.ex.FileEmptyException;
import com.syh.mchr.ex.FileSizeException;
import com.syh.mchr.ex.FileTypeException;
import com.syh.mchr.ex.FileUploadException;
import com.syh.mchr.ex.FileUploadIOException;
import com.syh.mchr.ex.FileUploadStateException;
import com.syh.mchr.ex.InsertException;
import com.syh.mchr.ex.NightSignException;
import com.syh.mchr.ex.PasswordNotMatchedException;
import com.syh.mchr.ex.PermissionException;
import com.syh.mchr.ex.ServiceException;
import com.syh.mchr.ex.UpdateException;
import com.syh.mchr.ex.UserNotFoundException;
import com.syh.mchr.util.JsonResult;

public class BaseController {
	public static final Integer SUCCESS=2000;
	
	@ExceptionHandler({ServiceException.class,FileUploadException.class})
	public JsonResult<Void> exceptionHandle(Exception e){
		JsonResult<Void> jr = new JsonResult<Void>();
		jr.setMessage(e.getMessage());
		if(e instanceof DuplicateException) {
			jr.setState(4000);
		} else if(e instanceof EventEmptyException) {
			jr.setState(4001);
		} else if(e instanceof FileEmptyException) {
			jr.setState(4002);
		} else if(e instanceof FileSizeException) {
			jr.setState(4003);
		} else if(e instanceof FileTypeException) {
			jr.setState(4004);
		} else if(e instanceof FileUploadIOException) {
			jr.setState(4005);
		} else if(e instanceof FileUploadStateException) {
			jr.setState(4006);
		} else if(e instanceof NightSignException) {
			jr.setState(4007);
		} else if(e instanceof PasswordNotMatchedException) {
			jr.setState(4008);
		} else if(e instanceof PermissionException) {
			jr.setState(4009);
		} else if(e instanceof UpdateException) {
			jr.setState(5001);
		} else if(e instanceof UserNotFoundException) {
			jr.setState(4010);
		} else if(e instanceof InsertException) {
			jr.setState(5000);
		}
		return jr;
	}
	
}
