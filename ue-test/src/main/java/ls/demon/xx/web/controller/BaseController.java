/**
 * LS_Demon Org.
 * Copyright (c) 2005-2015 All Rights Reserved.
 */
package ls.demon.xx.web.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author lisong
 * @version $Id: BaseController.java, v 0.1 2015��2��11�� ����8:16:21 lisong Exp $
 */
@Controller
public class BaseController {

    private final static org.slf4j.Logger logger     = LoggerFactory
                                                         .getLogger(BaseController.class);

    private static int                    counter    = 0;
    private static final String           VIEW_INDEX = "index";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(ModelMap model) {

        model.addAttribute("message", "Welcome");
        model.addAttribute("counter", ++counter);
        logger.debug("[welcome] counter : {}", counter);

        // Spring uses InternalResourceViewResolver and return back index.jsp
        return VIEW_INDEX;

    }

    @RequestMapping(value = "/{name}/list.htm", method = RequestMethod.GET)
    public String welcomeName(@PathVariable String name, ModelMap model) {

        model.addAttribute("message", "Welcome " + name);
        model.addAttribute("counter", ++counter);
        logger.debug("[welcomeName] counter : {}", counter);
        return VIEW_INDEX;

    }

}
