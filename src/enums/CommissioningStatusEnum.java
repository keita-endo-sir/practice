package enums;

/**
 * 稼働状況を表す列挙型クラス.
 * 
 * @author SIR遠藤
 */
public enum CommissioningStatusEnum {

    /** 未稼働. */
    NOTRUNNING("0", "未稼働"),

    /** 稼働. */
    RUNNING("1", "稼働");

    /** 稼働ステータスコード. */
    private String code;

    /** 稼働ステータス名. */
    private String name;

    /**
     * コンストラクタの定義.
     * 
     * @param code 稼働ステータスコード
     * @param name 稼働ステータス名
     */
    private CommissioningStatusEnum(String code, String name) {

        this.code = code;
        this.name = name;
    }

    /**
     * 稼働ステータスコードコードを取得する.
     * 
     * @return code 稼働ステータスコード
     */
    public String getCode() {

        return code;
    }

    /**
     * 稼働ステータス名を取得する.
     * 
     * @return name 稼働ステータス名
     */
    public String getName() {

        return name;
    }


    /**
     * 稼働ステータスコードに一致する稼働ステータス名を取得するメソッド.
     * 
     * @param code 稼働ステータスコード
     * @return name 稼働ステータス名
     */
    public static String getCommissioningStatus(String code) {
        // 拡張for文による走査
        for (CommissioningStatusEnum commissioningStatus : CommissioningStatusEnum.values()) {
            if (commissioningStatus.code.equals(code)) {
                // 条件に一致するインスタンスを返す
                return commissioningStatus.name;
            }
        }
        return "";
    }
}
