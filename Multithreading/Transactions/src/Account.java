public class Account {

    public Account(long money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
    }

    private long money;
    private String accNumber;

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }
}
