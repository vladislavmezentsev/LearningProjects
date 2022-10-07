import java.util.*;

public class Company {
    private double income = 115000d + Math.random() * 140000d;

    ArrayList<Employee> employees = new ArrayList<>();

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void hireAll(Collection<Employee> employeeList) {
        employees.addAll(employeeList);
    }

    public void fire(Employee employee) {
        employees.remove(employee);
    }

    List<Employee> getTopSalaryStaff(int count) {
        if (count <= 0 || count > employees.size()) {
            System.out.println("¬ведено некорректное число");
        } else {
            EmployeeComparator employeeComparator = new EmployeeComparator();
            Collections.sort(employees, employeeComparator);
            Collections.reverse(employees);
        }
        return new ArrayList<>(employees.subList(0, count));
    }

    List<Employee> getLowestSalaryStaff(int count) {
        if (count <= 0 || count > employees.size()) {
            System.out.println("¬ведено некорректное число");
        } else {
            EmployeeComparator employeeComparator = new EmployeeComparator();
            Collections.sort(employees, employeeComparator);
        }
        return new ArrayList<>(employees.subList(0, count));
    }
}
