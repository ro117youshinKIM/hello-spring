package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //String을 그대로 내려준다.
    }

    // json 방식으로 내려준다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // 객체가 오면 디폴트가 json방식으로 data를 만들어서 http응답에 반환한다.
        hello.setName(name);
        return hello;
    }
    // 웹브라우저 -> 내장 톰캣 서버 -> Spring boot에서 받아 @ResponseBody를 사용하여 리턴해준다.
    // 기존에 String을 넘기면 viewResolver가 반응 했는데 여기서는 HttpMessageConverter가 반응한다.
    // HttpMessageConverter가 그냥 String이면 StringConverter가 객체이면 JsonConverter가 동작한다.
    // 그래서 Hello 객체를 Json형태로 변환하여 보내준다.
    // Tip. Jackson은 객체를 json으로 변환해주는 라이브러리(Spring은 기본적으로 얘를 탑제)
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
