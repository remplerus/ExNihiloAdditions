package com.rempler.exnihiloadditions;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EXABlocksHelper {

    public static String titleCase(String name) {
        return Arrays.stream(name.split("_"))
                .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    private EXABlocksHelper() {
    }
}
