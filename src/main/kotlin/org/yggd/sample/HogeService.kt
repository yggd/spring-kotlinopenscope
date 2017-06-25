package org.yggd.sample

import org.springframework.stereotype.Component

interface HogeService {

    fun execute() : String
}

@Component
class HogeServiceImpl : HogeService {

    override fun execute() : String = "aaaaa"

}