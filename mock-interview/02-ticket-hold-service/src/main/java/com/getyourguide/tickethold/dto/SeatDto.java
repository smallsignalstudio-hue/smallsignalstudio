package com.getyourguide.tickethold.dto;

public record SeatDto(Long id, String section, int row, int number, String priceCategory, String status, Long holdId) {
}
