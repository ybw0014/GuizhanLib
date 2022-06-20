package net.guizhanss.guizhanlib.utils;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 字符串工具包
 *
 * @author ybw0014
 */
@UtilityClass
public final class StringUtil {
    /**
     * 将字符串转化为可读形式
     *
     * @param str 要转化的字符
     * @return 转化后的字符串
     */
    @Nonnull
    public static String humanize(@Nonnull String str) {
        Preconditions.checkNotNull(str, "the string should not be null");

        StringBuilder builder = new StringBuilder();
        str = str.toLowerCase(Locale.ROOT).replace(" ", "_");
        String[] segments = Pattern.compile("_").split(str);
        builder.append(capitalize(segments[0]));
        for (int i = 1; i < segments.length; i++) {
            builder.append(" ");
            builder.append(capitalize(segments[i]));
        }
        return builder.toString();
    }

    /**
     * 将字符串转化为大写+下划线形式
     *
     * @param str 要转化的字符
     * @return 转化后的字符串
     */
    @Nonnull
    public static String dehumanize(@Nonnull String str) {
        Preconditions.checkNotNull(str, "the string should not be null");

        return str.toUpperCase(Locale.ROOT)
            .replace(" ", "_")
            .replace("-", "_");
    }

    /**
     * 字符串是否为空
     *
     * @param str 要测试的字符
     * @return 是否为空
     */
    public static boolean isBlank(@Nullable String str) {
        if (str == null) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 首字母大写
     *
     * @param str 需要转换的字符串
     * @return 转换后的字符串
     */
    @Nullable
    public static String capitalize(@Nullable String str) {
        return capitalize(str, null);
    }

    /**
     * 首字母大写
     *
     * @param str 需要转换的字符串
     * @param delimiters 单词分割符
     * @return 转换后的字符串
     */
    public static String capitalize(String str, char[] delimiters) {
        int delimLen = (delimiters == null ? -1 : delimiters.length);
        if (str == null || str.length() == 0 || delimLen == 0) {
            return str;
        }
        int strLen = str.length();
        StringBuffer buffer = new StringBuffer(strLen);
        boolean capitalizeNext = true;
        for (int i = 0; i < strLen; i++) {
            char ch = str.charAt(i);

            if (isDelimiter(ch, delimiters)) {
                buffer.append(ch);
                capitalizeNext = true;
            } else if (capitalizeNext) {
                buffer.append(Character.toTitleCase(ch));
                capitalizeNext = false;
            } else {
                buffer.append(ch);
            }
        }
        return buffer.toString();
    }

    /**
     * 字符是否为单词分割符
     *
     * @param ch 要检测的字符
     * @param delimiters 分割符列表
     * @return 是否为单词分割符
     */
    private static boolean isDelimiter(char ch, char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch);
        }
        for (char delimiter : delimiters) {
            if (ch == delimiter) {
                return true;
            }
        }
        return false;
    }
}
