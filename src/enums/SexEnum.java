package enums;

/**
 * 性別を表す列挙型クラス.
 * 
 * @author SIR遠藤
 */
/**
 * @author en092
 */
public enum SexEnum {

    /** 男. */
    MAN("0", "男"),

    /** 女. */
    FEMALE("1", "女");

    /** 性別コード. */
    private String code;

    /** 性別名. */
    private String name;

    /**
     * コンストラクタの定義.
     * 
     * @param code 性別コード
     * @param name 性別名
     */
    private SexEnum(String code, String name) {

        this.code = code;
        this.name = name;
    }

    /**
     * 性別コードを取得する.
     * 
     * @return code 性別コード
     */
    public String getCode() {

        return code;
    }

    /**
     * 性別名を取得する.
     * 
     * @return name 性別名
     */
    public String getName() {

        return name;
    }

    /**
     * 性別コードに一致する性別名を取得するメソッド.
     * 
     * @param code 性別コード
     * @return name 性別名
     */
    public static String getSex(String code) {
        // 拡張for文による走査
        for (SexEnum sex : SexEnum.values()) {
            if (sex.code.equals(code)) {
                // 条件に一致するインスタンスを返す
                return sex.name;
            }
        }
        return "";
    }

}
