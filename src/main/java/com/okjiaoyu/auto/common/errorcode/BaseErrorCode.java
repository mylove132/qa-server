package com.okjiaoyu.auto.common.errorcode;

/**
 * 公共常量
 */
public interface BaseErrorCode<K, V, C extends Enum>  {

    /**
     * 返回枚举对象
     * */
    C get();

    /**
     * 返回枚举项的 key
     * */
    K key();

    /**
     * 返回枚举项的 value
     * */
    V value();
}
