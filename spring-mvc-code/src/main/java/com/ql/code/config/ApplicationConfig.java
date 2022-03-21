package com.ql.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * freeMarker配置类
 *
 * @author wanqiuli
 * @date 2022/3/21 12:17
 */
@Configuration
public class ApplicationConfig {

    @Bean(name = "freeMarkerConfigurer")
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setDefaultEncoding("UTF-8");
        // ftl目录
        configurer.setTemplateLoaderPath("classpath:/ftl");
        Map<String, Object> variables = new HashMap<>(1 << 1);
        variables.put("xml_escape", "fmXmlEscape");
        configurer.setFreemarkerVariables(variables);
        return configurer;
    }

}
