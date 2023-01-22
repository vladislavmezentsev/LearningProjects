package main;

import main.model.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Case;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CaseController {

    @Autowired
    private CaseRepository caseRepository;

    @GetMapping("/")
    public List<Case> list() {
        Iterable<Case> caseIterable = caseRepository.findAll();
        ArrayList<Case> caseArrayList = new ArrayList<>();
        for (Case eachCase : caseIterable) {
            caseArrayList.add(eachCase);
        }
        return caseArrayList;
    }

    @PostMapping("/")
    public int add(Case newCase) {
        Case addedCase = caseRepository.save(newCase);
        return addedCase.getId();
    }

    @GetMapping("//{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<Case> caseOptional = caseRepository.findById(id);
        if (!caseOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(caseOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("//{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Optional<Case> caseOptional = caseRepository.findById(id);
        if (!caseOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        caseRepository.delete(caseOptional.get());
        return new ResponseEntity(caseOptional, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public void deleteCases() {
        caseRepository.deleteAll();
    }

    @PutMapping("//{id}")
    public ResponseEntity put(@PathVariable int id, Case newCase) {
        Optional<Case> optionalCase = caseRepository.findById(id);
        if (!optionalCase.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Case oldCase = optionalCase.get();
        oldCase.setId(newCase.getId());
        oldCase.setName(newCase.getName());
        oldCase.setYear(newCase.getYear());
        return new ResponseEntity(caseRepository.save(oldCase), HttpStatus.OK);
    }

}
