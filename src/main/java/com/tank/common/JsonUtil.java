package com.tank.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JsonUtil<T> {

  public String convert2JsonStr(@NonNull final T obj) {
    val mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      log.error(String.format("deSerialization error: %s", e.getMessage()));
      return null;
    }
  }


}
