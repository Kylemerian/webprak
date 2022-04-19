delete from books;
delete from customers;
delete from orders;

insert into customers(lastname, firstname, address, phone_number, mail) values
                                                                            ('name1', 'fname1', 'Москва', '790852258743', 'mail@mail.ru'),
                                                                            ('name2', 'fname1', 'Москва', '790852258743', 'mail@mail.ru'),
                                                                            ('name3', 'fname1', 'Москва', '790852258743', 'mail@mail.ru'),
                                                                            ('name4', 'fname1', 'Москва', '790852258743', 'mail@mail.ru'),
                                                                            ('name5', 'fname1', 'Москва', '790852258743', 'mail@mail.ru');
insert into books (amount, title, author, genre, p_house, year, free_amount, isbn) values
                                                                                       (40,  'Война и мир 1', 'Л.Н. Толстой', 'Роман', 'Эксмо 0', 2011, 30, '12323'),
                                                                                       (40,  'Война и мир 2', 'А.С. Пушкин', 'Повесть', 'Эксмо 1', 2012, 30, '12323'),
                                                                                       (40,  'Война и мир 3', 'А.С. Пушкин', 'Сказка', 'Эксмо 0', 2013, 30, '12323'),
                                                                                       (40,  'Война и мир 4', 'Л.Н. Толстой', 'Рассказ', 'Эксмо 0', 2014, 30, '12323'),
                                                                                       (40,  'Война и мир 5', 'Л.Н. Толстой', 'Роман', 'Эксмо 4', 2015, 30, '12323');
insert into orders (customer_id, order_time, return_time, book_id) values
                                                                       (0, '2020-02-17', '2020-02-16', 0),
                                                                       (0, '2029-02-17', '2020-02-16', 0);