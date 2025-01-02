package com.inditex.price.infrastructure.configuration;

import com.inditex.price.domain.repository.PriceRepository;
import com.inditex.price.domain.services.PriceService;
import com.inditex.price.domain.services.impl.PriceServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurator {

  @Bean
  PriceService priceService(PriceRepository priceRepository) {
    return new PriceServiceImpl(priceRepository);
  }

}
