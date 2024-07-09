package testRunner;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
        (
        	features= {".//Features/Customers.feature"},    // To run one feature file
        	// features=".//Features/",                 // To run all feature files
        	 //features={".//Features/Login.feature", ".//Features/Customers.feature"},  //  To run more than one feature files
        	// And same thing for Step defination files also
        	glue="stepDefinitions",
        	dryRun=false,
        	monochrome=true,
        	plugin= {"pretty",
        		"html:test-output"}
        	//tags= {"@sanity"}   //  this will represent OR
        	//tags= {"@sanity", "@regression"}  // this will represent And 
		
		)
public class TestRun {

}
