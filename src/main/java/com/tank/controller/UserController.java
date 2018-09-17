package com.tank.controller;


import com.tank.common.Constants;
import com.tank.common.JsonUtil;
import com.tank.domain.User;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.URL_PREFIX + "user")
@CrossOrigin
@Slf4j
public class UserController {

  @GetMapping("/list")
  public ResponseEntity list() {
    val users = this.mongoTemplate.findAll(User.class);
    return ResponseEntity.ok(users);
  }

  @PostMapping("/create")
  public ResponseEntity create(@RequestBody @NonNull final User user) {
    System.out.println(jsonUtil.convert2JsonStr(user));
    this.mongoTemplate.save(user);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private JsonUtil<User> jsonUtil;
}
