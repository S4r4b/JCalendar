package ir.koroo.kcalendar;

public class GregorianLeap implements ICalendarLeap {
    public boolean isLeap(ICalendar date) {
        return ((date.getYear() % 4) == 0) && (!(((date.getYear() % 100) == 0) && ((date.getYear() % 400) != 0)));
    }
}