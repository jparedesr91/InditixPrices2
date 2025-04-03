package com.inditex.price.infrastructure.driving.rest.utils;

import java.text.MessageFormat;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageUtil {

  public static String getFormattedMsg(String msg, Object... params) {
    MessageFormat messageFormat = new MessageFormat(msg);
    String formattedMsg = messageFormat.format(params);
    log.debug("getFormattedMsg - message: {}, params: {}, formatted-message: {}", msg, params,
        formattedMsg);
    return formattedMsg;
  }

}
