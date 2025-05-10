package net.darkblade.smopmod.entity;

import java.util.Arrays;
import java.util.Comparator;

public enum TangofteroVariant {

    BLACK(0),
    BLUE(1),
    BROWN(2),
    CREAM(3),
    LAVANDER(4),
    RED(5),
    SILVER(6),
    WHITE(7),
    YELLOW(8);

    private static final TangofteroVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(TangofteroVariant::getId)).toArray(TangofteroVariant[]::new);
    private final int id;

    TangofteroVariant(int id){
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static TangofteroVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
