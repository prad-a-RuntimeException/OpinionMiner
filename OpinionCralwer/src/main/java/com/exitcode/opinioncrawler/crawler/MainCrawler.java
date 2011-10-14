package com.exitcode.opinioncrawler.crawler;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: pradheepraju
 * Date: Oct 14, 2011
 * Time: 11:53:59 AM
 * The Main type for performing crawling, the main step involved in crawling are
 * 1- Seeding the urls.
 * 2- Fetching the urls.
 * 3- Parsing the fetched content.
 * 4- Updating the fetched content
 * 5- Make the fetched content searchable.
 * <p/>
 * The final content is the responsibility of the search module. Once we incorporated these things, we will have
 * to expose the search content for our NLP module.
 */
public interface MainCrawler {


    void performSeeding(String crawlDbName, String urlDirectory);

    void performFetching(String crawlDb, String segmentDir);

    void parseFetchedContent();

    void updateFetchedContent();


}
