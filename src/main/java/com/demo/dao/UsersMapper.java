package com.demo.dao;

import org.springframework.stereotype.Component;

import com.demo.model.Users;

 
public interface UsersMapper {
 public Users selectById(Integer id);
}
