package org.example.config;


import com.careem.featuretoggle.sdk.service.application.FeatureService;
import com.careem.featuretoggle.sdk.service.application.FeatureServiceBuilder;

public class FeatureServiceConfig {

    public  FeatureService createFeatureService() {
        return new FeatureServiceBuilder().build("careem-qa-features", "features.json", "http://feature-management-service.careem-engineering.com", "care_resolution");
    }
}




