package com.inditex.price.application.ports.driving;

public interface ForFilteringPricesPort {
    FindByProductResult findByProduct(FindByProductQuery findByProductQuery);
}
