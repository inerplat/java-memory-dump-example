package com.example.demo.controller;

import com.example.demo.client.CommonClient;
import com.example.demo.service.UserService;
import com.example.demo.util.LargeMemoryUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;

@RestController
class RootController {
    private final CommonClient commonClient;
    private final UserService userService;

    private RootController(CommonClient commonClient, UserService userService) {
        this.commonClient = commonClient;
        this.userService = userService;
    }


    @GetMapping("/memory/static")
    public String memoryStatic() {
        LargeMemoryUtil.increase();
        return Arrays.toString(LargeMemoryUtil.generate());
    }

    @GetMapping("/client/cycle/{name}")
    public String clientCycle(@PathVariable String name) throws InterruptedException {
        String user = userService.update(name);
        var client = commonClient.clientCycle(name);
        client.subscribe();
        return user;
    }
}