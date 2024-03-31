package redbus;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;


import io.github.bonigarcia.wdm.WebDriverManager;

public class CalandarRedbus {
   static WebDriver driver;

   public static ArrayList<String> getWeekEndDates(String month) {

      ArrayList<String> weekendList1 = new ArrayList<String>();
      String searchingMonth = driver.findElement(By.cssSelector("div[class$='hHKFiR']>div:nth-child(1)>div:nth-child(2)")).getText();

      while (!searchingMonth.contains(month)) {
         System.out.println(
               driver.findElement(By.cssSelector("div[class$='hHKFiR']>div:nth-child(1)>div:nth-child(2)")).getText());
         driver.findElement(By.cssSelector("div[class$='hHKFiR']>div:nth-child(1)>div:nth-child(3)>svg")).click();
         searchingMonth = driver.findElement(By.cssSelector(".DayNavigator__IconBlock-qj8jdz-2.iZpveD:nth-child(2)"))
               .getText();

         if (driver.findElement(By.cssSelector(".DayNavigator__IconBlock-qj8jdz-2.iZpveD:nth-child(2)")).getText()
               .contains(month)) {
            List<WebElement> weekends1 = driver.findElements(By.cssSelector("span[class*='bwoYtA']"));

            for (WebElement w : weekends1) {
               weekendList1.add(w.getText());
            }
            System.out.println(driver
                  .findElement(By.cssSelector(".DayNavigator__IconBlock-qj8jdz-2.iZpveD:nth-child(2)")).getText());

         }

      }
      driver.quit();
      return weekendList1;
   }

   public static void main(String[] args) {

      WebDriverManager.edgedriver().setup();

      EdgeOptions options = new EdgeOptions();
      options.addArguments("--disable-notifications");
      driver = new EdgeDriver(options);
      driver.manage().window().maximize();
      driver.get("https://www.redbus.in/");
      driver.findElement(By.cssSelector(".dateText")).click();
      ArrayList<String> weekendList = getWeekEndDates("Dec 2025");
      System.out.println(weekendList);
   }

}
