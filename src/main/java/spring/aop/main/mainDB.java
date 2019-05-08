package spring.aop.main;

import java.sql.*;

public class mainDB {

    private static final String  URL = "jdbc:mysql://localhost:3306/mydbtest?useUnicode=true&serverTimezone=UTC";
    private static final String  USERNAME = "root";
    private static final String  PASSWORD = "Drauker111";


    public static void main(String[] args) {
        try {

            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement = connection.createStatement()) {

            statement.execute("INSERT into animal(anim_name, anim_col) VALUES ('name', 'desc')");
            int res = statement.executeUpdate("UPDATE animal SET anim_name = 'tiger' where id=1; ");
            ResultSet res2 = statement.executeQuery("SELECT * from animal");
            statement.addBatch("INSERT into animal(anim_name, anim_col) VALUES ('volf', 'desc')");
            statement.addBatch("INSERT into animal(anim_name, anim_col) VALUES ('pantera', 'desc')");
            statement.addBatch("INSERT into animal(anim_name, anim_col) VALUES ('leo', 'desc')");
            statement.executeBatch();
            statement.clearBatch();

//            System.out.println(res2);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
