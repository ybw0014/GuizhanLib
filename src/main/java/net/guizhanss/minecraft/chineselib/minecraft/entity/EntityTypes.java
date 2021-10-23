package net.guizhanss.minecraft.chineselib.minecraft.entity;

import lombok.Getter;
import net.guizhanss.minecraft.chineselib.utils.StringUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 所有实体类型
 * @author ybw0014
 */
public enum EntityTypes {
    AREA_EFFECT_CLOUD("", ""),
    ARMOR_STAND("", ""),
    ARROW("", ""),
    AXOLOTL("", ""),
    BAT("", ""),
    BEE("", ""),
    BLAZE("", ""),
    BOAT("", ""),
    CAT("", ""),
    CAVE_SPIDER("", ""),
    CHICKEN("", ""),
    COD("", ""),
    COW("", ""),
    CREEPER("", ""),
    DOLPHIN("", ""),
    DONKEY("", ""),
    DRAGON_FIREBALL("", ""),
    DROPPED_ITEM("", ""),
    DROWNED("", ""),
    EGG("", ""),
    ELDER_GUARDIAN("", ""),
    ENDER_CRYSTAL("", ""),
    ENDER_DRAGON("", ""),
    ENDER_PEARL("", ""),
    ENDER_SIGNAL("", ""),
    ENDERMAN("", ""),
    ENDERMITE("", ""),
    EVOKER("", ""),
    EVOKER_FANGS("", ""),
    EXPERIENCE_ORB("", ""),
    FALLING_BLOCK("", ""),
    FIREBALL("", ""),
    FIREWORK("", ""),
    FISHING_HOOK("", ""),
    FOX("", ""),
    GHAST("", ""),
    GIANT("", ""),
    GLOW_ITEM_FRAME("", ""),
    GLOW_SQUID("", ""),
    GOAT("", ""),
    GUARDIAN("", ""),
    HOGLIN("", ""),
    HORSE("", ""),
    HUSK("", ""),
    ILLUSIONER("", ""),
    IRON_GOLEM("", ""),
    ITEM_FRAME("", ""),
    LEASH_HITCH("", ""),
    LIGHTNING("", ""),
    LLAMA("", ""),
    LLAMA_SPIT("", ""),
    MAGMA_CUBE("", ""),
    MARKER("", ""),
    MINECART("", ""),
    MINECART_CHEST("", ""),
    MINECART_COMMAND("", ""),
    MINECART_FURNACE("", ""),
    MINECART_HOPPER("", ""),
    MINECART_MOB_SPAWNER("", ""),
    MINECART_TNT("", ""),
    MULE("", ""),
    MUSHROOM_COW("", ""),
    OCELOT("", ""),
    PAINTING("", ""),
    PANDA("", ""),
    PARROT("", ""),
    PHANTOM("", ""),
    PIG("", ""),
    PIGLIN("", ""),
    PIGLIN_BRUTE("", ""),
    PILLAGER("", ""),
    PLAYER("", ""),
    POLAR_BEAR("", ""),
    PRIMED_TNT("", ""),
    PUFFERFISH("", ""),
    RABBIT("", ""),
    RAVAGER("", ""),
    SALMON("", ""),
    SHEEP("", ""),
    SHULKER("", ""),
    SHULKER_BULLET("", ""),
    SILVERFISH("", ""),
    SKELETON("", ""),
    SKELETON_HORSE("", ""),
    SLIME("", ""),
    SMALL_FIREBALL("", ""),
    SNOWBALL("", ""),
    SNOWMAN("", ""),
    SPECTRAL_ARROW("", ""),
    SPIDER("", ""),
    SPLASH_POTION("", ""),
    SQUID("", ""),
    STRAY("", ""),
    STRIDER("", ""),
    THROWN_EXP_BOTTLE("", ""),
    TRADER_LLAMA("", ""),
    TRIDENT("", ""),
    TROPICAL_FISH("", ""),
    TURTLE("", ""),
    UNKNOWN("", ""),
    VEX("", ""),
    VILLAGER("", ""),
    VINDICATOR("", ""),
    WANDERING_TRADER("", ""),
    WITCH("", ""),
    WITHER("", ""),
    WITHER_SKELETON("", ""),
    WITHER_SKULL("", ""),
    WOLF("", ""),
    ZOGLIN("", ""),
    ZOMBIE("", ""),
    ZOMBIE_HORSE("", ""),
    ZOMBIE_VILLAGER("", ""),
    ZOMBIFIED_PIGLIN("", "");

    private @Getter String english;
    private @Getter String chinese;

    EntityTypes(@Nonnull String english, @Nonnull String chinese) {
        this.english = english;
        this.chinese = chinese;
    }

    @Override
    public String toString() {
        return this.chinese;
    }

    /**
     * 根据英文返回对应的枚举类型
     * @param english 提供的英文类型
     * @return 对应的枚举类型
     */
    @Nullable
    public static EntityTypes fromEnglish(String english) {
        String humanized = StringUtil.humanize(english);
        for (EntityTypes type : EntityTypes.values()) {
            if (type.getEnglish().equals(humanized)) {
                return type;
            }
        }
        return null;
    }
}
