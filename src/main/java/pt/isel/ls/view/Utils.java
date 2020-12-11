package pt.isel.ls.view;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utils {

    private static String toJsonHelper(Class cls, Object obj) {
        StringBuilder str = new StringBuilder("{");
        int i = 0;
        for (Field f : cls.getFields())
            try {
                str.append(i++ != 0 ? ", " : "")
                        .append(f.getName());
                if (f.getType().isPrimitive() || f.getType().equals(String.class) || f.getType().equals(Date.class))
                    str.append(!f.getType().isPrimitive() ? ":'" : ":")
                            .append(f.get(obj))
                            .append(!f.getType().isPrimitive() ? "'" : "");
                else str.append(':').append(toJsonHelper(f.getType(), f.get(obj)));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        return str.append('}').toString();
    }

    public static <T> String toJson(Class<T> cls, T t) {
        return toJsonHelper(cls, t);
    }

    public static String dateToSpecialString(Date date, int sum) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        localDate = localDate.plusDays(sum);
        int day = localDate.getDayOfMonth();
        String dayStr = day < 10 ? "0" + day : "" + day;
        int month = localDate.getMonthValue();
        String monthStr = month < 10 ? "0" + month : "" + month;
        return dayStr + monthStr + localDate.getYear();
    }


}
