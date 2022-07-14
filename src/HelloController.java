package kr.ac.kopo.ctc.spring.board.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.kopo.ctc.spring.board.domain.Hello;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {

	@RequestMapping(value = "") // class 위에 선언되어 있는 RequestMapping + helloSpringBoot = "/hello"+""
	public String helloSpringBoot(Model model) {
		model.addAttribute("name", "홍길동");
		return "hello";
	}
	
	// localhost:8080/hello/getParameter?name=김유신 :: name의 parameter값을 받아 출력
	@RequestMapping(value = "/getParameter") 
	public String getParameter(Model model, HttpServletRequest req) {
		String name = req.getParameter("name");
		model.addAttribute("name", name);
		return "hello";
	}
	
	// localhost:8080/hello/requestParam?name=홍길동 
	@RequestMapping(value = "/requestParam")
	public String requestParam(Model model, @RequestParam(value="name") String name) {
		model.addAttribute("name", name);
		return "hello";
	}
	
	// 경로상으로 보낼 때, {id}값을 주로 보낸다.
	// localhost:8080/hello/pathVariable/홍길동
	@RequestMapping(value = "/pathVariable/{name}")
	public String pathVariable(Model model, @PathVariable("name") String name) {
		model.addAttribute("name", name);
		return "hello";
	}
	
	// * 가장 많이 사용
	// localhost:8080/hello/modelAttribute?name=홍길동
	@RequestMapping(value = "/modelAttribute")
	public String modelAttribute(Model model, @ModelAttribute Hello hello) {
		model.addAttribute("name", hello.getName());
		return "hello";
	}
	
	// POST
	// localhost:8080/hello/requestBody1/
	// Headers - KEY: Content-Type, VALUE: application/json 
	// Body에서 json형식으로 데이터 넘겨서 확인하기
	@RequestMapping(value = "/requestBody1")
	public String requestBody1(Model model, @RequestBody Map<String, Object> obj) {
		model.addAttribute("name", obj.get("name"));
		return "hello";
	}
	
	// * 가장 많이 사용, 가장 Spring스러운 방법
	// hello 객체를 json타입으로 만들어서 보내줘야 한다.
	// localhost:8080/hello/requestBody2/
	// Headers - KEY: Content-Type, VALUE: application/json 
	// Body에서 json형식으로 데이터 넘겨서 확인하기
	@RequestMapping(value = "/requestBody2")
	public String requestBody2(Model model, @RequestBody Hello hello) { 
		model.addAttribute("name", hello.getName());
		return "hello";
	}
	
}
