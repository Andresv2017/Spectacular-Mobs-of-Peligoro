package net.darkblade.smopmod.entity.interfaces.gender;

import net.minecraft.util.RandomSource;

public interface Gendered {

    enum Gender {
        MALE,
        FEMALE;

        public static Gender fromBoolean(boolean b) {
            return b ? MALE : FEMALE;
        }

        public boolean toBoolean() {
            return this == MALE;
        }

        public static Gender random(RandomSource random) {
            return random.nextBoolean() ? MALE : FEMALE;
        }
    }

    default Gender getGender() {
        return isMale() ? Gender.MALE : Gender.FEMALE;
    }

    default void setGender(Gender gender) {
        setMale(gender == Gender.MALE);
    }

    boolean isMale();
    void setMale(boolean male);
}

