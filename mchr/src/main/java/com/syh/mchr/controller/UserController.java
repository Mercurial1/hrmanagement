package com.syh.mchr.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.syh.mchr.entity.Emp;
import com.syh.mchr.entity.User;
import com.syh.mchr.entity.UserVO;
import com.syh.mchr.ex.FileEmptyException;
import com.syh.mchr.ex.FileSizeException;
import com.syh.mchr.ex.FileTypeException;
import com.syh.mchr.ex.FileUploadIOException;
import com.syh.mchr.ex.FileUploadStateException;
import com.syh.mchr.service.UserService;
import com.syh.mchr.util.JsonResult;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping("reg")
	public JsonResult<Void> reg(HttpSession session,User user,Emp emp,String hiredate1,String birthday1){
		String eno = session.getAttribute("eno").toString();
		try {
			Date hiredate =new SimpleDateFormat("yyyy-MM-dd").parse(hiredate1);
			Date birthday =new SimpleDateFormat("yyyy-MM-dd").parse(birthday1);
			emp.setHiredate(hiredate);
			emp.setBirthday(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		userService.addpeople(user, emp, eno);
		return new JsonResult<Void>(SUCCESS);
	}
	
	@RequestMapping("isadmin")
	public JsonResult<Void> isAdmin(HttpSession session){
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		userService.isAdmin(uid);
		return new JsonResult<>(SUCCESS);
	}
	
	@RequestMapping("findinfo")
	public JsonResult<Emp> findEmp(HttpSession session,@RequestParam("eno")String eno){
		if("".equals(eno)) {
			eno = session.getAttribute("eno").toString();
		}
		Emp data = userService.getInfo(eno);
		return new JsonResult<Emp>(SUCCESS,data);
	}
	
	@RequestMapping("changeinfo")
	public JsonResult<Void> changeInfo(HttpSession session,@RequestParam("eno")String eno,Emp emp,String hiredate1,String birthday1){
		if("".equals(eno)) {
			eno = session.getAttribute("eno").toString();
		}
		try {
			Date hiredate =new SimpleDateFormat("yyyy-MM-dd").parse(hiredate1);
			Date birthday =new SimpleDateFormat("yyyy-MM-dd").parse(birthday1);
			emp.setHiredate(hiredate);
			emp.setBirthday(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		userService.changeInfo(eno,emp,session.getAttribute("eno").toString());
		
		return new JsonResult<Void>(SUCCESS);
	}
	
	@RequestMapping("login")
	public JsonResult<UserVO> login(User user,HttpSession session){
		UserVO vo = userService.login(user);
		session.setAttribute("uid", vo.getUid());
		session.setAttribute("eno", vo.getEno());
		return new JsonResult<UserVO>(SUCCESS,vo);
	}
	
	public static final int AVATAR_MAX_SIZE = 1 * 1024 * 1024;

	public static final List<String> AVATAR_CONTENT_TYPES = new ArrayList<>();

	public static final String AVATAR_DIR = "upload";

	static {
	    AVATAR_CONTENT_TYPES.add("image/jpeg");
	    AVATAR_CONTENT_TYPES.add("image/png");
	}

	@PostMapping("change_photo")
	public JsonResult<String> changeAvatar(
	    HttpServletRequest request, 
	    @RequestParam("file") MultipartFile file) {
	    // 检查文件是否为空
	    if (file.isEmpty()) {
	        throw new FileEmptyException("文件为空");
	    }

	    // 检查文件大小
	    if (file.getSize() > AVATAR_MAX_SIZE) {
	        throw new FileSizeException("文件过大");
	    }

	    // 检查文件类型
	    if (!AVATAR_CONTENT_TYPES.contains(file.getContentType())) {
	        throw new FileTypeException("不支持此文件类型");
	    }

	    // 确定文件夹
	    String dirPath = request.getServletContext().getRealPath(AVATAR_DIR);
	    File dir = new File(dirPath);
	    if (!dir.exists()) {
	        dir.mkdirs();
	    }

	    // 确定文件名
	    String originalFilename = file.getOriginalFilename();
	    String suffix = "";
	    int beginIndex = originalFilename.lastIndexOf(".");
	    if (beginIndex != -1) {
	        suffix = originalFilename.substring(beginIndex);
	    }
	    String filename = UUID.randomUUID().toString() + suffix;

	    // 执行保存
	    File dest = new File(dir, filename);
	    try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new FileUploadStateException("文件状态异常");
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileUploadIOException("文件保存错误");
		}

	    // 更新数据表
	    String photo = "/" + AVATAR_DIR + "/" + filename;
	    HttpSession session = request.getSession();
	    Integer uid = (Integer)session.getAttribute("uid");
	    String eno = (String)session.getAttribute("eno");
	    userService.changePhoto(uid, eno, photo);

	    // 返回
	    JsonResult<String> jr = new JsonResult<String>();
	    jr.setState(SUCCESS);
	    jr.setData(photo);
	    return jr;
	}
	
	
}
