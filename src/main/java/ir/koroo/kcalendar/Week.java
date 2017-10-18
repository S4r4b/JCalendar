package ir.koroo.kcalendar;

public class Week {
    public Week(String name, String shortName, int dayIndex, boolean isRestDay) {
        setName(name);
        setShortName(shortName);
        setDayIndex(dayIndex);
        setRestDay(isRestDay);
    }

    public Week(String name, String shortName, int dayIndex) {
        setName(name);
        setShortName(shortName);
        setDayIndex(dayIndex);
        setRestDay(false);
    }

    public Week(String name, int dayIndex) {
        setName(name);
        setShortName(name.substring(1));
        setDayIndex(dayIndex);
        setRestDay(false);
    }

    public Week(String name, int dayIndex, boolean isRestDay) {
        setName(name);
        setShortName(name.substring(1));
        setDayIndex(dayIndex);
        setRestDay(isRestDay);
    }

    private String Name;
    private String ShortName;
    private int DayIndex;
    private boolean IsRestDay;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }

    public int getDayIndex() {
        return DayIndex;
    }

    public void setDayIndex(int dayIndex) {
        DayIndex = dayIndex;
    }

    public boolean isRestDay() {
        return IsRestDay;
    }

    public void setRestDay(boolean restDay) {
        IsRestDay = restDay;
    }
}