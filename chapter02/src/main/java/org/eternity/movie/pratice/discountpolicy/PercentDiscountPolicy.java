package org.eternity.movie.pratice.discountpolicy;

import org.eternity.money.Money;
import org.eternity.movie.pratice.Screening;
import org.eternity.movie.pratice.discountcondition.DiscountCondition;

public class PercentDiscountPolicy extends DiscountPolicy {
    final double percent;

    public PercentDiscountPolicy(double percent ,DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }

}
