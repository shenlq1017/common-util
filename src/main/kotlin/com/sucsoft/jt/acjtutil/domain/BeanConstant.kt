package com.sucsoft.jt.acjtutil.domain

/**
 * 对象常量
 */
enum class BeanConstant constructor(val value: Int){
    //已经删除
    ISDELETED(1),
    //未删除
    UNDELETED(0),
    //是
    YES(1),
    //否
    NO(0),
    PAGE_INIT(1),
    PAGE_MAX(10000000)
}