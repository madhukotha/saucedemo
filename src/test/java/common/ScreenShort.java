package common;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenShort implements ITestListener{
	ParallelTest failedtest;
	@Override
	public void onTestFailure(ITestResult result) {
		
		try {
			failedtest.captureScreenshot(result.getMethod().getMethodName()+".jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
}
