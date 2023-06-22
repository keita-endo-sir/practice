package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.CompanyInfoDto;
import util.DatabaseConnection;

/**
 * DB接続部分を記述するCompanyInfoDaoクラス.
 * 
 * @author SIR遠藤
 */
public class CompanyInfoDao {

    /**
     * 会社情報の値を入れるリストメソッド.
     * 
     * @return companyList 会社情報リスト
     */
    public List<CompanyInfoDto> findAll() {

        // 会社情報リストの初期化
        List<CompanyInfoDto> companyList = new ArrayList<>();

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

            // トランザクション処理開始（オートコミットを解除）
            con.setAutoCommit(false);

            // SQL送信処理
            StringBuilder sql = new StringBuilder();

            // 送信すべきSQL文の雛形を準備
            sql.append("SELECT company_id, ");
            sql.append("company_name, ");
            sql.append("abbreviation ");
            sql.append("FROM company_info");

            // StringBuilder型のsqlをString型に格納
            String StrEmployeeInfoSql = sql.toString();

            // company_infoのsql文を受け取る
            pstmt = con.prepareStatement(StrEmployeeInfoSql);

            // 雛形に値を流し込みSQL文を組み立てて送信する
            rs = pstmt.executeQuery();

            // companyに値をセット
            // 行が見つかればtrueが返る
            while (rs.next()) {
                // レコードの数だけインスタンスを作成
                CompanyInfoDto company = new CompanyInfoDto();
                company.setCompanyId(rs.getString("company_id"));
                company.setCompanyName(rs.getString("company_name"));
                company.setAbbreviation(rs.getString("abbreviation"));

                // リストに格納
                companyList.add(company);
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
        return companyList;
    }
}
