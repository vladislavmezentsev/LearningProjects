public class TopManager implements Employee {
    public double topManagerSalary = 120000d + Math.random() * 150000d;
    public double topManagerSalaryBonus = topManagerSalary * 1.5;

    Company company = new Company();

    public double getTopManagerSalary() {
        return topManagerSalary;
    }

    public void setTopManagerSalary(double topManagerSalary) {
        this.topManagerSalary = this.topManagerSalary;
    }

    double scale = Math.pow(10, 3);

    @Override
    public double getMonthSalary() {
        if (company.getIncome() >= 10_000_000d) {
            topManagerSalary = Math.ceil(((topManagerSalary + topManagerSalaryBonus) * scale) / scale);

        }
        return Math.ceil((topManagerSalary * scale) / scale);
    }
}
