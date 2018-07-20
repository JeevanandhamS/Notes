package com.jeeva.notes.data.db;

import android.arch.persistence.room.TypeConverter;

import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * Created by jeevanandham on 19/07/18
 */
public class DateTypeConverter {

    private static DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @TypeConverter
    public static OffsetDateTime toOffsetDateTime(String value) {
        return null != value ? OffsetDateTime.parse(value) : null;
    }

    @TypeConverter
    public static String fromOffsetDateTime(OffsetDateTime date) {
        return null != date ? date.format(formatter) : null;
    }
}