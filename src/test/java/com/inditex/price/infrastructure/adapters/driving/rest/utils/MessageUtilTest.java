package com.inditex.price.infrastructure.adapters.driving.rest.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.inditex.price.infrastructure.driving.rest.utils.MessageUtil;
import org.junit.jupiter.api.Test;

class MessageUtilTest {

  private static final String HTTP_400_MISSING_PARAM = "Required Param {0} of type {1} is missing";

  @Test
  void Given_Params_When_Template_Then_ReturnMessage() {
    final String EXPECTED_MESSAGE = "Required Param param1 of type value1 is missing";
    String message = MessageUtil.getFormattedMsg(HTTP_400_MISSING_PARAM, "param1", "value1");
    assertEquals(EXPECTED_MESSAGE, message);
  }
}
