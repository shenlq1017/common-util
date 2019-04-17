package com.sucsoft.jt.acjtutil

import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType
import java.util.regex.Pattern


/**
 * @author shenlq
 * @description 关于文字类
 * @date 2019-04-03 20:09
 */
class JtLetterUtil {

    companion object {
        fun captureName(name: String): String {
            val chars = name.toCharArray()
            if (chars[0] in 'a'..'z') {
                chars[0] = (chars[0].toInt() - 32).toChar()
            }
            return String(chars)
        }

        /**
         * 获取汉字首字母或全拼大写字母
         *
         * @param chinese 汉字
         * @param isFull  是否全拼 true:表示全拼 false表示：首字母
         *
         * @return 全拼或者首字母大写字符窜
         */
        fun getUpperCase(chinese: String, isFull: Boolean): String {
            return convertHanzi2Pinyin(chinese, isFull).toUpperCase()
        }

        /**
         * 获取汉字首字母或全拼小写字母
         *
         * @param chinese 汉字
         * @param isFull 是否全拼 true:表示全拼 false表示：首字母
         *
         * @return 全拼或者首字母小写字符窜
         */
        fun getLowerCase(chinese: String, isFull: Boolean): String {
            return convertHanzi2Pinyin(chinese, isFull).toLowerCase()
        }

        fun getFirstLowerCase(chinese: String): String {
            if(chinese.isEmpty()) {
                return ""
            }
            return convertHanzi2Pinyin(chinese[0].toString(), false).toLowerCase()
        }

        /**
         * 将汉字转成拼音
         * <P>
         * 取首字母或全拼
         *
         * @param hanzi 汉字字符串
         * @param isFull 是否全拼 true:表示全拼 false表示：首字母
         *
         * @return 拼音
        </P> */
        private fun convertHanzi2Pinyin(hanzi: String?, isFull: Boolean): String {
            /***
             * ^[\u2E80-\u9FFF]+$ 匹配所有东亚区的语言
             * ^[\u4E00-\u9FFF]+$ 匹配简体和繁体
             * ^[\u4E00-\u9FA5]+$ 匹配简体
             */
            val regExp = "^[\u4E00-\u9FFF]+$"
            val sb = StringBuffer()
            if (hanzi == null || "" == hanzi.trim { it <= ' ' }) {
                return ""
            }
            var pinyin = ""
            for (i in 0 until hanzi.length) {
                val unit = hanzi[i]
                //是汉字，则转拼音
                if (match(unit.toString(), regExp)) {
                    pinyin = convertSingleHanzi2Pinyin(unit)
                    if (isFull) {
                        sb.append(pinyin)
                    } else {
                        sb.append(pinyin[0])
                    }
                } else {
                    sb.append(unit)
                }
            }
            return sb.toString()
        }

        /**
         * 将单个汉字转成拼音
         *
         * @param hanzi 汉字字符
         *
         * @return 拼音
         */
        private fun convertSingleHanzi2Pinyin(hanzi: Char): String {
            val outputFormat = HanyuPinyinOutputFormat()
            outputFormat.toneType = HanyuPinyinToneType.WITHOUT_TONE
            val res: Array<String>
            val sb = StringBuffer()
            try {
                res = PinyinHelper.toHanyuPinyinStringArray(hanzi, outputFormat)
                sb.append(res[0])//对于多音字，只用第一个拼音
            } catch (e: Exception) {
                e.printStackTrace()
                return ""
            }

            return sb.toString()
        }

        /***
         * 匹配
         * <P>
         * 根据字符和正则表达式进行匹配
         *
         * @param str 源字符串
         * @param regex 正则表达式
         *
         * @return true：匹配成功  false：匹配失败
        </P> */
        private fun match(str: String, regex: String): Boolean {
            val pattern = Pattern.compile(regex)
            val matcher = pattern.matcher(str)
            return matcher.find()
        }


//        @JvmStatic
//        fun main(args: Array<String>) {
//            println(getFirstLowerCase("角恐龙",false).toUpperCase())
//            println(getLowerCase("asdad数据库alsass))",true))
//        }
    }
}