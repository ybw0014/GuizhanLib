package net.guizhanss.guizhanlib.minecraft.helper.entity;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.utils.StringUtil;
import org.bukkit.entity.Fox;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * 狐狸({@link Fox})
 *
 * @author ybw0014
 */
@UtilityClass
@SuppressWarnings("unused")
public final class FoxHelper {
    /**
     * 获取狐狸的类型({@link Fox.Type})的中文
     *
     * @param type {@link Fox.Type} 狐狸的类型
     *
     * @return 狐狸的类型的中文
     */
    @Nonnull
    public static String getType(@Nonnull Fox.Type type) {
        return Type.fromType(type).getChinese();
    }

    /**
     * 获取狐狸的类型({@link Fox.Type})的中文
     *
     * @param type {@link String} 狐狸的类型
     *
     * @return 狐狸的类型的中文
     */
    @Nonnull
    public static String getType(@Nonnull String type) {
        Preconditions.checkArgument(type != null, "狐狸类型不能为空");

        try {
            Fox.Type foxType = Fox.Type.valueOf(type);
            return Type.fromType(foxType).getChinese();
        } catch (IllegalArgumentException ex) {
            return StringUtil.humanize(type);
        }
    }

    /**
     * 所有狐狸的类型
     */
    public enum Type {
        /**
         * 红狐
         */
        RED(Fox.Type.RED, "Red", "红狐"),
        /**
         * 雪狐
         */
        SNOW(Fox.Type.SNOW, "Snow", "雪狐");

        @Getter
        private final Fox.Type type;
        @Getter
        private final String english;
        @Getter
        private final String chinese;

        @ParametersAreNonnullByDefault
        Type(Fox.Type type, String english, String chinese) {
            this.type = type;
            this.english = english;
            this.chinese = chinese;
        }

        /**
         * 根据狐狸的类型返回对应的枚举
         *
         * @param foxType {@link Fox.Type} 狐狸的类型
         *
         * @return 对应的枚举
         */
        @Nonnull
        public static Type fromType(@Nonnull Fox.Type foxType) {
            Preconditions.checkArgument(foxType != null, "狐狸类型不能为空");

            for (Type type : Type.values()) {
                if (type.getType() == foxType) {
                    return type;
                }
            }
            throw new IllegalArgumentException("无效的狐狸类型");
        }

        /**
         * 根据英文返回对应的枚举
         *
         * @param english {@link String} 提供的英文
         *
         * @return 对应的枚举
         */
        @Nullable
        public static Type fromEnglish(@Nonnull String english) {
            Preconditions.checkArgument(english != null, "英文不能为空");

            String humanized = StringUtil.humanize(english);
            for (Type type : Type.values()) {
                if (type.getEnglish().equals(humanized)) {
                    return type;
                }
            }
            return null;
        }

        @Nonnull
        @Override
        public String toString() {
            return this.getChinese();
        }
    }
}
