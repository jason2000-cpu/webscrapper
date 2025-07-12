package com.scraper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ResultWriter {
    public static void WriteToCSV(List<Product> products, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Product p : products) {
                writer.write(p.getTitle() + ", " + p.getPrice() + "\n");
            }
            System.out.println("Result saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
