package org.eternity.practice;

import java.time.Duration;
import org.eternity.money.Money;

public class Factory {

    public Movie createAvatarMovie() {
        return new Movie("아바타",
                Duration.ofMinutes(120),
                Money.wons(10000)
        );
    }
}
