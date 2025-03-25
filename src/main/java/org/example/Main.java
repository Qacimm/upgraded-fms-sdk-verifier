package org.example;

import java.util.LinkedHashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.example.config.FeatureServiceConfig;

import com.careem.featuretoggle.sdk.service.application.FeatureService;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            // Initialize feature service
            FeatureServiceConfig config = new FeatureServiceConfig();
            FeatureService featureService = config.createFeatureService();
            
            // Path for input and output files
            String varsFilePath = Paths.get("active_vars.txt").toAbsolutePath().toString();
            String contextVarsFilePath = Paths.get("context_vars.txt").toAbsolutePath().toString();
            String varsOutputFilePath = Paths.get("sdk_active_vars.txt").toAbsolutePath().toString();
            String contextOutputFilePath = Paths.get("sdk_context_vars.txt").toAbsolutePath().toString();
            
            // Process regular variables with isActive
            processVars(featureService, varsFilePath, varsOutputFilePath);
            
            // Process context variables with isActiveForContextId
            processContextVars(featureService, contextVarsFilePath, contextOutputFilePath);
            
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void processVars(FeatureService featureService, String inputFilePath, String outputFilePath) {
        try {
            // Use LinkedHashMap to maintain insertion order
            Map<String, Boolean> featureStatus = new LinkedHashMap<>();
            
            // Read variables from file
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Skip empty lines
                    if (line.trim().isEmpty()) continue;
                    
                    // Check if feature is active
                    String featureName = line.trim();
                    boolean isEnabled = featureService.isActive(featureName);
                    featureStatus.put(featureName, isEnabled);
                }
            }
            
            // Write results to output file
            writeResults(outputFilePath, featureStatus, "Regular Feature Toggle Status Report");
            
        } catch (IOException e) {
            System.err.println("Error processing vars: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void processContextVars(FeatureService featureService, String inputFilePath, String outputFilePath) {
        try {
            // Use LinkedHashMap to maintain insertion order
            Map<String, Boolean> featureStatus = new LinkedHashMap<>();
            
            // Read variables from file
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Skip empty lines
                    if (line.trim().isEmpty()) continue;
                    
                    // Check if feature is active for context ID
                    String featureName = line.trim();
                    boolean isEnabled = featureService.isActiveForContextId(featureName, 100L);
                    featureStatus.put(featureName, isEnabled);
                }
            }
            
            // Write results to output file
            writeResults(outputFilePath, featureStatus, "Context Feature Toggle Status Report");
            
        } catch (IOException e) {
            System.err.println("Error processing context vars: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void writeResults(String outputFilePath, Map<String, Boolean> featureStatus, String reportTitle) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            // writer.write(reportTitle + " - Generated at: " + 
            //         LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            
            for (Map.Entry<String, Boolean> entry : featureStatus.entrySet()) {
                writer.write(String.format("%s: %s\n", entry.getKey(), entry.getValue() ? "ENABLED" : "DISABLED"));
            }
            
        }
    }
}
