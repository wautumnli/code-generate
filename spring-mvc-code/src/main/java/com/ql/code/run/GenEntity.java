package com.ql.code.run;

import com.ql.code.entity.ColumnClass;
import com.ql.code.util.GenDatabaseUtil;
import com.ql.code.util.GenUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * 启动类
 *
 * @author wanqiuli
 * @date 2022/3/21 12:21
 */
@Slf4j
@Component
public class GenEntity implements ApplicationRunner {

    @Resource
    private GenDatabaseUtil genDatabaseUtil;
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("begin generate code..");
        Configuration configuration = freeMarkerConfigurer.createConfiguration();
        // 获取数据库所有表
        List<String> tables = genDatabaseUtil.getTables();
        if (CollectionUtils.isEmpty(tables)) {
            log.error("database don't have tables!");
            return;
        }
        // 存放模板变量
        Map<String, Object> data = new HashMap<>();
        // 文件写入
        Template entityTemplate = null;
        for (String table : tables) {
            // 工具类将下划线命名转化为驼峰
            String entityClassName = GenUtil.underlineToHump(table, true);
            data.put("table_name", table);
            data.put("class_name", entityClassName);
            data.put("lowClassName", GenUtil.firstLow(entityClassName));
            List<ColumnClass> columns = genDatabaseUtil.getColumns(table);
            columns.removeIf(column -> column.getColumn().equals("id"));
            data.put("columns", columns);
            // pom
            entityTemplate = configuration.getTemplate("pom.ftl");
            initPom(entityTemplate);
            // run
            entityTemplate = configuration.getTemplate("springRun.ftl");
            initRun(entityTemplate);
            // resource
            entityTemplate = configuration.getTemplate("resource.ftl");
            initResource(entityTemplate);
            // entity
            entityTemplate = configuration.getTemplate("entity.ftl");
            initEntity(table, entityTemplate, data);
            // dao
            entityTemplate = configuration.getTemplate("dao.ftl");
            initDao(table, entityTemplate, data);
            // manager
            entityTemplate = configuration.getTemplate("manager.ftl");
            initManager(table, entityTemplate, data);
            // manager impl
            entityTemplate = configuration.getTemplate("managerImpl.ftl");
            initManagerImpl(table, entityTemplate, data);
            // service
            entityTemplate = configuration.getTemplate("service.ftl");
            initService(table, entityTemplate, data);
            // service impl
            entityTemplate = configuration.getTemplate("serviceImpl.ftl");
            initServiceImpl(table, entityTemplate, data);
            // controller
            entityTemplate = configuration.getTemplate("controller.ftl");
            initController(table, entityTemplate, data);
        }
    }

    private void initPom(Template entityTemplate) throws IOException, TemplateException {
        File file = new File("codeg/" + "pom.xml");
        log.info(file.getAbsolutePath());
        if (!file.exists()) {
            if (!new File("codeg/").mkdirs()) {
                log.error("创建文件夹失败");
                return;
            }
            if (!file.createNewFile()) {
                log.error("创建文件失败");
                return;
            }
        }
        FileWriter fileWriter = new FileWriter(file);
        entityTemplate.process(null, fileWriter);
        log.info("pom.ftl succeed!");
    }

    private void initRun(Template entityTemplate) throws IOException, TemplateException {
        File file = new File("codeg/src/main/java/com/ql/" + "ApplicationRun.java");
        log.info(file.getAbsolutePath());
        if (!file.exists()) {
            if (!new File("codeg/src/main/java/com/ql/").mkdirs()) {
                log.error("创建文件夹失败");
                return;
            }
            if (!file.createNewFile()) {
                log.error("创建文件失败");
                return;
            }
        }
        FileWriter fileWriter = new FileWriter(file);
        entityTemplate.process(null, fileWriter);
        log.info("run succeed!");
    }

    private void initResource(Template entityTemplate) throws IOException, TemplateException {
        File file = new File("codeg/src/main/resources/" + "application.yml");
        log.info(file.getAbsolutePath());
        if (!file.exists()) {
            if (!new File("codeg/src/main/resources/").mkdirs()) {
                log.error("创建文件夹失败");
                return;
            }
            if (!file.createNewFile()) {
                log.error("创建文件失败");
                return;
            }
        }
        FileWriter fileWriter = new FileWriter(file);
        entityTemplate.process(null, fileWriter);
        log.info("run succeed!");
    }

    private void initEntity(String table, Template entityTemplate, Map<String, Object> data) throws IOException, TemplateException {
        data.put("package_name", "com.ql.entity");
        File file = new File("codeg/src/main/java/com/ql/entity/" + GenUtil.underlineToHump(table, true) + GenUtil.SUFFIX);
        log.info(file.getAbsolutePath());
        if (!file.exists()) {
            if (!new File("codeg/src/main/java/com/ql/entity/").mkdirs()) {
                log.error("创建文件夹失败");
                return;
            }
            if (!file.createNewFile()) {
                log.error("{} 创建文件失败", table);
                return;
            }
        }
        FileWriter fileWriter = new FileWriter(file);
        entityTemplate.process(data, fileWriter);
        log.info("Table {} entity generate succeed!", table);
    }

    private void initDao(String table, Template entityTemplate, Map<String, Object> data) throws IOException, TemplateException {
        data.put("package_name", "com.ql.dao");
        data.put("import", "import com.ql.entity." + data.get("class_name") + ";");
        File daoFile = new File("codeg/src/main/java/com/ql/dao/" + GenUtil.underlineToHump(table, true) + "Dao" + GenUtil.SUFFIX);
        log.info(daoFile.getAbsolutePath());
        if (!daoFile.exists()) {
            if (!new File("codeg/src/main/java/com/ql/dao/").mkdirs()) {
                log.error("创建文件夹失败");
                return;
            }
            if (!daoFile.createNewFile()) {
                log.error("{} 创建文件失败", table);
                return;
            }
        }
        FileWriter fileWriter = new FileWriter(daoFile);
        entityTemplate.process(data, fileWriter);
        log.info("Table {} dao generate succeed!", table);
    }

    private void initManager(String table, Template entityTemplate, Map<String, Object> data) throws IOException, TemplateException {
        data.put("package_name", "com.ql.manager");
        File file = new File("codeg/src/main/java/com/ql/manager/" + GenUtil.underlineToHump(table, true) + "Manager" + GenUtil.SUFFIX);
        log.info(file.getAbsolutePath());
        if (!file.exists()) {
            if (!new File("codeg/src/main/java/com/ql/manager/").mkdirs()) {
                log.error("创建文件夹失败");
                return;
            }
            if (!file.createNewFile()) {
                log.error("{} 创建文件失败", table);
                return;
            }
        }
        FileWriter fileWriter = new FileWriter(file);
        entityTemplate.process(data, fileWriter);
        log.info("Table {} manager generate succeed!", table);
    }

    private void initManagerImpl(String table, Template entityTemplate, Map<String, Object> data) throws IOException, TemplateException {
        data.put("package_name", "com.ql.manager.impl");
        List<String> importList = new ArrayList<>();
        importList.add("import com.ql.manager." + data.get("class_name") + "Manager;");
        importList.add("import com.ql.dao." + data.get("class_name") + "Dao;");
        data.put("import", importList);
        File file = new File("codeg/src/main/java/com/ql/manager/impl/" + GenUtil.underlineToHump(table, true) + "ManagerImpl" + GenUtil.SUFFIX);
        log.info(file.getAbsolutePath());
        if (!file.exists()) {
            if (!new File("codeg/src/main/java/com/ql/manager/impl/").mkdirs()) {
                log.error("创建文件夹失败");
                return;
            }
            if (!file.createNewFile()) {
                log.error("{} 创建文件失败", table);
                return;
            }
        }
        FileWriter fileWriter = new FileWriter(file);
        entityTemplate.process(data, fileWriter);
        log.info("Table {} managerImpl generate succeed!", table);
    }

    private void initService(String table, Template entityTemplate, Map<String, Object> data) throws IOException, TemplateException {
        data.put("package_name", "com.ql.service");
        File file = new File("codeg/src/main/java/com/ql/service/" + GenUtil.underlineToHump(table, true) + "Service" + GenUtil.SUFFIX);
        log.info(file.getAbsolutePath());
        if (!file.exists()) {
            if (!new File("codeg/src/main/java/com/ql/service/").mkdirs()) {
                log.error("创建文件夹失败");
                return;
            }
            if (!file.createNewFile()) {
                log.error("{} 创建文件失败", table);
                return;
            }
        }
        FileWriter fileWriter = new FileWriter(file);
        entityTemplate.process(data, fileWriter);
        log.info("Table {} service generate succeed!", table);
    }

    private void initServiceImpl(String table, Template entityTemplate, Map<String, Object> data) throws IOException, TemplateException {
        data.put("package_name", "com.ql.service.impl");
        List<String> importList = new ArrayList<>();
        importList.add("import com.ql.manager." + data.get("class_name") + "Manager;");
        importList.add("import com.ql.dao." + data.get("class_name") + "Dao;");
        importList.add("import com.ql.service." + data.get("class_name") + "Service;");
        data.put("import", importList);
        File file = new File("codeg/src/main/java/com/ql/service/impl/" + GenUtil.underlineToHump(table, true) + "ServiceImpl" + GenUtil.SUFFIX);
        log.info(file.getAbsolutePath());
        if (!file.exists()) {
            if (!new File("codeg/src/main/java/com/ql/service/impl/").mkdirs()) {
                log.error("创建文件夹失败");
                return;
            }
            if (!file.createNewFile()) {
                log.error("{} 创建文件失败", table);
                return;
            }
        }
        FileWriter fileWriter = new FileWriter(file);
        entityTemplate.process(data, fileWriter);
        log.info("Table {} serviceImpl generate succeed!", table);
    }

    private void initController(String table, Template entityTemplate, Map<String, Object> data) throws IOException, TemplateException {
        data.put("package_name", "com.ql.controller");
        List<String> importList = new ArrayList<>();
        importList.add("import com.ql.service." + data.get("class_name") + "Service;");
        data.put("import", importList);
        File file = new File("codeg/src/main/java/com/ql/controller/" + GenUtil.underlineToHump(table, true) + "Controller" + GenUtil.SUFFIX);
        log.info(file.getAbsolutePath());
        if (!file.exists()) {
            if (!new File("codeg/src/main/java/com/ql/controller/").mkdirs()) {
                log.error("创建文件夹失败");
                return;
            }
            if (!file.createNewFile()) {
                log.error("{} 创建文件失败", table);
                return;
            }
        }
        FileWriter fileWriter = new FileWriter(file);
        entityTemplate.process(data, fileWriter);
        log.info("Table {} controller generate succeed!", table);
    }
}
