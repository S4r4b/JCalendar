package ir.koroo.kcalendar;

import java.util.Date;

public class PersianDate extends Calendar {

    public final double Epoch = 1948320.5;

    public PersianDate() throws CalendarExceptions {
        super();
    }
    public PersianDate(Date date) throws CalendarExceptions {
        super(date);
    }
    protected void init() {
        setCalendarCulture(new PersianCalendarCulture());
        setLeapAlgorithm(new PersianOfficialLeapI());
    }

    public PersianDate(int year, int month, int day) throws CalendarExceptions

    {
        super(year, month, day);
    }

    protected PersianDate(int year, int month, int day, int hour, int minute, int second, int millisecond) throws CalendarExceptions {
        super(year, month, day, hour, minute, second, millisecond);
    }

    protected PersianDate(int year, int month, int day, int hour, int minute, int second) throws CalendarExceptions {
        super(year, month, day, hour, minute, second);
    }

    public PersianDate(double julianNumber) throws CalendarExceptions {
        super(julianNumber);
    }

    public PersianDate(ICalendar ICalendar) throws CalendarExceptions {
        super(ICalendar);
    }


    public int dayOfYear() throws CalendarExceptions {
        return (int) (getJulianDay() - dateToJulian(new PersianDate(getYear(), 1, 1)));
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
        double equinox;
        double yday;
        double[] adr;
        double jd = Math.floor(julianNumber) + 0.5;
        adr = calcYear(jd);
        year = adr[0];
        equinox = adr[1];
        day = Math.floor((jd - equinox) / 30) + 1;
        yday = (Math.floor(jd) - dateToJulian(new PersianDate((int) year, 1, 1))) + 1;
        month = (yday <= 186) ? Math.ceil(yday / 31) : Math.ceil((yday - 6) / 30);
        day = (Math.floor(jd) - dateToJulian(new PersianDate((int) year, (int) month, 1))) + 1;
        setYear((int) year);
        setMonth(getCalendarCulture().getMonth((int) month));
        setDay((int) day);
        return new PersianDate(getYear(), getMonth().getIndex(), getDay());
    }

    @Override
    public double dateToJulian(ICalendar ICalendarDate) throws CalendarExceptions {
        double[] adr = {ICalendarDate.getYear() - 1, 0};
        double guess = (Epoch - 1) + (tropicalYear * ((ICalendarDate.getYear() - 1) - 1));
        while (adr[0] < ICalendarDate.getYear()) {
            adr = calcYear(guess);
            guess = adr[1] + (tropicalYear + 2);
        }
        double equinox = adr[1];

        double jd = equinox +
                ((ICalendarDate.getMonth().getIndex() <= 7) ?
                        ((ICalendarDate.getMonth().getIndex() - 1) * 31) :
                        (((ICalendarDate.getMonth().getIndex() - 1) * 30) + 6)
                ) +
                (ICalendarDate.getDay() - 1);
        return jd;
    }

    private double[] calcYear(double jd) throws CalendarExceptions {
        double guess = new GregorianDate(jd).getYear() - 2;
        double lasteq;
        double nexteq;
        double adr;

        lasteq = tehranEequinoxJd(guess);
        while (lasteq > jd) {
            guess--;
            lasteq = tehranEequinoxJd(guess);
        }
        nexteq = lasteq - 1;
        while (!((lasteq <= jd) && (jd < nexteq))) {
            lasteq = nexteq;
            guess++;
            nexteq = tehranEequinoxJd(guess);
        }
        adr = Math.round((lasteq - Epoch) / tropicalYear) + 1;

        return new double[]{adr, lasteq};
    }

    private double tehranEquinox(double year) {
        double equJED;
        double equJD;
        double equAPP;
        double equTehran;
        double dtTehran;

        //  March equinox in dynamical time
        equJED = equinox(year, 0);

        //  Correct for delta T to obtain Universal time
        equJD = equJED - (deltat(year) / (24 * 60 * 60));

        //  Apply the equation of time to yield the apparent time at Greenwich
        equAPP = equJD + equationOfTime(equJED);

            /*  Finally, we must correct for the constant difference between
                the Greenwich meridian andthe time zone standard for
            Iran Standard time, 52°30' to the East.  */

        dtTehran = (52 + (30 / 60.0) + (0 / (60.0 * 60.0))) / 360;
        equTehran = equAPP + dtTehran;

        return equTehran;
    }

        /*  TEHRAN_EQUINOX_JD  --  Calculate Julian day during which the
                                   March equinox, reckoned from the Tehran
                                   meridian, occurred for a given Gregorian
                                   year.  */

    private double tehranEequinoxJd(double year) {
        double ep;
        ep = tehranEquinox(year);
        double epg = Math.floor(ep);
        return epg;
    }

//        public static explicit operator PersianDate(String date)
//        {
//            return (PersianDate) parse(date,new PersianDate());
//        }
//
//
//        public static explicit operator PersianDate(double jDay)
//        {
//            return new PersianDate(jDay);
//        }


    @Override
    public ICalendar createInstance() throws CalendarExceptions {
        return new PersianDate();
    }

    @Override
    public ICalendar createInstance(Date date) throws CalendarExceptions {
        return new PersianDate(date);
    }

    @Override
    public ICalendar createInstance(int year, int month, int day) throws CalendarExceptions {
        return new PersianDate(year,month,day);
    }

    @Override
    public ICalendar createInstance(int year, int month, int day, int hour, int minute, int second) throws CalendarExceptions {
        return new PersianDate(year,month,day,hour,minute,second);
    }

    @Override
    public ICalendar createInstance(int year, int month, int day, int hour, int minute, int second, int millisecond) throws CalendarExceptions {
        return new PersianDate(year,month,day,hour,minute,second,millisecond);
    }

    @Override
    public ICalendar createInstance(ICalendar calendar) throws CalendarExceptions {
        return new PersianDate(calendar);
    }

    @Override
    public  ICalendar createInstance(double julianNumber) throws CalendarExceptions {
        return new PersianDate(julianNumber);
    }
}
