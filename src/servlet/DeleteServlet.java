package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeInfoDao;
import dto.LoginInfoDto;

/**
 * 社員情報を削除する為のServletクラス.
 * 
 * @author SIR遠藤
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * 削除押下で取得した社員IDの情報を削除 社員IDが取得できなかった場合はログイン画面へ戻る.
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // セッションを生成
        HttpSession session = request.getSession();

        // セッションスコープからインスタンスを取得
        LoginInfoDto user = (LoginInfoDto) session.getAttribute("loginInfo");

        // ユーザーIDがヌルだった場合の処理
        if (user == null) {
            // リダイレクト ログイン画面に遷移
            response.sendRedirect("./login");
        } else {

            // UTF-8にエンコーディング
            request.setCharacterEncoding("UTF-8");

            // リクエストパラメータを取得
            String empId = request.getParameter("empId");

            // インスタンス化してdeleteDaoに格納
            EmployeeInfoDao deleteDao = new EmployeeInfoDao();
            
            // 削除処理を実行
            deleteDao.delete(empId);

            // リダイレクト 社員一覧画面に遷移
            response.sendRedirect("./list");
        }

    }

    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}
