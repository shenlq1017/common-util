package com.sucsoft.jt.acjtutil

import com.sucsoft.jt.acjtutil.domain.TreeVO

/**
 * 树形结构工具类
 */
class JtTreeUtil {


    companion object {

        /**
         * @author shenlq
         * @description
         * @date 2019-03-29 13:11:44
         * @modifiedBy
         *
         * 说明：树形结构通用方法
         *
         * @param datas 数据
         * @param id id的字段名
         * @param pid 父节点id
         * @param title 名称
         * @param rootIds 根节点的id数组
         * @return
         * @throws
         */
        fun <T> backTree(datas:List<T>,id: String,pid: String,title: String,rootIds: List<String>): List<TreeVO> {
            var dataTrees = ArrayList<TreeVO>()
            for (it in datas) {
                var treeMap = JtBeanUtil.beanToMapCglib(it)
                dataTrees.add(TreeVO(treeMap[id]?.toString() ,treeMap[title]?.toString(),treeMap[pid]?.toString()))
            }
            var rootTrees = dataTrees.filter { rootIds.contains(it.id) }
            for (it in rootTrees) {
                loopTree(dataTrees,it)
            }
            return rootTrees
        }

        /**
         * @author shenlq
         * @description
         * @date 2019-03-29 13:28:37
         * @modifiedBy
         *
         * 说明：递归处置children
         *
         * @param dataTrees 树形结构
         * @param rootTree 上一级节点
         * @return
         * @throws
         */
        fun loopTree(dataTrees: List<TreeVO>,rootTree: TreeVO) {
            var children = dataTrees
            var needChild = ArrayList<TreeVO>()
            for (it in children) {
                if (rootTree.id == it.parentId) {
                    needChild.add(it)
                }
            }
            for (it in needChild) {
                loopTree(children,it)
            }
            rootTree.children = needChild
        }

    }

}