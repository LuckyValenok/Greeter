package net.luckyvalenok.greeter;

import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Pattern patternDate = Pattern.compile("^\\d{1,2}.\\d{1,2}.\\d{4}$");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Как вас зовут?");
        String name = scanner.next();
        System.out.println("Хорошо, теперь напишите дату своего рождения в формате дд.мм.гггг");
        String date = scanner.next();
        while (!patternDate.matcher(date).matches()) {
            System.out.println("Вы ввели неверно дату. Помните, формат даты - дд.мм.гггг");
            date = scanner.next();
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
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                    if (day < 1 || day > 31) {
                        System.out.println("В " + Month.values()[month - 1].name + " всего 31 день.");
                        return;
                    }
                    break;
                case 2:
                    if (isLeapYear(year) && (day < 1 || day > 29)) {
                        System.out.println("В високосном году в феврале 29 дней.");
                        return;
                    } else if (day < 1 || day > 28) {
                        System.out.println("В феврале 28 дней.");
                        return;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (day < 1 || day > 30) {
                        System.out.println("В " + Month.values()[month - 1].name + " всего 30 дней.");
                        return;
                    }
                    break;
                default:
                    System.out.println("Такого месяца нет.");
                    return;
            }
            System.out.println("Привет, " + name + ". Ваш возраст: " + format(nowYear - year - (month > nowMonth || month == nowMonth && day > nowDay ? 1 : 0), "год", "года", "лет")
                    + ". Приятно познакомиться");
        }
    }

    private static boolean isLeapYear(int year) {
        if (year % 4 == 0)
            if (year % 100 == 0)
                return year % 400 == 0;
            else
                return true;
        else
            return false;
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

    enum Month {
        JANUARY("январе"), FEBRUARY("феврале"), MARCH("марте"),
        APRIL("апреле"), MAY("мае"), JUNE("июне"), JULY("июле"),
        AUGUST("августе"), SEPTEMBER("сентябре"), OCTOBER("октябре"),
        NOVEMBER("ноябре"), DECEMBER("декабре");

        String name;

        Month(String name) {
            this.name = name;
        }
    }
}
