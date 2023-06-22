package dto;

/**
 * 会社情報を記述するCompanyInfoDtoクラス.
 * 
 * @author SIR遠藤
 */
public class CompanyInfoDto {

    /** 会社ID. */
    private String companyId;

    /** 会社名. */
    private String companyName;

    /** 略称. */
    private String abbreviation;

    /**
     * 会社IDを取得する.
     * 
     * @return companyId 会社ID
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 会社IDを設定する.
     * 
     * @param companyId 会社ID
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 会社名を取得する.
     * 
     * @return companyName 会社名
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 会社名を設定する.
     * 
     * @param companyName 会社名
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 略称を取得する.
     * 
     * @return abbreviation 略称
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * 略称を設定する.
     * 
     * @param abbreviation 略称
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

}
