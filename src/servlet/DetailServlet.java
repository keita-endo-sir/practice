package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeInfoDao;
import dto.EmployeeInfoDto;
import dto.LoginInfoDto;
import util.ValidateDetailForm;
import java.util.List;
import dao.CompanyInfoDao;
import enums.DepartmentEnum;
import enums.SexEnum;
import enums.CommissioningStatusEnum;
import enums.StatusEnum;

/**
 * 詳細画面を表示する為のServletクラス.
 * 
 * @author SIR遠藤
 */
@WebServlet("/detail")
public class DetailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * 社員IDに一致した情報を取得し、社員詳細画面に遷移.
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

            // UTF-8にエンコーディング
            request.setCharacterEncoding("UTF-8");

            // 社員IDパラメータの取得
            String empId = request.getParameter("empId");

            CompanyInfoDao companyInfoDao = new CompanyInfoDao();

            // companyInfoDaoのメソッドをスコープに保存
            request.setAttribute("companyInfoDtoList", companyInfoDao.findAll());

            // empIdのヌル判定を行いヌルでなければ社員情報を取得し変数に格納。
            if (empId != null) {
                EmployeeInfoDao empDao = new EmployeeInfoDao();

                // 社員IDに一致する社員情報を取得し変数に格納
                EmployeeInfoDto empInfoDto = empDao.findOne(empId);

                // empInfoDtoのメソッドをリクエストスコープに保存
                request.setAttribute("empInfoDto", empInfoDto);

                // 更新ボタン作成の為セッションスコープにテキスト保存
                session.setAttribute("buttonName", "更新");

            } else {
                // 登録ボタン作成の為セッションスコープにテキスト保存
                session.setAttribute("buttonName", "登録");
            }

            // SexEnumのメソッドをスコープに保存
            session.setAttribute("sex", SexEnum.values());

            // CommissioningStatusEnumのメソッドをスコープに保存
            session.setAttribute("commissioningStatus", CommissioningStatusEnum.values());

            // DepartmentEnumのメソッドをスコープに保存
            session.setAttribute("department", DepartmentEnum.values());

            // DepartmentEnumのメソッドをスコープに保存
            session.setAttribute("status", StatusEnum.values());

            // フォワード 社員詳細画面に遷移
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/detail.jsp");

            dispatcher.forward(request, response);

        }
    }

    /**
     * 登録、更新処理.
     * 社員詳細画面のフォームの値を受け取り、EmployeeInfoDtoクラスに値をセット
     * バリデーションチェックを行いエラーが見つかった場合は社員詳細画面に戻る
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @throws ServletException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // セッション生成
        HttpSession session = request.getSession();

        // セッションスコープからインスタンスを取得
        LoginInfoDto user = (LoginInfoDto) session.getAttribute("loginInfo");

        // ユーザーIDがヌルだった場合の処理
        if (user == null) {

            // リダイレクト ログイン画面に遷移
            response.sendRedirect("./login");
        } else {

            EmployeeInfoDao empDao = new EmployeeInfoDao();
            EmployeeInfoDto empInfo = new EmployeeInfoDto();

            // UTF-8にエンコーディング
            request.setCharacterEncoding("UTF-8");

            // 社員IDパラメータの取得
            String empId = request.getParameter("employeeId");

            // 社員詳細画面から取得した値をDTOにセット
            // ID取得
            empInfo.setEmployeeId(request.getParameter("employeeId"));

            // name取得
            empInfo.setName(request.getParameter("name"));

            // nameHiragana取得
            empInfo.setNameHiragana(request.getParameter("nameHiragana"));

            // birthday取得
            empInfo.setBirthday(request.getParameter("birthday"));

            // sex取得
            empInfo.setSex(request.getParameter("sex"));

            // mailAddress取得
            empInfo.setMailAddress(request.getParameter("mailAddress"));

            // telephoneNumber取得
            empInfo.setTelephoneNumber(request.getParameter("telephoneNumber"));

            // companyInfoId取得
            empInfo.setCompanyInfoId(request.getParameter("companyInfoId"));

            // businessManager取得
            empInfo.setBusinessManager(request.getParameter("businessManager"));

            // department取得
            empInfo.setDepartment(request.getParameter("department"));

            // commissioningStatus取得
            empInfo.setCommissioningStatus(request.getParameter("commissioningStatus"));

            // hireDate取得
            empInfo.setHireDate(request.getParameter("hireDate"));

            // retireDate取得
            empInfo.setRetireDate(request.getParameter("retireDate"));

            // status取得
            empInfo.setStatus(request.getParameter("stat"));

            ValidateDetailForm validateDetailForm = new ValidateDetailForm();

            // エラーメッセージを取得しリストに格納
            List<String> errorMessage = validateDetailForm.validateDetailForm(empInfo);

            // エラーメッセージが1つ以上あった場合の処理
            if (errorMessage.size() != 0) {

                // errorMessageをスコープに保存
                request.setAttribute("errorMessage", errorMessage);

                CompanyInfoDao companyInfoDao = new CompanyInfoDao();

                // companyInfoDaoのfindAllメソッドをスコープに保存
                request.setAttribute("companyInfoDtoList", companyInfoDao.findAll());

                // empInfoのfindメソッドをスコープに保存
                request.setAttribute("empInfoDto", empInfo);

                // フォワード 社員詳細画面に遷移
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/detail.jsp");

                dispatcher.forward(request, response);

            } else {

                // 社員IDの有無によって条件分岐
                if (empId.isEmpty()) {

                    // 新規登録処理
                    empDao.insert(empInfo, user.getLoginId());
                } else {
                    // 更新登録処理
                    empDao.update(empInfo, user.getLoginId());
                }

                // リダイレクト 社員一覧画面に遷移
                response.sendRedirect("./list");

            }
        }
    }

}