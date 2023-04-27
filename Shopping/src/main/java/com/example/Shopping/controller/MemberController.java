package com.example.Shopping.controller;

import com.example.Shopping.dto.MemberDto;
import com.example.Shopping.entity.Member;
import com.example.Shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/sign")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/sign-up")
    public String signUpView() {
        return "sign/sign-up";
    }

//    @GetMapping("/sign-in")
//    public String singInView() {
//        return "sign/sign-in";
//    }

    /**
     * 회원가입
     */
    @PostMapping("/register")
    public ResponseEntity<?> saveMember(@RequestBody MemberDto memberDto, HttpServletResponse response) {
        log.info("memberDto: {}", memberDto);
        try {
            Member saveMember = memberService.saveMember(memberDto);
            response.sendRedirect("/sign/sign-in");
            return ResponseEntity.ok(saveMember);
        } catch (DuplicateKeyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Redirect Error");
        }
    }

    /**
    * 로그인
    */
    @GetMapping("/sign-in")
    public String login() {
        return "sign/sign-in";
    }

    @GetMapping("/username")
    @ResponseBody
    public String currentUserName(Principal principal) {
        log.info("username: {}", principal);
        return principal.getName();
    }

    @GetMapping("/sign-in/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        log.info("model: {}", model);
        return "sign/sign-in";
    }

//    @PostMapping("/sign-in")
//    public String login(@RequestParam String id, @RequestParam String password) {
//        // 로그인 처리 로직 작성
//        return "redirect:/"; // 로그인 후 메인 페이지로 이동
//    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody MemberDto memberDto) {
//        String id = memberDto.getId();
//        String password = memberDto.getPassword();
//        log.info("id: {}", id);
//        log.info("password: {}", password);
//        if (memberService.isPasswordMatch(id, password)) {
//            return ResponseEntity.ok("success");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디나 비밀번호가 일치하지 않습니다.");
//        }
//    }

    /**
     * 실시간 아이디 체크
     */
    @PostMapping("/id_check")
    public ResponseEntity<Map<String, Object>> checkId(@RequestParam String userId) {
        Map<String, Object> response = new HashMap<>();
        boolean isExists = memberService.isIdExists(userId);
        if (isExists) {
            response.put("message", "아이디가 이미 존재합니다.");
            response.put("result", "fail");
        } else {
            response.put("message", "사용 가능한 아이디입니다.");
            response.put("result", "success");
        }
        return ResponseEntity.ok(response);
    }
}
