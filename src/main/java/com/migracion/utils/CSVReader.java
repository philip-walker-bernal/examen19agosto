package main.java.com.migracion.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length == 12) { // Aseguramos que cada línea tenga 12 campos
                    records.add(values);
                } else {
                    System.out.println("Línea ignorada por formato incorrecto: " + line);
                }
            }
        }
        return records;
    }
}