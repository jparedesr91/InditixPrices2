package com.inditex.price.infrastructure.configuration;

import com.inditex.price.application.ports.driven.PriceRepositoryPort;
import com.inditex.price.application.ports.driving.ForFilteringPricesPort;
import com.inditex.price.application.usecases.FilterPriceUseCase;
import com.inditex.price.infrastructure.driven.database.adapters.PriceRepositoryAdapter;
import com.inditex.price.infrastructure.driven.database.repositories.PriceJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurator {

  @Bean
  ForFilteringPricesPort forFilteringPrices(PriceRepositoryPort forStoringData) {
    return new FilterPriceUseCase(forStoringData);
  }

  @Bean
  PriceRepositoryPort forStoringData(PriceJpaRepository priceRepository) {
    return new PriceRepositoryAdapter(priceRepository);
  }

}
