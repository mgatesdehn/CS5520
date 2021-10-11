package edu.neu.khoury.madsea.matthewgatesdehn.db;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Converters {
        @TypeConverter
        public static LocalDateTime fromTimestamp(Long value) {
            return value == null ? null : LocalDateTime.ofInstant(Instant.ofEpochSecond(value), ZoneOffset.UTC);
        }

        @TypeConverter
        public static Long dateToTimestamp(LocalDateTime localDateTime) {
            return localDateTime == null ? null : localDateTime.toEpochSecond(ZoneOffset.UTC);
        }

        @TypeConverter
        public static String setToString(Set<String> tags) {
            return tags.toString();
        }

        @TypeConverter
        public static Set<String> stringToSet(String tags) {
            return Arrays.stream(tags.substring(1,tags.length()-1).split("\\s*,\\s*")).collect(Collectors.toSet());
        }

    }
