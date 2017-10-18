package ir.koroo.kcalendar;

public class GregorianCalendarCulture extends CalendarCulture {
    public GregorianCalendarCulture() {
        weeks = new Week[]
                {
                        new Week("Saturday", 7),
                        new Week("Sunday", 1),
                        new Week("Monday", 2),
                        new Week("Tuesday", 3),
                        new Week("Wednesday", 4),
                        new Week("Thursday", 5),
                        new Week("Friday", 6, true)
                };
        months = new Month[]
                {
                        new Month("January", 1, 31),
                        new Month("February", 2, false, 28, 29),
                        new Month("March", 3, 31),
                        new Month("April", 4, 30),
                        new Month("May", 5, 31),
                        new Month("June", 6, 30),
                        new Month("July", 7, 31),
                        new Month("August", 8, 31),
                        new Month("September", 9, 30),
                        new Month("October", 10, 31),
                        new Month("November", 11, 30),
                        new Month("December", 12, 31)
                };
        AMName = "AM";
        PMName = "PM";
    }
}