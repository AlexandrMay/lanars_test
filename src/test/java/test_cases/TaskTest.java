package test_cases;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.PageActions;
import properties.PropertiesLoader;

import java.io.IOException;


public class TaskTest implements PropertiesLoader {


    @BeforeClass
    public static void setUp() throws IOException {
    TaskTest t = new TaskTest();
    t.loadProperties();
    }

    @Test
    public void compareNotebooksTestCase() throws InterruptedException {
        PageActions mainPage = new PageActions();
        mainPage.open();
        mainPage.hoverOnCategory("Ноутбуки и компьютеры");
        mainPage.clickToCategory("Ноутбуки");
        mainPage.scrollToElement("Ноутбуки с SSD");
        mainPage.clickToElement("Ноутбуки с SSD");
        mainPage.addLaptopToCompare(0);
        mainPage.addLaptopToCompare(1);
        mainPage.compareClick();
        mainPage.compareButtonClick("Сравнить эти товары");
        mainPage.goToSiteMismatches();
        Assert.assertEquals(mainPage.siteMismatches(), mainPage.countingOfMismatches());
}









}
