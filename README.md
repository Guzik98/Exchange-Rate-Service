# Spring Boot Echange Rate Service

# What application does?
It is an exchange rate service that every day exclude 
weekends download and store in database currency rate from ECB (European Central 
Bank). Program is able to convert every currency. Application is written in Java, using 
Spring Boot framework, with H2 as database

# How to run appliation

Clone the repository to your development environment exmaple Intellij IDE and run the application. Make sure, to have installed Lombok pluggin

# How to use app

Example request: http://localhost:8080/?time=2021-06-08&fromCurrency=czk&toCurrency=gbp

Example respone: Dnia 2021-06-08 kurs z CZK do GBP 0,03



