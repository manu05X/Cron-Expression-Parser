package com.example.CronPaserCLI.config;


import com.example.CronPaserCLI.managers.ParsingManager;
import com.example.CronPaserCLI.managers.impl.ParsingManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserConfig {
    
    @Bean
    public ParsingManager parsingManager() {
        ParsingManager manager = new ParsingManagerImpl();
        manager.registerParsers();
        return manager;
    }
}