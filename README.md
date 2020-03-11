Добрый день.

Перед запуском приложения необходимо выполнить:

1 Установить PostgreSQL

	$sudo apt update
	$sudo apt install postgresql postgresql-contrib

2. Создать базу данных {DATABASE_NAME}
3. Сменить настройки в src/main/resource/sapplication.properties, в { } необходимо указать Ваши данные
       
	spring.datasource.url=jdbc:postgresql://localhost/{DATABASE_NAME}
	spring.datasource.username={DATABASE_USER}
	spring.datasource.password={DATABASE_PWD}

Таблицы БД (ord (order), task) будут созданы автоматически по настройкам src/main/resource/db/migration/V1__Init_DB


Запуск приложения производится с использованием Maven:

В директории с pom.xml фалом открыть терминал и выполнить команду 

$mvn spring-boot:run


Параметры объектов.

Параметры заказа:
    id - номер заказа, устанавливается автоматически;
    date - дата доставки заказа;
    infomation - информация о заказе;
    active - статус активности заказа (true - в работе/false - 'не успеваю');
    done - статус выполненного заказа (true - завершен/false - не завершен).

Параметры задания:
    id - номер задания, устанавливается автоматически;
    date - дата доставки заказа;
    time - время создания заказа;
    finish - статус выполненного задания (true - задание отработано/false - задание в очереди); ,
    order_id - номер заказа;




По следующему адресу будут показаны сущ. заказы (изначально 5 шт.).

http://localhost:8080/orders 

К данной странице имеет доступ (условный) оператор который по средствам GET, POST, PUT, DELETE запросов создает, редактирует, удаляет заказы для курьера.


// GET, вывод  заказа по 'ID'
fetch('/orders/ID')
.then(response => response.json()
.then(console.log))


// POST, добавить новый заказ
fetch(
'/orders', 
{ 
 method: 'POST', 
 headers: { 'Content-Type': 'application/json' },
 body: JSON.stringify({ contactInfo: 'some_Information', arrivalDate: '2020-11-11'})
}).then(result => result.json().then(console.log))

*arrivalDate - без указания даты, будет указана текущая системная дата


// PUT, изменение заказа по 'ID'
fetch(
  '/orders/ID', 
{ 
 method: 'PUT', 
 headers: { 'Content-Type': 'application/json' },
 body: JSON.stringify({ contactInfo: 'new_some_Information'})
}
).then(result => result.json().then(console.log))


// DELETE, удаление заказа по 'ID'
fetch('/orders/ID', { 
method: 'DELETE'}
).then(result => console.log(result))

*DELETE заказа возможно до создания заказа для CallCenter


На следующей странице отображаются заказы курьера, отрабатывает GET метод, который создает задание для CallCenter

http://localhost:8080/task


// GET, создание задания по 'ID' заказа
fetch(
'/task/late?id=ID', 
{ 
 method: 'GET', 
 headers: { 'Content-Type': 'application/json' },
})


На следующей странице отображаются задания для курьера CallCenter

http://localhost:8080/center


// GET, фильтрует список заданий по дате от 'DATA1' до 'DATA2'
fetch(
'/center/filter?date1=DATA1&date2=DATA2', 
{ 
 method: 'GET', 
 headers: { 'Content-Type': 'application/json' },
})

// GET, вывод  задания по 'ID'
fetch('/orders/ID')
.then(response => response.json()
.then(console.log))
})

*команды выполнялись в консоле браузера GoogleChrome
