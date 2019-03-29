package services;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

public class ListHelper {

    private SelenideElement listElement;

    public ListHelper(SelenideElement listElement) {
        this.listElement = listElement;
    }

    public List<SelenideElement> itemsList(String path){
        List<SelenideElement> someList = listElement.$$(By.xpath(path));
        return someList;
    }


}
