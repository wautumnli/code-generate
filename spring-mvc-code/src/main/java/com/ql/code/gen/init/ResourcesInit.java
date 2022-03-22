package com.ql.code.gen.init;

import com.ql.code.config.GenConfig;
import com.ql.code.util.GenUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author wanqiuli
 * @date 2022/3/22 15:34
 */
@Service
public class ResourcesInit extends Init{
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Resource
    private GenConfig genConfig;

    @Override
    Template prepare(Map<String, Object> dataMap) throws TemplateException, IOException {
        dataMap.put("module", "resources");
        dataMap.put("package", dataMap.get("base_package"));
        dataMap.put("path", dataMap.get("project_name") + "/src/main/resources/");
        dataMap.put("file_path", dataMap.get("path") + "application.yml");
        dataMap.put("entity_path", dataMap.get("package") + ".entity");
        dataMap.put("jdbc_url",genConfig.getUrl());
        dataMap.put("jdbc_username",genConfig.getUsername());
        dataMap.put("jdbc_password",genConfig.getPassword());
        Configuration configuration = freeMarkerConfigurer.createConfiguration();
        return configuration.getTemplate("resource.ftl");
    }
}
