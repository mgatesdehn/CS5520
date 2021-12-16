package edu.neu.khoury.madsea.matthewgatesdehn.db;

import android.text.TextUtils;
import android.util.Log;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.HashSet;
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
            if (tags.isEmpty()){
                return "";
            }

            String tagString = tags.toString();

            return tagString.substring(1, tagString.length()-1);
        }

        @TypeConverter
        public static Set<String> stringToSet(String tags) {
//            if (tags.equals("\u000B")) {
//                Log.d("Converters", "set from string: no tags present");
//                return new HashSet<String>();
//            }

            if (TextUtils.isEmpty(tags)) {
                Log.d("Converters", "set from string: no tags present");
                return new HashSet<String>();
            }

            Set<String> outset = Arrays.stream(tags.split("\\s*,\\s*")).collect(Collectors.toSet());
            return outset;
        }

    }
