package com.scraper;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScraperService {
    private ExecutorService executor;

    public ScraperService(int threadCount) {
        this.executor = Executors.newFixedThreadPool(threadCount);
    }

    public void scrape(List<String> urls, List<Product> sharedResults){
        for (String url : urls){
            ScraperTask task = new ScraperTask(url, sharedResults);
            executor.submit(task);
        }
    }

    public boolean isTerminated() {
        return executor.isTerminated();
    }

    public void awaitTermination(long timeout, TimeUnit unit) throws  InterruptedException {
        executor.awaitTermination(timeout, unit);
    }

    public void shutdown() {
        executor.shutdown();
    }
}
