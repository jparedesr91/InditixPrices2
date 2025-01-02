INSERT INTO BRAND (ID, NAME)
VALUES (1, 'ZARA');

INSERT INTO PRODUCT (ID, NAME)
VALUES (35455, 'SHOES');

INSERT INTO PRICES (ID, PRODUCT_ID, BRAND_ID, START_DATE, END_DATE, PRIORITY, PRICE, CURR)
VALUES (1, 35455, 1, '2020-06-14 00.00.00', '2020-12-31 23.59.59', 0, 35.50, 'EUR'),
(2, 35455, 1, '2020-06-14 15.00.00', '2020-06-14 18.30.00', 1, 25.45, 'EUR'),
(3, 35455, 1, '2020-06-15 00.00.00', '2020-06-15 11.00.00', 1, 30.50, 'EUR'),
(4, 35455, 1, '2020-06-15 16.00.00', '2020-12-31 23.59.59', 1, 38.95, 'EUR');