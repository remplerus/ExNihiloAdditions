package com.rempler.exnihiloadditions.custom.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rempler.exnihiloadditions.ExNihiloAdditions;
import net.neoforged.fml.loading.FMLPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomVariantLoader {

    private static final Logger LOGGER = ExNihiloAdditions.LOGGER;

    private static final Gson GSON = new GsonBuilder().create();

    private static final Map<String, CustomVariantConfig> VARIANTS = new LinkedHashMap<>();
    private static final Map<String, CustomDollConfig> DOLLS = new LinkedHashMap<>();

    public static void init() {
        Path base = FMLPaths.CONFIGDIR.get().resolve("exnihiloadditions");
        loadVariants(base.resolve("variants"));
        loadDolls(base.resolve("dolls"));
        LOGGER.info("[ExNihiloAdditions] Loaded {} custom variant(s) and {} custom doll(s)",
                VARIANTS.size(), DOLLS.size());
    }

    // ── Variants ─────────────────────────────────────────────────────────────

    private static void loadVariants(Path dir) {
        try {
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
                LOGGER.info("[ExNihiloAdditions] Created variants directory: {}", dir);
                return;
            }
            Files.walk(dir)
                    .filter(p -> p.toString().endsWith(".json"))
                    .forEach(p -> {
                        try {
                            String json = Files.readString(p);
                            CustomVariantConfig raw = GSON.fromJson(json, CustomVariantConfig.class);
                            String id = deriveId(dir, p);
                            CustomVariantConfig config = new CustomVariantConfig(
                                    id, raw.displayName(),
                                    raw.hasSieve(), raw.hasBarrel(), raw.hasCrucible(), raw.crucibleType());
                            if (VARIANTS.containsKey(id)) {
                                LOGGER.warn("[ExNihiloAdditions] Duplicate variant id '{}', skipping {}", id, p);
                            } else {
                                VARIANTS.put(id, config);
                                LOGGER.debug("[ExNihiloAdditions] Loaded variant '{}'", id);
                            }
                        } catch (Exception e) {
                            LOGGER.error("[ExNihiloAdditions] Failed to load variant config {}: {}", p, e.getMessage());
                        }
                    });
        } catch (IOException e) {
            LOGGER.error("[ExNihiloAdditions] Failed to scan variants directory: {}", e.getMessage());
        }
    }

    // ── Dolls ─────────────────────────────────────────────────────────────────

    private static void loadDolls(Path dir) {
        try {
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
                LOGGER.info("[ExNihiloAdditions] Created dolls directory: {}", dir);
                return;
            }
            Files.walk(dir)
                    .filter(p -> p.toString().endsWith(".json"))
                    .forEach(p -> {
                        try {
                            String json = Files.readString(p);
                            CustomDollConfig raw = GSON.fromJson(json, CustomDollConfig.class);
                            String id = deriveId(dir, p);
                            CustomDollConfig config = new CustomDollConfig(
                                    id, raw.displayName(), raw.fluid(),
                                    raw.entity(), raw.ticks(), raw.texture());
                            if (DOLLS.containsKey(id)) {
                                LOGGER.warn("[ExNihiloAdditions] Duplicate doll id '{}', skipping {}", id, p);
                            } else {
                                DOLLS.put(id, config);
                                LOGGER.debug("[ExNihiloAdditions] Loaded doll '{}'", id);
                            }
                        } catch (Exception e) {
                            LOGGER.error("[ExNihiloAdditions] Failed to load doll config {}: {}", p, e.getMessage());
                        }
                    });
        } catch (IOException e) {
            LOGGER.error("[ExNihiloAdditions] Failed to scan dolls directory: {}", e.getMessage());
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    /** "bloodwood_mod/bloodwood.json" relative to base → "bloodwood_mod/bloodwood" */
    private static String deriveId(Path base, Path file) {
        String rel = base.relativize(file).toString().replace('\\', '/');
        return rel.endsWith(".json") ? rel.substring(0, rel.length() - 5) : rel;
    }

    // ── Accessors ─────────────────────────────────────────────────────────────

    public static Collection<CustomVariantConfig> getAllVariants() { return VARIANTS.values(); }
    public static CustomVariantConfig getVariant(String id) { return VARIANTS.get(id); }

    public static Collection<CustomDollConfig> getAllDolls() { return DOLLS.values(); }
    public static CustomDollConfig getDoll(String id) { return DOLLS.get(id); }
}
