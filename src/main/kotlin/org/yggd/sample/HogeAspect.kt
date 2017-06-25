package org.yggd.sample

import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class HogeAspect {

    @After("execution(* org.yggd.sample.HogeService.execute(..))")
    fun after() {
        throw IllegalStateException("force throwing...")
    }
}