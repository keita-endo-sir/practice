package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ログアウトする為のServletクラス
 * 
 * @author SIR遠藤
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * セッションを破棄してログイン画面へ.
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // セッション生成
        HttpSession session = request.getSession(true);

        // セッション破棄
        session.invalidate();

        // ログイン画面へリダイレクト
        response.sendRedirect("./login");
    }

    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

}