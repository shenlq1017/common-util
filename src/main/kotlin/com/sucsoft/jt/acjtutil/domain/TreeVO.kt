package com.sucsoft.jt.acjtutil.domain


/**
 * @author shenlq
 * @description 树形vo
 * @date 2019-03-26 16:09
 */
class TreeVO {
    var id: String? = null
    var name: String? = null
    var parentId: String? = null
    var children: List<TreeVO> = ArrayList()

}