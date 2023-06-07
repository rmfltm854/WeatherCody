package com.example.weathercody.Controller;


import com.example.weathercody.Crawler.CrollerMan;
import com.example.weathercody.DTO.UserDTO;
import com.example.weathercody.JPA.ImageEntity;
import com.example.weathercody.JPA.UserJPA;
import com.example.weathercody.Repository.ImageRepository;
import com.example.weathercody.Repository.UserRepository;
import com.example.weathercody.Service.EmailService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@RequiredArgsConstructor
@Controller
@Slf4j
public class MainController {

    private final ImageRepository Imagerepo;
    private final UserRepository userRepo;

    private final EmailService service;
    @GetMapping("/")
    public String Index(Model model){
        model.addAttribute("list",Imagerepo.getRanking());
        System.out.println(Imagerepo.getRanking());
        model.addAttribute("ranking6",Imagerepo.getRanking());
        return "index";
    }

//    @GetMapping("/shop")
//    public String shop(Model model){
//        model.addAttribute("list",Imagerepo.ProductRender(1));
//        System.out.println(Imagerepo.getRanking());
//        return "product";
//    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/loginForm")
    @ResponseBody
    public int login(String email, String pw, HttpSession session){
        System.out.println(email);
        System.out.println(pw);
        UserJPA jpa = userRepo.sinInUser(email);
        UserDTO dto = UserDTO.toDTO(jpa);
        if(dto.getEmail().equals(email) && dto.getPw().equals(pw) ){
            System.out.println("맞음");
            session.setAttribute("userInfo",userRepo.sinInUser(email));
            return 1;
        }else{
            System.out.println("틀");
            return 0;
        }
    }
    @PostMapping("/signIn")
    @ResponseBody
    public int SignIn(String name, int age, String tel, String email, String pw, String emailNum,String number) throws IOException {
        UserJPA jpa = new UserJPA();
        System.out.println(number);
        System.out.println(emailNum);
        jpa = userRepo.sinInUser(email);
        UserDTO dto = UserDTO.toDTO(jpa);
        if(dto.getEmail()==null && emailNum==number){
            System.out.println("실행");
            jpa.setName(name);
            jpa.setAge(age);
            jpa.setTel(tel);
            jpa.setEmail(email);
            jpa.setPW(pw);
            userRepo.save(jpa);
            return 1;
        }else{
            return 0;
        }
    }
    @RequestMapping("/mail")
    @ResponseBody
    public String mailCheck(String email,Model model)throws Exception{
        String confirm = service.sendSimpleMessage(email);
        System.out.println(confirm);
        return confirm;
    }
    @GetMapping("/product")
    public String product(Model model){
        model.addAttribute("list",Imagerepo.ProductRender(1));
        return "product";
    }
    @PostMapping("/product")
    @ResponseBody
    public String product(Model model,int pageNo){
        System.out.println(pageNo);
        System.out.println(Imagerepo.ProductRender(pageNo));
        model.addAttribute("list",Imagerepo.ProductRender(pageNo));
        return "product";
    }
    @GetMapping("/productDetail")
    public String productDetail(){
        return "product-details";
    }

    @GetMapping("userPick")
    public String userPick(){
        return "userPick";
    }
    @GetMapping("userInfo")
    public String userInfo(HttpSession session,Model model){

        model.addAttribute("userInfo",session.getAttribute("userInfo"));
        return "userInfo";
    }

}
