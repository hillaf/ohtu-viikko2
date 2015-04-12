package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {
    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:8090");
        System.out.println( driver.getPageSource() );
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click(); 
        
        System.out.println("==");
        
        System.out.println( driver.getPageSource() );
        
        // wrong password
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("asfjgkjd");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        // non-existing username
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("skdgkjsusg");
        element = driver.findElement(By.name("password"));
        element.sendKeys("asfjgkjd");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        
       
        // go to create new user
        element = driver.findElement(By.linkText("back to home"));       
        element.click();
        
        element = driver.findElement(By.linkText("register new user"));       
        element.click();
        
        System.out.println("==");
        System.out.println( driver.getPageSource() );
        
        // create new user
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("hurpa");
        element = driver.findElement(By.name("password"));
        element.sendKeys("vituttaa2");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("vituttaa2");
        element = driver.findElement(By.name("add"));
        element.submit();
        
        // logout
        
        element = driver.findElement(By.linkText("continue to application mainpage"));       
        element.click();
        
        System.out.println("==");
        System.out.println( driver.getPageSource() );
        
        element = driver.findElement(By.linkText("logout"));       
        element.click();
        
        System.out.println("==");
        System.out.println( driver.getPageSource() );
        
        // login with correct credentials
        
        element = driver.findElement(By.linkText("login"));       
        element.click();
       
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        System.out.println("==");
        System.out.println( driver.getPageSource() );
        
    }
}
