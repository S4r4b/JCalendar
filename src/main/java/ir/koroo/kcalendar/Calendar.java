package ir.koroo.kcalendar;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Calendar extends PositionalAstronomy implements ICalendar {

    private ICalendarLeap leapAlgorithm;

    protected Calendar() throws CalendarExceptions {
        init();
    }

    protected Calendar(int year, int month, int day) throws CalendarExceptions {
        super();
        init();
        setYear(year);
        setMonth(getCalendarCulture().getMonth(month));
        setDay(day);
    }

    protected Calendar(double julianNumber) throws CalendarExceptions {
        super();
        init();
        julianToDate(julianNumber);
    }

    protected Calendar(ICalendar ICalendar) throws CalendarExceptions {
        super();
        init();
        julianToDate(ICalendar.dateToJulian());
    }

    protected Calendar(int year, int month, int day, int hour, int minute, int second) throws CalendarExceptions {
        super();
        init();
        setYear(year);
        setMonth(getCalendarCulture().getMonth(month));
        setDay(day);
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }

    protected Calendar(int year, int month, int day, int hour, int minute, int second, int millisecond) throws CalendarExceptions {
        super();
        init();
        setYear(year);
        setMonth(getCalendarCulture().getMonth(month));
        setDay(day);
        setHour(hour);
        setMinute(minute);
        setSecond(second);
        setMillisecond(millisecond);
    }

    private int year = 0;
    private Month month = null;
    private int day = 0;
    private int hour = 0;
    private int minute = 0;
    private int second = 0;
    private int millisecond = 0;
    protected double Epoch = 0;
    private boolean isLeap = false;
    private int dayOfYear = 0;
    private Week dayOfWeek = null;

    public Calendar(Date date) throws CalendarExceptions {
        super();
        init();
        setYear(date.getYear());
        setMonth(getCalendarCulture().getMonth(date.getMonth()));
        setDay(date.getDay());
    }

    public abstract ICalendar createInstance(double julianNumber) throws CalendarExceptions;
    //region Property

    //endregion

    @Override
    public int monthCount() {
        return getCalendarCulture().monthCount();
    }


    @Override
    public double getJulianDay() throws CalendarExceptions {
        return dateToJulian(this);
    }

    @Override
    public boolean isLeap() throws CalendarExceptions {
        boolean isLeap = getLeapAlgorithm().isLeap(this);
        getCalendarCulture().setLeap(isLeap);
        return isLeap;
    }


    @Override
    public Week getDayOfWeek() throws CalendarExceptions {
        double jd = getJulianDay();
        if (!((jd - (int) jd) >= 0.5))
            jd += 0.5;
        return getCalendarCulture().getWeekDay((int) (Math.ceil(Math.floor((jd + 1.5)) % 7)) + 1);
    }


    @Override
    public ICalendar addDay(int day) throws CalendarExceptions {
        double j = getJulianDay() + day;
        return julianToDate(j);
    }

    private Month changeMonth(int amountOfChangeMonth) throws CalendarExceptions {
        if (amountOfChangeMonth >= monthCount() || amountOfChangeMonth < 1)
            throw new CalendarExceptions();
        setMonth(getCalendarCulture().getMonth(getMonth().getIndex() + amountOfChangeMonth));
        return getCalendarCulture().getMonth(getMonth());
    }

    @Override
    public Month changeMonth(Month month, int amountOfChangeMonth) throws CalendarExceptions {
        if (amountOfChangeMonth >= monthCount() || amountOfChangeMonth < 1)
            throw new CalendarExceptions();
        month = getCalendarCulture().getMonth(month.getIndex() + amountOfChangeMonth);
        return month;
    }

    //region addMonth
    /// <summary>
    /// Add or minus some month to this instant 
    /// </summary>
    /// <param name="month">can be variable int number ; negative for minus month and positiv for add month</param>
    /// <returns>this.clone of this instant after calculate</returns>
    @Override
    public ICalendar addMonth(int month) throws CalendarExceptions, CloneNotSupportedException {
        int r;
        while (true) {
            if (month == 0) break;
            if (month <= monthCount()) {
                setMonth(changeMonth(month));
                break;
            }
            r = month / monthCount();
            setYear(getYear() + r);
            month -= (r * monthCount());
        }
        return (ICalendar) this.clone();
    }
    //endregion addMonth

    @Override
    public ICalendar addYear(int year) throws CloneNotSupportedException {
        setYear(getYear() + year);
        return (ICalendar) this.clone();
    }

    private CalendarCulture calendarCulture;

    @Override
    public double dateToJulian() throws CalendarExceptions {
        return dateToJulian(this);
    }

    @Override
    public ICalendar castTo(Class<? extends ICalendar> destinationType) throws Exception {
        if (destinationType == null) throw new Exception("");
        ICalendar iCalendar = destinationType.newInstance();
        return iCalendar.julianToDate(getJulianDay());
    }

    @Override
    public Month getMonthInfo(int month) throws Exception {
        if (month < 1 && month > monthCount())
            throw new Exception("");
        return getCalendarCulture().getMonth(month);//-1
    }

    /// <summary>
    /// Converts the String representation of a date format to its Calendar date equivalent.
    /// </summary>
    /// <param name="date">A String containing a date format to convert.</param>
    /// <returns>Calendar equivalent to the  date contained in date.</returns>
    @Override
    public ICalendar parse(String date) throws Exception {
        if (date == null) throw new InvalidParameterException();
        date = date.toLowerCase().trim();
        if (date.length() < 6) {
            throw new Exception("Format of String is not a date format!");
        }
        String[] tmp = date.replace('\\', '/').split("/");
        if (tmp == null) throw new Exception("Date Not in correct format!");
        if (tmp[2].length() > tmp[0].length()) {
            setYear(Integer.parseInt(tmp[2]));
            setMonth(getMonthInfo(Integer.parseInt(tmp[1])));
            setDay(Integer.parseInt(tmp[0]));
        } else {
            setYear(Integer.parseInt(tmp[0]));
            setMonth(getMonthInfo(Integer.parseInt(tmp[1])));
            setDay(Integer.parseInt(tmp[2]));
        }
        return (ICalendar) this.clone();
    }

    @Override
    public Date toDate() throws Exception {
        GregorianDate gd = (GregorianDate) castTo(GregorianDate.class);
        Date dt = new Date(gd.getYear(), gd.getMonth().getIndex(), gd.getDay());
        return dt;
    }

    public static Date toDate(ICalendar ICalendarDate) throws Exception {
        if (ICalendarDate == null) throw new InvalidParameterException();
        GregorianDate gd = (GregorianDate) ICalendarDate.castTo(GregorianDate.class);
        Date dt = new Date(gd.getYear(), gd.getMonth().getIndex(), gd.getDay());
        return dt;
    }

    /// <summary>
    /// Converts the String representation of a date format to its Calendar date equivalent.
    /// </summary>
    /// <param name="date">A String containing a date format to convert. yyyy/MM/dd</param>
    /// <param name="calendarType">a instance of calendar that date String based on it</param>
    /// <returns>Calendar equivalent to the  date contained in date.</returns>
    public static ICalendar parse(String date, ICalendar ICalendarType) throws Exception {
        if (date == null) throw new InvalidParameterException();
        if (ICalendarType == null) throw new InvalidParameterException();
        date = date.toLowerCase().trim();
        if (date.length() < 6) {
            throw new Exception("Format of String is not a date format!");
        }
        String[] tmp = date
                .replace('\\', '/')
                .replace("-", "/")
                .replace(".", "/")
                .replace("_", "/")
                .split("/");
        if (tmp[2].length() > tmp[0].length()) {
            ICalendarType.setYear(Integer.parseInt(tmp[2]));
            ICalendarType.setMonth(ICalendarType.getMonthInfo(Integer.parseInt(tmp[1])));
            ICalendarType.setDay(Integer.parseInt(tmp[0]));
        } else {
            ICalendarType.setYear(Integer.parseInt(tmp[0]));
            ICalendarType.setMonth(ICalendarType.getMonthInfo(Integer.parseInt(tmp[1])));
            ICalendarType.setDay(Integer.parseInt(tmp[2]));
        }
        return ICalendarType;
    }

    @Override
    public String toString(String format) throws CalendarExceptions {
        return getCalendarCulture().toString(this, format);
    }

    @Override
    public String toString(DateFormat format) throws CalendarExceptions {
        return getCalendarCulture().toString(this, format);
    }

//    public static explicit operator    Date(Calendar calendar) {
//        return new Date(calendar.year, calendar.month, calendar.day);
//    }

    protected abstract void init();

    @Override
    public ICalendar firstNextMonth(Class<? extends ICalendar> destinationType, int month) throws Exception {
        ICalendar destinationDate = castTo(destinationType);
        destinationDate = destinationDate.gotoDate(destinationDate.getYear(), month, 1);
        double julianDay = getJulianDay();
        return julianDay <= destinationDate.getJulianDay() ? destinationDate : destinationDate.gotoDate(destinationDate.getYear() + 1, month, 1);
    }

    @Override
    public ICalendar gotoDate(int year, int month, int day) throws Exception {
        setYear(year);
        setMonth(getMonthInfo(month));
        setDay(day);
        return julianToDate(getJulianDay());
    }

    @Override
    public ICalendar gotoDate(int month, int day) throws Exception {
        setMonth(getMonthInfo(month));
        setDay(day);
        return julianToDate(getJulianDay());
    }

    @Override
    public ICalendar firstNextDay(Class<? extends ICalendar> destinationType, int month, int day) throws Exception {
        ICalendar nextMonth = firstNextMonth(destinationType, month);
        return nextMonth.addDay(day - 1);
    }

    @Override
    public ICalendar firstWeekDayDate(Class<? extends ICalendar> destinationType) throws Exception {
        ICalendar destinationDate = castTo(destinationType);
        int dayofWeekDayIndex = destinationDate.getDayOfWeek().getDayIndex();
        if (dayofWeekDayIndex == 7) {
            return destinationDate;
        }
        destinationDate = destinationDate.addDay(-1 * dayofWeekDayIndex);
        return destinationDate;
    }

    @Override
    public ICalendar lastWeekDayDate(Class<? extends ICalendar> destinationType) throws Exception {
        ICalendar destinationDate = castTo(destinationType);
        int dayofWeekDayIndex = destinationDate.getDayOfWeek().getDayIndex();
        if (dayofWeekDayIndex == 7) {
            return destinationDate.addDay(6);
        }
        destinationDate = destinationDate.addDay(6 - dayofWeekDayIndex);
        return destinationDate;
    }

    @Override
    public ICalendarLeap getLeapAlgorithm() {
        return leapAlgorithm;
    }

    @Override
    public void setLeapAlgorithm(ICalendarLeap leapAlgorithm) {
        this.leapAlgorithm = leapAlgorithm;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public ir.koroo.kcalendar.Month getMonth() {
        return month;
    }

    @Override
    public void setMonth(ir.koroo.kcalendar.Month month) {
        this.month = month;
    }

    @Override
    public int getDay() {
        return day;
    }

    @Override
    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public int getHour() {
        return hour;
    }

    @Override
    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public int getMinute() {
        return minute;
    }

    @Override
    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public int getSecond() {
        return second;
    }

    @Override
    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public int getMillisecond() {
        return millisecond;
    }

    @Override
    public void setMillisecond(int millisecond) {
        this.millisecond = millisecond;
    }

    @Override
    public void setLeap(boolean leap) {
        isLeap = leap;
    }

    @Override
    public int getDayOfYear() {
        return dayOfYear;
    }

    @Override
    public void setDayOfYear(int dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    @Override
    public void setDayOfWeek(Week dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public CalendarCulture getCalendarCulture() {
        return calendarCulture;
    }

    @Override
    public void setCalendarCulture(CalendarCulture calendarCulture) {
        this.calendarCulture = calendarCulture;
    }

    @Override
    public ICalendar parseFromPatternToGregorian(String date, SimpleDateFormat dateFormat) throws ParseException, CalendarExceptions {
        Date parsedDate = dateFormat.parse(date);
        return new GregorianDate(parsedDate.getYear(), parsedDate.getMonth(), parsedDate.getDay());
    }

    @Override
    public ICalendar parseFromPattern(String date, SimpleDateFormat dateFormat, Class<? extends ICalendar> destinationType) {
        return null;
    }

    @Override
    public ICalendar parseFromPatternToGregorian(String date, String dateFormat) throws ParseException, CalendarExceptions {
        SimpleDateFormat parser = new SimpleDateFormat(dateFormat);
        Date parsedDate = parser.parse(date);
        return new GregorianDate(parsedDate.getYear(), parsedDate.getMonth(), parsedDate.getDay());
    }

    @Override
    public ICalendar parseFromPattern(String date, String dateFormat, Class<? extends ICalendar> destinationType)
            throws IllegalAccessException, InstantiationException, CalendarExceptions, ParseException {
        SimpleDateFormat parser = new SimpleDateFormat(dateFormat);
        Date parsedDate = parser.parse(date);
        ICalendar calendar = destinationType.newInstance();
        return calendar.createInstance(parsedDate.getYear(), parsedDate.getMonth(), parsedDate.getDay());
    }

}
