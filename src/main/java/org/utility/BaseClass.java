package org.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	 
	
	//browser launch
	
      public static WebDriver browserLaunch(String browsername) {
    	  
    	  if(browsername.equalsIgnoreCase("chrome")) {
    	  WebDriverManager.chromedriver().setup();
  	    driver=new ChromeDriver();
    	  }
    	  else if(browsername.equalsIgnoreCase("firefox")) {
    		  WebDriverManager.firefoxdriver().setup();
    		  driver=new FirefoxDriver();
    	  }
    	  else if(browsername.equalsIgnoreCase("edge")) {
    		  WebDriverManager.edgedriver().setup();
    		  driver=new EdgeDriver();
    	  }
         return driver;
	}

      public static void quit() {
		 driver.quit();

	}
    //browser launch 2
       
      public static WebDriver launchBrowser(String browsername) {
    	  
    	  switch(browsername) {
    	  
    	  case "chrome":
    		  WebDriverManager.chromedriver().setup();
    	  	    driver=new ChromeDriver();
    	  	    break;
    	  	    
    	  case "firefox":
    		  WebDriverManager.firefoxdriver().setup();
    		  driver=new FirefoxDriver();
    		  break;
    		  
    	  case "edge":
    		  WebDriverManager.edgedriver().setup();
    		  driver=new EdgeDriver();
    	      break;
    		  
    	  }
    	  return driver;
      }

	
	public static void chromeBrowser() {
		WebDriverManager.chromedriver().setup();
	    driver=new ChromeDriver();

	}
	
	//url launch
	 
	public static void urlLaunch(String url) {
		 driver.get(url);
		 
	}
	
	public static void maximize() {
		 driver.manage().window().maximize();
	}
	
	public static void implictWait(long secs) {
		driver.manage().timeouts().implicitlyWait(secs, TimeUnit.SECONDS);

	}
	
	public static String currentUrl() {
		 String currentUrl = driver.getCurrentUrl();
		 return currentUrl;
		 
		 
		 

	}
	
	 
	
	//WebElements
	
	public static void sendKeys(WebElement e,String value) {
		e.sendKeys(value);
	}
	
	public static void click(WebElement e) {
		e.click();
		 	}
	
	public static String getText(WebElement e) {
		String text = e.getText();
		return text;
	}
	
	// Actions
	
	public static void moveToElement(WebElement e) {
     Actions a=new Actions(driver);
     a.moveToElement(e).perform();
	}
	
	//Select
	
	public static void selectByIndex(WebElement e,int index) {
      Select s=new Select(e);
      s.selectByIndex(index);
	}
	
	public static void selectByValue(WebElement e,String value ) {
		Select s=new Select(e);
		s.selectByValue(value);
	}
	
	public static  void selectByVisibleText(WebElement e,String value) {
		Select s=new Select(e);
		s.selectByVisibleText(value);
		}
	
	public static String getFirstSelectedOption(WebElement e) {
		Select s=new Select(e);
		WebElement first = s.getFirstSelectedOption();
		String text = first.getText();
		return text;
	}
	
	 public static void getAllSelectedOptions(WebElement e,String value1) {
		 Select s=new Select(e);
		 List<WebElement> all = s.getAllSelectedOptions();
		 for(int i=0;i<all.size();i++) {
			 WebElement w = all.get(i);
			 String value = w.getAttribute(value1);
			 s.selectByValue(value);
		 }
	}
	
	 
	
	
	//Frames 
	
	public static void frameIndex(int index) {
		driver.switchTo().frame(index);
	}
	
	public static void frameName(String value) {
       driver.switchTo().frame(value);
	}
	
	// default content
	
	public static void defaultcontent() {
		driver.switchTo().defaultContent();
	 
	}
	 
	// parent frame
	public static void parentframe() {
		driver.switchTo().parentFrame();

	}
	 
	//  Alerts
     
	public static void alerts() {
		Alert s = driver.switchTo().alert();
		s.accept();
	}
	
	public static void alert() {
		Alert s = driver.switchTo().alert();
		s.dismiss();
	}
	
	//navigate 
	
	public static void navigateTo(String url) {
    
		driver.navigate().to(url);
	}
	
	 public static void back() {
      driver.navigate().back();
	}
	
	 public static void forward() {
     driver.navigate().forward();
	}

	 public static void refresh() {
     driver.navigate().refresh();
	}
	
	 //Screenshot
	 
	public static void screenshot(String filename) throws IOException {
     
		TakesScreenshot tk=(TakesScreenshot) driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		File des=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Screenshot\\"+filename+""+System.currentTimeMillis()+".png");
		FileUtils.copyDirectory(src, des);
	} 
	 
	
	//javascript Executor
	
	public static void jsSendKeys(WebElement e,String value) {
      JavascriptExecutor js=(JavascriptExecutor) driver;
      js.executeScript("argument[0].setAttribute('value',"+value+"')",e);
	}
	
	public static void scrollDown(WebElement e) {
	      JavascriptExecutor js=(JavascriptExecutor) driver;
	      js.executeScript("arguments[0].scrollIntoView(true)",e);
		}
	
	public static void scrollUp(WebElement e) {
	      JavascriptExecutor js=(JavascriptExecutor) driver;
	      js.executeScript("arguments[0].scrollIntoView(flase)",e);
		}
	
	public static String jsGetText(WebElement e) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		String value = js.executeScript("arguments[0].getAttribute('value')", e).toString();
	    return value;
	}
	
	public static String excelRead(String filename, String sheetname,int row,int cell) throws IOException {
    File loc=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\"+filename+".xlsx");
		
		FileInputStream fs=new FileInputStream(loc);
		
		Workbook w=new XSSFWorkbook(fs);
		
		Sheet s = w.getSheet(sheetname);
		
		Row r = s.getRow(row);
		
		Cell c = r.getCell(cell);
		
		int type = c.getCellType();
		
		
		 String value="";
		if(type==1) {
			 value = c.getStringCellValue();
		}
		else {
			if(DateUtil.isCellDateFormatted(c)) {
				Date date = c.getDateCellValue();
				SimpleDateFormat sf=new SimpleDateFormat("dd-MM-yyyy");
				 value = sf.format(date);
			}
			else {
				double db = c.getNumericCellValue();
				long num=(long) db;
				 value = String.valueOf(num);
			}
		}
		return value;
	}
	
	public static String getAttribute(WebElement e) {
		String value = e.getAttribute("value");
		 return value;

	}
		  
	}
	
	
		

