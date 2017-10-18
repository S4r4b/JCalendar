package ir.koroo.kcalendar;


public abstract class CalendarCulture {
    protected Month[] months;
    protected Week[] weeks;
    protected String AMName;
    protected String PMName;

    public CalendarCulture() {
    }

    public int monthCount() {
        return months.length;
    }

    public void setLeap(boolean isLeapYear) {
        for (Month item : months) {
            item.setLeap(isLeapYear);
        }
    }

    public Month getMonth(int month) throws CalendarExceptions {
        if (month <= 0 || month > monthCount()) throw new CalendarExceptions();
        for (Month item : months)
            if (item.getIndex() == month)
                return item;
        throw new CalendarExceptions();
    }

    public Month getMonth(Month month) throws CalendarExceptions {
        if (month == null) throw new CalendarExceptions();
        for (Month item : months)
            if (item.getIndex() == month.getIndex())
                return item;
        throw new CalendarExceptions();
    }

    public Week getWeekDay(int day) throws CalendarExceptions {
        if (day < 1 || day > 7)
            throw new CalendarExceptions();
        for (Week item : weeks) {
            if (item.getDayIndex() == day) {
                return item;
            }
        }
        throw new CalendarExceptions();
    }


    /// <summary>
    ///<para>&#160;</para> d	Represents the day of the month as a number from 1 through 31. A single-digit day is formatted without a leading zero
    ///<para>&#160;</para>dd	Represents the day of the month as a number from 01 through 31. A single-digit day is formatted with a leading zero
    ///<para>&#160;</para>ddd	Represents the abbreviated name of the day of the week (Mon, Tues, Wed etc)
    ///<para>&#160;</para>dddd	Represents the full name of the day of the week (Monday, Tuesday etc)
    ///<para>&#160;</para>h	12-hour clock hour (e.g. 7)
    ///<para>&#160;</para>hh	12-hour clock, with a leading 0 (e.g. 07)
    ///<para>&#160;</para>H	24-hour clock hour (e.g. 19)
    ///<para>&#160;</para>HH	24-hour clock hour, with a leading 0 (e.g. 19)
    ///<para>&#160;</para>m	Minutes
    ///<para>&#160;</para>mm	Minutes with a leading zero
    ///<para>&#160;</para>M	Month number
    ///<para>&#160;</para>MM	Month number with leading zero
    ///<para>&#160;</para>MMM	Abbreviated Month Name (e.g. Dec)
    ///<para>&#160;</para>MMMM	Full month name (e.g. December)
    ///<para>&#160;</para>s	Seconds
    ///<para>&#160;</para>ss	Seconds with leading zero
    ///<para>&#160;</para>t	Abbreviated AM / PM (e.g. A or P)
    ///<para>&#160;</para>y	Year, no leading zero (e.g. 2001 would be 1)
    ///<para>&#160;</para>yy	Year, leadin zero (e.g. 2001 would be 01)
    ///<para>&#160;</para>yyy	Year, (e.g. 2001 would be 2001)
    ///<para>&#160;</para>yyyy	Year, (e.g. 2001 would be 2001)
    ///<para>&#160;</para>f	Represents the most significant digit of the seconds fraction; that is, it represents the tenths of a second in a date and time value.
    ///<para>&#160;</para>ff	Represents the two most significant digits of the seconds fraction; that is, it represents the hundredths of a second in a date and time value.
    ///<para>&#160;</para>fff	Represents the three most significant digits of the seconds fraction; that is, it represents the milliseconds in a date and time value.
    /// </summary>
    /// <param name="calendar"></param>
    /// <param name="format"></param>
    /// <returns></returns>
    public String toString(ICalendar ICalendar, String format) throws CalendarExceptions {
        if (format == null || format.length() <= 1) {
            return format;
        }
        if (format.length() == 1) {
            if (format.contains("d"))
                return format.replace("d", toString(ICalendar, "MM/dd/yyyy"));
            if (format.contains("D"))
                return format.replace("D", toString(ICalendar, "dddd, dd MMMM yyyy"));
            if (format.contains("f"))
                return format.replace("f", toString(ICalendar, "dddd, dd MMMM yyyy HH:mm"));
            if (format.contains("F"))
                return format.replace("F", toString(ICalendar, "dddd, dd MMMM yyyy HH:mm:ss"));
            if (format.contains("g"))
                return format.replace("g", toString(ICalendar, "MM/dd/yyyy HH:mm"));
            if (format.contains("G"))
                return format.replace("G", toString(ICalendar, "MM/dd/yyyy HH:mm:ss"));
            if (format.contains("m"))
                return format.replace("m", toString(ICalendar, "MMMM dd"));
            if (format.contains("r"))
                return format.replace("r", toString(ICalendar, "ddd, dd MMM yyyy HH':'mm':'ss 'GMT"));
            if (format.contains("s"))
                return format.replace("s", toString(ICalendar, "yyyy'-'MM'-'dd'T'HH':'mm':'ss"));
            if (format.contains("u"))
                return format.replace("u", toString(ICalendar, "yyyy'-'MM'-'dd HH':'mm':'ss'Z'"));
            if (format.contains("U"))
                return format.replace("U", toString(ICalendar, "dddd, dd MMMM yyyy HH:mm:ss"));
            if (format.contains("y"))
                return format.replace("y", toString(ICalendar, "yyyy MMMM"));
        }
        if (format.length() > 1) {
            return format
                    .replace("yyyy", String.valueOf(ICalendar.getYear()))
                    .replace("yyy", String.valueOf(ICalendar.getYear()))
                    .replace("yy", paddingToLeft(ICalendar.getYear()))
                    .replace("y", String.valueOf(ICalendar.getYear() % 100))
                    .replace("MMMM", ICalendar.getMonth().getName())
                    .replace("MMM", ICalendar.getMonth().getShortName())
                    .replace("MM", paddingToLeft(ICalendar.getMonth().getIndex()))
                    .replace("M", String.valueOf(ICalendar.getMonth().getIndex()))
                    .replace("dddd", String.valueOf(ICalendar.getDayOfWeek().getName()))
                    .replace("ddd", ICalendar.getDayOfWeek().getShortName())
                    .replace("dd", paddingToLeft(ICalendar.getDay()))
                    .replace("d", String.valueOf(ICalendar.getDay()))
                    .replace("tt", ICalendar.getHour() > 12 ? ICalendar.getCalendarCulture().PMName : ICalendar.getCalendarCulture().AMName)
                    .replace("HH", paddingToLeft(ICalendar.getHour()))
                    .replace("H", String.valueOf(ICalendar.getHour()))
                    .replace("hh", String.valueOf(ICalendar.getHour() > 12 ? (ICalendar.getHour() - 1) : ICalendar.getHour()))
                    .replace("h", ICalendar.getHour() > 12 ? paddingToLeft(ICalendar.getHour() - 1) : paddingToLeft(ICalendar.getHour()))
                    .replace("mm", paddingToLeft(ICalendar.getMinute()))
                    .replace("m", String.valueOf(ICalendar.getMinute()))
                    .replace("ss", paddingToLeft(ICalendar.getSecond()))
                    .replace("s", String.valueOf(ICalendar.getSecond()))
                    .replace("f", String.valueOf(ICalendar.getMillisecond() / 100))
                    .replace("ff", String.valueOf(ICalendar.getMillisecond() / 10))
                    .replace("fff", String.valueOf(ICalendar.getMillisecond()));
        }
        return format;
    }

    /// <summary>
    /// Converts the value of the current Calendar object to its equivalent String representation using the specified format.
    /// </summary>
    /// <param name="calendar"></param>
    /// <param name="format">A persian date and time format String.</param>
    /// <returns>A String representation of value of the current Calendar object as specified by format.</returns>
    public String toString(ICalendar ICalendar, DateFormat format) throws CalendarExceptions {
        switch (format) {
            case Date:
                return ICalendar.getYear() + "/" + paddingToLeft(ICalendar.getMonth().getIndex()) + "/" + paddingToLeft(ICalendar.getDay());

            case FullDate:
                return ICalendar.getDayOfWeek().getName() + " " + ICalendar.getDay() + " " + ICalendar.getMonth().getName() + " " + ICalendar.getYear();

            case LongDate:
                return ICalendar.getDayOfWeek().getName() + " " + ICalendar.getDay() + " " + ICalendar.getMonth().getName();

            case ShortDate:
                return ICalendar.getDay() + " " + ICalendar.getMonth().getIndex();
            default:
                throw new CalendarExceptions("NotImplementedException");
        }
    }

    public String paddingToLeft(int x) {
        if (x < 10) {
            return "0" + x;
        }
        return String.valueOf(x);
    }
}