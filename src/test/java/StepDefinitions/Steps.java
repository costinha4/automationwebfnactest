package StepDefinitions;

import Helpers.HelperStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.StringReader;
import java.text.ParseException;

public class Steps extends HelperStep {
    String description;

    @Given("Open the Firefox and launch the application")
    public void open_the_firefox_and_launch_the_application() {
        NavigateToPage();
        AcceptCookies();
    }

    /*CENARIO 1*/
    @When("The user search for {string}")
    public void the_user_search_for(String searchString) {
        Search(searchString);
    }

    @Then("Validate that the book {string} is listed in the results")
    public void validate_that_the_book_fascism_and_democracy_is_listed_in_the_results(String bookTitle) throws InterruptedException {
        FindBookTitle(bookTitle);
    }

    @And("Validate that the book description contains the word {string}")
    public void validate_that_the_book_description_contains_the_word_world(String word) {
        FindWordDescription(word);
        closeBrowser();
    }
    /*----------*/

    /*CENARIO 2*/
    @Then("Validate that the author is {string}")
    public void validateThatTheAuthorIsGeorgeOrwell(String author) {
        ClickGoTheBookPage();
        ClickSeeBookCharacteristics();
        ValidateAuthor(author);
    }

    @And("Validate that the ISBN is {string}")
    public void validateThatTheISBNIs(String bookNumber) {
        ValidateISBN(bookNumber);
    }

    @And("Validate that the number of pages is {string}")
    public void validateThatTheNumberOfPagesIs(String pagesNumber) {
        ValidateNumberPages(pagesNumber);
    }

    @And("Validate that the dimensions of the book are {string}")
    public void validateThatTheDimensionsOfTheBookAreXCm(String dimensions) {
        ValidateDimensions(dimensions);
        closeBrowser();
    }
    /*----------*/

    /*CENARIO 3*/
    @Then("Validate that the oldest comment published on the book is from {string}")
    public void validateThatTheOldestCommentPublishedOnTheBookIsFrom(String date) throws ParseException {
        ClickGoTheBookPage();
        GoOpinionPage();
        OldestComment(date);
    }

    @And("Validate that there are {int} ratings with {int} star")
    public void validateThatThereAreRatingsWithStar(int arg, int starRating) {
        ValidateTheRatings(arg, starRating);
        closeBrowser();
    }
    /*----------*/

    /*CENARIO 4*/
    @Then("The user consults the author's biography")
    public void theUserConsultsTheAuthorSBiography() {
        ClickGoTheBookPage();
        GoToAuthorPage();
        ConsultAuthorsBiography();
    }

    @And("Validate that the book {string} is part of his works")
    public void validateThatTheBookAnimalFarmIsPartOfHisWorks(String book) {
        ValidateBookExistsInAuthorsBiography(book);
        closeBrowser();
    }
    /*----------*/

    /*CENARIO 5*/
    @When("The user adds book to shopping cart")
    public void theUserAddsBookToShoppingCart() {
        String bookName = "Fascism and Democracy";
        AddBookToShoppingCart(bookName);
        CloseBasketContent();
    }

    @Then("Validates that the number of items in the shopping cart is {string}")
    public void validatesThatTheNumberOfItemsInTheShoppingCartIs(String basketItem) {
        ValidateNumberItensShoppingCart(basketItem);
    }

    @And("Validates that the total \\(with IVA) is equal to the book value plus estimated delivery costs")
    public void validatesThatTheTotalWithIVAIsEqualToTheBookValuePlusEstimatedDeliveryCosts() {
        ClickToGoShoppingCart();
        ValidateTheTotalCosts();
        closeBrowser();
    }
    /*----------*/

    /*CENARIO 6*/
    @And("The user find a {string} store")
    public void theUserFindAStore(String storeName) {
        SearchForStoreAndGoStoresPage(storeName);
    }

    @When("The user select the {string} store, from the list of results")
    public void theUserSelectTheStoreFromTheListOfResults(String storeZone) throws InterruptedException {
        SelectTheStoreInZone(storeZone);
    }

    @Then("Check that the store\'s zip code is {string}")
    public void checkThatTheStoresZipCodeIs(String zipCode) {
        ValidateStoreZipCode(zipCode);
    }

    @And("Validates that the store is open every day between {string} and {string}")
    public void validatesThatTheStoreIsOpenEveryDayBetweenAnd(String startHour, String endHour) {
        ValidateStoreSchedule(startHour, endHour);
        closeBrowser();
    }
}