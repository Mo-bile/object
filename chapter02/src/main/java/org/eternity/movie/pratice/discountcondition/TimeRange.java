package org.eternity.movie.pratice.discountcondition;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeRange {

    private LocalTime startTime;
    private LocalTime endTime;

    public TimeRange(LocalTime startTime, LocalTime endTime) {
        validate(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void validate(LocalTime startTime, LocalTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("끝나는 시간이 시작 시간보다 앞에 올 수 없습니다");
        }
    }

    public boolean contains(LocalDateTime whenScreened) {
        return !startTime.isAfter(whenScreened.toLocalTime()) && !endTime.isBefore(whenScreened.toLocalTime());
    }
}