import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "dkflsql";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select\n" +
                    "\t`course_name`, (count(`subscription_date`) / 8) averageSubs\n" +
                    "from \t\n" +
                    "\t`purchaselist`\n" +
                    "where \n" +
                    "    (`subscription_date` between '2018-01-01 00:00:00' and '2018-08-31 23:59:59')\n" +
                    "group by\n" +
                    "\t`course_name`;");
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String averageSubs = resultSet.getString("averageSubs");
                System.out.println("Среднее количество покупок в месяц для курса " + courseName + " за 2018 год - "
                        + averageSubs);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
