package com.example.demo.controller;

import com.example.demo.repository.EngSubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DemoController {


    @Autowired
    EngSubRepository engSubRepository;

    @GetMapping("/sample")
    public String showSample(Model model) {
        model.addAttribute("message", "테스트중");
        List<String> eng =  engSubRepository.findFirstFiveTextSub();
        List<Map<String,Object>> sub =  engSubRepository.findRandomSubtitles();
        model.addAttribute("sub", sub);
        return "index";
    }

    /*Model 객체는 일반적으로 컨트롤러에서 뷰로 데이터를 전달하기 위해 사용됩니다. 만약 당신이 AJAX 응답의 일부로 모델 객체를 직접 반환하려는 경우,
    Model 객체를 직접 반환하는 대신 Map이나 다른 DTO (Data Transfer Object) 형식을 사용*/
    @PostMapping("/studyDramSub")
    @ResponseBody
    public Map<String, Object> getData(@RequestBody Map<String, Object> requestData, Model model) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String,Object>> sub =  engSubRepository.findRandomSubtitles();
        response.put("sub", sub);
        //yes i can used to be a english sub common!
        return response;
    }

    @PostMapping("/ajax/checkAnswer")
    @ResponseBody
    public Map<String, Object> checkAnswer(@RequestBody Map<String, Object> requestData, Model model) {
        Map<String, Object> response = new HashMap<>();
        Map<String,Object> selectedSub =  engSubRepository.findCustomDataBySeq((String) requestData.get("seq"));
        System.out.println(selectedSub);

        String dbEng = (String) selectedSub.get("e_text_sub");
        String cusEng = (String) requestData.get("sentence");

        if(dbEng.equals(cusEng)){
            response.put("answerStatus", "correct");
        }else{
            response.put("answerStatus", "incorrect");
        }

        return response;
    }


}
