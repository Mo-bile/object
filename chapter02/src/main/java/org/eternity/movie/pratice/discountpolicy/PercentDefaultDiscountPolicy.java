package org.eternity.movie.pratice.discountpolicy;

import org.eternity.money.Money;
import org.eternity.movie.pratice.Screening;
import org.eternity.movie.pratice.discountcondition.DiscountCondition;

public class PercentDefaultDiscountPolicy extends DefaultDiscountPolicy {
    final double percent;

    public PercentDefaultDiscountPolicy(double percent ,DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }

}
