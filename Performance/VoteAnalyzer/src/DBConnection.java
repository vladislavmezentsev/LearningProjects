import java.sql.*;

public class DBConnection {

    private static Connection connection;

    private static String dbName = "voteanalyzer";
    private static String dbUser = "root";
    private static String dbPass = "dkflsql";
    private static int counter = 0;

    private static StringBuilder insertQuery = new StringBuilder();

    public static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(("jdbc:mysql://localhost:3306/" + dbName +"?useSSL=false"),
                        dbUser, dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id))");
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() throws SQLException
    {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO voter_count(name, birthDate, `count`) VALUES").append(insertQuery.toString());
        DBConnection.getConnection().createStatement().execute(sql.toString());
        insertQuery.delete(0, insertQuery.length());
        counter++;
    }

    public static void countVoter(String name, String birthDay)
    {
        birthDay = birthDay.replace('.', '-');
        insertQuery.append((insertQuery.length() == 0 ? "" : ",") + " ('" + name + "', '" + birthDay + "', 1) ");
    }

    public static void printVoterCounts() throws SQLException
    {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next())
        {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }
}