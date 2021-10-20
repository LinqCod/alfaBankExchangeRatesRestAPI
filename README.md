AlfaBank ExchangeRatesAPI
---
Тестовое задание на стажировку в Альфа Банк

Описание:
---
```
Создать сервис, который обращается к сервису курсов валют, и отображает gif:

если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
если ниже - отсюда https://giphy.com/search/broke

Ссылки
REST API курсов валют - https://docs.openexchangerates.org/
REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide

Must Have
Сервис на Spring Boot 2 + Java / Kotlin
Запросы приходят на HTTP endpoint (должен быть написан в соответствии с rest conventions), туда передается код валюты по отношению с которой сравнивается USD
Для взаимодействия с внешними сервисами используется Feign
Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки
На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или WireMock)
Для сборки должен использоваться Gradle
Результатом выполнения должен быть репо на GitHub с инструкцией по запуску

Nice to Have
Сборка и запуск Docker контейнера с этим сервисом
```  
Endpoints:
---  
Получить список кодов для валют:  
```
GET /exchangerates/api/getcurrencycodes
```  
Получить гифку  
(пример ответа: https://api.giphy.com/v1/gifs/random?api_key=cxX3zcBM5VePPL8JEwVPhUGg4IN0aowH&tag=broke):
```
GET /exchangerates/api/gif/{code}
```  
Простой front-end реализован с использованием html+js+jquery, при старте приложения доступен по localhost:8080/  
 
Запуск .jar:
---
```
java -jar alfaBankExchangeRatesRestAPI-0.0.1-SNAPSHOT.jar
```
