package com.smart.store.controller.v1;

import com.smart.store.service.SignService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/sign")
@Api(value = "Sign_Op")
public class SignController {

    @Autowired
    SignService signService;

    @PostMapping("/{userId}")
    public int sign(@PathVariable String userId) {
        return signService.sign(userId);
    }

    @GetMapping("/{year}/{month}/{userId}")
    public List<Integer> getSignDaysByMonthAndYear(@PathVariable int year, @PathVariable int month, @PathVariable String userId) {
        return signService.getSignDaysByMonth(year, month, userId);
    }
}
