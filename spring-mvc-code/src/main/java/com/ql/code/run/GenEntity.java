package com.ql.code.run;

import com.ql.code.config.GenDatabaseUtil;
import com.ql.code.util.GenUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class GenEntity implements ApplicationRunner {

	@Resource
    private GenDatabaseUtil genDatabaseUtil;
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Configuration configuration = freeMarkerConfigurer.createConfiguration();
        // 他会自己寻找 resources 下的 templates 目录下的模板文件
        Template entityTemplate = configuration.getTemplate("entity.ftl");
        // 获取数据库所有表
        List<String> tables = genDatabaseUtil.getTables();
        // 存放模板变量
        Map<String, Object> data = new HashMap<>();
        data.put("package_name", "cn.wanqiuli.entity");
        // 文件写入
        FileWriter fileWriter;
        for (String table : tables) {
        	// 工具类将下划线命名转化为驼峰
            String entityClassName = GenUtil.underlineToHump(table, true);
            data.put("table_name", table);
            data.put("class_name", entityClassName);
            // 获取当前表的所有列
            data.put("columns", genDatabaseUtil.getColumns(table));
            // 文件创建
            File file = new File("src/main/java/com/ql/gen/entity/" + GenUtil.underlineToHump(table, true) + GenUtil.SUFFIX);
            log.info(file.getAbsolutePath());
            if (!file.exists()) {
                if (!new File("src/main/java/com/ql/gen/entity/").mkdirs()) {
                    log.error("创建文件夹失败");
                    return;
                }
                if (!file.createNewFile()) {
                    log.error("{} 创建文件失败", table);
                    return;
                }
            }
            fileWriter = new FileWriter(file);
            entityTemplate.process(data, fileWriter);
            log.info("Table {} generate succeed!", table);
        }
    }
}
