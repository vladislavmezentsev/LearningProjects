import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company company1 = new Company();

        for (int i = 0; i <= 180; i++) {
            company1.hire(new Operator());
        }

        for (int i = 0; i <= 80; i++) {
            company1.hire(new Manager());
        }

        for (int i = 0; i <= 10; i++) {
            company1.hire(new TopManager());
        }

        List<Employee> topSalaryStaff = company1.getTopSalaryStaff(10);
        for (Employee e : topSalaryStaff) {
            System.out.println(e.getMonthSalary() + " рублей");
        }
        System.out.println(" ");

        List<Employee> lowSalaryStaff = company1.getLowestSalaryStaff(30);
        for (Employee e : lowSalaryStaff) {
            System.out.println(e.getMonthSalary() + " рублей");
        }

        for (int i = 0; i <= (company1.employees.size() / 2); i++) {
            company1.fire(company1.employees.get((int) (0 + Math.random() * company1.employees.size())));
        }
        System.out.println(" ");

        topSalaryStaff = company1.getTopSalaryStaff(10);
        for (Employee e : topSalaryStaff) {
            System.out.println(e.getMonthSalary() + " рублей");
        }
        System.out.println(" ");

        lowSalaryStaff = company1.getLowestSalaryStaff(30);
        for (Employee e : lowSalaryStaff) {
            System.out.println(e.getMonthSalary() + " рублей");
        }
    }
}