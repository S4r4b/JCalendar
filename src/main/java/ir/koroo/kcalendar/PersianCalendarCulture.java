package ir.koroo.kcalendar;

public class PersianCalendarCulture extends CalendarCulture {
    public PersianCalendarCulture() {
        weeks = new Week[]
                {
                        new Week("شنبه", 7),
                        new Week("یکشنبه", 1),
                        new Week("دوشنبه", 2),
                        new Week("سه شنبه", 3),
                        new Week("چهارشنبه", 4),
                        new Week("پنجشنبه", 5),
                        new Week("جمعه", 6, true)
                };
        months = new Month[]
                {
                        new Month("فروردین", 1, 31),
                        new Month("اردیبهشت", 2, 31),
                        new Month("خرداد", 3, 31),
                        new Month("تیر", 4, 31),
                        new Month("مرداد", 5, 31),
                        new Month("شهریور", 6, 31),
                        new Month("مهر", 7, 30),
                        new Month("آبان", 8, 30),
                        new Month("آذر", 9, 30),
                        new Month("دی", 10, 30),
                        new Month("بهمن", 11, 30),
                        new Month("اسفند", 12, false, 29, 30)
                };
        AMName = "ق.ظ";
        PMName = "ب.ظ";
    }


}