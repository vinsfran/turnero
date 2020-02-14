package py.gov.asuncion.turnero.all.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author vinsfran
 */
public class DateUtil {

    public static String parseToString(Date dateTrn, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(dateTrn);
    }

    public static boolean compararFechas(Date fecha1, Date fecha2) {
        if (fecha1 == null || fecha2 == null) {
            return false;
        }
        if (extraerAnio(fecha1).equals(extraerAnio(fecha2))) {
            if (extraerMes(fecha1).equals(extraerMes(fecha2))) {
                if (extraerDia(fecha1).equals(extraerDia(fecha2))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Integer extraerAnio(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        return calendar.get(Calendar.YEAR);
    }

    public static Integer extraerMes(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static Integer extraerDia(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

}
