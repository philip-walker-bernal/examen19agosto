package main.java.com.migracion.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ErrorLogger {
    private static final String LOG_FILE = "transfer.log";

    public static void logError(String message, String data) {
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            out.println(LocalDateTime.now() + " - Error: " + message + " - Data: " + data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}