package com.ql.code.gen.init;

import com.ql.code.util.GenUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wanqiuli
 * @date 2022/3/22 15:17
 */
@Service
public class ManagerImplInit extends Init{
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    Template prepare(Map<String, Object> dataMap) throws TemplateException, IOException {
        dataMap.put("module", "manager.impl");
        dataMap.put("package", dataMap.get("base_package") + "." + "manager.impl");
        dataMap.put("path", dataMap.get("project_name") + "/src/main/java/" +
                dataMap.get("package").toString().replaceAll("\\.", "/") + "/");
        dataMap.put("file_path", dataMap.get("path") +
                GenUtil.underlineToHump((String) dataMap.get("table_name"), true) + "ManagerImpl" + GenUtil.SUFFIX);
        List<String> importList = new ArrayList<>();
        importList.add(dataMap.get("base_package") + "." + "dao." + dataMap.get("class_name") + "Dao");
        importList.add(dataMap.get("base_package") + "." + "manager." + dataMap.get("class_name") + "Manager");
        dataMap.put("import", importList);
        Configuration configuration = freeMarkerConfigurer.createConfiguration();
        return configuration.getTemplate("managerImpl.ftl");
    }
}
