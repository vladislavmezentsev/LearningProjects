package main;

import main.model.Case;
import main.model.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    CaseRepository caseRepository;
    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Case> caseIterable = caseRepository.findAll();
        ArrayList<Case> caseArrayList = new ArrayList<>();
        for (Case eachCase : caseIterable) {
            caseArrayList.add(eachCase);
        }
        model.addAttribute("cases", caseArrayList);
        model.addAttribute("casesCount", caseArrayList.size());
        return "index";
    }
}
