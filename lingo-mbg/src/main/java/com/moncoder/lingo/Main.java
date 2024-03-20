package com.moncoder.lingo;

import com.moncoder.lingo.util.Generator;



/**
 * @author Moncoder
 * @version 1.0
 * @description MBG代码生成工具使用
 * @date 2024/3/20 13:34
 */
public class Main {

    public static void main(String[] args) {
        Generator.getInstance()
                .execute();
    }
}
