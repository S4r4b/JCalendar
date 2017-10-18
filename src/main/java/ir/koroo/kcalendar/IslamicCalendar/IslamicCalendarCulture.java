package ir.koroo.kcalendar.IslamicCalendar;

import ir.koroo.kcalendar.CalendarCulture;
import ir.koroo.kcalendar.Month;
import ir.koroo.kcalendar.Week;

public class IslamicCalendarCulture extends CalendarCulture {
    public IslamicCalendarCulture() {
        weeks = new Week[]
                {
                        new Week("الأحد", 7),
                        new Week("الإثنين", 1),
                        new Week("الثلاثاء", 2),
                        new Week("الأربعاء", 3),
                        new Week("الخميس", 4),
                        new Week("الجمعة", 5),
                        new Week("السبت", 6, true)
                };
        months = new Month[]
                {
                        new Month("محرم", 1, false, 29, 30),
                        new Month("صفر", 2, false, 29, 30),
                        new Month("ربیع‌الاول", 3, false, 29, 30),
                        new Month("ربیع‌الثانی", 4, false, 29, 30),
                        new Month("جمادی‌الاول", 5, false, 29, 30),
                        new Month("جمادی‌الثانی", 6, false, 29, 30),
                        new Month("رجب", 7, false, 29, 30),
                        new Month("شعبان", 8, false, 29, 30),
                        new Month("رمضان", 9, false, 29, 30),
                        new Month("شوال", 10, false, 29, 30),
                        new Month("ذیقعده", 11, false, 29, 30),
                        new Month("ذیحجه", 12, false, 29, 30)
                };

        PMName = "مساء";
        AMName = "صباحاً";
    }

}