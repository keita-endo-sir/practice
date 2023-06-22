package dto;

/**
 * ログイン情報を記述するLoginInfoDtoクラス.
 * 
 * @author SIR遠藤
 */
public class LoginInfoDto {

    /** ログインID. */
    private String loginId;

    /**
     * ログインIDを取得する.
     * 
     * @return loginId ログインID
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * ログインIDを設定する.
     * 
     * @param loginId ログインID
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

}
