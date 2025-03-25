package org.example.config;


import com.careem.featuretoggle.sdk.service.application.FeatureService;
import com.careem.featuretoggle.sdk.service.application.FeatureServiceBuilder;

public class FeatureServiceConfig {

    public  FeatureService createFeatureService() {
        return new FeatureServiceBuilder().build("careem-prod-features", "features.json", "http://feature-management-service.careem-engineering.com", "trip_rating_service");
    }
}




