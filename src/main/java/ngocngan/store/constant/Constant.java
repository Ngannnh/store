package ngocngan.store.constant;

import ngocngan.store.controller.MainController;

/**
 * @author ngan nnh on 5/16/2019
 * @project store
 */

public class Constant {

    // Log config
    public static String LOGGER_INFO_SAVE_SUCCESS(String name) {
        return "Save product " + name + " success!";
    }
    public static String LOGGER_INFO_DELETE_SUCCESS(String uuid) {
        return "Delete product " + uuid + " success!";
    }
    public static String LOGGER_INFO_LIST_SIZE(int size) {
        return "Size list of products: " + size;
    }
    public static String LOGGER_WARN(Exception e) {
        return "Exception " + MainController.getMethodName() + ": " + e;
    }
}
