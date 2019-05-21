package ngocngan.store.constant;

import ngocngan.store.controller.BaseController;

/**
 * @author ngan nnh on 5/16/2019
 * @project store
 */

public class Constant {

    // Log list config

    // Log product config
    public static String LOGGER_SAVE_PRODUCT_SUCCESS(String name) {
        return "SAVE PRODUCT " + name + " SUCCESS!";
    }

    public static String LOGGER_DELETE_PRODUCT_SUCCESS(String uuid) {
        return "DELETE PRODUCT " + uuid + " SUCCESS!";
    }

    public static String LOGGER_PRODUCT_FOUND(String uuid) {
        return "THE PRODUCT " + uuid + " BE FOUND";
    }

    public static String LOGGER_PRODUCT_NOT_FOUND(String uuid) {
        return "THE PRODUCT " + uuid + " CAN'T BE FOUND";
    }

    public static String LOGGER_INFO_PRODUCTS_SIZE(int size) {
        return "SIZE OF PRODUCT LIST: " + size;
    }

    // Log USER config
    public static String LOGGER_SAVE_USER_SUCCESS(String email) {
        return "SAVE USER " + email + " SUCCESS!";
    }

    public static String LOGGER_DELETE_USER_SUCCESS(String uuid) {
        return "DELETE USER " + uuid + " SUCCESS!";
    }

    public static String LOGGER_USER_FOUND(String uuid) {
        return "THE USER " + uuid + " BE FOUND";
    }

    public static String LOGGER_USER_NOT_FOUND(String uuid) {
        return "THE USER " + uuid + " CAN'T BE FOUND";
    }

    public static String LOGGER_INFO_USERS_SIZE(int size) {
        return "SIZE OF USER LIST: " + size;
    }

    // Log ROLE config
    public static String LOGGER_SAVE_ROLE_SUCCESS(String role) {
        return "SAVE ROLE " + role + " SUCCESS!";
    }

    public static String LOGGER_DELETE_ROLE_SUCCESS(String uuid) {
        return "DELETE ROLE " + uuid + " SUCCESS!";
    }

    public static String LOGGER_ROLE_FOUND(String uuid) {
        return "THE ROLE " + uuid + " BE FOUND";
    }

    public static String LOGGER_ROLE_NOT_FOUND(String uuid) {
        return "THE ROLE " + uuid + " CAN'T BE FOUND";
    }

    public static String LOGGER_INFO_ROLES_SIZE(int size) {
        return "SIZE OF ROLE LIST: " + size;
    }

    // Log Exception config
    public static String LOGGER_EXCEPTION(Exception e) {
        return "EXCEPTION " + new Throwable().getStackTrace()[1].getMethodName() + ": " + e;
    }

    // Image empty config
    public static final String EMPTY_IMAGE_PRODUCT = "https://cdn2.iconfinder.com/data/icons/lucid-food/24/french_fries_snacks_junk_food_finger_chips-512.png";
    public static final String EMPTY_IMAGE_USER = "https://cdn2.iconfinder.com/data/icons/budicon-user/16/32-user_-_single-512.png";
}
