package org.example.config;

import com.careem.galileo.sdk.Galileo;

public class GalileoConfig {


    public Galileo galileo() throws Exception{
        return Galileo.builder()
                .withService("customer_api_gateway")
                .withEnv("prod")
                .build();
    }
}

