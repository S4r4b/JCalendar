package ir.koroo.kcalendar;

class PersianBirashkLeap implements ICalendarLeap {
    public boolean isLeap(ICalendar date) {
        return ((((((date.getYear() - ((date.getYear() > 0) ? 474 : 473)) % 2820) + 474) + 38) * 682) % 2816) < 682;
    }
}
