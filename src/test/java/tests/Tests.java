package tests;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utilities.Read_JSON;

public class Tests {

	int actual_number_of_Wicket_Keepers;
	int actual_number_of_foreign_players;
	int expected_minimum_number_of_Wicket_Keepers;
	int expected_number_of_foreign_players;
	Read_JSON readjson;
	JSONObject jsonobject;
	SoftAssert softAssert;
	ExtentReports report;
	ExtentSparkReporter reporter;
	ExtentTest test;
	
	@BeforeSuite
	public void setup() throws IOException, ParseException
	{
		readjson=new Read_JSON();
		jsonobject=readjson.getJsonData();
		expected_number_of_foreign_players=4;
		
		report=new ExtentReports();
		reporter=new ExtentSparkReporter("target/spark/spark.html");
		actual_number_of_Wicket_Keepers=readjson.get_NumberofWicketKeepers(jsonobject);
		System.out.println("actual number of wicket keepers are: " + actual_number_of_Wicket_Keepers);
		expected_minimum_number_of_Wicket_Keepers=1;
		
		
		actual_number_of_foreign_players=readjson.get_NumberofForeignPlayers(jsonobject);
		System.out.println("actual number of foreign players are: "+actual_number_of_foreign_players);
		
		softAssert =new SoftAssert();
	}
	
	@Test
	public void verify_Team_Has_Only_4_Foreign_players()
	{
		
		test=report.createTest("verify number of foreign players");
		report.attachReporter(reporter);
		if(actual_number_of_foreign_players==expected_number_of_foreign_players)
		{
			Assert.assertTrue(true);
			System.out.println("test case for number of foreign players is passed");
			test.log(Status.PASS, "foreign players are equal to 4");
		}
		else
		{
			System.out.println("test case for number of foreign players is falied");
			test.log(Status.FAIL, "foreign players are not equal to 4");
			Assert.assertTrue(false);				
		}
	}
	
	@Test
	public void verify_Team_has_atleast_1_wicket_keeper()
	{
		test=report.createTest("verify that atleast one wicket keeper is there");
		if(actual_number_of_Wicket_Keepers>=expected_minimum_number_of_Wicket_Keepers)
		{
			System.out.println("test for number of wicket keeper is passed");
			test.log(Status.PASS, "Atleast 1 wicket keeper is there");
			Assert.assertTrue(true);
			
		}
		else
		{
			System.out.println("test case for number of wicket keepers is failed");
			test.log(Status.FAIL, "wicket keeper is not available");
			Assert.assertTrue(false);
		}
	}
	
	@AfterSuite
	public void tearDown()
	{
		report.flush();
	}
}
