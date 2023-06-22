package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.Period;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * utilityメソッドを管理するクラス.
 * 
 * @author SIR遠藤
 */
public class CommonUtil {

    /**
     * 日付yyyy-MM-ddをyyyy/MM/ddに変換するメソッド.
     * 
     * @param day 日付を取得する
     * @return stDay 日付
     */
    public String sdfDay(String day) {
        // 生年月日を保存する為の変数にヌルを代入
        String stDay = null;

        try {
            // String型のyyyy-MM-ddをdate型に変換
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdFormat.parse(day);

            // Date型のyyyy-MM-ddをyyyy/MM/ddのstring型に変換
            stDay = new SimpleDateFormat("yyyy/MM/dd").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return stDay;
    }

    /**
     * 日付yyyy/MM/ddをyyyy-MM-ddに変換するメソッド.
     * 
     * @param day 日付を取得する
     * @return setDay 日付
     */
    public String conDay(String day) {
        // 生年月日を保存する為の変数にヌルを代入
        String setDay = null;

        if (day != "") {
            try {
                // String型のyyyy/MM/ddをdate型に変換
                SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = sdFormat.parse(day);

                // Date型のyyyy/MM/ddをyyyy-MM-ddのstring型に変換
                setDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return setDay;
    }

    /**
     * 生年月日から年齢を計算するメソッド.
     * 
     * @param birthday 誕生日 年齢を取得する
     * @return age 年齢
     */
    public int calcAge(String birthday) {
        // 年齢を保存するint型の変数ageを宣言0で初期化
        int age = 0;

        // 誕生日を保存する変数にヌルを代入
        LocalDate localDateBirthday = null;
        // 日付フォーマットをyyyy-MM-ddに指定
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            // LocalDateを生成
            localDateBirthday = LocalDate.parse(birthday, formatter);
        } catch (Exception e) {
            age = -1;
        } finally {
            // 現在の日付を取得
            LocalDate now = LocalDate.now();
            // 生年月日と現在の日付の年数の差を取得する
            Period period = Period.between(localDateBirthday, now);
            age = period.getYears();
        }
        return age;
    }
}