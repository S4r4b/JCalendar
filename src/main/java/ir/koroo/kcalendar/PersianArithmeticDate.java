package ir.koroo.kcalendar;

import java.util.Date;

public class PersianArithmeticDate extends Calendar {
    public PersianArithmeticDate() throws CalendarExceptions {
        super();
    }

    public final double Epoch = 1948320.5;

    protected void init() {
        setCalendarCulture(new PersianCalendarCulture());
        setLeapAlgorithm(new PersianBirashkLeap());
    }

    public PersianArithmeticDate(int year, int month, int day) throws CalendarExceptions
    {
        super(year, month, day);
    }
    public PersianArithmeticDate(Date date) throws CalendarExceptions {
        super(date);
    }
    protected PersianArithmeticDate(int year, int month, int day, int hour, int minute, int second, int millisecond) throws CalendarExceptions {
        super(year, month, day, hour, minute, second, millisecond);
    }

    protected PersianArithmeticDate(int year, int month, int day, int hour, int minute, int second) throws CalendarExceptions {

        super(year, month, day, hour, minute, second);
    }

    public PersianArithmeticDate(double julianNumber) throws CalendarExceptions {
        super(julianNumber);
    }

    public PersianArithmeticDate(ICalendar ICalendar) throws CalendarExceptions {
        super(ICalendar);
    }



    public int dayOfYear() throws CalendarExceptions {
      return   (int)(getJulianDay() - dateToJulian(new PersianArithmeticDate(getYear(), 1,1)));
    }


    /// <summary>
    /// Convert Julian number to persian calendarDate
    /// </summary>
    /// <param name="julianNumber"></param>
    /// <returns></returns>
    @Override
    public ICalendar julianToDate(double julianNumber) throws CalendarExceptions {
        double year;
        double month;
        double day;
        double depoch;
        double cycle;
        double cyear;
        double ycycle;
        double aux1;
        double aux2;
        double yday;
        double jd;

        jd = Math.floor(julianNumber) + 0.5;

        depoch = jd - dateToJulian(new PersianArithmeticDate(475, 1, 1));
        cycle = Math.floor(depoch / 1029983);
        cyear = mod(depoch, 1029983);
        if (Math.abs(cyear - 1029982) < 0.5) {
            ycycle = 2820;
        } else {
            aux1 = Math.floor(cyear / 366);
            aux2 = cyear % 366;
            ycycle = Math.floor(((2134 * aux1) + (2816 * aux2) + 2815) / 1028522) + aux1 + 1;
        }
        year = ycycle + (2820 * cycle) + 474;
        year = year <= 0 ? year - 1 : year;
        if (year <= 0) {
            year--;
        }
        yday = (jd - dateToJulian(new PersianArithmeticDate((int) year, 1, 1))) + 1;
        month = (yday <= 186) ? Math.ceil(yday / 31) : Math.ceil((yday - 6) / 30);
        day = (jd - dateToJulian(new PersianArithmeticDate((int) year, (int) month, 1))) + 1;
        setYear((int) year);
        setMonth(getCalendarCulture().getMonth((int) month));//(int)month;
        setDay((int) day);
        return new PersianArithmeticDate(getYear(), getMonth().getIndex(), getDay());
    }

    @Override
    public double dateToJulian(ICalendar ICalendarDate) {
        double epbase = ICalendarDate.getYear() - ((ICalendarDate.getYear() >= 0) ? 474 : 473);
        double epyear = 474 + (epbase % 2820);
        return (ICalendarDate.getDay() + ((ICalendarDate.getMonth().getIndex() <= 7) ? ((ICalendarDate.getMonth().getIndex() - 1) * 31) : (((ICalendarDate.getMonth().getIndex() - 1) * 30) + 6))
                + Math.floor(((epyear * 682) - 110) / 2816) + (epyear - 1) * 365 + (Epoch - 1));
    }


    @Override
    public ICalendar createInstance() throws CalendarExceptions {
        return new PersianArithmeticDate();
    }

    @Override
    public ICalendar createInstance(Date date) throws CalendarExceptions {
        return new PersianArithmeticDate(date);
    }

    @Override
    public ICalendar createInstance(int year, int month, int day) throws CalendarExceptions {
        return new PersianArithmeticDate(year,month,day);
    }

    @Override
    public ICalendar createInstance(int year, int month, int day, int hour, int minute, int second) throws CalendarExceptions {
        return new PersianArithmeticDate(year,month,day,hour,minute,second);
    }

    @Override
    public ICalendar createInstance(int year, int month, int day, int hour, int minute, int second, int millisecond) throws CalendarExceptions {
        return new PersianArithmeticDate(year,month,day,hour,minute,second,millisecond);
    }

    @Override
    public ICalendar createInstance(ICalendar calendar) throws CalendarExceptions {
        return new PersianArithmeticDate(calendar);
    }

    @Override
    public ICalendar createInstance(double julianNumber) throws CalendarExceptions {
        return new PersianArithmeticDate(julianNumber);
    }
}