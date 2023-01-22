package main;

import main.model.Case;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {
    public static AtomicInteger currentId = new AtomicInteger(1);
    private static HashMap<Integer, Case> cases = new HashMap<>();

    public static List<Case> getAllCases() {
        ArrayList<Case> caseArrayListList = new ArrayList<>();
        caseArrayListList.addAll(cases.values());
        return caseArrayListList;
    }

    public static int addCase(Case newCase) {
        int id = currentId.incrementAndGet();
        newCase.setId(id);
        cases.put(id, newCase);
        return id;
    }

    public static Case getCase(int caseId) {
        if (cases.containsKey(caseId)) {
            return cases.get(caseId);
        }
        return null;
    }

    public static Case deleteCase(int id) {
        if (cases.containsKey(id)) {
            currentId.decrementAndGet();
            return cases.remove(id);
        }
        return null;
    }

    public static void deleteAllCases() {
        currentId.getAndSet(0);
        cases.clear();
    }


    public static Case updateCase(Case oldCase, Case newCase) {
        if (cases.containsValue(oldCase)) {
            return cases.replace(oldCase.getId(), newCase);
        }
        return null;
    }

}
