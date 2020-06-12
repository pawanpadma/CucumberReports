package com.selenium.automation.cucumberreport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/", glue = { "steps" }, plugin = { "pretty",
		"html:target/cucumber-reports", "json:target/cucumber-reports/Cucumber.json" }, monochrome = true)
public class AppTest {

	@AfterClass
	public static void generateReport() {

		
		File reportOutputDirectory = new File("target/demo");
		List<String> jsonFiles = new ArrayList<String>();
		jsonFiles.add("target/cucumber-reports/Cucumber.json");

		String buildNumber = "101";
		String projectName = "Live Demo Project";
		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		configuration.setBuildNumber(buildNumber);

		configuration.addClassifications("Browser", "Firefox");
		configuration.addClassifications("Branch", "release/1.0");
		configuration.setSortingMethod(SortingMethod.NATURAL);
		configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
		configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
		// configuration.setQualifier("sample", "Some Qualifier");
		// points to the demo trends which is not used for other tests
		configuration.setTrendsStatsFile(new File("target/test-classes/demo-trends.json"));

		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		reportBuilder.generateReports();
	}

}
