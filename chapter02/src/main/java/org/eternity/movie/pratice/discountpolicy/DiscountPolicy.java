package org.eternity.movie.pratice.discountpolicy;

import org.eternity.money.Money;
import org.eternity.movie.pratice.Screening;

public interface DiscountPolicy {

    Money calculateDiscountAmount(Screening screening);
}
