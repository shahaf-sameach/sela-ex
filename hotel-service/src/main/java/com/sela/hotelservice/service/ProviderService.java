package com.sela.hotelservice.service;

import com.sela.hotelservice.data.Provider;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@ConfigurationProperties(prefix = "application")
public class ProviderService {

    @Getter
    private List<Provider> providers = new ArrayList<>();

    @PostConstruct
    public void foo() {
        log.info("Init providers: {}", providers);
        if (providers.size() < 1)
            log.warn("Empty Provider list provided");
    }

}

