package ii_conventions

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class MyDate (val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

    override operator fun compareTo(other: MyDate): Int {
        if (year != other.year) return year - other.year
        if (month != other.month) return month - other.month
        return dayOfMonth - other.dayOfMonth
    }

    operator fun plus(interval: TimeInterval): MyDate {
        return addTimeIntervals(interval, 1)
    }

    operator fun plus(interval: RepeatedTimeInterval): MyDate {
        return addTimeIntervals(interval.ti, interval.n)
    }

    operator fun times(interval: RepeatedTimeInterval): MyDate {
        return addTimeIntervals(interval.ti, interval.n)
    }
}

operator fun MyDate.rangeTo(end: MyDate) = DateRange(this, end)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(i: Int): RepeatedTimeInterval {
        return RepeatedTimeInterval(this, i)
    }
}

class DateRange(public override val start: MyDate, public override val end: MyDate) : Iterable<MyDate>, Range<MyDate> {

    operator override fun contains(item: MyDate): Boolean {
        for (date in this) {
            if (date == item) {
                return true
            }
        }
        return false
    }

    override fun iterator(): Iterator<MyDate> {
        var day = start
        return object : Iterator<MyDate> {
            override fun next(): MyDate {
                val oldDate = day
                day = oldDate.addTimeIntervals(TimeInterval.DAY, 1)
                return oldDate
            }

            override fun hasNext(): Boolean {
                return day <= end
            }
        }
    }

}
