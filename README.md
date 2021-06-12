# Spring Boot Echange Rate Service

# What application does?
It is echange rate servis that every day exclude weekends download and store currency rate, from ECB(European Central Bank) to make sure that everything is up-do-date.
Aftherwards, when the question appear he convert them from any currency to any currency, the client choose.

# How to run appliation

Clone the repository to your development environment exmaple Intellij IDE and run the application. Make sure, to have installed Lombok pluggin

# How to use app

Example request: http://localhost:8080/?time=2021-06-08&fromCurrency=czk&toCurrency=gbp

Example respone: Dnia 2021-06-08 kurs z CZK do GBP 0,03



