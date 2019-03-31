package com.sucsoft.jt.acjtutil

import com.sucsoft.jt.acjtutil.domain.TreeVO
import net.sf.cglib.beans.BeanMap
import java.beans.Introspector
import java.lang.reflect.Modifier
import java.util.HashMap
import kotlin.reflect.full.memberProperties


/**
 * @author shenlq
 * @description 关于类-map转换工具类
 * @date 2019-03-28 13:27
 */
class JtBeanUtil {

    companion object {
        /**
         * cglib 的javabean 转map
         */
        fun <T> beanToMapCglib(bean: T): Map<String, Any?> {
            var map = HashMap<String, Any?>()
            if (bean != null) {
                if(bean is Map<*,*>) {
                    map = bean as HashMap<String, Any?>
                }else {
                    val beanMap = BeanMap.create(bean)
                    for ((key, value) in beanMap) {
                        map[key.toString()] = value
                    }
                }
            }
            return map
        }

        /**
         * cglib 将map装换为javabean对象
         */
        fun <T> mapToBeanCglib(map: Map<String, Any?>, bean: T): T {
            val beanMap = BeanMap.create(bean)
            beanMap.putAll(map)
            return bean
        }

        /**
         * 普通 将map装换为javabean对象
         */
        @Throws(Exception::class)
        fun mapToObjectB(map: Map<String, Any?>, beanClass: Class<*>): Any? {
            if (map == null) {
                return null
            }
            val obj = beanClass.newInstance()

            val beanInfo = Introspector.getBeanInfo(obj.javaClass)
            val propertyDescriptors = beanInfo.propertyDescriptors
            for (property in propertyDescriptors) {
                val setter = property.writeMethod
                setter?.invoke(obj, map[property.name])
            }

            return obj
        }
        /**
         * 普通 的javabean 转map
         */
        @Throws(Exception::class)
        fun objectToMapB(obj: Any): Map<String, Any?> {
            val map = HashMap<String, Any?>()

            val beanInfo = Introspector.getBeanInfo(obj::class.java)
            val propertyDescriptors = beanInfo.propertyDescriptors
            for (property in propertyDescriptors) {
                val key = property.name
                if (key.compareTo("class", ignoreCase = true) == 0) {
                    continue
                }
                val getter = property.readMethod
                val value = getter?.invoke(obj)
                map[key] = value
            }

            return map
        }


    }

}