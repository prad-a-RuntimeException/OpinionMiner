package com.exitcode.opinioncrawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: pradheepraju
 * Date: Oct 14, 2011
 * Time: 2:10:48 PM
 * Load constants from testproperty files.
 */
@Component
public class LoadConstants {

    Logger logger = LoggerFactory.getLogger(LoadConstants.class);


    @Value("${urlDirectory}")
    protected String urlDirectory;

    @Value("${nutch-plugin-folder}")
    protected String nutchPluginFolder;


    public String getUrlDirectory() {

        return urlDirectory;
    }


    public String getNutchPluginFolder() {
        return nutchPluginFolder;
    }
}
