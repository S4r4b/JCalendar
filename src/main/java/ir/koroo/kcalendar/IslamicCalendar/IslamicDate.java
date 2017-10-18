package ir.koroo.kcalendar.IslamicCalendar;

import java.util.Date;

import ir.koroo.kcalendar.Calendar;
import ir.koroo.kcalendar.CalendarExceptions;
import ir.koroo.kcalendar.ICalendar;

public class IslamicDate extends Calendar {

    public final double Epoch = 1948439.5;

    public IslamicDate() throws CalendarExceptions {
        super();

    }

    public IslamicDate(ICalendar calendar) throws CalendarExceptions {
        super(calendar);

    }

    protected void init() {
        setCalendarCulture(new IslamicCalendarCulture());
        setLeapAlgorithm(new IslamicLeap());
    }

    public IslamicDate(int year, int month, int day) throws CalendarExceptions

    {
        super(year, month, day);
    }

    public IslamicDate(Date date) throws CalendarExceptions {
        super(date);
    }

    protected IslamicDate(int year, int month, int day, int hour, int minute, int second, int millisecond) throws CalendarExceptions {
        super(year, month, day, hour, minute, second, millisecond);
    }

    protected IslamicDate(int year, int month, int day, int hour, int minute, int second) throws CalendarExceptions {

        super(year, month, day, hour, minute, second);
    }

    public IslamicDate(double julianNumber) throws CalendarExceptions {
        super(julianNumber);
    }

    public IslamicDate(Calendar calendar) throws CalendarExceptions {
        super(calendar);
    }


    public int dayOfYear() throws CalendarExceptions {
        return (int) (getJulianDay() - dateToJulian(new IslamicDate(getYear(), 1, 1)));
    }

    @Override
    public Calendar julianToDate(double julianNumber) throws CalendarExceptions {
        double year;
        double month;
        double day;

        julianNumber = Math.floor(julianNumber) + 0.5;
        year = Math.floor(((30 * (julianNumber - Epoch)) + 10646) / 10631);
        month = Math.min(12, Math.ceil((julianNumber - (29 + dateToJulian(new IslamicDate((int) year, 1, 1)))) / 29.5) + 1);
        day = (julianNumber - dateToJulian(new IslamicDate((int) year, (int) month, 1))) + 1;
        setYear((int) year);
        setMonth(getCalendarCulture().getMonth((int) month));
        setDay((int) day);
        return new IslamicDate(getYear(), getMonth().getIndex(), getDay());
    }



    @Override
    public double dateToJulian(ICalendar calendarDate) {
        return (calendarDate.getDay() +
                Math.ceil(29.5 * (calendarDate.getMonth().getIndex() - 1))
                + (calendarDate.getYear() - 1) * 354
                + Math.floor((3 + (11 * calendarDate.getYear())) / 30.0) + Epoch) - 1;
    }


    @Override
    public ICalendar createInstance() throws CalendarExceptions {
        return new IslamicDate();
    }

    @Override
    public ICalendar createInstance(Date date) throws CalendarExceptions {
        return new IslamicDate(date);
    }

    @Override
    public ICalendar createInstance(int year, int month, int day) throws CalendarExceptions {
        return new IslamicDate(year, month, day);
    }

    @Override
    public ICalendar createInstance(int year, int month, int day, int hour, int minute, int second) throws CalendarExceptions {
        return new IslamicDate(year, month, day, hour, minute, second);
    }

    @Override
    public ICalendar createInstance(int year, int month, int day, int hour, int minute, int second, int millisecond) throws CalendarExceptions {
        return new IslamicDate(year, month, day, hour, minute, second, millisecond);
    }

    @Override
    public ICalendar createInstance(ICalendar calendar) throws CalendarExceptions {
        return new IslamicDate(calendar);
    }

    @Override
    public ICalendar createInstance(double julianNumber) throws CalendarExceptions {
        return new IslamicDate(julianNumber);
    }
}