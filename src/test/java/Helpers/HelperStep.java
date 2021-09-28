package Helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class HelperStep {

    public static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(HelperStep.class);
    private By imgLogoFnac = By.xpath(".//a[@href='https://www.fnac.pt/']/span");
    private By inputSearch = By.xpath(".//input[@id='Fnac_Search']");
    private By btnSearch = By.xpath(".//div[contains(@class, 'Header__search')]/button[contains(@class, 'Header__search-submit')]");
    private By btnActionNext = By.xpath(".//a[contains(@class, 'actionNext')]");
    private By btnAcceptCookies = By.xpath(".//button[@id='onetrust-accept-btn-handler']");
    private By seeBookCharact = By.xpath(".//div[@class='f-productVariousData-actions']/a");
    private By authorNameCharact = By.xpath(".//div[@class='productStrates__column']//dt[text()='Autor']/..//a");
    private By isbnCharact = By.xpath(".//div[@class='productStrates__column']//dt[text()='ISBN']/..//p");
    private By pagesNumberCharact = By.xpath(".//div[@class='productStrates__column']//dt[text()='Nº Páginas']/..//p");
    private By bookDimentionsCharact = By.xpath(".//div[@class='productStrates__column']//dt[text()='Dimensões']/..//p");

    public HelperStep() {
        driver = openBrowser("Firefox");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static WebDriver openBrowser(String browser) {
        WebDriver driver = null;

        try {
            switch (browser) {
                case ("Chrome"):
                    System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
                    driver = new ChromeDriver();
                    break;
                case ("Firefox"):
                    System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("Expected value: Firefox or Chrome");
            }
            return driver;
        } catch (Exception E) {
            E.printStackTrace();
        }
        return driver;
    }

    public void closeBrowser() {
        driver.close();
    }

    public void NavigateToPage() {
        driver.get("https://www.fnac.pt/");
        driver.findElement(imgLogoFnac).isDisplayed();
    }

    public void AcceptCookies() {
        driver.findElement(btnAcceptCookies).click();
    }

    public void ScrollToElement(WebElement xpath, WebElement xpathToFind) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = xpath;
        js.executeScript("arguments[0].scrollIntoView();", Element);
        while (!(xpathToFind).isDisplayed()) {
            js.executeScript("arguments[0].scrollIntoView();", Element);
        }
    }

    public void CloseNotifyContent() {
        try {
            Thread.sleep(5000);
            if (driver.findElement(By.xpath(".//div[@class='inside_notify_content']")).isDisplayed()) {
                try {
                    driver.findElement(By.xpath(".//div[@class='inside_closeButton fonticon icon-hclose']")).click();
                    Thread.sleep(1000);
                    System.out.println("Notify content closed!");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Search(String stringSearch) {
        driver.findElement(inputSearch).sendKeys(stringSearch);
        driver.findElement(btnSearch).click();
    }

    public void FindBookTitle(String bookTitle) {
        int i = 1;
        int tam = driver.findElements(By.xpath(".//div[@class= 'clearfix Article-item js-Search-hashLinkId']")).size();

        System.out.println("TAMANHO: " + tam);
        while (i < tam + 1) {
            String title = driver.findElement(By.xpath("(.//div[contains(@data-automation-id, 'product-block')])[" + i + "]//a[contains(@class, 'Article-title')]")).getText();

            if (bookTitle.equals(title)) {
                System.out.println("Book found with title:" + title + " . In url: " + driver.getCurrentUrl());
                break;
            } else {
                if (i == tam) {
                    ScrollToElement(driver.findElement(By.xpath("(.//div[contains(@data-automation-id, 'product-block')])[" + i + "]//a[contains(@class, 'Article-title')]")), driver.findElement(btnActionNext));
                    try {
                        Thread.sleep(5000);
                        driver.findElement(btnActionNext).click();
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    try {
                        Thread.sleep(5000);
                        tam = driver.findElements(By.xpath(".//div[@class= 'clearfix Article-item js-Search-hashLinkId']")).size();
                        i = 1;
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    i++;
                }
            }
        }
    }

    public void FindWordDescription(String word) {
        String description = driver.findElement(By.xpath(".//div[contains(@data-automation-id, 'product-block')]//a[contains(text(), 'Fascism and Democracy') and contains(@class, 'Article-title')]/../..//p[@class='summary']/span")).getText();
        if (description.contains(word)) {
            System.out.println("I found the keyword");
        } else {
            throw new java.lang.Error("A word '" + word + "' not found in a description!");
        }
    }

    public void ClickGoTheBookPage() {
        driver.findElement(By.xpath("(.//div[contains(@data-automation-id, 'product-block')]//a[text()='1984' and contains(@class, 'Article-title')])[1]")).click();
    }

    public void ClickSeeBookCharacteristics() {
        driver.findElement(seeBookCharact).click();
    }

    public void ValidateAuthor(String name) {
        if (driver.findElement(authorNameCharact).getText().equals(name)) {
            System.out.println("Valid author name.");
        } else {
            throw new java.lang.Error("The author '" + name + "' not matches.");
        }
    }

    public void ValidateISBN(String number) {
        if (driver.findElement(isbnCharact).getText().equals(number)) {
            System.out.println("Valid author ISBN.");
        } else {
            throw new java.lang.Error("The ISBN '" + number + "' not matches.");
        }
    }

    public void ValidateNumberPages(String number) {
        if (driver.findElement(pagesNumberCharact).getText().equals(number)) {
            System.out.println("Valid number of pages.");
        } else {
            throw new java.lang.Error("The number of pages '" + number + "' not matches.");
        }
    }

    public void ValidateDimensions(String dimensions) {
        if (driver.findElement(bookDimentionsCharact).getText().equals(dimensions)) {
            System.out.println("Valid book dimensions.");
        } else {
            throw new java.lang.Error("The book dimensions '" + dimensions + "' not matches.");
        }
    }

    public void GoOpinionPage() {
        ScrollToElement(driver.findElement(By.xpath(".//section[@id='CustomersAlsoBought']")), driver.findElement(By.xpath(".//span[text()='Ver todas as opiniões']")));
        driver.findElement(By.xpath(".//span[text()='Ver todas as opiniões']")).click();
    }

    public void OldestComment(String date) {
        WebElement buttonEndComment = driver.findElement(By.xpath("(.//button[@class='paginate-item-number js-paginate-item'])[3]"));
        driver.findElement(By.xpath(".//a[text()='As mais recentes']")).click();
        try {
            buttonEndComment.click();
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }
        int tam = driver.findElements(By.xpath(".//div[@class='f-review-customerReview']")).size();
        String commentDate = driver.findElement(By.xpath("(.//div[@class='f-review-customerReview'])[" + tam + "]//div[@class='f-reviews-date']/p")).getText();
        String replacedDate = commentDate.replace("Publicada a ", "");
        String[] splitDate = replacedDate.split("\\s+"); //split by space
        System.out.println(Arrays.toString(splitDate));
        String dateFormated = "";
        if (splitDate[1].equals("ago")) {
            dateFormated = String.join("/", splitDate[0], splitDate[1].replace(splitDate[1], "08"), splitDate[2]);
            System.out.println(dateFormated);
        }
        if (date.equals(dateFormated)) {
            System.out.println("Valid date");
        } else {
            throw new java.lang.Error("The last date was '" + dateFormated + "'.");
        }
    }

    public void ValidateTheRatings(int validator, int starRating) {
        ScrollToElement(driver.findElement(By.xpath(".//span[text()='Opiniões']/span[text()='clientes']")), driver.findElement(By.xpath(".//div[contains(@class,'customerReviewsRating')]/div[contains(@class,'customerReviewChart')]")));
        if (driver.findElement(By.xpath(".//div[@class='customerReviewChart__star']/span[text()='" + starRating + "']")).isDisplayed()) {
            String ratingExpected = String.valueOf(validator);
            String ratingValue = driver.findElement(By.xpath(".//div[@class='customerReviewChart__star']/span[text()='" + starRating + "']/../..//span")).getText();
            if (ratingExpected.equals(ratingValue)) {
                logger.info("Valid rating");
                System.out.println("Valid rating");
            } else {
                throw new java.lang.Error("The rating for '" + starRating + "star' is not '" + ratingExpected + "'. It is '" + ratingValue + "'.");
            }
        } else {
            throw new java.lang.Error("The rating for '" + starRating + "star' is not present on page.");
        }
    }

    public void GoToAuthorPage() {
        ScrollToElement(driver.findElement(By.xpath(".//section[@id='CustomerReviews']")), driver.findElement(By.xpath(".//div[@class= 'productStrates__column authorStrate__column']")));
        driver.findElement(By.xpath(".//div[@class= 'productStrates__column authorStrate__column']//a[text() = 'Tudo sobre o autor']")).click();
    }

    public void ConsultAuthorsBiography() {
        driver.findElement(By.xpath(".//a[contains(text(), 'Ler Biografia')]")).click();
        if (!(driver.findElement(By.xpath(".//section[contains(@class, 'Biography')]")).isDisplayed())) {
            throw new java.lang.Error("The author's biography is not present.");
        } else {
            String authorName = driver.findElement(By.xpath(".//div[contains(@class, 'intervenantBiography-backgroundWord')]")).getText();
            String authorBiography = driver.findElement(By.xpath(".//div[contains(@class, 'intervenantBiography-text')]")).getText();
            System.out.println("The author's biography for author " + authorName + " is: " + authorBiography);
        }
    }

    public void ValidateBookExistsInAuthorsBiography(String bookName) {
        int i = 1;
        int flag = 0;
        int tam = driver.findElements(By.xpath(".//div[contains(@class,'Carousel-item')]")).size();
        System.out.println("TAMANHO: " + tam);

        ScrollToElement(driver.findElement(By.xpath(".//span[text()='Bibliografia']")), driver.findElement(By.xpath(".//div[contains(@class, 'js-Carousel-container')]")));

        while (i < tam + 1) {
            String bookTitle = driver.findElement(By.xpath("(.//span[@class='thumbnail-title']/a)[" + i + "]")).getText();
            if (bookName.equals(bookTitle)) {
                flag = 1;
                System.out.println("Book found with title:" + bookTitle + ".");
                break;
            } else {
                if (driver.findElement(By.xpath("(.//span[@class='thumbnail-title']/a)[" + i + "]")).isDisplayed()) {
                    i++;
                } else {
                    Actions a = new Actions(driver);
                    a.click(driver.findElement(By.xpath(".//button[contains(@class,'Carousel-arrow--rightFull')]"))).build().perform();
                    i++;
                }
            }
        }
        if (flag == 0) {
            throw new Error("The book '" + bookName + "is not present on the author's biography page.");
        }

    }

    public void AddBookToShoppingCart(String bookName) {
        int i = 1;
        int tam = driver.findElements(By.xpath(".//div[@class= 'clearfix Article-item js-Search-hashLinkId']")).size();

        while (i < tam + 1) {
            String title = driver.findElement(By.xpath("(.//div[contains(@data-automation-id, 'product-block')])[" + i + "]//a[contains(@class, 'Article-title')]")).getText();

            if (bookName.equals(title)) {
                System.out.println("Book found with title:" + title);
                driver.findElement(By.xpath("(.//button/span[text()='Adicionar ao Cesto'])[" + i + "]")).click();
                break;
            } else {
                if (i == tam) {
                    ScrollToElement(driver.findElement(By.xpath("(.//div[contains(@data-automation-id, 'product-block')])[" + i + "]//a[contains(@class, 'Article-title')]")), driver.findElement(btnActionNext));
                    try {
                        Thread.sleep(5000);
                        driver.findElement(btnActionNext).click();
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    try {
                        Thread.sleep(5000);
                        tam = driver.findElements(By.xpath(".//div[@class= 'clearfix Article-item js-Search-hashLinkId']")).size();
                        i = 1;
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    i++;
                }
            }
        }

    }

    public void CloseBasketContent() {
        try {
            Thread.sleep(3000);
            driver.findElement(By.xpath(".//button[@title='Close (Esc)']")).click();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ValidateNumberItensShoppingCart(String numberItens) {
        String numberItemBasket = driver.findElement(By.xpath(".//div[@data-automation-id='header-icon-basket']//span")).getText();
        if (numberItens.equals(numberItemBasket)) {
            System.out.println("The number of itens in basket is valid:" + numberItemBasket);
        } else {
            throw new java.lang.Error("Expected '" + numberItens + "' in basket but found '" + numberItemBasket + "'");
        }
    }

    public void ClickToGoShoppingCart() {
        CloseNotifyContent();
        driver.findElement(By.xpath(".//div[@data-automation-id='header-tab-basket']//a")).click();
        try {
            Thread.sleep(2000);
            CloseNotifyContent();
            driver.findElement(By.xpath(".//div[@class='LayerBasket__total-action']//a[text()='Ver o meu cesto']")).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ValidateTheTotalCosts() {
        String ExpectedBookPrice = driver.findElement(By.xpath(".//span[text()='Cesto']/../span[@class='basket-informations__price']/span")).getText();
        String bookPrice = ExpectedBookPrice.replace("€", ".");

        String ExpectedDeliveryPrice = driver.findElement(By.xpath(".//span[text()='Custos de entrega estimados']/../span[@class='basket-informations__price']/span")).getText();
        String deliveryPrice = ExpectedDeliveryPrice.replace("€", ".");

        String ExpectedTotalPrice = driver.findElement(By.xpath(".//div[text()='TOTAL']/span[text()='(com IVA)']/../span[@class='basket-informations__price basket-informations__price--total']/span")).getText();
        String totalPrice = ExpectedTotalPrice.replace("€", ".");

        System.out.println("BOOK: " + ExpectedBookPrice + "     " + bookPrice);
        System.out.println("DELIVERY: " + ExpectedDeliveryPrice + "     " + deliveryPrice);
        System.out.println("TOTAL: " + ExpectedTotalPrice + "     " + totalPrice);

        float book = Float.parseFloat(bookPrice);
        float delivery = Float.parseFloat(deliveryPrice);
        float total = Float.parseFloat(totalPrice);
        float billCountTotal = book + delivery;
        System.out.println("Total calculated is: " + billCountTotal);

        if (total == billCountTotal) {
            System.out.println("The expected amount to pay for the book is:" + total);
        } else {
            throw new java.lang.Error("Expected amount to pay for the book is'" + billCountTotal + "' and in basket found '" + total + "'");
        }
    }

    public void SearchForStoreAndGoStoresPage(String storeName) {
        driver.findElement(By.xpath(".//a[contains(@class,'store')]")).click();
        driver.findElement(By.xpath(".//input[@name='city']")).sendKeys(storeName);
        driver.findElement(By.xpath("(.//div/button[contains(@class,'store')])[2]")).click();
    }

    public void SelectTheStoreInZone(String storeZone) throws InterruptedException {
        int i = 1;
        int tam = driver.findElements(By.xpath(".//li[contains(@class,'store')]")).size();

        while (i < tam + 1) {
            String zone = driver.findElement(By.xpath("(.//li[contains(@class,'store')])[" + i + "]//a")).getText();

            if (storeZone.toUpperCase().equals(zone)) {
                System.out.println("Found store " + zone);
                if (driver.findElement(By.xpath("(.//li[contains(@class,'store')])[" + i + "]//div[@class='StoreFinder-storeContent']")).isDisplayed()) {
                    Thread.sleep(1000);
                    CloseNotifyContent();
                    driver.findElement(By.xpath("(.//li[contains(@class,'store')])[" + i + "]//div[@class='StoreFinder-storeContent']")).click();
                    Thread.sleep(500);
                    CloseNotifyContent();
                    break;
                } else {
                    if (driver.findElement(By.xpath(".//div[@class='inside_notify_content']")).isDisplayed()) {
                        driver.findElement(By.xpath(".//div[@class='inside_closeButton fonticon icon-hclose']")).click();
                    }
                    try {
                        WebElement scrollIntoStoresList = driver.findElement(By.xpath(".//div[contains(@class,'shopList')]"));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrollIntoStoresList);
                        driver.findElement(By.xpath("(.//li[contains(@class,'store')])[" + i + "]//a")).click();
                        Thread.sleep(800);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                }
            } else {
                if (i == tam) {
                    throw new java.lang.Error("A store in '" + storeZone + "' not found in a list of stores!");
                } else {
                    i++;
                }
            }
        }
    }

    public void ValidateStoreZipCode(String ExpectedZipCode) {
        CloseNotifyContent();
        String zipAddress = driver.findElement(By.xpath(".//div[@class='store_address']/div[2]")).getText();
        String[] zipAddressArray = zipAddress.split("\\s+");

        if (ExpectedZipCode.equals(zipAddressArray[0])) {
            System.out.println("Valid zip code (" + zipAddressArray[0] + ") for that store.");
        } else {
            throw new java.lang.Error("Invalid zip code (" + zipAddressArray[0] + ")' for that store. Expected '" + ExpectedZipCode + "'");
        }
    }

    public void ValidateStoreSchedule(String startHour, String endHour) {
        String[] week = {"segunda", "terça", "quarta", "quinta", "sexta", "sábado", "domingo"};
        int daysOfWeekTam = driver.findElements(By.xpath(".//span[@class='store_dayofweek']")).size();
        int i = 1;
        if (daysOfWeekTam == week.length) {
            while (i < daysOfWeekTam + 1) {
                String weekDay = driver.findElement(By.xpath("(.//div/span[@class='store_dayofweek'])[" + i + "]")).getText();
                if (weekDay.equals(week[i - 1])) {
                    String schedule = (driver.findElement(By.xpath("(.//div/span[@class='store_openingtime'])[" + i + "]")).getText()).replace(" - ", " ");
                    String[] scheduleArray = schedule.split("\\s+");
                    if (scheduleArray[0].equals(startHour) && scheduleArray[1].equals(endHour)) {
                        System.out.println("In " + weekDay + " the store open at " + scheduleArray[0] + "h and close at " + scheduleArray[1] + "h");
                    } else {
                        throw new java.lang.Error("The explected schedule for day" + weekDay + " it's open at " + startHour + "h and close at " + endHour + "h");
                    }
                } else {
                    throw new java.lang.Error("Expected the store is open every day of week. The " + week[i - 1] + "is not found in schedule.");
                }
                i++;
            }
        } else {
            throw new java.lang.Error("The store is not open every day");
        }
    }
}