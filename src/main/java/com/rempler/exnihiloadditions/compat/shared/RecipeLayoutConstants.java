package com.rempler.exnihiloadditions.compat.shared;

/**
 * Shared layout constants and calculations for recipe displays across JEI, EMI, and REI.
 * EMI serves as the gold standard - JEI and REI should match EMI's layout as closely as possible.
 *
 * All coordinates are relative to the recipe display area (0,0 = top-left of the recipe widget).
 */
public final class RecipeLayoutConstants {

    private RecipeLayoutConstants() {}

    // ── Grid layout: used by Compost, Crushing, Harvest, Melting ──
    /** Max items per row in grid-based displays */
    public static final int GRID_COLS = 7;
    /** Slot spacing in grid layouts */
    public static final int SLOT_SIZE = 18;

    // ── Simple recipe layout: Input → Arrow → Output ──
    /** Width for simple 1-input, 1-output recipes (Compost, Melting) */
    public static final int SIMPLE_WIDTH = 70;
    public static final int SIMPLE_HEIGHT = 18;
    /** Arrow X offset from input slot */
    public static final int ARROW_OFFSET_X = 19;
    /** Output slot X offset from start */
    public static final int OUTPUT_OFFSET_X = 43;

    // ── Compost ──
    public static final int COMPOST_WIDTH = SIMPLE_WIDTH;
    public static final int COMPOST_HEIGHT = SIMPLE_HEIGHT;

    // ── Melting ──
    public static final int MELTING_WIDTH = SIMPLE_WIDTH;
    public static final int MELTING_HEIGHT = 20;

    // ── Crushing / Harvest (variable-width grid) ──
    /** First output slot X */
    public static final int GRID_OUTPUT_START_X = 43;

    /**
     * Calculate display width for grid-based recipes.
     * Width shrinks when fewer outputs are present.
     */
    public static int gridDisplayWidth(int outputCount) {
        int cols = Math.min(outputCount, GRID_COLS);
        return GRID_OUTPUT_START_X + cols * SLOT_SIZE;
    }

    /**
     * Calculate display height for grid-based recipes.
     */
    public static int gridDisplayHeight(int outputCount) {
        if (outputCount <= 0) return SLOT_SIZE;
        int rows = (outputCount - 1) / GRID_COLS + 1;
        return rows * SLOT_SIZE;
    }

    // ── Solidifying: FluidInTank + FluidOnTop → Item ──
    public static final int SOLIDIFYING_WIDTH = SIMPLE_WIDTH;
    public static final int SOLIDIFYING_HEIGHT = 40;
    public static final int SOLIDIFYING_TANK_X = 0;
    public static final int SOLIDIFYING_TANK_Y = 20;
    public static final int SOLIDIFYING_CATALYST_X = 25;
    public static final int SOLIDIFYING_CATALYST_Y = 0;
    public static final int SOLIDIFYING_ARROW_X = 22;
    public static final int SOLIDIFYING_ARROW_Y = 20;
    public static final int SOLIDIFYING_OUTPUT_X = 51;
    public static final int SOLIDIFYING_OUTPUT_Y = 20;

    // ── Precipitate: Fluid + Item → Item ──
    public static final int PRECIPITATE_WIDTH = SIMPLE_WIDTH;
    public static final int PRECIPITATE_HEIGHT = 40;
    public static final int PRECIPITATE_FLUID_X = 0;
    public static final int PRECIPITATE_FLUID_Y = 20;
    public static final int PRECIPITATE_ITEM_X = 25;
    public static final int PRECIPITATE_ITEM_Y = 0;
    public static final int PRECIPITATE_ARROW_X = 22;
    public static final int PRECIPITATE_ARROW_Y = 19;
    public static final int PRECIPITATE_OUTPUT_X = 51;
    public static final int PRECIPITATE_OUTPUT_Y = 20;

    // ── Transition: Fluid → (Barrel + Catalyst blocks) → Fluid ──
    public static final int TRANSITION_WIDTH = SIMPLE_WIDTH;
    public static final int TRANSITION_HEIGHT = 35;
    public static final int TRANSITION_INPUT_FLUID_X = 0;
    public static final int TRANSITION_INPUT_FLUID_Y = 0;
    public static final int TRANSITION_ARROW1_X = 18;
    public static final int TRANSITION_ARROW1_Y = 1;
    public static final int TRANSITION_BLOCK_X = 32;
    public static final int TRANSITION_BARREL_Y = 6;
    public static final int TRANSITION_CATALYST_Y = 21;
    public static final int TRANSITION_OUTPUT_FLUID_X = 51;
    public static final int TRANSITION_OUTPUT_FLUID_Y = 0;

    // ── Heat: Block + Crucible block render ──
    public static final int HEAT_WIDTH = SIMPLE_WIDTH;
    public static final int HEAT_HEIGHT = 40;
    public static final int HEAT_INPUT_X = 0;
    public static final int HEAT_INPUT_Y = 12;
    public static final int HEAT_BLOCK_RENDER_X = 55;

    // ── Sifting: Input → [Output + Mesh] grid ──
    /** Number of output columns for sifting before wrapping */
    public static final int SIFT_OUTPUT_COLS = 7;
    /** X position where sifting outputs start */
    public static final int SIFT_OUTPUT_START_X = 43;
    /** Y spacing between output row and its mesh row beneath */
    public static final int SIFT_MESH_OFFSET_Y = 20;

    /**
     * Calculate sifting display width based on output count.
     * Mirrors EMI's logic exactly.
     */
    public static int siftDisplayWidth(int outputCount) {
        int cols = Math.min(outputCount, SIFT_OUTPUT_COLS);
        // EMI: 187 - (7-cols)*18 for cols <= 7
        if (cols == 0) return 187;
        return 187 - (SIFT_OUTPUT_COLS - cols) * SLOT_SIZE;
    }

    /**
     * Calculate sifting display height based on output count.
     * Each row of 7 = 18px outputs + 20px mesh row below.
     */
    public static int siftDisplayHeight(int outputCount) {
        int rows = outputCount <= 0 ? 1 : (outputCount - 1) / SIFT_OUTPUT_COLS + 1;
        return 40 + (rows - 1) * SLOT_SIZE;
    }

    // ── Tank rendering size (common for all fluid-using categories) ──
    public static final int TANK_WIDTH = 18;
    public static final int TANK_HEIGHT = 18;
    public static final int TANK_CAPACITY = 1000;
    public static final float BLOCK_RENDER_SCALE = 13f;
}
