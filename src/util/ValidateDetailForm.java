package util;

import java.util.ArrayList;
import java.util.List;
import dto.EmployeeInfoDto;

/**
 * バリデーションチェックメソッドを管理するクラス.
 * 
 * @author SIR遠藤
 */
public class ValidateDetailForm {

    /**
     * エラーメッセージを格納するためのArrayListを生成する.
     * 
     * @param employeeInfoDto 社員情報
     * @return errorMessages エラーメッセージ
     */
    public List<String> validateDetailForm(EmployeeInfoDto employeeInfoDto) {

        // エラーメッセージのArrayListを生成する
        List<String> errorMessages = new ArrayList<>();

        // 正規表現の定数を定義
        final String REGEX_ZENKAKU = "^[0-9a-zA-Z]+$";
        final String REGEX_HIRAGANA_ZENKAKU = "^[ぁ-ん]+$";
        final String REGEX_DAY = "^[0-9]{4}/[01]?[0-9]/[0123]?[0-9]$";
        final String REGEX_HANKAKU = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        final String REGEX_NUMBER = "^[0-9]+$";

        // バリデーションチェックを行う為、氏名を変数に格納
        String name = employeeInfoDto.getName();

        // エラーメッセージ出力
        if (name.isEmpty()) {
            // 未入力の場合
            errorMessages.add("氏名を入力して下さい。");
        } else if (name.length() > 20) {
            // 文字数が21字以上の場合
            errorMessages.add("氏名は20文字以内で入力して下さい。");
        } else if (name.matches(REGEX_ZENKAKU)) {
            // 全角以外の場合
            errorMessages.add("氏名は全角で入力して下さい。");
        }

        // バリデーションチェックを行う為、氏名（ひらがな）を変数に格納
        String nameHiragana = employeeInfoDto.getNameHiragana();

        // エラーメッセージ出力
        if (nameHiragana.isEmpty()) {
            // 未入力の場合
            errorMessages.add("氏名(ひらがな)を入力して下さい。");
        } else if (nameHiragana.length() > 20) {
            // 文字数が21字以上の場合
            errorMessages.add("氏名(ひらがな)は20文字以内で入力して下さい。");
        } else if (!nameHiragana.matches(REGEX_HIRAGANA_ZENKAKU)) {
            // 全角以外の場合
            errorMessages.add("氏名(ひらがな)は全角で入力して下さい。");
        }

        // バリデーションチェックを行う為、生年月日を変数に格納
        String birthday = employeeInfoDto.getBirthday();

        // エラーメッセージ出力
        if (birthday.isEmpty()) {
            // 未入力の場合
            errorMessages.add("生年月日を入力して下さい。");
        } else if (birthday.length() > 10) {
            // 文字数が11字以上の場合
            errorMessages.add("生年月日は10文字以内で入力して下さい。");
        } else if (!birthday.matches(REGEX_DAY)) {
            // YYYY/MM/DDの書式以外の場合
            errorMessages.add("生年月日はYYYY/MM/DDの書式で入力して下さい。");
        }

        // バリデーションチェックを行う為、性別を変数に格納
        String sex = employeeInfoDto.getSex();

        // エラーメッセージ出力
        if (sex == null) {
            // 未選択の場合
            errorMessages.add("性別を選択して下さい。");
        }

        // バリデーションチェックを行う為、メールアドレスを変数に格納
        String mailAddress = employeeInfoDto.getMailAddress();

        // エラーメッセージ出力
        if (mailAddress.isEmpty()) {
            // 未入力の場合
            errorMessages.add("メールアドレスを入力して下さい。");
        } else if (mailAddress.length() > 50) {
            // 文字数が51字以上の場合
            errorMessages.add("メールアドレスは50文字以内で入力して下さい。");
        } else if (!mailAddress.matches(REGEX_HANKAKU)) {
            // 半角英数字記号以外の場合
            errorMessages.add("メールアドレスは半角英数字記号で入力して下さい。");
        }

        // バリデーションチェックを行う為、電話番号を変数に格納
        String telephoneNumber = employeeInfoDto.getTelephoneNumber();

        // エラーメッセージ出力
        if (telephoneNumber.isEmpty()) {
            // 未入力の場合
            errorMessages.add("電話番号を入力して下さい。");
        } else if (telephoneNumber.length() > 11) {
            // 文字数が21字以上の場合
            errorMessages.add("電話番号は11文字以内で入力して下さい。");
        } else if (!telephoneNumber.matches(REGEX_NUMBER)) {
            // 半角数字以外の場合
            errorMessages.add("電話番号は半角数字で入力して下さい。");
        }

        // バリデーションチェックを行う為、所属会社を変数稼働状況に格納
        String companyInfoId = employeeInfoDto.getCompanyInfoId();

        // エラーメッセージ出力
        if (companyInfoId.isEmpty()) {
            // 未選択の場合
            errorMessages.add("所属会社を選択して下さい。");
        }

        // バリデーションチェックを行う為、担当管理営業を変数に格納
        String businessmanager = employeeInfoDto.getBusinessManager();

        // エラーメッセージ出力
        if (businessmanager.isEmpty()) {
            // 未入力の場合
            errorMessages.add("担当管理営業を入力して下さい。");
        } else if (businessmanager.length() > 20) {
            // 文字数が21字以上の場合
            errorMessages.add("担当管理営業は20文字以内で入力して下さい。");
        } else if (businessmanager.matches(REGEX_ZENKAKU)) {
            // 全角以外の場合
            errorMessages.add("担当管理営業は全角で入力して下さい。");
        }

        // バリデーションチェックを行う為、事業部を変数稼働状況に格納
        String department = employeeInfoDto.getDepartment();

        // エラーメッセージ出力
        if (department.isEmpty()) {
            // 未選択の場合
            errorMessages.add("事業部を選択して下さい。");
        }

        // バリデーションチェックを行う為、稼働状況を変数に格納
        String commissioningStatus = employeeInfoDto.getCommissioningStatus();

        // エラーメッセージ出力
        if (commissioningStatus == null) {
            // 未選択の場合
            errorMessages.add("稼働状況を選択して下さい。");
        }

        // バリデーションチェックを行う為、入社日を変数に格納
        String hireDate = employeeInfoDto.getHireDate();

        // エラーメッセージ出力
        if (hireDate.isEmpty()) {
            // 未入力の場合
            errorMessages.add("入社日を入力して下さい。");
        } else if (hireDate.length() > 10) {
            // 文字数が11字以上の場合
            errorMessages.add("入社日は10文字以内で入力して下さい。");
        } else if (!hireDate.matches(REGEX_DAY)) {
            // YYYY/MM/DDの書式以外の場合
            errorMessages.add("入社日はYYYY/MM/DDの書式で入力して下さい。");
        }

        // バリデーションチェックを行う為、退職日を変数に格納
        String retireDate = employeeInfoDto.getRetireDate();

        // エラーメッセージ出力
        if (!retireDate.isEmpty()) {
            if (retireDate.length() > 10) {
                // 文字数が11字以上の場合
                errorMessages.add("退職日は10文字以内で入力して下さい。");
            } else if (!retireDate.matches(REGEX_DAY)) {
                // YYYY/MM/DDの書式以外の場合
                errorMessages.add("退職日はYYYY/MM/DDの書式で入力して下さい。");
            }
        }

        // バリデーションチェックを行う為、ステータスを変数に格納
        String status = employeeInfoDto.getStatus();

        // エラーメッセージ出力
        if (status.isEmpty()) {
            // 未選択の場合
            errorMessages.add("ステータスを選択して下さい。");
        }
        return errorMessages;
    }
}