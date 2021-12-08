package com.example.Test;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import java.io.File;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Testproyectofinal {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  
  @Before
  public void setUp() throws Exception {
	WebDriverManager.chromedriver().setup();
	
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testAagregarusuario() throws Exception {
    driver.get("https://mern-crud.herokuapp.com/");
    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("Alan p");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("alanp@gmail.com");
    driver.findElement(By.name("age")).click();
    driver.findElement(By.name("age")).clear();
    driver.findElement(By.name("age")).sendKeys("24");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::span[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
    pause(10000);
    
    String textoEjecucion = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nice one!'])[1]/following::p[1]")).getText();
    assertThat(("Successfully added!"),is(textoEjecucion));
	  }

  @Test
  public void testBmodificarusuario() throws Exception {
    driver.get("https://mern-crud.herokuapp.com/");
    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button")).click();
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("Alan C");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
    pause(10000);
    
    String textoEjecucion = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nice one!'])[1]/following::p[1]")).getText();
    assertThat(("Successfully updated!"),is(textoEjecucion));
  }
  
  @Test
   public void testCeliminarusuario() throws Exception {
   driver.get("https://mern-crud.herokuapp.com/");
   /*driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button[2]")).click();
   driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Delete User'])[1]/following::button[1]")).click();
 */
   WebElement table = driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/thead/tr"));
   WebElement reg = table.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr"));
   WebElement email = reg.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[2]"));
   
   String emailEliminado = email.getText();
   //System.out.print("Email a borar" + emailEliminado);
   
   WebElement elemBtn = driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button[2]"));
   elemBtn.click();
   
   pause(2500);
   WebElement elemConf = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Alan C'])[2]/following::button[1]"));
   elemConf.click();
   
   pause(2500);
   boolean eliminado = false;
    
    if(!table.getText().contains(emailEliminado)){

        eliminado = true;
        //System.out.print("usuario eliminado");
    }
    assertTrue(eliminado);
  }

  
  
  
@After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
  private void pause(long mils) {
	  try {
		  Thread.sleep(mils);
	  }catch(Exception e){
		  e.printStackTrace();
	  }

}
}
