package services;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TableHelper {

    private SelenideElement tableElement;

    public TableHelper(SelenideElement tableElement) {
        this.tableElement = tableElement;
    }


    public List<SelenideElement> listOfElements(String path){
        List<SelenideElement> rows = tableElement.$$(By.xpath(path));
        return rows;
    }

    public List<List<SelenideElement>> listOfElementsContains(String path, String eachElementPath){
        List<SelenideElement> rows = listOfElements(path);
        List<List<SelenideElement>> columns = new ArrayList<List<SelenideElement>>();
        for (SelenideElement row: rows) {
            List<SelenideElement> column = row.$$(By.xpath(eachElementPath));
            columns.add(column);
        }
        return columns;
    }

    public SelenideElement elementOfTableByText(String text, String path, String eachElementPath){
        SelenideElement cell = null;
        List<List<SelenideElement>> rowsWithColumns = listOfElementsContains(path, eachElementPath);
        for (int i = 1; i < rowsWithColumns.size(); i++) {
            for (int j = 0; j <= rowsWithColumns.size(); j++) {
                if (rowsWithColumns.get(i).get(j).getText().equals(text)) cell = rowsWithColumns.get(i).get(j);
            }
        }
        return cell;
    }

    public List<String> getPropertiesFromTable(String path, String eachElementPath, int column){
        LinkedList<String> properties = new LinkedList<String>();
        List<List<SelenideElement>> rowsWithColumns = listOfElementsContains(path, eachElementPath);
        for (int i = 1; i < rowsWithColumns.size(); i++) {
           properties.add(i-1, rowsWithColumns.get(i).get(column).getText());
            }
            return properties;
    }






}
