package com.getyourguide.availability.service;

import com.getyourguide.availability.model.Activity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public final class ScheduleExpander {

    private ScheduleExpander() {
    }

    /** One departure per calendar date, inclusive, at the activity's local time. DST-safe. */
    public static List<ZonedDateTime> departures(Activity activity, LocalDate from, LocalDate to) {
        ZoneId zone = ZoneId.of(activity.getZoneId());
        List<ZonedDateTime> departures = new ArrayList<>();
        for (LocalDate day = from; !day.isAfter(to); day = day.plusDays(1)) {
            departures.add(ZonedDateTime.of(day, activity.getLocalTime(), zone));
        }
        return departures;
    }
}
