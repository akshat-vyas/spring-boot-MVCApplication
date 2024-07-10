package com.week2lectures.introductionToMVCarchitecture.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}