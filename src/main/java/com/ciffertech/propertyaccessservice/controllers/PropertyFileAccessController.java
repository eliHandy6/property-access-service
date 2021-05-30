package com.ciffertech.propertyaccessservice.controllers;

import com.ciffertech.propertyaccessservice.beans.PropertyAccessValue;
import com.ciffertech.propertyaccessservice.beans.PropertyAcessBeans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
@RequestMapping("/access")
public class PropertyFileAccessController {

    @Autowired
    PropertyAcessBeans propertyAcessBeans;


    @GetMapping("accessPropertyFile")
    public PropertyAccessValue accessPropertyFile() {
//        refreshActuator();
        return new PropertyAccessValue(propertyAcessBeans.getName(),
                propertyAcessBeans.getDescription());
    }

    public void refreshActuator() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8100/actuator/refresh";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> entity = restTemplate.postForEntity(url, httpEntity, String.class);
    }
}
