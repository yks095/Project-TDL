package com.kiseok.controller;

import com.kiseok.domain.User;
import com.kiseok.domain.UserDTO;
import com.kiseok.repository.UserRepository;
import com.kiseok.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Random;


@Controller
@RequestMapping("/toDoList")
public class FindController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ToDoListService toDoListService;

    @Autowired
    JavaMailSender javaMailSender;

    Random random = new Random();

    String i = String.valueOf(random.nextInt(1000000));

    @GetMapping("/findID")
    public String findID()    {
        return "/toDoList/findID";
    }

    @GetMapping("/findPW")
    public String findPW()    {
        return "/toDoList/findPW";
    }


    @PostMapping("/findID")
    public String findIDPost()    {
        return "redirect:/toDoList/findID";
    }

    @PostMapping("/findPW")
    public String findPWPost()    {
        return "redirect:/toDoList/findPW";
    }

    @PostMapping("/api/findID")
    public ResponseEntity<?> checkEmail(@RequestBody Map<String, String> map) {

        User user = userRepository.findUserByEmail(map.get("email"));

        String mail = user.getEmail();

        if ("".equals(mail)) {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
        else {
            String findId = user.getId();

            SimpleMailMessage smm = new SimpleMailMessage();

            smm.setFrom("rltjr219@gmail.com");
            smm.setTo(mail);
            smm.setSubject("from ToDoList_Yang");
            smm.setText("Your Account : " + findId);

            javaMailSender.send(smm);

            return new ResponseEntity<>("{}", HttpStatus.OK);
        }
    }

    @PostMapping("/api/findPW")
    public ResponseEntity<?> sendCode(@RequestBody Map<String, String> map) {

        User user = userRepository.findUserByEmail(map.get("email"));

        String mail = user.getEmail();

        if ("".equals(mail)) {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
        else {

            SimpleMailMessage smm = new SimpleMailMessage();

            smm.setFrom("rltjr219@gmail.com");
            smm.setTo(mail);
            smm.setSubject("from ToDoList_Yang");
            smm.setText("Code : " + i);
            System.out.println("보낸 코드" + i);
            javaMailSender.send(smm);

            return new ResponseEntity<>("{}", HttpStatus.OK);
        }
    }

    @PostMapping("/api/checkCode")
    public ResponseEntity<?> checkCode(@RequestBody Map<String, String> map) {

        String code = map.get("code");
        System.out.println("입력 코드" + code);
        System.out.println("실제 코드" + i);

        if (code.equals(i)) {
            return new ResponseEntity<>("{}", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/changePW")
    public ResponseEntity<?> changePW(@RequestBody Map<String, String> map, UserDTO userDTO) {

        User user = userRepository.findUserByEmail(map.get("email"));
        String firstPW = map.get("firstPW");
        String secondPW = map.get("secondPW");

        if(firstPW.equals(secondPW))    {
            System.out.println(firstPW);
            return new ResponseEntity<>("{}", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
    }
}


