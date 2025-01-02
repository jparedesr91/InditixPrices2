package com.inditex.price.functional.hooks;

import io.cucumber.java.Before;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
public class CucumberHooks {

  private static boolean isDatabaseInitialized = false;
  private final JdbcTemplate jdbcTemplate;
  private final ResourceLoader resourceLoader;

  public CucumberHooks(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) {
    this.jdbcTemplate = jdbcTemplate;
    this.resourceLoader = resourceLoader;
  }

  @Before
  public void beforeAllTests() {
    if (!isDatabaseInitialized) {
      try {
        Resource resource = resourceLoader.getResource("classpath:init.sql");
        Path sql = resource.getFile().toPath();

        jdbcTemplate.execute(new String(Files.readAllBytes(sql)));
        isDatabaseInitialized = true;
      } catch (Exception e) {
        log.error(e.getMessage());
      }
    }
  }

}
