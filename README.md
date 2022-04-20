# Практикум по веб-технологиям
Вариант 12: Система библиотечного учета для управления данными о читателях и книгах, о выдаче книг читателям
# Схема базы данных
![java_db](https://user-images.githubusercontent.com/67842019/164250681-046a9cc1-6d82-41e2-b2ee-4b07ec6b0849.png)
- unique_book_id - уникальный идентификатор экземпляра книги
- instance_id - номер экземпляра определенной книги, заданной book_id

# Описание страниц
![java_pages](https://user-images.githubusercontent.com/67842019/164251388-b3177c1b-cf5c-4a27-866a-b809bbec42c2.png)
### Страница со списком книг(Главная страница)
- Кнопка добавления новой книги перенаправляет на страницу редактирования(создания) книги
- Переход по нажатию на книгу перенаправляет на страницу информации о книге
- Панель для поиска книги по автору, жанру, названию книги
- Переход на страницы со списком читателей и списком заказов
- Список всех книг с указанием авторов, названия и жанра
### Страница со списком читателей
- Кнопка добавления нового читателя перенаправляет на страницу редактирования(создания) читателя
- Переход по нажатию на читателя перенаправляет на страницу информации о читателе
- Панель для поиска читателя по имени или его идентификатору(номеру читательского билета)
- Переход на страницы со списком книг и списком заказов
- Список всех читателей с указанием ФИ и номера идентификатора(номера читательского билета)
### Страница со списком заказов
- Кнопка добавления нового заказа перенаправляет на страницу редактирования(создания) заказа
- Переход по нажатию на заказ перенаправляет на страницу информации о заказе
- Панель для поиска заказа по номеру заказа, идентификатору читателя
- Переход на страницы со списком читателей и списком книг
- Список всех заказов с указанием номера заказа и номера читательского билета читателя
### Страницы редактирования(создания) {читателя, книги, заказа}
- Форма для ввода данных
  - Редактируемые данные:
    - для читателей: адрес, номер телефона, e-mail
    - для книг: количество экземпляров, количество свободных экземпляров
    - для заказов: дата возврата
- Кнопка подтверждения добавления
- Кнопка отмены
### Страницы информации о {читателе, книге, заказе}
- Информация о {читателе, книге, заказе}
- Кнопка для изменения данных перенаправляет на соответствующую страницу редактирования
- Кнопка удаления
- Указанная информация:
  - Для читателей: номер читательского билета, ФИ, адрес, e-mail, номер телефона
  - Для книг: название, авторы, жанр, издательство, ISBN, год издания, количество экземпляров и количество свободных экземпляров
  - Для заказов: номер заказа, номер читательского билета, дата заказа, дата возврата, уникальный идентификатор экземпляра книги

# Сценарии использования
- Добавление нового читателя(аналогично для книг и заказов)
  - Перейти на страницу всех читателей
  - Нажать на кнопку добавления читателя
  - Ввести информацию о читателе
  - Нажать кнопку "подтвердить"
- Получение информации о читателе(аналогично для книг и заказов)
  - Перейти на страницу всех читателей
  - В панеле для поиска ввести номер читательского билета
- Удаление читателя(аналогично для книг и заказов)
  - Перейти на страницу об информации о читателе(сценарий выше)
  - Нажать кнопку "удалить"
- Получение информации о заказах пользователя
  - Перейти на страницу всех заказов
  - В панеле поиска ввести номер читательского билета
