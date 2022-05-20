package ru.webprak.Controllers;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WebTest{
    private final String rootTitle = "Главная страница";
    private final String customersTitle = "Люди";
    private final String booksTitle = "Книги";
    private final String ordersTitle = "Выдачи книг";
    private final String addCustomerTitle = "Редактировать читателя";
    private final String addBookTitle = "Редактировать книгу";
    private final String addOrderTitle = "Редактировать выдачу";
    private final String customerTitle = "Информация о читателе";
    private final String bookTitle = "Информация о книге";
    private final String orderTitle = "Информация о выдаче";

    @Test
    void MainPage() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        assertEquals(rootTitle, driver.getTitle());
        driver.quit();
    }

    @Test
    void filterCustomer(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/customers");
        assertEquals(customersTitle, driver.getTitle());
        driver.findElement(By.id("lastName")).sendKeys("Pavlov");
        driver.findElement(By.id("firstName")).sendKeys("Ivan");
        driver.findElement(By.id("id")).sendKeys("859");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(customersTitle, driver.getTitle());

        List<WebElement> cells = driver.findElements(By.tagName("tr"));

        assertEquals(2, cells.size());
        driver.quit();
    }

    @Test
    void filterBook(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/books");
        assertEquals(booksTitle, driver.getTitle());
        driver.findElement(By.id("genre")).sendKeys("Tale");
        driver.findElement(By.id("author")).sendKeys("Bulgakov");
        driver.findElement(By.id("title")).sendKeys("Master and Margaret");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(booksTitle, driver.getTitle());

        List<WebElement> cells = driver.findElements(By.tagName("tr"));

        assertEquals(2, cells.size());
        driver.quit();
    }

    @Test
    void filterOrder(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/orders");
        assertEquals(ordersTitle, driver.getTitle());
        driver.findElement(By.id("customerFilter")).sendKeys("859");
        driver.findElement(By.id("orderFilter")).sendKeys("367");
        driver.findElement(By.id("bookFilter")).sendKeys("2267");
        driver.findElement(By.id("instanceFilter")).sendKeys("1");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(ordersTitle, driver.getTitle());

        List<WebElement> cells = driver.findElements(By.tagName("tr"));

        assertEquals(2, cells.size());
        driver.quit();
    }

    @Test
    void toAddCustomer(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/customers");
        assertEquals(customersTitle, driver.getTitle());
        driver.findElement(By.id("addPersonButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(addCustomerTitle, driver.getTitle());
        driver.quit();
    }

    @Test
    void toAddBook(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/books");
        assertEquals(booksTitle, driver.getTitle());
        driver.findElement(By.id("addBookButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(addBookTitle, driver.getTitle());
        driver.quit();
    }

    @Test
    void toAddOrder(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/orders");
        assertEquals(ordersTitle, driver.getTitle());
        driver.findElement(By.id("addOrderButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(addOrderTitle, driver.getTitle());
        driver.quit();
    }

    @Test
    void toCustomerInfo(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/customers");
        assertEquals(customersTitle, driver.getTitle());
        List<WebElement> cells = driver.findElements(By.tagName("td"));
        cells.get(0).findElement(By.tagName("a")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(customerTitle, driver.getTitle());
        driver.quit();
    }

    @Test
    void toBookInfo(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/books");
        assertEquals(booksTitle, driver.getTitle());
        List<WebElement> cells = driver.findElements(By.tagName("td"));
        cells.get(0).findElement(By.tagName("a")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(bookTitle, driver.getTitle());
        driver.quit();
    }

    @Test
    void toOrderInfo(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/orders");
        assertEquals(ordersTitle, driver.getTitle());
        List<WebElement> cells = driver.findElements(By.tagName("td"));
        cells.get(0).findElement(By.tagName("a")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(orderTitle, driver.getTitle());
        driver.quit();
    }

    @Test
    void addUpdateDeleteCustomer(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/add_customer");
        assertEquals(addCustomerTitle, driver.getTitle());
        driver.findElement(By.id("customerName")).sendKeys("testFirstName");
        driver.findElement(By.id("customerLastname")).sendKeys("testLastName");
        driver.findElement(By.id("customerAddress")).sendKeys("testAddress");
        driver.findElement(By.id("customerEmail")).sendKeys("testMail");
        driver.findElement(By.id("customerPhone")).sendKeys("7777777777");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(customerTitle, driver.getTitle());
        List<WebElement> cells = driver.findElements(By.tagName("p"));
        List<WebElement> names = driver.findElements(By.tagName("h4"));
        assertEquals(names.get(0).getText(), "testLastName testFirstName");
        assertEquals(cells.get(1).getText(), "Адрес: testAddress");
        assertEquals(cells.get(3).getText(), "Почта: testMail");
        assertEquals(cells.get(2).getText(), "Телефон: 7777777777");

        driver.findElement(By.id("editButton")).click();
        driver.findElement(By.id("customerAddress")).sendKeys("testAddress2");
        driver.findElement(By.id("customerEmail")).sendKeys("testMail2");
        driver.findElement(By.id("customerPhone")).sendKeys("88888888888");
        driver.findElement(By.id("submitButton")).click();
        assertEquals(customerTitle, driver.getTitle());
        List<WebElement> cells2 = driver.findElements(By.tagName("p"));
        assertEquals(cells2.get(1).getText(), "Адрес: testAddress2");
        assertEquals(cells2.get(3).getText(), "Почта: testMail2");
        assertEquals(cells2.get(2).getText(), "Телефон: 88888888888");
        driver.findElement(By.id("deleteButton")).click();
        assertEquals(customersTitle, driver.getTitle());

        driver.quit();
    }

    @Test
    void addUpdateDeleteBook(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/add_book");
        assertEquals(addBookTitle, driver.getTitle());
        driver.findElement(By.id("title")).sendKeys("testTitle");
        driver.findElement(By.id("authors")).sendKeys("testAuthors");
        driver.findElement(By.id("publ_year")).sendKeys("2000");
        driver.findElement(By.id("isbn")).sendKeys("testISBN");
        driver.findElement(By.id("publ_house")).sendKeys("testPHouse");
        driver.findElement(By.id("genre")).sendKeys("testGenre");
        driver.findElement(By.id("amount")).sendKeys("5");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(bookTitle, driver.getTitle());
        List<WebElement> cells = driver.findElements(By.tagName("p"));
        List<WebElement> names = driver.findElements(By.tagName("h4"));
        assertEquals(names.get(0).getText(), "testTitle");
        assertEquals(cells.get(0).getText(), "Авторы: testAuthors");
        assertEquals(cells.get(1).getText(), "Год издания: 2000");
        assertEquals(cells.get(2).getText(), "ISBN: testISBN");
        assertEquals(cells.get(3).getText(), "Издательство: testPHouse");
        assertEquals(cells.get(4).getText(), "Жанр: testGenre");
        assertEquals(cells.get(5).getText(), "Количество экземпляров: 5");
        assertEquals(cells.get(6).getText(), "Количество свободных экземпляров: 5");

        driver.findElement(By.id("editButton")).click();

        driver.findElement(By.id("publ_year")).sendKeys("2001");
        driver.findElement(By.id("isbn")).sendKeys("testISBN2");
        driver.findElement(By.id("publ_house")).sendKeys("testPHouse2");
        driver.findElement(By.id("genre")).sendKeys("testGenre2");
        driver.findElement(By.id("submitButton")).click();
        assertEquals(bookTitle, driver.getTitle());
        List<WebElement> cells2 = driver.findElements(By.tagName("p"));
        assertEquals(cells2.get(1).getText(), "Год издания: 2001");
        assertEquals(cells2.get(2).getText(), "ISBN: testISBN2");
        assertEquals(cells2.get(3).getText(), "Издательство: testPHouse2");
        assertEquals(cells2.get(4).getText(), "Жанр: testGenre2");
        driver.findElement(By.id("deleteButton")).click();
        assertEquals(booksTitle, driver.getTitle());

        driver.quit();
    }

    @Test
    void addBookFindInstanceCreateOrderReturnOrder(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/add_book");
        assertEquals(addBookTitle, driver.getTitle());
        driver.findElement(By.id("title")).sendKeys("testTitle");
        driver.findElement(By.id("authors")).sendKeys("testAuthors");
        driver.findElement(By.id("publ_year")).sendKeys("2000");
        driver.findElement(By.id("isbn")).sendKeys("testISBN");
        driver.findElement(By.id("publ_house")).sendKeys("testPHouse");
        driver.findElement(By.id("genre")).sendKeys("testGenre");
        driver.findElement(By.id("amount")).sendKeys("5");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(bookTitle, driver.getTitle());
        driver.findElement(By.id("instanceID")).sendKeys("1");
        driver.findElement(By.id("checkInstButton")).click();
        assertEquals(bookTitle, driver.getTitle());
        driver.findElement(By.id("addOrderButton")).click();
        driver.findElement(By.id("customer_id")).sendKeys("859");
        driver.findElement(By.id("order_date")).sendKeys("2022-05-05");
        driver.findElement(By.id("return_date")).sendKeys("2022-06-06");
        driver.findElement(By.id("submitButton")).click();
        assertEquals(orderTitle, driver.getTitle());
        driver.findElement(By.id("returnButton")).click();
        assertEquals(orderTitle, driver.getTitle());
        driver.findElement(By.id("deleteButton")).click();
        assertEquals(ordersTitle, driver.getTitle());
        driver.quit();
    }

    @Test
    void createOrderReturn(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/orders");
        assertEquals(ordersTitle, driver.getTitle());
        driver.findElement(By.id("addOrderButton")).click();
        driver.findElement(By.id("customer_id")).sendKeys("859");
        driver.findElement(By.id("order_date")).sendKeys("2022-05-05");
        driver.findElement(By.id("return_date")).sendKeys("2022-06-06");
        driver.findElement(By.id("book_id")).sendKeys("2264");
        driver.findElement(By.id("instance_id")).sendKeys("1");
        driver.findElement(By.id("submitButton")).click();
        assertEquals(orderTitle, driver.getTitle());

        List<WebElement> cells = driver.findElements(By.tagName("p"));
        assertEquals(cells.get(0).getText(), "Номер читательского билета: 859");
        assertEquals(cells.get(1).getText(), "Дата выдачи: 2022-05-05");
        assertEquals(cells.get(2).getText(), "Дата возврата: 2022-06-06");
        assertEquals(cells.get(3).getText(), "Книга: Tihiy don");
        assertEquals(cells.get(4).getText(), "Номер экземпляра: 1");
        driver.findElement(By.id("returnButton")).click();
        assertEquals(orderTitle, driver.getTitle());
        driver.findElement(By.id("deleteButton")).click();
        assertEquals(ordersTitle, driver.getTitle());
        driver.quit();
    }
}
