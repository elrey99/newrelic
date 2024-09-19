package com.porvenir.config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Configuration
public class ConfigVariable {

    @Value("${id.service}")
    private String applicationName;


}
