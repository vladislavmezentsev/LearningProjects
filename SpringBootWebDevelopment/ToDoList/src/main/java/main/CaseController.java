package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Case;

import java.util.List;

@RestController
public class CaseController {

    @GetMapping("/")
    public List<Case> list() {
        return Storage.getAllCases();
    }

    @PostMapping("/")
    public int add(Case newCase) {
        return Storage.addCase(newCase);
    }

    @GetMapping("//{id}")
    public ResponseEntity get(@PathVariable int id) {
        Case caseToDo = Storage.getCase(id);
        if (caseToDo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(caseToDo, HttpStatus.OK);
    }

    @DeleteMapping("//{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Case caseToDelete = Storage.getCase(id);
        if (caseToDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Storage.deleteCase(caseToDelete.getId());
        return new ResponseEntity(caseToDelete, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public static void deleteCases() {
        Storage.deleteAllCases();
    }

    @PutMapping("//{id}")
    public static ResponseEntity put(@PathVariable int id, Case newCase) {
        Case oldCase = Storage.getCase(id);
        if (oldCase == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(Storage.updateCase(oldCase, newCase), HttpStatus.OK);
    }

}
