package dto;

/**
 * 社員情報を記述するEmployeeInfoDtoクラス.
 * 
 * @author SIR遠藤
 */
public class EmployeeInfoDto {

    /** 社員ID. */
    private String employeeId;

    /** 氏名. */
    private String name;

    /** 氏名（ひらがな）. */
    private String nameHiragana;

    /** 生年月日. */
    private String birthday;

    /** 性別. */
    private String sex;

    /** メールアドレス. */
    private String mailAddress;

    /** 電話番号. */
    private String telephoneNumber;

    /** 所属会社ID. */
    private String companyInfoId;

    /** 担当管理営業. */
    private String businessManager;

    /** 事業部. */
    private String department;

    /** 稼働状況. */
    private String commissioningStatus;

    /** 入社日. */
    private String hireDate;

    /** 退職日. */
    private String retireDate;

    /** ステータス. */
    private String status;

    /** ログインID. */
    private String loginId;

    /** 会社略称. */
    private String abbreviation;

    /** 年齢. */
    private int age;

    /** 社員ID. */
    private int employeeInfoId;

    /**
     * 社員IDを取得する.
     * 
     * @return employeeId 社員ID
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * 社員IDを設定する.
     * 
     * @param employeeId 社員ID
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * 氏名を取得する.
     * 
     * @return name 氏名
     */
    public String getName() {
        return name;
    }

    /**
     * 氏名を設定する.
     * 
     * @param name 氏名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 氏名（ひらがな）を取得する.
     * 
     * @return nameHiragana 氏名（ひらがな）
     */
    public String getNameHiragana() {
        return nameHiragana;
    }

    /**
     * 氏名（ひらがな）を設定する.
     * 
     * @param nameHiragana 氏名（ひらがな）
     */
    public void setNameHiragana(String nameHiragana) {
        this.nameHiragana = nameHiragana;
    }

    /**
     * 生年月日を設定する.
     * 
     * @return birthday 生年月日
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 生年月日を取得する.
     * 
     * @param birthday 生年月日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 性別を設定する.
     * 
     * @return sex 性別
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性別を設定する.
     * 
     * @param sex 性別
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * メールアドレスを取得する.
     * 
     * @return mailAddress メールアドレス
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * メールアドレスを設定する.
     * 
     * @param mailAddress メールアドレス
     */
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    /**
     * 電話番号を取得する.
     * 
     * @return telephoneNumber 電話番号
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * 電話番号を設定する.
     * 
     * @param telephoneNumber 電話番号
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * 所属会社IDを取得する.
     * 
     * @return companyInfoId 所属会社ID
     */
    public String getCompanyInfoId() {
        return companyInfoId;
    }

    /**
     * 所属会社IDを設定する.
     * 
     * @param companyInfoId 所属会社ID
     */
    public void setCompanyInfoId(String companyInfoId) {
        this.companyInfoId = companyInfoId;
    }

    /**
     * 担当管理営業を取得する.
     * 
     * @return businessManeger 担当管理営業
     */
    public String getBusinessManager() {
        return businessManager;
    }

    /**
     * 担当管理営業を設定する.
     * 
     * @param businessManager 担当管理営業
     */
    public void setBusinessManager(String businessManager) {
        this.businessManager = businessManager;
    }

    /**
     * 事業部を取得する.
     * 
     * @return department 事業部
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 事業部を設定する.
     * 
     * @param department 事業部
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * 稼働状況を取得する.
     * 
     * @return commissioningStatus 稼働状況
     */
    public String getCommissioningStatus() {
        return commissioningStatus;
    }

    /**
     * 稼働状況を設定する.
     * 
     * @param commissioningStatus 稼働状況
     */
    public void setCommissioningStatus(String commissioningStatus) {
        this.commissioningStatus = commissioningStatus;
    }

    /**
     * 入社日を取得する.
     * 
     * @return hireDate 入社日
     */
    public String getHireDate() {
        return hireDate;
    }

    /**
     * 入社日を設定する.
     * 
     * @param hireDate 入社日
     */
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * 退職日を取得する.
     * 
     * @return retireDate 退職日
     */
    public String getRetireDate() {
        return retireDate;
    }

    /**
     * 退職日を設定する.
     * 
     * @param retireDate 退職日
     */
    public void setRetireDate(String retireDate) {
        this.retireDate = retireDate;
    }

    /**
     * ステータスを取得する.
     * 
     * @return status ステータス
     */
    public String getStatus() {
        return status;
    }

    /**
     * ステータスを設定する.
     * 
     * @param status ステータス
     */
    public void setStatus(String status) {
        this.status = status;
    }

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

    /**
     * 会社略称を取得する.
     * 
     * @return abbreviation 会社略称
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * 会社略称を設定する.
     * 
     * @param abbreviation 会社略称
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * 生年月日を取得する.
     * 
     * @return age 生年月日
     */
    public int getAge() {
        return age;
    }

    /**
     * 生年月日を設定する.
     * 
     * @param age 生年月日
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 社員IDを取得する.
     * 
     * @return employeeInfoId 社員ID
     */
    public int getEmployeeInfoId() {
        return employeeInfoId;
    }

    /**
     * 社員IDを設定する.
     * 
     * @param employeeInfoId 社員ID
     */
    public void setEmployeeInfoId(int employeeInfoId) {
        this.employeeInfoId = employeeInfoId;
    }
}