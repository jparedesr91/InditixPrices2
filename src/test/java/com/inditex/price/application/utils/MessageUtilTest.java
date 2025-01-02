package com.inditex.price.application.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MessageUtilTest {

  private final MessageUtil messageUtil = new MessageUtil();
  private static final String HTTP_400_MISSING_PARAM = "Required Param {0} of type {1} is missing";

  @Test
  void Given_Params_When_Template_Then_ReturnMessage() {
    final String EXPECTED_MESSAGE = "Required Param param1 of type value1 is missing";
    String message = messageUtil.getFormattedMsg(HTTP_400_MISSING_PARAM, "param1", "value1");
    assertEquals(EXPECTED_MESSAGE, message);
  }
}
