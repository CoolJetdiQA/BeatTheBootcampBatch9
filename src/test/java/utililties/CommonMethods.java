package utililties;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods extends PageInitializer {

	// TC01_USER003 SIRI
	public static void schrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(0,300)", "");

	}

	public static WebDriverWait getWaitObject() {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		return wait;
	}

	public static List<WebElement> waitForAllElementsToBeVisible(List<WebElement> element) {
		return getWaitObject().until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public static WebElement waitForVisibility(WebElement element) {
		return getWaitObject().until(ExpectedConditions.visibilityOf(element));
	}

	public static WebElement waitForClickability(WebElement element) {
		return getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void click(WebElement element) {
		waitForClickability(element);
		element.click();
	}

	public static byte[] takeScreenshot(String filename) {
		TakesScreenshot ts = (TakesScreenshot) getDriver();
		byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);

		File file = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = "" + filename + getTimeStemp() + ".png";

		try {
			FileUtils.copyFile(file, new File(destinationFile));
		} catch (Exception ex) {
			System.out.println("Cannot take screenshot!");
		}

		return picBytes;
	}

	public static String getTimeStemp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		return sdf.format(date.getTime());
	}

	// TC03_USER001 - Siri
	public static Boolean waitForInvisibility(WebElement element) {
		return getWaitObject().until(ExpectedConditions.invisibilityOf(element));
	}

}
