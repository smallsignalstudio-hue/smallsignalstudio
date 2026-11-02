package com.getyourguide.tickethold.service;

import com.getyourguide.tickethold.model.Seat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Picks consecutive seats without leaving a single unsellable seat on either side of the party. */
public final class BestSeatPicker {

    private BestSeatPicker() {
    }

    public static List<Seat> pick(List<Seat> availableInSection, int count) {
        List<Seat> ordered = availableInSection.stream()
                .sorted(Comparator.comparingInt(Seat::getSeatRow).thenComparingInt(Seat::getSeatNumber))
                .toList();
        List<Seat> chosen = null;
        int row = Integer.MIN_VALUE;
        List<Seat> run = new ArrayList<>();
        for (int i = 0; i <= ordered.size(); i++) {
            Seat seat = i < ordered.size() ? ordered.get(i) : null;
            boolean continues = seat != null && seat.getSeatRow() == row && !run.isEmpty()
                    && seat.getSeatNumber() == run.get(run.size() - 1).getSeatNumber() + 1;
            if (seat != null && (run.isEmpty() || continues)) {
                if (run.isEmpty()) {
                    row = seat.getSeatRow();
                }
                run.add(seat);
                continue;
            }
            List<Seat> candidate = window(run, count);
            if (candidate != null) {
                return candidate;
            }
            run.clear();
            if (seat != null) {
                row = seat.getSeatRow();
                run.add(seat);
            }
        }
        return chosen;
    }

    private static List<Seat> window(List<Seat> run, int count) {
        if (run.size() < count) {
            return null;
        }
        for (int offset = 0; offset <= run.size() - count; offset++) {
            int left = offset;
            int right = run.size() - count - offset;
            if (left == 1 || right == 1) {
                continue;
            }
            return List.copyOf(run.subList(offset, offset + count));
        }
        return null;
    }
}
