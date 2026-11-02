package com.getyourguide.availability.service;

import com.getyourguide.availability.model.Activity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class ScheduleExpanderTest {

    @Test
    void keepsLocalTimeAcrossDstAndIncludesBothEnds() {
        Activity berlin = activity("Europe/Berlin", 10);
        var slots = ScheduleExpander.departures(berlin, LocalDate.of(2026, 3, 28), LocalDate.of(2026, 3, 30));
        assertThat(slots).hasSize(3);
        assertThat(slots).allSatisfy(slot -> assertThat(slot.getHour()).isEqualTo(10));
        assertThat(slots.get(1).getOffset().getTotalSeconds()).isEqualTo(2 * 3600);
    }

    @Test
    void overnightDepartureStaysOnTheRequestedDate() {
        Activity tokyo = activity("Asia/Tokyo", 22);
        var slots = ScheduleExpander.departures(tokyo, LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 1));
        assertThat(slots.get(0).toLocalDate()).isEqualTo(LocalDate.of(2026, 6, 1));
        assertThat(slots.get(0).getZone()).isEqualTo(ZoneId.of("Asia/Tokyo"));
    }

    private static Activity activity(String zone, int hour) {
        Activity activity = new Activity();
        activity.setZoneId(zone);
        activity.setLocalTime(LocalTime.of(hour, 0));
        return activity;
    }
}
