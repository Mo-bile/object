package org.eternity.movie.pratice.discountpolicy;

import org.eternity.money.Money;
import org.eternity.movie.pratice.Screening;

public class NoneDiscountPolicy implements DiscountPolicy {

    // 문제점 : 이 조건은 DiscountCondition이 없어서 여기 getDiscountAmount 호출안함
    // 해결 : DiscountPolicy 를 interface 로 변경 후 여기서 calculateDiscountAmount 직접 이용하게 변경
    // 개념적 혼란과 결합 제거
//    @Override
//    protected Money getDiscountAmount(Screening screening) {
//        return Money.ZERO;
//    }

    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
