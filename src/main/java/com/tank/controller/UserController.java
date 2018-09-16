package com.tank.controller;


import com.tank.common.Constants;
import com.tank.domain.User;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
@RequestMapping(Constants.URL_PREFIX + "user")
public class UserController {

  @GetMapping("/list")
  public ResponseEntity list() {
    val users = new LinkedList<User>();
    users.add(new User().setGender("M").setGender("lisi"));
    users.add(new User().setGender("F").setGender("xiaoh"));
    return ResponseEntity.ok(users);
  }
  
}
