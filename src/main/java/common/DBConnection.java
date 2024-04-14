package common;

import java.sql.*;

public class DBConnection {
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnect() {

        final String dbPath = "C:/제로베이스/wifiMission/wifi_mission";
        final String fileLocation = dbPath + "/wifi_db.db";

        // SQLite connection string
        String url = "jdbc:sqlite:" + fileLocation;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public static void close(ResultSet rs, PreparedStatement preparedStatement, Connection connection) {

        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void connect() {
//        Connection conn = null;
//        try {
//            // SQLite 데이터베이스 파일 경로
//            String url = "jdbc:sqlite:C:/제로베이스/wifiMission/WifiMission/wifi_db.db"; // 실제 경로로 변경
//            // 데이터베이스 연결
//            conn = DriverManager.getConnection(url);
//
//            String sql = "SHOW TABLES";
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//
//            System.out.println(rs.getString(rs.getString(1)));
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//    }
}