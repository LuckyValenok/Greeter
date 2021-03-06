package net.luckyvalenok.greeter;

import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    private static final String[] month = new String[]{"январе", "феврале", "марте", "апреле", "мае", "июне", "июле", "августе", "сенятбре", "октябре", "ноябре", "декабре"};
    private static final Pattern patternDate = Pattern.compile("^\\d{1,2}.\\d{1,2}.\\d{4}$");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Как вас зовут?");
        String name = scanner.nextLine();
        System.out.println("Хорошо, теперь напишите дату своего рождения в формате дд.мм.гггг");
        String date = scanner.nextLine();
        while (!patternDate.matcher(date).matches()) {
            System.out.println("Вы ввели неверно дату. Помните, формат даты - дд.мм.гггг");
            date = scanner.nextLine();
        }
        String[] component = date.split("\\.");
        int day = Integer.parseInt(component[0]);
        int month = Integer.parseInt(component[1]);
        int year = Integer.parseInt(component[2]);
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = calendar.get(Calendar.MONTH) + 1;
        int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (year > nowYear || (year == nowYear && month > nowMonth) || (year == nowYear && month == nowMonth && day > nowDay)) {
            System.out.println("Похоже, что вы еще не родились...");
        } else {
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8) {
                if (day < 1 || day > 31) {
                    System.out.println("В " + Main.month[month - 1] + " всего 31 день.");
                    return;
                }
            } else if (month == 2) {
                if (isLeapYear(year) && (day < 1 || day > 29)) {
                    System.out.println("В високосном году в феврале 29 дней.");
                    return;
                } else if (day < 1 || day > 28) {
                    System.out.println("В феврале 28 дней.");
                    return;
                }
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day < 1 || day > 30) {
                    System.out.println("В " + Main.month[month - 1] + " всего 30 дней.");
                    return;
                }
            } else {
                System.out.println("Такого месяца нет.");
                return;
            }
            System.out.println("Привет, " + name + ". Ваш возраст: "
                    + format(nowYear - year - (month > nowMonth || month == nowMonth && day > nowDay ? 1 : 0), "год", "года", "лет")
                    + ". Приятно познакомиться");
        }
    }

    private static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public static String format(int i, String one, String two, String three) {
        if (i % 100 > 10 && i % 100 < 15)
            return i + " " + three;
        switch (i % 10) {
            case 1:
                return i + " " + one;
            case 2:
            case 3:
            case 4:
                return i + " " + two;
        }
        return i + " " + three;
    }
}
