package com.exitcode.opinioncrawler.crawler;

import com.exitcode.opinioncrawler.configurations.NutchConfigurationCreator;
import com.google.common.base.Preconditions;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.nutch.crawl.Generator;
import org.apache.nutch.crawl.Injector;
import org.apache.nutch.util.NutchConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: pradheepraju
 * Date: Oct 14, 2011
 * Time: 11:59:54 AM
 * Implementation of the MainCrawler
 */

//TODO: Refine the exception mangement.
public class MainCrawlerImpl implements MainCrawler {


    Configuration configuration;
    private final String agentName;

    private static Logger logger = LoggerFactory.getLogger(MainCrawlerImpl.class);

    public MainCrawlerImpl(String agentName) {
        checkNotNull(agentName);
        this.agentName = agentName;

    }

    /**
     * The first step of the crawling process, where the database is seeded with the urls that
     * we need to crawl.
     *
     * @param crawlDbName
     * @param urlDirectory
     */
    @Override
    public void performSeeding(final String crawlDbName, final String urlDirectory) {

        checkNotNull(crawlDbName, "CrawlDb is an mandatory field");
        checkNotNull(urlDirectory,
                "Please specify an url directory with a flat file containing urls that needs to be crawled");
        File urlDir = new File(urlDirectory);
        checkArgument(urlDir.exists(), "The url directory does not exist");
        logger.info("The urldir {} is a directory {}", urlDir, urlDir.isDirectory());
        checkArgument(urlDir.isDirectory(), "The File location is not an directory");


        configuration = NutchConfigurationCreator.createNutchConfigurationForInjection(agentName).getConfiguration();
        String[] args = new String[]{crawlDbName, urlDirectory};

        try {
            int i = ToolRunner.run(NutchConfiguration.create(), new Injector(), args);
        } catch (Exception e) {
            logger.error(" Exception occured while seeding  " + e.getMessage());
            e.printStackTrace();

        }


    }

    /**
     * Fetches the seeded urls.
     *
     * @param crawlDb
     * @param segmentDir
     */
    @Override
    public void performFetching(final String crawlDb, final String segmentDir) {

        Preconditions.checkNotNull(crawlDb);
        Preconditions.checkNotNull(segmentDir);
        logger.info("Entering performFetching for {} and {}", crawlDb, segmentDir);

        configuration = NutchConfigurationCreator.createNutchConfigurationForInjection(agentName).getConfiguration();
        String[] args = new String[]{crawlDb, segmentDir};

        try {
            int res = ToolRunner.run(NutchConfiguration.create(), new Generator(), args);
        } catch (Exception e) {
            logger.error(" Exception occured while generating  " + e.getMessage());
            e.printStackTrace();
        }

        // If there are no exception, then we need to parse the list of files in the segment directory and
        // create reference of this directory.

        File segDir = new File(segmentDir);
        if(segDir.exists() && segDir.isDirectory()) {
            // The file exists and it is a directory
            segDir.listFiles()
        }

        logger.info("Exiting performFetching");

    }

    @Override
    public void parseFetchedContent() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateFetchedContent() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
