import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Mapprr {
	
	static AppiumDriver driver;
	
	WebDriver driver1;
	static AndroidDriver driver2;
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			openMapprr();
			
		}catch(Exception exp)
		{
		//System.out.println(exp.getCause());
		//System.out.println(exp.getMessage());
		exp.printStackTrace();
		}
	}
	
	public static void openMapprr() throws Exception
	{
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Mi A3");
		cap.setCapability("udid", "8d4dc0f182d3");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "9");
		cap.setCapability("appPackage",  "com.bts.consumer");
		cap.setCapability("appActivity",  "com.bts.consumer.SplashUiBrandLogo");
		cap.setCapability("noReset", "True");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		
		driver = new AppiumDriver<MobileElement>(url,cap);
		
		System.out.println("Application Started....");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		while(getFourthStore().size() == 0) {
			scrollDown();
		}
		
		if(getFourthStore().size() > 0) {
			getFourthStore().get(0).click();
		}
		Thread.sleep(4000);
		
		getStoreFromList().click();
		WebElement product=getProduct();
		
		if (product == null)
		{
			System.out.println("Store is closed or product is unavailable, please try again later");
		}
		else {
			
			getProduct().click();
			proceedToCheckout().click();
		}
		}

	public static void scrollDown() {
		Dimension dimension = driver.manage().window().getSize();
		Double scrollHeightStart = dimension.getHeight() * 0.5;
		int scrollStart = scrollHeightStart.intValue();
		
		Double scrollHeightEnd = dimension.getHeight() * 0.2;
		int scrollEnd = scrollHeightEnd.intValue();
		
		new TouchAction((PerformsTouchActions) driver)
		.press(PointOption.point(0, scrollStart))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(0,  scrollEnd))
		.release().perform();
		
	}
	
	
	public static List<WebElement> getFourthStore() {
		return driver.findElements(By.xpath("//android.view.ViewGroup[@instance='4']/android.widget.TextView[1]"));
	}
	
	public static WebElement getStoreFromList() {
		return driver.findElement(By.xpath("//android.widget.LinearLayout[@instance='5']/android.widget.TextView[1]"));
	}
	
	public static WebElement getProduct() {
		
		
		try {
			WebElement checkStoreOpen = driver.findElement(By.xpath("//android.widget.LinearLayout[@instance='3']/android.widget.ImageButton[2]"));
			
			return checkStoreOpen;
		}catch(Exception e)
		{	
			return null;
		}
	}
	
	
	public static WebElement proceedToCheckout() {
		return driver.findElement(By.xpath("//android.widget.TextView[@text='PROCEED TO CHECKOUT']"));
	}
	
	public static WebElement scrollToAnElementByText(AppiumDriver<WebElement> driver, String text) {
        return driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector())" +
                ".scrollIntoView(new UiSelector().text(\"" + text + "\"));"));
}

	    }
		
	

