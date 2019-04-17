package com.sucsoft.jt.acjtutil.domain

import java.io.Serializable


/**
 * @author shenlq
 * @description 树形vo
 * @date 2019-03-26 16:09
 */
class TreeVO: Serializable {
    var id: String? = null
    var title: String? = null
    var parentId: String? = null
    var expand: Boolean? = false
    var belongNum: Int = 0
    var children: List<TreeVO> = ArrayList()

    constructor()
    constructor(id: String?, title: String?, parentId: String?) {
        this.id = id
        this.title = title
        this.parentId = parentId
    }

    constructor(id: String?, title: String?, parentId: String?, belongNum: Int) {
        this.id = id
        this.title = title
        this.parentId = parentId
        this.belongNum = belongNum
    }


}