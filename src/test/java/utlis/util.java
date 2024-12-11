package utlis;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class util {
    public static void scroll(WebDriver driver, int height){

        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+height+")");
    }
}
