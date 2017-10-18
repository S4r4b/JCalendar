package ir.koroo.kcalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mjavad on 11/10/2017.
 */

public interface ICalendar {
    int monthCount();

    double getJulianDay() throws CalendarExceptions;

    boolean isLeap() throws CalendarExceptions;

    Week getDayOfWeek() throws CalendarExceptions;

    ICalendar julianToDate(double julianNumber) throws CalendarExceptions;

    ICalendar addDay(int day) throws CalendarExceptions;

    Month changeMonth(Month month, int amountOfChangeMonth) throws CalendarExceptions;

    //region addMonth
    /// <summary>
    /// Add or minus some month to this instant
    /// </summary>
    /// <param name="month">can be variable int number ; negative for minus month and positiv for add month</param>
    /// <returns>this.clone of this instant after calculate</returns>
    ICalendar addMonth(int month) throws CalendarExceptions, CloneNotSupportedException;

    ICalendar addYear(int year) throws CloneNotSupportedException;

    double dateToJulian() throws CalendarExceptions;

    double dateToJulian(ICalendar ICalendarDate) throws CalendarExceptions;

    ICalendar castTo(Class<? extends ICalendar> type) throws Exception;

    Month getMonthInfo(int month) throws Exception;

    /// <summary>
    /// Converts the String representation of a date format to its Calendar date equivalent.
    /// </summary>
    /// <param name="date">A String containing a date format to convert.</param>
    /// <returns>Calendar equivalent to the  date contained in date.</returns>
    ICalendar parse(String date) throws Exception;

    Date toDate() throws Exception;

    String toString(String format) throws CalendarExceptions;

    String toString(DateFormat format) throws CalendarExceptions;

    ICalendar firstNextMonth(Class<? extends ICalendar> destinationType, int month) throws Exception;

    ICalendar gotoDate(int year, int month, int day) throws Exception;

    ICalendar gotoDate(int month, int day) throws Exception;

    ICalendar firstNextDay(Class<? extends ICalendar> destinationType, int month, int day) throws Exception;

    ICalendar firstWeekDayDate(Class<? extends ICalendar> destinationType) throws Exception;

    ICalendar lastWeekDayDate(Class<? extends ICalendar> destinationType) throws Exception;

    ICalendarLeap getLeapAlgorithm();

    void setLeapAlgorithm(ICalendarLeap leapAlgorithm);

    int getYear();

    void setYear(int year);

    Month getMonth();

    void setMonth(Month month);

    int getDay();

    void setDay(int day);

    int getHour();

    void setHour(int hour);

    int getMinute();

    void setMinute(int minute);

    int getSecond();

    void setSecond(int second);

    int getMillisecond();

    void setMillisecond(int millisecond);

    void setLeap(boolean leap);

    int getDayOfYear();

    void setDayOfYear(int dayOfYear);

    void setDayOfWeek(Week dayOfWeek);

    CalendarCulture getCalendarCulture();

    void setCalendarCulture(CalendarCulture calendarCulture);


    ICalendar createInstance() throws CalendarExceptions;

    ICalendar createInstance(Date date) throws CalendarExceptions;

    ICalendar createInstance(int year, int month, int day) throws CalendarExceptions;

    ICalendar createInstance(int year, int month, int day, int hour, int minute, int second) throws CalendarExceptions;

    ICalendar createInstance(int year, int month, int day, int hour, int minute, int second, int millisecond) throws CalendarExceptions;

    ICalendar createInstance(ICalendar calendar) throws CalendarExceptions;

    ICalendar  createInstance(double julianNumber) throws CalendarExceptions;


    ICalendar parseFromPatternToGregorian(String date, SimpleDateFormat dateFormat) throws ParseException, CalendarExceptions;

    ICalendar parseFromPattern(String date, SimpleDateFormat dateFormat, Class<? extends ICalendar> destinationType);

    ICalendar parseFromPatternToGregorian(String date, String dateFormat) throws ParseException, CalendarExceptions;

    ICalendar parseFromPattern(String date, String dateFormat, Class<? extends ICalendar> destinationType) throws IllegalAccessException, InstantiationException, CalendarExceptions, ParseException;

}
