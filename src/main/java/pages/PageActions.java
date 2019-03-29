package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import services.ListHelper;
import services.TableHelper;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

public class PageActions {

    private static final Logger log = LogManager.getLogger(PageActions.class);


    SelenideElement laptopsCategoriesTable = $(xpath("//div[@class='pab-table']"));
    SelenideElement laptopsList = $(xpath("//div[@class='clearfix']"));
    SelenideElement comparisonTableElement = $(xpath("//div[@class='comparison-t']"));

    private String menuCategoriesItem = "//ul[@class='menu-categories menu-categories_type_main']/li[@class='menu-categories__item']/a[text()='%s']";
    private String categoryItem = "//div[@class='menu__hidden-content']/div[@class='menu__main-cats']/div[@class='menu__main-cats-inner']/div[@class='menu__hidden-column']/ul[@class='menu__hidden-list']/li/a[text()='%s']";
    private String laptopsCategories = ".//div[@class='pab-row pab-row-fourth']";
    private String laptopCategory = ".//div[@class='pab-cell pab-img-150']/div[@class='clearfix sprite-side ']/p/a";
    private String laptopsItemsList = "//div[@class='g-i-tile g-i-tile-catalog']";
    private String compareCheckbox = "//div[@class='g-i-tile-i-box-desc']/div[@class='g-tools-container clearfix']/ul/li[2]/div[@name='comparison_new_catalog']/label/span[@class='g-compare']";
    private By toCompareButton = xpath("//li[@class='hub-i hub-i-comparison']/div[@id='comparison']/a");
    private String comparingItemsButton = "//div[@class='btn-link-to-compare']/a/span[text()='%s']";
    private String comparisonTableRows = ".//div[@class='comparison-t-row']";
    private String comparisonTablecolumn = ".//div[@class='comparison-t-cell']";
    private By goToMismaches = xpath("//div[@id='compare-menu']/ul/li[2]/a");

    TableHelper laptopsTable = new TableHelper(laptopsCategoriesTable);
    ListHelper listHelper = new ListHelper(laptopsList);
    TableHelper comparisonTable = new TableHelper(comparisonTableElement);


    public PageActions open(){
        Selenide.open("/");
        log.info("Page with baseURL=https://rozetka.com.ua/ is opened");
        return this;
    }

    public void hoverOnCategory(String category){
        log.info("User hovers to " + category + " category");
        $(xpath(String.format(menuCategoriesItem, category))).hover();
    }

    public void clickToCategory(String category){
        log.info("User clicks to " + category + " category");
        $(xpath(String.format(categoryItem, category))).click();
    }

    public void goToSiteMismatches(){
        log.info("User clicks to " + $(goToMismaches).getText());
        $(goToMismaches).shouldBe(Condition.visible).click();
    }

    public int siteMismatches(){
        int counter = 0;
        List<String> firstItemProps = comparisonTable.getPropertiesFromTable(comparisonTableRows, comparisonTablecolumn, 0);
        for (int i = 0; i < firstItemProps.size(); i++) {
            counter++;
        }
        log.info("User see " + counter + " mismatches");
        return counter;
    }

    public int countingOfMismatches(){
        int counter = 0;
        List<String> firstItemProps = comparisonTable.getPropertiesFromTable(comparisonTableRows, comparisonTablecolumn, 0);
        for (int i = 0; i < firstItemProps.size(); i++) {
            log.info("User see property  " + firstItemProps.get(i));
        }
        List<String> secondItemProps = comparisonTable.getPropertiesFromTable(comparisonTableRows, comparisonTablecolumn, 1);
        for (int i = 0; i < secondItemProps.size(); i++) {
            log.info("User see property  " + secondItemProps.get(i));
        }
        if (firstItemProps.size() == secondItemProps.size()) {
        for (int i = 0; i < firstItemProps.size(); i++) {
            if (!firstItemProps.get(i).equals(secondItemProps.get(i))) counter++;
        } } else {
            throw new RuntimeException("Items can`t be matched");
        }
        log.info("User see " + counter + " mismatches");
        return counter;
    }

    public boolean listsMatcher(){
        return siteMismatches() == countingOfMismatches();
    }

    public void scrollToElement(String text){
        log.info("User scrolls to " + text + " element");
        laptopsTable.elementOfTableByText(text, laptopsCategories, laptopCategory).scrollIntoView(true);
    }

    public void clickToElement(String text){
        log.info("User clicks to " + text + " element");
        laptopsTable.elementOfTableByText(text, laptopsCategories, laptopCategory).click();
    }

    public void addLaptopToCompare(int index) {
        log.info("User added " + index + " item to compare");
        listHelper.itemsList(laptopsItemsList).get(index).hover();
        listHelper.itemsList(laptopsItemsList).get(index).$(xpath(compareCheckbox)).shouldBe(Condition.visible).scrollIntoView(false).click();
    }

    public void compareClick(){
        log.info("User clicks to " + $(toCompareButton).getText());
        $(toCompareButton).scrollIntoView(true).click();
    }

    public void compareButtonClick(String buttonName){
        log.info("User clicks to " + buttonName);
        $(xpath(String.format(comparingItemsButton, buttonName))).click();
    }








}
