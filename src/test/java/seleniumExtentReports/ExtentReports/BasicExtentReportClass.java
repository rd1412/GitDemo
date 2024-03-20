package seleniumExtentReports.ExtentReports;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BasicExtentReportClass {

	// creating methods of some Extent classes

	ExtentSparkReporter htmlreporter;
	ExtentReports reports;
	ExtentTest test;

	@BeforeTest
	public void configureReport() {

		htmlreporter = new ExtentSparkReporter("ExtentReport.html");
		reports = new ExtentReports();
		reports.attachReporter(htmlreporter);

		// configuration to change look and feel
		htmlreporter.config().setDocumentTitle("Test Report");
		htmlreporter.config().setReportName("Web Automation Test");

		// add env details
		reports.setSystemInfo("Machine", "MacBook");
		reports.setSystemInfo("Tester", "Raymond");
	}

	@Test
	public void launchBrowser() {
		//store in ExtentTest Object
		test = reports.createTest("Launch Browser");
		Assert.assertTrue(true);
	}

	@Test
	public void getUrl() {
		test = reports.createTest("Url name");
		Assert.assertTrue(false);
	}

	@Test
	public void verifyTitle() {
		test = reports.createTest("Verify Title");
		throw new SkipException("Skipping this test case with exception..");
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
		}
		else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.YELLOW));
		}
	}

	@AfterTest
	public void teadDown() {
		reports.flush();
	}
}
