package com.ql.code.gen.init;

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
public class SpringRunInit extends Init {
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    Template prepare(Map<String, Object> dataMap) throws TemplateException, IOException {
        dataMap.put("module", "spring.run");
        dataMap.put("package", dataMap.get("base_package"));
        dataMap.put("path", dataMap.get("project_name") + "/src/main/java/" +
                dataMap.get("package").toString().replaceAll("\\.", "/") + "/");
        dataMap.put("file_path", dataMap.get("path") + "DemoGenerateRun" + GenUtil.SUFFIX);
        dataMap.put("mapper_scan", dataMap.get("package") + ".dao");
        Configuration configuration = freeMarkerConfigurer.createConfiguration();
        return configuration.getTemplate("springRun.ftl");
    }
}
