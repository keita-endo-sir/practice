package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginInfoDao;
import dto.LoginInfoDto;

/**
 * ログイン画面を表示する為のServletクラス.
 * 
 * @author SIR遠藤
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * login.jspを呼び出して、ログイン押下でdoPostメソッドへ.
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // フォワード ログイン画面に遷移
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * ログイン画面のフォームの値を受け取り、LoginInfoDaoクラスでログイン情報を検索
     * ログイン情報が見つからなかった場合はlogin.jspに戻る ログイン情報が見つかった場合は一覧画面へ
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // UTF-8にエンコーディング
        request.setCharacterEncoding("UTF-8");

        // ログインIDパラメータの取得
        String loginId = request.getParameter("loginId");
        // パスワードパラメータの取得
        String password = request.getParameter("password");

        // ログイン処理の実行
        // LoginInfoDaoクラスのインスタンス生成し変数に格納
        LoginInfoDao dao = new LoginInfoDao();
        
        // 入力された値でログイン情報検索し、LoginInfoDtoに代入
        LoginInfoDto login = dao.find(loginId, password);

        // ログイン失敗時
        if (login == null) {

            // リクエストにログイン実行失敗保存
            request.setAttribute("message", "IDまたはパスワードが異なります。");

            // フォワード ログイン画面に遷移
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");

            dispatcher.forward(request, response);     
         // ログイン成功時
        } else {
            // セッションを生成
            HttpSession session = request.getSession();

            // ログイン情報をスコープに保存
            session.setAttribute("loginInfo", login);

            // リダイレクト 社員一覧画面に遷移
            response.sendRedirect("./list");
        }
    }
}
