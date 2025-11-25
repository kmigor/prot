### Проект автотестов (Selenide, JUnit5, Allure, Owner)

**Важно**: тесты открывают локальный файл: **resourses/localResourse/qa-test.html**

**Важно**: перед запуском следует добавить файл **src/test/resources/credentials.properties** 
с параметрами **login**=Логин для входа **password**=Пароль для входа. Файл **credentials.properties** не отслеживается гитом.

**Запуск**: *gradle clean build allureServe* .

**Отчет**: в аллюр отчет прикладываются **скриншоты** браузера и **структура веб страницы** перед закрытием вебдрайвера.