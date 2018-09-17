package com.tank.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fuchun
 */
@Data
@Accessors(chain = true)
@Document(collection = "tab_users")
public class User {

  @Id
  private String id;

  private String name;

  private String gender;
  
}
