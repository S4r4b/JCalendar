package ir.koroo.kcalendar;


import java.util.Date;

public class GregorianDate extends Calendar {
    private final double Epoch = 1721425.5;

    public GregorianDate() throws CalendarExceptions {
        super();
    }

    public GregorianDate(Date date) throws CalendarExceptions {
        super(date);
    }

    protected void init() {
        setCalendarCulture(new GregorianCalendarCulture());
        setLeapAlgorithm(new GregorianLeap());
    }

    public GregorianDate(int year, int month, int day) throws CalendarExceptions

    {
        super(year, month, day);
    }

    protected GregorianDate(int year, int month, int day, int hour, int minute, int second, int millisecond) throws CalendarExceptions {
        super(year, month, day, hour, minute, second, millisecond);
    }

    protected GregorianDate(int year, int month, int day, int hour, int minute, int second) throws CalendarExceptions {

        super(year, month, day, hour, minute, second);
    }

    public GregorianDate(double julianNumber) throws CalendarExceptions {
        super(julianNumber);
    }

    public GregorianDate(ICalendar ICalendar) throws CalendarExceptions {
        super(ICalendar);
    }


    public int DayOfYear() throws CalendarExceptions {
        return (int) (getJulianDay() - dateToJulian(new GregorianDate(getYear(), 1, 1)));
    }


    public ICalendar julianToDate(double julianNumber) throws CalendarExceptions {
        double wjd;
        double depoch;
        double quadricent;
        double dqc;
        double cent;
        double dcent;
        double quad;
        double dquad;
        double yindex;
        double year;
        double yearday;
        double leapadj;

        wjd = Math.floor(julianNumber - 0.5) + 0.5;
        depoch = wjd - Epoch;
        quadricent = Math.floor(depoch / 146097);
        dqc = depoch % 146097;
        cent = Math.floor(dqc / 36524);
        dcent = dqc % 36524;
        quad = Math.floor(dcent / 1461);
        dquad = dcent % 1461;
        yindex = Math.floor(dquad / 365);
        year = (quadricent * 400) + (cent * 100) + (quad * 4) + yindex;
        if (!((cent == 4) || (yindex == 4))) {
            year++;
        }
        yearday = wjd - dateToJulian(new GregorianDate((int) year, 1, 1));
        leapadj = ((wjd < dateToJulian(new GregorianDate((int) year, 3, 1))) ? 0
                :
                (getLeapAlgorithm().isLeap(new GregorianDate((int) year, 1, 1)) ? 1 : 2)
        );
        double month = Math.floor((((yearday + leapadj) * 12) + 373) / 367);
        double day = (wjd - dateToJulian(new GregorianDate((int) year, (int) month, 1))) + 1;
        setYear((int) year);
        setMonth(getCalendarCulture().getMonth((int) month));
        setDay((int) day);
        return new GregorianDate(getYear(), getMonth().getIndex(), getDay());
    }


    public double dateToJulian(ICalendar ICalendarDate) throws CalendarExceptions {
        if (ICalendarDate == null) throw new CalendarExceptions("NotImplementedException");
        double julianDay =
                (
                        (Epoch - 1)
                                + (365 * (ICalendarDate.getYear() - 1)
                        )
                                + Math.floor((double) ((ICalendarDate.getYear() - 1) / 4))
                                + ((-1) * Math.floor((double) (ICalendarDate.getYear() - 1) / 100))
                                + Math.floor((double) (ICalendarDate.getYear() - 1) / 400)
                                + Math.floor((((367 * (double) ICalendarDate.getMonth().getIndex()) - 362) / 12)
                                + ((ICalendarDate.getMonth().getIndex() <= 2) ? 0 : (getLeapAlgorithm().isLeap(ICalendarDate) ? -1 : -2)) +
                                ICalendarDate.getDay()));
        return julianDay;
    }

    @Override
    public ICalendar createInstance() throws CalendarExceptions {
        return new GregorianDate();
    }

    @Override
    public ICalendar createInstance(Date date) throws CalendarExceptions {
        return new GregorianDate(date);
    }

    @Override
    public ICalendar createInstance(int year, int month, int day) throws CalendarExceptions {
        return new GregorianDate(year,month,day);
    }

    @Override
    public ICalendar createInstance(int year, int month, int day, int hour, int minute, int second) throws CalendarExceptions {
        return new GregorianDate(year,month,day,hour,minute,second);
    }

    @Override
    public ICalendar createInstance(int year, int month, int day, int hour, int minute, int second, int millisecond) throws CalendarExceptions {
        return new GregorianDate(year,month,day,hour,minute,second,millisecond);
    }

    @Override
    public ICalendar createInstance(ICalendar calendar) throws CalendarExceptions {
        return new GregorianDate(calendar);
    }

    @Override
    public ICalendar createInstance(double julianNumber) throws CalendarExceptions {
        return new GregorianDate(julianNumber);
    }


}