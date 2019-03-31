package com.sucsoft.jt.acjtutil.domain


/**
 * @author shenlq
 * @description 树形vo
 * @date 2019-03-26 16:09
 */
class TreeVO {
    var id: String? = null
    var title: String? = null
    var parentId: String? = null
    var expand: Boolean? = false
    var children: List<TreeVO> = ArrayList()

    constructor()
    constructor(id: String?, title: String?, parentId: String?) {
        this.id = id
        this.title = title
        this.parentId = parentId
    }


}