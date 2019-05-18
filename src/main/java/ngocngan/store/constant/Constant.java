package ngocngan.store.constant;

import ngocngan.store.controller.BaseController;

/**
 * @author ngan nnh on 5/16/2019
 * @project store
 */

public class Constant {

    // Log list config
    public static String LOGGER_INFO_LIST_SIZE(int size) {
        return "SIZE LIST OF PRODUCT: " + size;
    }

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

    // Log USER config
    public static String LOGGER_SAVE_USER_SUCCESS(String name) {
        return "SAVE USER " + name + " SUCCESS!";
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

    // Log USER config
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

    // Log Exception config
    public static String LOGGER_EXCEPTION(Exception e) {
        return "EXCEPTION " + BaseController.getMethodName() + ": " + e;
    }

}
