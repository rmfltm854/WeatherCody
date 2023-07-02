package com.example.weathercody.Controller;

import com.example.weathercody.Crawler.ChildURL;
import com.example.weathercody.Crawler.CrollerMan;
import com.example.weathercody.DTO.UserDTO;
import com.example.weathercody.JPA.*;
import com.example.weathercody.OpenAPI.WeatherAPI;
import com.example.weathercody.Repository.*;
import com.example.weathercody.Service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MainController {
    private final UserRepository userRepo;

    private final ProductsRepository productsRepository;

    private final ProductRepository productRepository;

    private final WomanProductsRepository WproductsRepository;

    private final WomanProductRepository WproductRepository;
    private final EmailService service;

    private final ManBoardRepository manBoardRepo;

    private final WomanBoardRepository womanBoardRepo;
    WeatherAPI api = new WeatherAPI();
    @GetMapping("/")
    public String Index(Model model, HttpSession session, HttpServletRequest request){
        String weather = "Rain";
        System.out.println("날씨" + weather);
        System.out.println(weather);
        if(weather.equals("Rain")){
            model.addAttribute("MRank",productRepository.getRainRanking());//남자 랭킹 얻어오는 메소드
            model.addAttribute("WMrank",WproductRepository.getRainRanking());//여자 랭킹 얻어오는 메소드
        }else{
            model.addAttribute("MRank",productRepository.getRanking());//남자 랭킹 얻어오는 메소드
            model.addAttribute("WMrank",WproductRepository.getRanking());//여자 랭킹 얻어오는 메소드
        }
        System.out.println(WproductRepository.getRainRanking());
        return "index";
    }

    @GetMapping("/login")//login페이지로 보내주는 메소드
    public String login(){
        return "login";
    }

    @PostMapping("/loginForm")//로그인 유효성검사 with ajax
    @ResponseBody
    public int login(String email, String pw, HttpSession session){
        System.out.println(email);
        System.out.println(pw);
        UserJPA jpa = userRepo.sinInUser(email);
        UserDTO dto = UserDTO.toDTO(jpa);
        if(dto.getEmail().equals(email) && dto.getPw().equals(pw) ){
            System.out.println("맞음");
            session.setAttribute("userInfo",userRepo.sinInUser(email));
            session.setAttribute("userName",jpa.getName());
            return 1;
        }else{
            System.out.println("틀");
            return 0;
        }
    }

    @GetMapping("/logout")//세션 삭제후 main페이지로 return
    public String logOut(HttpSession session, HttpServletRequest request){
        session = request.getSession();
        System.out.println(session.getAttribute("userInfo"));
        session.invalidate();
        return "redirect:/";
    }
    @PostMapping("/signIn")//회원 가입 로직
    @ResponseBody
    public int SignIn(String name, int age, String tel, String email, String pw, String emailNum,String number) throws IOException {
        System.out.println(emailNum);
        UserDTO dto = new UserDTO();
        try{
            UserJPA jpa = new UserJPA();
            jpa = userRepo.sinInUser(email);
            dto = UserDTO.toDTO(jpa);
             return 0;
        }catch (NullPointerException e){
            UserJPA jpa = new UserJPA();
            System.out.println("실행");
            jpa.setName(name);
            jpa.setAge(age);
            jpa.setTel(tel);
            jpa.setEmail(email);
            jpa.setPW(pw);
            userRepo.save(jpa);
            return 1;
        }
    }
    @RequestMapping("/mail")//회원가입 중 email인증 메소드
    @ResponseBody
    public String mailCheck(String email,Model model)throws Exception{
        String confirm = service.sendSimpleMessage(email);
        System.out.println(confirm);
        return confirm;
    }
    @GetMapping("/product")//상품 전체
    public String product(Model model, @RequestParam(value = "pageNo",required = false
    ,defaultValue = "0") int pageNo, @RequestParam(value = "serverNum",required = false
            ,defaultValue = "1") int serverNum, @RequestParam(value = "gender",required = false
            ,defaultValue = "남자") String gender){
        String weather = "Rain";
        System.out.println(gender);
        String Sergender = "남자";
        Sergender = gender;
        if(gender.equals("남자")){//남자
            if(weather.equals("Rain")){
                model.addAttribute("list",productRepository.ProductRainRender(pageNo));
                model.addAttribute("pageNo",serverNum);
                model.addAttribute("gender",Sergender);
            }else{
                model.addAttribute("list",productRepository.ProductRender(pageNo));
                model.addAttribute("pageNo",serverNum);
                model.addAttribute("gender",Sergender);
            }
        }else{//여자
            if(weather.equals("Rain")){
                model.addAttribute("list",WproductRepository.ProductRainRender(pageNo));
                model.addAttribute("pageNo",serverNum);
                model.addAttribute("gender",Sergender);
            }else{
                model.addAttribute("list",WproductRepository.ProductRender(pageNo));
                model.addAttribute("pageNo",serverNum);
                model.addAttribute("gender",Sergender);
            }
        }
        System.out.println(pageNo);
        System.out.println(serverNum);
        System.out.println(productRepository.ProductRender(pageNo));
        return "product";
    }
    @GetMapping("/productDetail")//상품 장제정보
    public String productDetail(Model model, @RequestParam(value="idx",required = false,defaultValue = "0") int idx, @RequestParam(value = "gender",
    required = false,defaultValue = "남자") String gender){
//        List<List<ProductsEntity>> list = productRepository.getProductDetail(idx);
        if(gender.equals("남자")){
            String ProductUrl = productRepository.getProductUrl(idx);//전체코디 사진
            List<ProductsEntity>  list = productsRepository.findAllByProductIdx(idx);//전체코디사진에 따른 개별 상품 링크
            List<ManBoardEntity> review = manBoardRepo.GetReview(idx);//라뷰가져오는 메소드
            model.addAttribute("productUrl",ProductUrl);
            model.addAttribute("list",list);
            model.addAttribute("pidx",idx);
            model.addAttribute("gender",gender);
            model.addAttribute("review",review);
        }else {
            String ProductUrl = WproductRepository.getProductUrl(idx);
            List<WomanProductsEntity>  list = WproductsRepository.findAllByProductIdx(idx);
            List<WomanBoardEntity> review = womanBoardRepo.GetReview(idx);
            model.addAttribute("productUrl",ProductUrl);
            model.addAttribute("list",list);
            model.addAttribute("pidx",idx);
            model.addAttribute("gender",gender);
            model.addAttribute("review",review);
        }
       System.out.println(idx + "idx값");
        return "product-details";
    }

    @GetMapping("userPick")
    public String userPick(){
        return "userPick";
    }
    @GetMapping("userInfo")//회원 정보
    public String userInfo(HttpSession session,Model model){

        model.addAttribute("userInfo",session.getAttribute("userInfo"));
        return "userInfo";
    }

//    @GetMapping("/productLink")
//    public void testMapping(){
//        ProductEntity entity = new ProductEntity();
//        entity.setImage("이미지주소");
//        entity.setLikeCount(3);
//        entity.setName("여름옷2번");
//        productRepository.save(entity);
//        productRepository.Dropindex();
//        for(int i = 0;i<3;i++){
//            ProductsEntity ProEntity = new ProductsEntity();
//            ProEntity.setClothsImage("옷이미지주소");
//            ProEntity.setClothsLink("옷 url");
//            ProEntity.setProduct(entity);
//            productsRepository.save(ProEntity);
//        }
//        System.out.println("완료");
//    }

    @GetMapping("/testCrawler")//크롤러
    public void testCrawl() throws IOException {
        CrollerMan man = new CrollerMan();
        ChildURL url = new ChildURL();
        List<HashMap<String,String>> result = man.setData();
        List<HashMap<String,String>> result2 = man.setData();
        for(int i = 0;i<result.size();i++){
            WomanProductEntity entity = new WomanProductEntity();
            entity.setImage(result.get(i).get("url"));
            entity.setName(result.get(i).get("name"));
            entity.setLikeCount(0);
            entity.setWeather("비");
            entity.setProductImageURL(result.get(i).get("productDetailURL"));
            String resultU = result.get(i).get("productDetailURL");
            WproductRepository.save(entity);
            result2 = url.getProductDetail2(resultU);
            for(int j =0;j<result2.size();j++){
                WomanProductsEntity Pentity = new WomanProductsEntity();
                Pentity.setClothsImage("https:"+result2.get(j).get("detailImage"));
                Pentity.setClothsLink("https://www.musinsa.com/"+result2.get(j).get("detailLink"));
                Pentity.setProduct(entity);
                WproductsRepository.save(Pentity);
            }

        }
    }

    @GetMapping("/Count")//좋아요기능 구현 with ajax
    @ResponseBody
    public int likeCount(@RequestParam(value = "idx",required = false
            ,defaultValue = "0") int idx, @RequestParam(value = "gender",
            required = false,defaultValue = "남자") String gender){
        int proudctIdx;
        System.out.println(idx);
        if(gender.equals("남자")){
            proudctIdx = productRepository.howLike(idx);
            System.out.println(proudctIdx);
            int num = productRepository.UpLike(proudctIdx + 1,idx);
            System.out.println(num);
        }else {
            proudctIdx = WproductRepository.howLike(idx);
            System.out.println(proudctIdx);
            int num = WproductRepository.UpLike(proudctIdx + 1,idx);
            System.out.println(num);
        }
        return proudctIdx+1;
    }

    @PostMapping("/ProductReview")//리뷰기능 구현 with ajax
    @ResponseBody
    public int Review(@RequestParam(value = "idx",required = false
            ,defaultValue = "0") int idx, @RequestParam(value = "review",required = false,defaultValue = "none") String review,
                      @RequestParam(value = "gender",required = false,defaultValue = "남자") String gender){
        ManBoardEntity mantity = new ManBoardEntity();
        WomanBoardEntity wentity = new WomanBoardEntity();
        System.out.println("productReview"+idx);
        System.out.println("productReview"+review);
        System.out.println("productReview"+gender);
        if(gender.equals("남자")){
            mantity.setReview(review);
            mantity.setProduct_idx(idx);
            manBoardRepo.save(mantity);
        }else{
            wentity.setReview(review);
            wentity.setProduct_idx(idx);
        }
        return 1;
    }
}
