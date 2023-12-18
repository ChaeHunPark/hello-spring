package hello.hellospring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    /*
    * 웹에서 파라미터 받기
    * @RequestParam
    * */
    @GetMapping("hello-mvc") // localhost:8080/hello-mvc
    public String helloMvc(@RequestParam("name") String name, Model model){
        /*
        * model에 담으면 view에서 렌더링할 때 사용한다.
        * 파라미터로 넘어온 name을 넘긴다.
        */
        model.addAttribute("name",name);
        return "hello-template";
    }
}
