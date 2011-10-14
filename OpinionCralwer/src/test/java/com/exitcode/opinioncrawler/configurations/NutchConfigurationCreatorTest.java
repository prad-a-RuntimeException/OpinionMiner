package com.exitcode.opinioncrawler.configurations;

import org.apache.hadoop.conf.Configuration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: pradheepraju
 * Date: Oct 14, 2011
 * Time: 12:39:58 PM
 * Nutch Configuration testing Class.
 */

public class NutchConfigurationCreatorTest {

    NutchConfigurationCreator nutchConfigurationCreator;
    private static Logger logger = LoggerFactory.getLogger(NutchConfigurationCreatorTest.class);

    @Test
    public void testCreateNutchConfigurationForInjection() throws Exception {

        Configuration configuration = NutchConfigurationCreator.createNutchConfigurationForInjection("mangoAgent")
                .getConfiguration();

        String agentName = configuration.get("http.agent.name");
        String fileContentLimit = configuration.get("file.content.limit");
        String nutchPluginFolder = configuration.get("plugin.folders");
        logger.info(agentName);
        logger.info(fileContentLimit);
        logger.info(nutchPluginFolder);

        assertNotNull(agentName);
        assertNotNull(fileContentLimit);
        assertEquals(agentName, "mangoAgent");
        assertNotNull(nutchPluginFolder);
        assertNotNull(configuration);


    }
}
