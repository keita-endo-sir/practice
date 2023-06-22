package enums;

/**
 * ステータスを表す列挙型クラス.
 * 
 * @author SIR遠藤
 */
public enum StatusEnum {

    /** 在籍. */
    ENROLLMENT("0", "在籍"),

    /** 退職. */
    RETIREMENT("1", "退職"),

    /** 入社待. */
    JOINED_WAIT("2", "入社待"),

    /** 入社取り消し. */
    JOINED_CANCELLATION("3", "入社取り消し");

    /** ステータスコード. */
    private String code;

    /** ステータス名. */
    private String name;

    /**
     * コンストラクタの定義.
     * 
     * @param code ステータスコード
     * @param name ステータス名
     */
    private StatusEnum(String code, String name) {

        this.code = code;
        this.name = name;
    }

    /**
     * ステータスコードを取得する.
     * 
     * @return code ステータスコード
     */
    public String getCode() {

        return code;
    }

    /**
     * ステータス名を取得する.
     * 
     * @return name ステータス名
     */
    public String getName() {

        return name;
    }


    /**
     * ステータスコードに一致するステータス名を取得するメソッド.
     * 
     * @param code ステータスコード
     * @return name ステータス名
     */
    public static String getStatus(String code) {
        // 拡張for文による走査
        for (StatusEnum status : StatusEnum.values()) {
            if (status.code.equals(code)) {
                // 条件に一致するインスタンスを返す
                return status.name;
            }
        }
        return "";
    }
}
