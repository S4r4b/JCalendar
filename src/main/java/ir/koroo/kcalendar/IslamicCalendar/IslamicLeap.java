package ir.koroo.kcalendar.IslamicCalendar;

import ir.koroo.kcalendar.CalendarExceptions;
import ir.koroo.kcalendar.ICalendar;
import ir.koroo.kcalendar.ICalendarLeap;

public class IslamicLeap implements ICalendarLeap {
    @Override  public boolean isLeap(ICalendar date) throws CalendarExceptions {
        return (((date.getYear() * 11) + 14) % 30) < 11;
    }
}