package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dto.LoginInfoDto;
import util.DatabaseConnection;

/**
 * DB接続部分を記述するLoginInfoDaoクラス.
 * 
 * @author SIR遠藤
 */
public class LoginInfoDao {

    /**
     * 引数の値に一致するログイン情報テーブルを取得するメソッド.
     * 
     * @param loginId ログインID
     * @param password パスワード
     * @return login ログイン
     */
    public LoginInfoDto find(String loginId, String password) {

        // ログイン情報を保存する為の変数にヌルを代入
        LoginInfoDto login = null;

        // 事前準備(JAR配置を含む)
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            // JDBCドライバが見つからなかった場合の処理
            e.printStackTrace();
        }

        // データベースへの接続や切断を行う為の変数にヌルを代入
        Connection con = null;
        // SQLの送信を行う為の変数にヌルを代入
        PreparedStatement pstmt = null;
        // データベースから検索結果を受け取る為の変数にヌルを代入
        ResultSet rs = null;

        // データベースの接続
        DatabaseConnection databaseConnection = new DatabaseConnection();

        con = databaseConnection.dbConnection();

        try {
            // SQL送信処理
            // 送信すべきSQL文の雛形を準備
            pstmt = con
                    .prepareStatement("SELECT login_id FROM login_info WHERE login_id = ? AND password = ?");
            pstmt.setString(1, loginId);
            pstmt.setString(2, password);
            // 雛形に値を流し込みSQL文を組み立てて送信する
            rs = pstmt.executeQuery();

            // loginに値をセット
            // 行が見つかればtrueが返る
            while (rs.next()) {
                login = new LoginInfoDto();
                login.setLoginId(rs.getString("login_id"));

            }
        } catch (SQLException e) {
            // 接続や、SQL処理の失敗時の処理
            e.printStackTrace();
        } finally {
            // クローズ処理
            databaseConnection.closeResultSet(rs);
            databaseConnection.closePreparedStatement(pstmt);
            databaseConnection.closeConnection(con);
        }
        return login;
    }
}
