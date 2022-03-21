package com.ql.code.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置参数
 *
 * @author wanqiuli
 * @date 2022/3/18 17:30
 */
@Data
@Component
@ConfigurationProperties(prefix = "application.generate")
public class GenConfig {
    /**
     * 数据库驱动类
     */
    private String driverClass;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 库名
     */
    private String catalog;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 主键名称
     */
    private String id = "id";
}