package ${package_name};

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

<#list import as imp>
${imp}
</#list>

/**
* 服务实现层
*
* @author wanqiuli
* @date ${.now?datetime}
*/
@Service
public class ${class_name}ServiceImpl implements ${class_name}Service {

    @Resource
    private ${class_name}Dao ${low_class_name}Dao;
    @Resource
    private ${class_name}Manager ${low_class_name}Manager;

}