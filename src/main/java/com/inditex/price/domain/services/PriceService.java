package com.inditex.price.domain.services;

public interface PriceService {

  FindByProductResult findByProduct(FindByProductQuery findByProductQuery);

}