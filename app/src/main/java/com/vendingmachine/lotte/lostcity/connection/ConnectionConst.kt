package com.vendingmachine.lotte.lostcity.connection

import java.math.BigInteger
import java.util.*

object ConnectionConst {
    private val code = "0f14d0ab96054a62a9e45ed26688389b"
    val GameUUID = UUID(BigInteger(code.substring(0,16),16).toLong(), BigInteger(code.substring(16),16).toLong())!!

}