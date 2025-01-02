package com.inditex.price.application.utils;

import java.text.MessageFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageUtil {

  public String getFormattedMsg(String msg, Object... params) {
    MessageFormat messageFormat = new MessageFormat(msg);
    String formattedMsg = messageFormat.format(params);
    log.debug("getFormattedMsg - message: {}, params: {}, formatted-message: {}", msg, params,
        formattedMsg);
    return formattedMsg;
  }

}
