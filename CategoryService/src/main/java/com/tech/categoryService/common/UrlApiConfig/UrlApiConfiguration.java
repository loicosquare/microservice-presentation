package com.tech.categoryService.common.UrlApiConfig;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("url-configuration.yml")
@Getter
public class UrlApiConfiguration {
}
