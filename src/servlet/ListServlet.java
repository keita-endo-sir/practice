package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeInfoDao;
import dto.EmployeeInfoDto;
import dto.LoginInfoDto;

/**
 * 一覧画面を表示する為のServletクラス.
 * 
 * @author SIR遠藤
 */
@WebServlet("/list")
public class ListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * EmployeeDaoクラスでメッセージ情報を全て検索してリクエストに保存しlist.jsp呼び出し
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @throws ServletException
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
            EmployeeInfoDao infoDao = new EmployeeInfoDao();

            // 社員情報を全て検索しリストに格納
            List<EmployeeInfoDto> empList = infoDao.findAll();

            // 変数empListに社員情報を保存
            request.setAttribute("empId", empList);

            // フォワード 社員一覧画面に遷移
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
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