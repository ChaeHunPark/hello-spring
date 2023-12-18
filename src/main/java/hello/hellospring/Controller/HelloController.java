package hello.hellospring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @GetMapping("hello-string")
    @ResponseBody
    /*
    * body의 내용을 return 값으로 직접 넣어준다.
    * */
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    /*
    * @ResponseBody 를 사용
    *    HTTP의 BODY에 문자 내용을 직접 반환
    *    viewResolver 대신에 HttpMessageConverter 가 동작
    *    기본 문자처리: StringHttpMessageConverter
    *    기본 객체처리: MappingJackson2HttpMessageConverter
    *    byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음
    * */
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
