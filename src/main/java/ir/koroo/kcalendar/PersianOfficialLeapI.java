package ir.koroo.kcalendar;

public class PersianOfficialLeapI implements ICalendarLeap
    {
        public boolean isLeap(ICalendar date) throws CalendarExceptions {
            return (
                (date.dateToJulian(new PersianDate(date.getYear() + 1, 1, 1))) - date.dateToJulian(new PersianDate(date.getYear(), 1, 1))
                ) > 365;
        }
    } 