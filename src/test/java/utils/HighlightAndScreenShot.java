package utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;


public class HighlightAndScreenShot {

	ChromeDriver driver;
	WebDriverWait wait;
	ChromeOptions options;
	Actions actions;
	JavascriptExecutor jse;
	File screenshotFile;
	
	public HighlightAndScreenShot(ChromeDriver driver) {
		wait = new WebDriverWait(driver, 10);
		actions = new Actions(driver);
		jse = (JavascriptExecutor) driver;
		this.driver = driver;
	}
	
	public byte[] resizeScreenShot(byte[] screenshot) throws IOException {

		ByteArrayInputStream bis = new ByteArrayInputStream(screenshot);
		BufferedImage bimg = ImageIO.read(bis);
		BufferedImage reSizedImg = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D = reSizedImg.createGraphics();
	    graphics2D.drawImage(bimg, 0, 0, bimg.getWidth(), bimg.getHeight(), null);
	    graphics2D.dispose();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(reSizedImg, "png", baos);
		byte[] bytes = baos.toByteArray();
		return bytes;
		
	}
	
	public void doProcess(WebElement element, String fileName, Scenario scenario) throws IOException, InterruptedException {
		
		jse.executeScript("arguments[0].style.border='2px solid red'", element);
		screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(".//screenshot/" + fileName + ".png"));
		byte[] screenshot =  ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		screenshot = resizeScreenShot(screenshot);
		scenario.attach(screenshot, "image/png", fileName);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].style.border='0px solid red'", element);
	}
	
	public void doProcessList(List<WebElement> elements, String fileName, Scenario scenario) throws IOException, InterruptedException {
		
		jse.executeScript("arguments[0].style.border='2px solid red'", elements.get(0));
		screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(".//screenshot/" + fileName + ".png"));
		byte[] screenshot =  ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		screenshot = resizeScreenShot(screenshot);
		scenario.attach(screenshot, "image/png", fileName);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].style.border='0px solid red'", elements.get(0));
	}
	
}
