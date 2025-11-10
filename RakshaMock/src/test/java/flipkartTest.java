import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import FileUtility.ExcelUtility;

public class flipkartTest {
	ExcelUtility eLib = new ExcelUtility();

	@DataProvider
	public Object[][] getData() throws Throwable{
		Object[][] obj=new Object[3][1];
		for(int i=0;i<3;i++) {
			obj[i][0]=eLib.readdatafromExcel1("product",i+1, 0);
		}
		return obj;
		
	}
	@Test(dataProvider = "getData")
	public void Flipkarttest1(String data) throws Throwable {
	
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		WebElement search_Product = driver.findElement(By.xpath("//input[@class='Pke_EE']"));
		search_Product.sendKeys(data);
		search_Product.sendKeys(Keys.ENTER);
		String price = driver.findElement(By.xpath("//div[.='"+data+ "']/../following-sibling::div[@class='col col-5-12 BfVC2z']/descendant::div[@class='Nx9bqj _4b5DiR']")).getText();
		
		for(int i=0;i<=eLib.getRowcount("product");i++) {
			if(eLib.readdatafromExcel1("product", i, 0).contains(data)){
				eLib.writeDatabackToExcel1("product", i, 1, price);
				break;
			}
		}
		
		}
	
	
}
		


