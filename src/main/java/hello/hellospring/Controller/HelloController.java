package hello.hellospring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//MVC의 Controller
@Controller
public class HelloController {

    //REST의 Get
    @GetMapping("hello") // localhost:8080/hello
    public String hello(Model model){
        // 매핑된 후 data를 찾아서 Value인 hello를 넘겨준다.
        model.addAttribute("data", "hello");
        return "hello"; // templates/hello.html을 매핑한다.
    }
}
