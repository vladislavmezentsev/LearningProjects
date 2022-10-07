public class Operator implements Employee {
    public double operatorSalary = 40000d + Math.random() * 60000d;

    public double getOperatorSalary() {
        return operatorSalary;
    }

    public void setOperatorSalary(double operatorSalary) {
        this.operatorSalary = operatorSalary;
    }

    Company company = new Company();
    double scale = Math.pow(10, 3);

    @Override
    public double getMonthSalary() {
        return Math.ceil((operatorSalary * scale) / scale);
    }
}
