import org.functions.Utility.BrowserFactory;
import org.functions.Utility.ConfigReader;
import org.functions.Utility.DriverManager;
import org.testng.annotations.AfterTest;

import java.text.BreakIterator;

public class Test {

    @org.testng.annotations.Test
    public void test() throws Exception {
        BrowserFactory.launchApplication("URL");
    }
    @AfterTest
    public void tearDown() throws Exception {
        BrowserFactory.tearDownBrowser();
    }
}
