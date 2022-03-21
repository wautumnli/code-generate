package com.ql.code;

import com.ql.code.util.GenDatabaseUtil;
import com.ql.code.run.GenEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenDemoApplicationTests {


    @Resource
    private GenDatabaseUtil genDatabaseUtil;
    @Resource
    private GenEntity genEntity;


    @Test
    public void testTables() {
        genDatabaseUtil.getTables().forEach(System.out::println);
    }

    @Test
    public void testColumns() {
        genDatabaseUtil.getColumns("au_user").forEach(System.out::println);
    }

    @Test
    public void run() throws Exception {
        genEntity.run(null);
    }
}