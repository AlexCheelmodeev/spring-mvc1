package ru.maxima.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maxima.springmvc.dao.PersonDAO;

@Controller
@RequestMapping("/test-batch-update")

public class BatchController {

    private final PersonDAO personDAO;

    public BatchController( PersonDAO personDAO){
        this.personDAO = personDAO;
    }
@GetMapping()
    public String index(){
        return "batch/index";
    }
@GetMapping("/without")
    public String withOutBatchUpdate(){
        personDAO.testWithOutBatch();
        return "redirect:/people";
    }

    @GetMapping("/with")
    public String withBatchUpdate(){
        personDAO.testWithBatch();
        return "redirect:/people";
    }
}
