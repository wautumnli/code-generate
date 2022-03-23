package com.ql.code.gen;

import com.ql.code.gen.init.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanqiuli
 * @date 2022/3/22 13:01
 */
@Component
public class InitConfig {
    @Resource
    private EntityInit entityInit;
    @Resource
    private DaoInit daoInit;
    @Resource
    private PomInit pomInit;
    @Resource
    private ManagerInit managerInit;
    @Resource
    private ServiceInit serviceInit;
    @Resource
    private ServiceImplInit serviceImplInit;
    @Resource
    private ManagerImplInit managerImplInit;
    @Resource
    private ControllerInit controllerInit;
    @Resource
    private SpringRunInit springRunInit;
    @Resource
    private ResourcesInit resourcesInit;
    @Resource
    private DtoInit dtoInit;
    @Resource
    private ResultInit resultInit;
    @Resource
    private AdviceInit adviceInit;

    @Bean("initList")
    public List<Init> initList() {
        List<Init> initList = new ArrayList<>();
        initList.add(entityInit);
        initList.add(daoInit);
        initList.add(managerInit);
        initList.add(serviceInit);
        initList.add(managerImplInit);
        initList.add(serviceImplInit);
        initList.add(controllerInit);
        initList.add(dtoInit);
        return initList;
    }

    @Bean("firstInitList")
    public List<Init> firstInitList() {
        List<Init> initList = new ArrayList<>();
        initList.add(pomInit);
        initList.add(springRunInit);
        initList.add(resourcesInit);
        initList.add(resultInit);
        initList.add(adviceInit);
        return initList;
    }
}
