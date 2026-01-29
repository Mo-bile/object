package org.eternity.movie.pratice.service;

import static org.junit.Assert.assertEquals;
import java.lang.reflect.Field;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.eternity.money.Money;
import org.eternity.movie.pratice.Customer;
import org.eternity.movie.pratice.Movie;
import org.eternity.movie.pratice.Reservation;
import org.eternity.movie.pratice.Screening;
import org.eternity.movie.pratice.discountcondition.PeriodCondition;
import org.eternity.movie.pratice.discountcondition.SequenceCondition;
import org.eternity.movie.pratice.discountpolicy.AmountDefaultDiscountPolicy;
import org.eternity.movie.pratice.discountpolicy.NoneDiscountPolicy;
import org.junit.Test;

public class ReservationServiceTest {

    @Test
    public void createsReservationWithDiscountedFee() throws Exception {
        Movie avatar = new Movie(
            "아바타",
            Duration.ofMinutes(120),
            Money.wons(1000),
            new AmountDefaultDiscountPolicy(Money.wons(800),
                new SequenceCondition(1),
                new SequenceCondition(10),
                new PeriodCondition(
                    DayOfWeek.WEDNESDAY,
                    LocalTime.of(10, 0),
                    LocalTime.of(11, 59)),
                new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(20, 59))
            )
        );

        Customer customer = new Customer("모", "1");
        Screening screening = new Screening(avatar, 1, LocalDateTime.of(2026, 1, 25, 10, 0));
        Reservation reservation = screening.reserve(customer, 2);

        Money expectedFee = avatar.calculateMovieFee(screening).times(2);

        assertEquals(expectedFee, readFee(reservation));
        assertEquals(2, readAudienceCount(reservation));
    }

    @Test
    public void createsReservationWithoutDiscount() throws Exception {
        Movie soul = new Movie(
            "soul",
            Duration.ofMinutes(120),
            Money.wons(10000),
            new NoneDiscountPolicy()
        );

        Customer customer = new Customer("모", "1");
        Screening screening = new Screening(soul, 1, LocalDateTime.of(2026, 1, 25, 10, 0));
        Reservation reservation = screening.reserve(customer, 1);

        Money expectedFee = soul.calculateMovieFee(screening).times(1);

        assertEquals(expectedFee, readFee(reservation));
        assertEquals(1, readAudienceCount(reservation));
    }

    // Reservation에 getter가 없어서 학습 목적의 테스트에서만 리플렉션으로 확인한다.
    private Money readFee(Reservation reservation) throws Exception {
        Field feeField = Reservation.class.getDeclaredField("fee");
        feeField.setAccessible(true);
        return (Money) feeField.get(reservation);
    }

    private int readAudienceCount(Reservation reservation) throws Exception {
        Field countField = Reservation.class.getDeclaredField("audienceCount");
        countField.setAccessible(true);
        return (int) countField.get(reservation);
    }
}
