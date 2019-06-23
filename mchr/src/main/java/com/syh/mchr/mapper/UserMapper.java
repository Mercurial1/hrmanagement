package com.syh.mchr.mapper;


import com.syh.mchr.entity.User;

public interface UserMapper {
	User findByUsername(String username);
	User findByUid(Integer uid);
}
