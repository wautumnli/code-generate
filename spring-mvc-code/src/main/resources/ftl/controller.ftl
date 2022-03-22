package ${package};


import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

<#list import as imp>
import ${imp};
</#list>


/**
* 服务实现层
*
* @author wanqiuli
* @date ${.now?datetime}
*/
@RestController
public class ${class_name}Controller {

    @Resource
    private ${class_name}Service ${low_class_name}Service;

}