package spring.db.main;

import java.sql.*;

public class mainDB {

    private static final String  URL = "jdbc:mysql://localhost:3306/mydbtest?useUnicode=true&serverTimezone=UTC";
    private static final String  USERNAME = "root";
    private static final String  PASSWORD = "Drauker111";




    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement = connection.createStatement()) {

            ResultSet res2 = statement.executeQuery("SELECT * from animal");

            while (res2.next()) {
                String id = res2.getString(2);
                System.out.println(id);

            }

//            System.out.println(res2);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
