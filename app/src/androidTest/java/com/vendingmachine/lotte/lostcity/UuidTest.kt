package com.vendingmachine.lotte.lostcity

import android.support.test.runner.AndroidJUnit4
import com.vendingmachine.lotte.lostcity.connection.ConnectionConst
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import org.junit.Assert.*
import java.math.BigInteger

@RunWith(AndroidJUnit4::class)
class UuidTest {
    /**
     * UUID 유효성 검사
     */
    @Test
    fun checkUUID(){
        assertNotNull("Test UUID is Null", ConnectionConst.GameUUID)
    }
}