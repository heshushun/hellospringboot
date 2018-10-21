package springboot.hello.hellospringboot.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springboot.hello.hellospringboot.service.StockService;
import springboot.hello.hellospringboot.task.ScheduleTask;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hss
 * @since 2018-10-21
 */
@RestController
@RequestMapping("/stock")
public class StockController {


}
