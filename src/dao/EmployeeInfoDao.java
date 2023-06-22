package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.EmployeeInfoDto;
import enums.CommissioningStatusEnum;
import enums.StatusEnum;
import enums.DepartmentEnum;

import util.CommonUtil;
import util.DatabaseConnection;

/**
 * DB接続部分を記述するEmployeeInfoDaoクラス.
 * 
 * @author SIR遠藤
 */
public class EmployeeInfoDao {

    /**
     * 社員情報の値を入れるリストメソッド.
     * 
     * @return empList 社員情報リスト
     */
    public List<EmployeeInfoDto> findAll() {

        // 社員情報リストの初期化
        List<EmployeeInfoDto> empList = new ArrayList<>();

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
            sql.append("SELECT empsta.company_info_id, ");
            sql.append("cominfo.abbreviation, ");
            sql.append("empsta.department, ");
            sql.append("empinfo.name, ");
            sql.append("empinfo.name_hiragana, ");
            sql.append("empinfo.birthday, ");
            sql.append("empsta.business_manager, ");
            sql.append("empsta.commissioning_status, ");
            sql.append("empinfo.employee_id, ");
            sql.append("empsta.hire_date ");
            sql.append("FROM company_info cominfo, ");
            sql.append("employee_info empinfo, ");
            sql.append("employee_state empsta ");
            sql.append("WHERE empsta.company_info_id = cominfo.company_id ");
            sql.append("AND empsta.employee_info_id = empinfo.employee_id;");

            // StringBuilder型のsqlをString型に格納
            String StrEmployeeInfoSql = sql.toString();

            // employee_infoのsql文を受け取る
            pstmt = con.prepareStatement(StrEmployeeInfoSql);

            // 雛形に値を流し込みSQL文を組み立てて送信する
            rs = pstmt.executeQuery();
            
            //CommonUtilの変数を定義
            CommonUtil common = new CommonUtil();
           
            // empDtoに値をセット
            // 行が見つかればtrueが返る
            while (rs.next()) {
                // レコードの数だけインスタンスを作成
                EmployeeInfoDto empDto = new EmployeeInfoDto();
                empDto.setCompanyInfoId(rs.getString("company_info_id"));
                empDto.setBirthday(common.sdfDay(rs.getString("birthday")));
                empDto.setEmployeeId(rs.getString("employee_id"));
                empDto.setAbbreviation(rs.getString("abbreviation"));
                empDto.setDepartment(DepartmentEnum.getDepartment(rs.getString("department")));
                empDto.setName(rs.getString("name"));
                empDto.setNameHiragana(rs.getString("name_hiragana"));
                empDto.setBusinessManager(rs.getString("business_manager"));
                empDto.setHireDate(common.sdfDay(rs.getString("hire_date")));
                empDto.setCommissioningStatus(CommissioningStatusEnum.getCommissioningStatus(rs.getString("commissioning_status")));
                // 年齢を計算
                empDto.setAge(common.calcAge((rs.getString("birthday"))));

                // リストに格納
                empList.add(empDto);
            }
        } catch (SQLException e) {
            // 接続や、SQL処理の失敗時の処理
            e.printStackTrace();
        } finally {
            databaseConnection.closeResultSet(rs);
            databaseConnection.closePreparedStatement(pstmt);
            databaseConnection.closeConnection(con);
        }
        return empList;
    }

    /**
     * 社員IDに一致する社員情報を取得するメソッド.
     * 
     * @param employeeId 社員ID
     * @return empDetail 社員情報
     */
    public EmployeeInfoDto findOne(String employeeId) {
        // 社員情報を保存する為の変数にヌルを代入
        EmployeeInfoDto empDetail = null;

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
            sql.append("SELECT empinfo.employee_id, ");
            sql.append("empinfo.name, ");
            sql.append("empinfo.name_hiragana, ");
            sql.append("empinfo.birthday, ");
            sql.append("empinfo.sex, ");
            sql.append("empinfo.mail_address, ");
            sql.append("empinfo.telephone_number, ");
            sql.append("empsta.company_info_id, ");
            sql.append("empsta.business_manager, ");
            sql.append("empsta.department, ");
            sql.append("empsta.commissioning_status, ");
            sql.append("empsta.hire_date, ");
            sql.append("empsta.retire_date, ");
            sql.append("empsta.status ");
            sql.append("FROM employee_info empinfo ");
            sql.append("INNER JOIN employee_state empsta ");
            sql.append("ON empsta.employee_info_id = empinfo.employee_id ");
            sql.append("WHERE employee_id = ? ");

            // StringBuilder型のsqlをString型に格納
            String StrEmployeeInfoSql = sql.toString();

            // employee_infoのsql文を受け取る
            pstmt = con.prepareStatement(StrEmployeeInfoSql);

            // 送信すべきSQL文の雛形を準備
            pstmt.setString(1, employeeId);

            // 雛形に値を流し込みSQL文を組み立てて送信する
            rs = pstmt.executeQuery();

            //CommonUtilの変数を定義
            CommonUtil common = new CommonUtil();
            
            // empDetailに値をセット
            // 行が見つかればtrueが返る
            while (rs.next()) {
                empDetail = new EmployeeInfoDto();
                empDetail.setCompanyInfoId(rs.getString("company_info_id"));
                empDetail.setEmployeeId(rs.getString("employee_id"));
                empDetail.setName(rs.getString("name"));
                empDetail.setNameHiragana(rs.getString("name_hiragana"));
                empDetail.setBirthday(common.sdfDay(rs.getString("birthday")));
                empDetail.setSex(rs.getString("sex"));
                empDetail.setMailAddress(rs.getString("mail_address"));
                empDetail.setTelephoneNumber(rs.getString("telephone_number"));
                empDetail.setBusinessManager(rs.getString("business_manager"));
                empDetail.setDepartment(DepartmentEnum.getDepartment(rs.getString("department")));
                empDetail.setCommissioningStatus(rs.getString("commissioning_status"));
                empDetail.setHireDate(common.sdfDay(rs.getString("hire_date")));
                String retireDate = rs.getString("retire_date");
                empDetail.setStatus(StatusEnum.getStatus(rs.getString("status")));
                // 退職日をyyyy/MM/ddに変換処理
                if (retireDate != null) {
                    empDetail.setRetireDate(common.sdfDay(retireDate));
                }
            }
        } catch (SQLException e) {
            // 接続や、SQL処理の失敗時の処理
            e.printStackTrace();
        } finally {
            databaseConnection.closeResultSet(rs);
            databaseConnection.closePreparedStatement(pstmt);
            databaseConnection.closeConnection(con);
        }
        return empDetail;
    }

    /**
     * DBの登録を行うメソッド.
     * 
     * @param ins 社員情報
     * @param loginId ログインID
     */
    public void insert(EmployeeInfoDto ins, String loginId) {

        // データベースへの接続や切断を行う為の変数にヌルを代入
        Connection con = null;
        // SQLの送信を行う為の変数にヌルを代入
        PreparedStatement pstmtEmpInfo = null;
        PreparedStatement pstmtEmpState = null;
        // データベースから検索結果を受け取る為の変数にヌルを代入
        ResultSet res = null;
        
        //CommonUtilの変数を定義
        CommonUtil common = new CommonUtil();

        // 生年月日yyyy/MM/ddをyyyy-MM-ddに変換処理
        ins.setBirthday(common.conDay(ins.getBirthday()));

        // 入社日yyyy/MM/ddをyyyy-MM-ddに変換処理
        ins.setHireDate(common.conDay(ins.getHireDate()));

        // 退職日yyyy/MM/ddをyyyy-MM-ddに変換処理
        ins.setRetireDate(common.conDay(ins.getRetireDate()));

        // データベースの接続
        DatabaseConnection databaseConnection = new DatabaseConnection();

        con = databaseConnection.dbConnection();

        try {

            // トランザクション処理開始（オートコミットを解除）
            con.setAutoCommit(false);

            // SQL送信処理
            StringBuilder sql = new StringBuilder();

            // 送信すべきSQL文の雛形を準備
            sql.append("INSERT INTO employee_info ");
            sql.append("(name, ");
            sql.append("name_hiragana, ");
            sql.append("birthday, ");
            sql.append("sex, ");
            sql.append("mail_address, ");
            sql.append("telephone_number, ");
            sql.append("created_id, ");
            sql.append("modified_id) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?)");

            // StringBuilder型のsqlをString型に格納
            String StrEmployeeInfoSql = sql.toString();

            // ステートメント生成
            // ここでRETURN_GENERATED_KEYSを設定する
            // employee_infoのsql文を受け取る
            pstmtEmpInfo = con.prepareStatement(StrEmployeeInfoSql, java.sql.Statement.RETURN_GENERATED_KEYS);

            // 雛形に値を流し込みSQL文を組み立てて送信する
            pstmtEmpInfo.setString(1, ins.getName());
            pstmtEmpInfo.setString(2, ins.getNameHiragana());
            pstmtEmpInfo.setString(3, ins.getBirthday());
            pstmtEmpInfo.setString(4, ins.getSex());
            pstmtEmpInfo.setString(5, ins.getMailAddress());
            pstmtEmpInfo.setString(6, ins.getTelephoneNumber());
            pstmtEmpInfo.setString(7, loginId);
            pstmtEmpInfo.setString(8, loginId);

            // INSERT文を実行する
            pstmtEmpInfo.executeUpdate();

            // getGeneratedKeys()により、Auto_IncrementされたIDを取得する
            res = pstmtEmpInfo.getGeneratedKeys();

            // AutoIncrementされたキーをEmployeeInfoIdにセット
            if (res.next()) {
                ins.setEmployeeInfoId(res.getInt(1));
            }

            // sql文リセット
            sql.setLength(0);

            // 送信すべきSQL文の雛形を準備
            sql.append("INSERT INTO employee_state ");
            sql.append("(company_info_id, ");
            sql.append("employee_info_id, ");
            sql.append("business_manager, ");
            sql.append("department, ");
            sql.append("commissioning_status, ");
            sql.append("status, ");
            sql.append("hire_date, ");
            sql.append("retire_date, ");
            sql.append("created_id, ");
            sql.append("modified_id) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,?)");

            // StringBuilder型のsqlをString型に格納
            String StrEmployeeStateSql = sql.toString();

            // employee_infoのsql文を受け取る
            pstmtEmpState = con.prepareStatement(StrEmployeeStateSql);

            // 雛形に値を流し込みSQL文を組み立てて送信する
            pstmtEmpState.setString(1, ins.getCompanyInfoId());
            pstmtEmpState.setInt(2, ins.getEmployeeInfoId());
            pstmtEmpState.setString(3, ins.getBusinessManager());
            pstmtEmpState.setString(4, ins.getDepartment());
            pstmtEmpState.setString(5, ins.getCommissioningStatus());
            pstmtEmpState.setString(6, ins.getStatus());
            pstmtEmpState.setString(7, ins.getHireDate());
            pstmtEmpState.setString(8, ins.getRetireDate());
            pstmtEmpState.setString(9, loginId);
            pstmtEmpState.setString(10, loginId);

            // INSERT文を実行する
            pstmtEmpState.executeUpdate();

            // コミット
            con.commit();

            // 例外処理
        } catch (SQLException e) {
            // 接続や、SQL処理の失敗時の処理
            e.printStackTrace();
            try {
                // ロールバック処理
                con.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            // データベース接続の切断
            // クローズ処理
            databaseConnection.closeResultSet(res);
            databaseConnection.closePreparedStatement(pstmtEmpInfo);
            databaseConnection.closePreparedStatement(pstmtEmpState);
            databaseConnection.closeConnection(con);
        }
    }

    /**
     * DBの更新を行うメソッド.
     * 
     * @param upd 社員情報
     * @param loginId ログインID
     */
    public void update(EmployeeInfoDto upd, String loginId) {

        // データベースへの接続や切断を行う為の変数にヌルを代入
        Connection con = null;
        // SQLの送信を行う為の変数にヌルを代入
        PreparedStatement pstmtEmpInfo = null;
        PreparedStatement pstmtEmpState = null;
        
        //CommonUtilの変数を定義
        CommonUtil common = new CommonUtil();

        // 生年月日yyyy/MM/ddをyyyy-MM-ddに変換処理
        upd.setBirthday(common.conDay(upd.getBirthday()));

        // 入社日yyyy/MM/ddをyyyy-MM-ddに変換処理
        upd.setHireDate(common.conDay(upd.getHireDate()));

        // 退職日yyyy/MM/ddをyyyy-MM-ddに変換処理
        upd.setRetireDate(common.conDay(upd.getRetireDate()));

        // データベースの接続
        DatabaseConnection databaseConnection = new DatabaseConnection();

        con = databaseConnection.dbConnection();

        try {
            // トランザクション処理開始（オートコミットを解除）
            con.setAutoCommit(false);

            // SQL送信処理
            StringBuilder sql = new StringBuilder();

            // 送信すべきSQL文の雛形を準備
            sql.append("UPDATE employee_info SET ");
            sql.append("name = ?, ");
            sql.append("name_hiragana = ?, ");
            sql.append("birthday = ?, ");
            sql.append("sex = ?, ");
            sql.append("mail_address = ?, ");
            sql.append("telephone_number = ?, ");
            sql.append("modified_id = ? ");
            sql.append("WHERE employee_id = ?");

            // StringBuilder型のsqlをString型に格納
            String StrEmployeeInfoSql = sql.toString();

            // employee_infoのsql文を受け取る
            pstmtEmpInfo = con.prepareStatement(StrEmployeeInfoSql);

            // 雛形に値を流し込みSQL文を組み立てて送信する
            pstmtEmpInfo.setString(1, upd.getName());
            pstmtEmpInfo.setString(2, upd.getNameHiragana());
            pstmtEmpInfo.setString(3, upd.getBirthday());
            pstmtEmpInfo.setString(4, upd.getSex());
            pstmtEmpInfo.setString(5, upd.getMailAddress());
            pstmtEmpInfo.setString(6, upd.getTelephoneNumber());
            pstmtEmpInfo.setString(7, loginId);
            pstmtEmpInfo.setString(8, upd.getEmployeeId());

            // UPDATE文を実行する
            pstmtEmpInfo.executeUpdate();

            // sql文リセット
            sql.setLength(0);

            // 送信すべきSQL文の雛形を準備
            sql.append("UPDATE employee_state SET ");
            sql.append("company_info_id = ?, ");
            sql.append("business_manager = ?, ");
            sql.append("department = ?, ");
            sql.append("commissioning_status = ?, ");
            sql.append("status = ?, ");
            sql.append("hire_date = ?, ");
            sql.append("retire_date = ?,");
            sql.append("modified_id = ?");
            sql.append("WHERE employee_info_id = ?");

            // StringBuilder型のsqlをString型に格納
            String StrEmployeeStateSql = sql.toString();

            // employee_infoのsql文を受け取る
            pstmtEmpState = con.prepareStatement(StrEmployeeStateSql);

            // 雛形に値を流し込みSQL文を組み立てて送信する
            pstmtEmpState.setString(1, upd.getCompanyInfoId());
            pstmtEmpState.setString(2, upd.getBusinessManager());
            pstmtEmpState.setString(3, upd.getDepartment());
            pstmtEmpState.setString(4, upd.getCommissioningStatus());
            pstmtEmpState.setString(5, upd.getStatus());
            pstmtEmpState.setString(6, upd.getHireDate());
            pstmtEmpState.setString(7, upd.getRetireDate());
            pstmtEmpState.setString(8, upd.getEmployeeId());
            pstmtEmpState.setString(9, upd.getEmployeeId());

            // UPDATE文を実行する
            pstmtEmpState.executeUpdate();

            // コミット
            con.commit();

            // 例外処理
        } catch (SQLException e) {
            // 接続や、SQL処理の失敗時の処理
            e.printStackTrace();
            try {
                // ロールバック処理
                con.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            // データベース接続の切断
            // クローズ処理
            databaseConnection.closePreparedStatement(pstmtEmpInfo);
            databaseConnection.closePreparedStatement(pstmtEmpState);
            databaseConnection.closeConnection(con);
        }
    }

    /**
     * DBの削除を行うメソッド.
     * 
     * @param employeeId 社員ID
     */
    public void delete(String employeeId) {

        // データベースへの接続や切断を行う為の変数にヌルを代入
        Connection con = null;
        // SQLの送信を行う為の変数にヌルを代入
        PreparedStatement pstmtEmpInfo = null;
        PreparedStatement pstmtEmpState = null;

        // データベースの接続
        DatabaseConnection databaseConnection = new DatabaseConnection();

        con = databaseConnection.dbConnection();

        try {
            // トランザクション処理開始（オートコミットを解除）
            con.setAutoCommit(false);

            // SQL送信処理
            StringBuilder sql = new StringBuilder();

            // 送信すべきSQL文の雛形を準備
            sql.append("DELETE FROM employee_state ");
            sql.append("WHERE employee_info_id = ?");

            // StringBuilder型のsqlをString型に格納
            String StrEmployeeStateSql = sql.toString();

            // employee_infoのsql文を受け取る
            pstmtEmpState = con.prepareStatement(StrEmployeeStateSql);

            // ?の部分に値をセット
            pstmtEmpState.setString(1, employeeId);

            // DELETE文を実行する
            pstmtEmpState.executeUpdate();

            // SQL文をリセット
            sql.setLength(0);

            // 送信すべきSQL文の雛形を準備
            sql.append("DELETE FROM employee_info ");
            sql.append("WHERE employee_id = ?");

            // StringBuilder型のsqlをString型に格納
            String StrEmployeeInfoSql = sql.toString();

            // employee_infoのsql文を受け取る
            pstmtEmpInfo = con.prepareStatement(StrEmployeeInfoSql);

            // ?の部分に値をセット
            pstmtEmpInfo.setString(1, employeeId);

            // DELETE文を実行する
            pstmtEmpInfo.executeUpdate();

            // コミット
            con.commit();

            // 例外処理
        } catch (SQLException e) {
            // 接続や、SQL処理の失敗時の処理
            e.printStackTrace();
            try {
                // ロールバック処理
                con.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            // データベース接続の切断
            // クローズ処理
            databaseConnection.closePreparedStatement(pstmtEmpInfo);
            databaseConnection.closePreparedStatement(pstmtEmpState);
            databaseConnection.closeConnection(con);
        }
    }
}
