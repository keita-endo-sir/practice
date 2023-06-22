package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB接続部分を記述するDatabaseConnectionクラス.
 * 
 * @author SIR遠藤
 */
public class DatabaseConnection {

    /**
     * 変数に接続先DBを代入.
     */
    private static final String URL = "jdbc:sqlite:C:/develop/workspace/java/EmployeeManagementSystem/data/management.sqlite3";

    /**
     * DBへ接続するメソッド.
     * 
     * @return con
     */
    public Connection dbConnection() {

        // 事前準備(JAR配置を含む)
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            // JDBCドライバが見つからなかった場合の処理
            e.printStackTrace();
        }
        // データベースへの接続や切断を行う為の変数にヌルを代入
        Connection con = null;
        try {
            // JDBC URLを指定
            con = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            // 接続やSQL処理失敗時の処理
            e.printStackTrace();
        }
        return con;
    }

    /**
     * ResultSetを切断する.
     * 
     * @param rs ResultSet
     */
    public void closeResultSet(ResultSet rs) {

        // クローズ処理
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // 切断失敗時の処理
                e.printStackTrace();
            }
        }
    }

    /**
     * PreparedStatementを切断する.
     * 
     * @param pstmt PreparedStatement
     */
    public void closePreparedStatement(PreparedStatement pstmt) {

        // クローズ処理
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                // 切断失敗時の処理
                e.printStackTrace();
            }
        }
    }

    /**
     * Connectionを切断する.
     * 
     * @param con Connection
     */
    public void closeConnection(Connection con) {

        // クローズ処理
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // 切断失敗時の処理
                e.printStackTrace();
            }
        }

    }
}
