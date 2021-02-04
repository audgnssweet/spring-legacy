package kr.or.connect.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GoodsController {

    @RequestMapping(path = "/goods/{id}", method = RequestMethod.GET)
    public String getGoods(
        @PathVariable(name = "id") Integer id,
        @RequestHeader(value = "User-Agent", defaultValue = "myBrowser") String userAgent,
        HttpServletRequest httpServletRequest,
        ModelMap map
    ) {
        //attribute name에 - 들어가면 안된다.
        map.addAttribute("id", id);
        map.addAttribute("browserInfo", userAgent);
        map.addAttribute("path", httpServletRequest.getServletPath());
        return "goodsbyid";
    }
}
