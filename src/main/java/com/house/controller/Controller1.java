package com.house.controller;

import com.house.common.Result;
import com.house.dto.LoginUser;
import com.house.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Tim
 * @Date 2020/11/13 10:36
 */
@RestController
public class Controller1 {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/exep")
    public Object exep() {
        int a = 1/0;
        return "error";
    }

    @GetMapping(value = "/findAll")
    public Result login(){
        //int a = 1/0;
        LoginUser loginUser = new LoginUser();
        Result data = userInfoService.loginOnSystem(loginUser);
        System.out.println("=========data:" + data.getMessage());
        return userInfoService.loginOnSystem(loginUser);
    }


//    public static void main(String[] args) {
//        // 用我们的配置文件来启动一个 ApplicationContext
//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
//
//        System.out.println("context 启动成功");
//
//        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
//        MessageService messageService = context.getBean(MessageService.class);
//        // 这句将输出: hello world
//        System.out.println(messageService.getMessage());
//    }

//    public static void main(String[] args) {
//
//        String json="{code:'1000', id:1, data : [{id:1, name: 'MBHK'}, {id:2, name: 'MBAPP'}] }";
//        JSONObject object = JSONObject.parseObject(json);
//        System.out.println(object.get("code")+"/ "+object.get("data"));
//        JSONArray list = JSONObject.parseArray(object.getString("data"));
//        List<String> nameList= new ArrayList<>();
//        for (Object s : list) {
//            JSONObject obj = JSONObject.parseObject(s.toString());
//            nameList.add(obj.getString("name"));
//        }
//        System.out.println(nameList);
//    }




}
