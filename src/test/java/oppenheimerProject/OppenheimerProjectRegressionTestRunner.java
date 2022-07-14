package oppenheimerProject;


import common.CommonRunnerConfig;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/oppenheimerProject/features"},
        glue = {"oppenheimerProject/steps"})


public class OppenheimerProjectRegressionTestRunner extends CommonRunnerConfig {
}
