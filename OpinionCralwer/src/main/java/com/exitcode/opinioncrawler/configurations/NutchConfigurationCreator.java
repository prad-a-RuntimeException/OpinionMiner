package com.exitcode.opinioncrawler.configurations;

import com.exitcode.opinioncrawler.LoadConstants;
import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.util.NutchConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;


/**
 * Created by IntelliJ IDEA.
 *
 * @author: pradheepraju
 * Date: Oct 14, 2011
 * Time: 12:17:24 PM
 * Factory method to create Nutch Configuration for different levels of the Crawling process.
 */

public class NutchConfigurationCreator {


    protected Configuration configuration;
    private Properties properties;

    @Autowired
    private LoadConstants loadConstants;


    private NutchConfigurationCreator() {

        //generateConfiguration(customProperties);


    }

    private NutchConfigurationCreator generateConfiguration(Properties customProperties) {
        this.properties = customProperties;
        //properties.setProperty("plugin.folders", loadConstants.getNutchPluginFolder());

        //addAdditionalProperties();
        configuration = NutchConfiguration.create(true, customProperties);
        return this;
    }


    public static NutchConfigurationCreator createNutchConfigurationForInjection(final String agentName) {

        Properties customProperties = new Properties();
        customProperties.setProperty("http.agent.name", agentName);
        NutchConfigurationCreator configurationCreator = new NutchConfigurationCreator();
        return configurationCreator.generateConfiguration(customProperties);
    }

    public static NutchConfigurationCreator createNutchConfigurationForFetching(final String agentName) {

        Properties customProperties = new Properties();
        customProperties.setProperty("http.agent.name", agentName);
        NutchConfigurationCreator configurationCreator = new NutchConfigurationCreator();
        return configurationCreator.generateConfiguration(customProperties);
    }


    private void addAdditionalProperties() {
        properties.setProperty("plugin.folders",
                "Users/pradheepraju/projects/OpinionMiner/OpinionCralwer/src/main/resources/plugins");

    }


    public Configuration getConfiguration() {
        return configuration;
    }
}
