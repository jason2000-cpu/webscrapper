package com.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

public class ScraperTask implements  Runnable {
    private String url;
    private List<Product> sharedResults;

    public ScraperTask(String url, List<Product> sharedResults) {
        this.url = url;
        this.sharedResults = sharedResults;
    }

    @Override
    public void run(){
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(5000)
                    .get();
            String title = doc.selectFirst("h1.product-title").text();
            String price = doc.selectFirst("span.price").text();

            Product product = new Product(title, price);

            synchronized (sharedResults){
                sharedResults.add(product);
            }

            System.out.println("Scraped: " + product );

        } catch (Exception e) {
            System.err.println("Error scrapping " + url + ": " + e.getLocalizedMessage());
        }
    }
}
