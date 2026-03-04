package org.eternity.practice.client;

import java.time.Duration;
import org.eternity.money.Money;
import org.eternity.practice.Factory;
import org.eternity.practice.Movie;
import org.eternity.practice.pricing.AmountDiscountPolicy;
import org.eternity.practice.pricing.SequenceCondition;

public class Client {

    private Factory factory;

    public Money getAvatarFee() {
        Movie avatar = new Movie("아바타",
                Duration.ofMinutes(120),
                Money.wons(10000),
                new AmountDiscountPolicy(
                    Money.wons(800),
                    new SequenceCondition(1),
                    new SequenceCondition(10)));
        return avatar.getFee();
    }

    public Money getAvatarFee2() {
        return new Movie("아바타",
                Duration.ofMinutes(120),
                Money.wons(10000)
        ).getFee();
    }

    public Money getAvatarFee3() {
        return factory.createAvatarMovie().getFee();
    }
}
