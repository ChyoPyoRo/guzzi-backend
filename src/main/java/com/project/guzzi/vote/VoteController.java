package com.project.guzzi.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class VoteController {

	private MybatisTestService testService;

	@Autowired
	public void  MybatisTestController (MybatisTestService testService){
		this.testService = testService;
	}


	@GetMapping("/getTest")
	public List<Map<String, Object>> getTest(){
		return testService.getTest();
	}









	@PostMapping("/api/votes")
	public String createVote(@RequestBody CreateVoteRequest request) {
		System.out.println("content = " + request.getContent());
		System.out.println("firstAnswer = " + request.getFirstAnswer());
		System.out.println("secondAnswer = " + request.getSecondAnswer());
		return "Hello";
	}


//	@ResponseBody
//	@Validated
//	@RequestMapping("/createPosts")
//	public String CreatePost(@RequestBody HttpServletRequest httpServletRequest, Model model, HttpServletResponse response) {
//		String content = httpServletRequest.getParameter("content");
//        String firstAnswer = httpServletRequest.getParameter("firstAnswer");
//        String secondAnswer = httpServletRequest.getParameter("secondAnswer");
//        System.out.println(httpServletRequest);
////        model.addAttribute("id", id);
////        model.addAttribute("pwd", pwd);
//
//    	return "send data";
//
//
//	}

}
