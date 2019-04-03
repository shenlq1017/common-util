package com.sucsoft.jt.acjtutil

import java.util.*


/**
 * @author shenlq
 * @description id生成util
 * @date 2019-04-02 14:00
 */
class JtIdCreateUtil {

    companion object {
        fun generateUUID(): String {
            return UUID.randomUUID().toString().replace("-","")
        }

        fun generateLongID(): String {
            return System.nanoTime().toString()
        }
    }
}