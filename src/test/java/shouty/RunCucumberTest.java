package shouty;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("shouty")
/*
 * junit
 * pretty
 * html:target/my-report
 * progress, junit:target/junit.xml
 * progress, rerun:target/rerun.txt
 */
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "shouty")
/*
@focus -> Executa somente quem tem a tag 
@SHOUTY-11 -> Tag declarada no topo da feature é herdada por todos cenários
not @tag -> não executa que tiver a tag
*/
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "not @todo")
public class RunCucumberTest {
}
