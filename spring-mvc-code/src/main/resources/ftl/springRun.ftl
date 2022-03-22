package ${package};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* @author wanqiuli
* @date ${.now?datetime}
*/
@SpringBootApplication
@MapperScan("${mapper_scan}")
public class DemoGenerateRun {

    public static void main(String[] args) {
        SpringApplication.run(DemoGenerateRun.class, args);
    }
}
