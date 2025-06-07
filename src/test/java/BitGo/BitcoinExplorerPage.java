package BitGo;

import UtilityManager.DriverManager;
import UtilityManager.JavaScriptExecutorUtils;
import UtilityManager.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class BitcoinExplorerPage {
    public static List<String> input = new ArrayList<>();
    public static List<String> output = new ArrayList<>();
    public static List<String> hashesWithOneInputTwoOutputs = new ArrayList<>();
    public BitcoinExplorerPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(text(),'of') and contains(text(),'Transactions')]")
    public WebElement HeaderValue;

    @FindBy(xpath = "//div[@class='txn font-p2']/a")
    public List<WebElement> HashLinks;

    @FindBy(xpath = "//div[@class='vins' and count(./div[@class='vin']) = 1]")
    public List<WebElement> InputValue;

    @FindBy(xpath = "//div[@class='vouts' and count(./div[@class='vout']) = 2]")
    public List<WebElement> OutputsValue;

    public Boolean findheader(){
        WaitUtils.waitForElementToBeVisible(HeaderValue);
        JavaScriptExecutorUtils.scrollIntoView(HeaderValue); // Assuming this is static
        if(HeaderValue.isDisplayed()){
            return true;
        }
        return false;
    }

    public void ValidatedInputandOutPut(){
        System.out.println("Total Transaction Hashes found: " + HashLinks.size());

        InputValue.clear();
        OutputsValue.clear();
        hashesWithOneInputTwoOutputs.clear();

        for (WebElement hashLink : HashLinks) {
            WebElement transactionBox = hashLink.findElement(By.xpath("./ancestor::div[@class='transaction-box']"));

            boolean hasOneInput = false;
            boolean hasTwoOutputs = false;

            for (WebElement vinContainer : InputValue) {
                if (vinContainer.findElement(By.xpath("./ancestor::div[@class='transaction-box']")).equals(transactionBox)) {
                    hasOneInput = true;
                    input.add(hashLink.getText());
                    break;
                }
            }
            for (WebElement voutContainer : OutputsValue) {
                if (voutContainer.findElement(By.xpath("./ancestor::div[@class='transaction-box']")).equals(transactionBox)) {
                    hasTwoOutputs = true;
                    output.add(hashLink.getText());
                    break;
                }
            }
            if (hasOneInput && hasTwoOutputs) {
                hashesWithOneInputTwoOutputs.add(hashLink.getText());
                System.out.println("Found hash with 1 input and 2 outputs: " + hashLink.getText());
            }
        }
    }

}
