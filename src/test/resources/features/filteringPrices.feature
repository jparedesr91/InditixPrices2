Feature: Filtering Product's Prices

  Scenario Outline: Search product's prices by brand, product and application date.
    Given The folling resquests for filtering prices
      | productId   | brandId   | applicationDate   |
      | <productId> | <brandId> | <applicationDate> |
    Then I should be able to get price by product, brand and application date
      | expectedPrice   |
      | <expectedPrice> |
    And I should receive response with status code
      | statusCode   |
      | <statusCode> |

    Examples:
      | expectedPrice | productId | brandId | applicationDate     | statusCode |
      | 35.5          | 35455     | 1       | 2020-06-14T10:00:00 | 200        |
      | 25.45         | 35455     | 1       | 2020-06-14T16:00:00 | 200        |
      | 35.5          | 35455     | 1       | 2020-06-14T21:00:00 | 200        |
      | 30.5          | 35455     | 1       | 2020-06-15T10:00:00 | 200        |
      | 38.95         | 35455     | 1       | 2020-06-16T21:00:00 | 200        |

  Scenario Outline: Search product's prices by brand, product and application date, but that price doesn't exist.
    Given The folling resquests for filtering prices
      | productId   | brandId   | applicationDate   |
      | <productId> | <brandId> | <applicationDate> |
    Then I should get not found resource
    And I should receive response with status code
      | statusCode   |
      | <statusCode> |

    Examples:
      | productId | brandId | applicationDate     | statusCode |
      | 35455     | 2       | 2020-06-14T10:00:00 | 404        |

  Scenario Outline: Search product's prices by brand, product and application date, but that price doesn't exist.
    Given The folling resquests for filtering prices
      | productId   | brandId   | applicationDate   |
      | <productId> | <brandId> | <applicationDate> |
    Then I should get bad request
    And I should receive response with status code
      | statusCode   |
      | <statusCode> |

    Examples:
      | productId | brandId | applicationDate     | statusCode |
      | 35455     |         | 2020-06-14T10:00:00 | 400        |