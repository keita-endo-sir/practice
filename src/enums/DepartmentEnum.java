package enums;

/**
 * 事業部を表す列挙型クラス.
 * 
 * @author SIR遠藤
 */
public enum DepartmentEnum {

    /** 開発. */
    DEVELOPMENT("0", "開発"),

    /** NW. */
    NETWORK("1", "NW"),

    /** 検証. */
    VERIFICATION("2", "検証"),

    /** オフィス. */
    OFFICE("3", "オフィス"),

    /** 管理. */
    MANAGEMENT("4", "管理");

    /** 事業部コード. */
    private String code;

    /** 事業部名. */
    private String name;

    /**
     * コンストラクタの定義.
     * 
     * @param code 事業部コード
     * @param name 事業部名
     */
    private DepartmentEnum(String code, String name) {

        this.code = code;
        this.name = name;
    }

    /**
     * 事業部コードを取得する.
     * 
     * @return code 事業部コード
     */
    public String getCode() {

        return code;
    }

    /**
     * 事業部名を取得する.
     * 
     * @return name 事業部名
     */
    public String getName() {

        return name;
    }

    /**
     * 事業部コードに一致する事業部名を取得するメソッド.
     * 
     * @param code 事業部コード
     * @return name 事業部名
     */
    public static String getDepartment(String code) {
        // 拡張for文による走査
        for (DepartmentEnum department : DepartmentEnum.values()) {
            if (department.code.equals(code)) {
                // 条件に一致するインスタンスを返す
                return department.name;
            }
        }
        return "";
    }
}
