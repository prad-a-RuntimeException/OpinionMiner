package com.exitcode.opinioncrawler.crawler;

import com.exitcode.opinioncrawler.LoadConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: pradheepraju
 * Date: Oct 14, 2011
 * Time: 1:48:18 PM
 * Test class for MainCrawler.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/opinioncrawler.xml"})
public class MainCrawlerImplTest {


    private static Logger logger = LoggerFactory.getLogger(MainCrawlerImplTest.class);

    @Autowired
    private MainCrawler mainCrawler;


    @Autowired
    private LoadConstants loadConstants;

    @Before
    public void setUp() throws Exception {

        assertNotNull(mainCrawler);

    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void testPerformSeeding() throws Exception {
        logger.info("Entering testPerformSeeding");
        String crawlDbName = "crawlDb";
        logger.info("The valid url directory is {}", loadConstants.getUrlDirectory());
//
        mainCrawler.performSeeding(crawlDbName, loadConstants.getUrlDirectory());
    }

    @Test(expected = NullPointerException.class)
    public void testPerformSeedingWithEmptyCrawlDb() throws Exception {

        String urlDirectory = "urlDirctory";
        mainCrawler.performSeeding(null, urlDirectory);
    }

    @Test(expected = NullPointerException.class)
    public void testPerformSeedingWithEmptyDir() throws Exception {

        String crawlDbName = "crawlDb";
        String urlDirectory = null;
        mainCrawler.performSeeding(crawlDbName, urlDirectory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPerformSeedingWithInvalidDirectory() throws Exception {

        String crawlDbName = "crawlDb";
        String urlDirectory = "urlDirctory";
        mainCrawler.performSeeding(crawlDbName, urlDirectory);
    }

    @Test
    public void testPerformFetching() throws Exception {
    }

    @Test
    public void testParseFetchedContent() throws Exception {
    }

    @Test
    public void testUpdateFetchedContent() throws Exception {
    }
}
