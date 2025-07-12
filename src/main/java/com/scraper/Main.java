package com.scraper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<String> urls = List.of(
                "https://books.toscrape.com/catalogue/a-light-in-the-attic_1000/index.html",
                "https://books.toscrape.com/catalogue/tipping-the-velvet_999/index.html",
                "https://books.toscrape.com/catalogue/soumission_998/index.html",
                "https://books.toscrape.com/catalogue/sharp-objects_997/index.html",
                "https://books.toscrape.com/catalogue/sapiens-a-brief-history-of-humankind_996/index.html"
        );

        List<Product> results = Collections.synchronizedList(new ArrayList<>());

        ScraperService scraper = new ScraperService(5);
        scraper.scrape(urls, results);

        scraper.shutdown();
        try {
            scraper.awaitTermination(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        ResultWriter.WriteToCSV(results, "products.csv");
    }
}