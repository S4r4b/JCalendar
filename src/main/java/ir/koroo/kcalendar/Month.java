package ir.koroo.kcalendar;

public class Month {
    public Month(String name, String shortName, int index, boolean isLeapMonth, int normalLength, int leapLength) {
        setLeapMonth(isLeapMonth);
        setNormalLength(normalLength);
        setLeapLength(leapLength);
        setName(name);
        setShortName(shortName);
        setIndex(index);
    }

    public Month(String name, String shortName, int index, int normalLength) {
        setLeapMonth(false);
        setNormalLength(normalLength);
        setLeapLength(normalLength);
        setName(name);
        setShortName(shortName);
        setIndex(index);
    }

    public Month(String name, int index, boolean isLeapMonth, int normalLength, int leapLength) {
        setLeapMonth(isLeapMonth);
        setNormalLength(normalLength);
        setLeapLength(leapLength);
        setName(name);
        setShortName(name.substring(1));
        setIndex(index);
    }

    public Month(String name, int index, int normalLength) {
        setLeapMonth(false);
        setNormalLength(normalLength);
        setLeapLength(normalLength);
        setName(name);
        setShortName(name.substring(1));
        setIndex(index);
    }

    private boolean isLeapMonth;
    private int normalLength;
    private int leapLength;
    private String name;
    private String shortName;
    private int index;

    public void setLeap(boolean isLeapYear) {
        if (getLeapLength() == getNormalLength()) return;
        setLeapMonth(isLeapYear);
    }

    public boolean isLeapMonth() {
        return isLeapMonth;
    }

    public void setLeapMonth(boolean leapMonth) {
        isLeapMonth = leapMonth;
    }

    public int getNormalLength() {
        return normalLength;
    }

    public void setNormalLength(int normalLength) {
        this.normalLength = normalLength;
    }

    public int getLeapLength() {
        return leapLength;
    }

    public void setLeapLength(int leapLength) {
        this.leapLength = leapLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
//        public static implicit operator int(Month month)
//        {
//            return month.index;
//        }
}