package ${package};

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

<#list import as imp>
import ${imp};
</#list>

/**
* 事务实现层
*
* @author wanqiuli
* @date ${.now?datetime}
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class ${class_name}ManagerImpl implements ${class_name}Manager {

    @Resource
    private ${class_name}Dao ${low_class_name}Dao;

}