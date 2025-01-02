# Inditex Prices
Prices API

## Software version used
- Java 21
- Maven 3.9.8

## How to run tests and start
1. Step on the source folder.
2. Execute the following command `mvn clean install`.

To run it, just execute the following command `java -jar target/inditex-prices-0.0.1.jar`.

## Dockerize API
1. Step on the source folder.
2. Execute the following command `mvn clean install`.
3. Execute `mvn docker:build`
4. Execute `mvn docker:run`


## Use example

`curl --location 'http://localhost:8080/inditex/prices/filter'
--header 'Content-Type: application/json'
--data '{
    "productId": 35455,
    "brandId": 1,
    "applicationDate": "2020-06-14T16:00:00"
}'`
