package com.smart.store.controller.v1;

import com.smart.store.service.VacationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/vacation")
@Api(value = "Vacation_Op")
public class VacationController {

    @Autowired
    VacationService vacationService;

    @PostMapping("/")
    public void setVacation() {
        vacationService.setVacation();
    }

    @GetMapping("/{year}/{month}")
    public List<Integer> getVacationByYearAndMonth(@PathVariable int year, @PathVariable int month) {
        return vacationService.getVacation(year, month);
    }

    @GetMapping("/between")
    public int getDaysBetween() {
        return 0;
    }
}
