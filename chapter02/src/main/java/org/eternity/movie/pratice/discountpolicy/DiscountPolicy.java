package org.eternity.movie.pratice.discountpolicy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.eternity.money.Money;
import org.eternity.movie.pratice.discountcondition.DiscountCondition;
import org.eternity.movie.pratice.Screening;

public abstract class DiscountPolicy {

    private List<DiscountCondition> conditions = new ArrayList<>();

    public DiscountPolicy(DiscountCondition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    public Money calculateDiscountAmount(Screening screening) {
        for (DiscountCondition condition : conditions) {
            if (condition.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }
        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount(Screening screening);
}
