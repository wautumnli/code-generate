package ${package};

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
* advice拦截
*
* @author wanqiuli
* @date ${.now?datetime}
*/
@RestControllerAdvice
public class MyAdviceController {

    @ExceptionHandler(value = Exception.class)
    public Result<?> handler(Exception e) {
        return Result.failure(e.getMessage());
    }
}
