package com.newborne.yizhi.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * The enum Sex enum.
 */
public enum SexEnum implements IEnum<Integer> {

    /**
     * Man sex enum.
     */
    MAN(1,"男"),
    /**
     * Woman sex enum.
     */
    WOMAN(2,"女"),
    /**
     * Unknown sex enum.
     */
    UNKNOWN(3,"未知");

    private int value;
    private String desc;

    SexEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
