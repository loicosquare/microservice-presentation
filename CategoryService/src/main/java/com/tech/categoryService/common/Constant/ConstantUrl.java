package com.tech.categoryService.common.Constant;

public class ConstantUrl {

    private ConstantUrl(){

    }

    /* Start URL Related to Games. */
        public static final String GET_GAME_BY_CATEGORY = "/{categoryId}/games";
        public static final String ADD_GAME_TO_CATEGORY = "/{categoryId}/game";
        public static final String GAMES = "/games";
        public static final String GET_ALL_GAME = "/all";
        public static final String GET_ONE_GAME = "/{gameId}";
        public static final String UPDATE_GAME = "/update/{gameId}";
        public static final String DELETE_GAME = "/delete/{gameId}";
        public static final String CREATE_GAME = "/create";
    /* End URL Related to Games. */

    /* Start URL Related to categories. */
        public static final String CATEGORIES = "/categories";
        public static final String GET_ALL_CATEGORIES = "/all";
        public static final String GET_ONE_CATEGORY = "/{categoryId}";
        public static final String UPDATE_CATEGORY = "/update/{categoryId}";
        public static final String DELETE_CATEGORY = "/delete/{categoryId}";
        public static final String CREATE_CATEGORY = "/create";
    public static final String TEMP_IMAGE_BASE_URL = "https://robohash.org/";
    /* End URL Related to categories. */
}
