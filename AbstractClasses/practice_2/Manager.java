import java.text.DecimalFormat;

public class Manager implements Employee {
    Company company = new Company();
    public double managerSalary = 80000d + Math.random() * 100000d;
    public double managerSalaryBonus = company.getIncome() * 0.05;

    public double getManagerSalary() {
        return managerSalary;
    }

    public void setManagerSalary(double managerSalary) {
        this.managerSalary = managerSalary;
    }

    double scale = Math.pow(10, 3);
    double result = Math.ceil(((managerSalary + managerSalaryBonus) * scale) / scale);

    @Override
    public double getMonthSalary() {
        return result;
    }
}
