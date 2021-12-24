package com.harmonyos.restapiexample.utils;

import com.harmonyos.restapiexample.ResourceTable;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;
import ohos.global.resource.NotExistException;
import ohos.global.resource.ResourceManager;
import ohos.global.resource.WrongTypeException;
import ohos.net.NetHandle;
import ohos.net.NetManager;

import java.io.IOException;

import static com.harmonyos.restapiexample.Constants.TOAST_DURATION_LONG_MS;


public class Util {

    private final static String TAG = "TAG";

    public static boolean isInternetAvailable(Context context) {
        NetManager netManager = NetManager.getInstance(context);
        NetHandle[] netHandles = netManager.getAllNets();

        if (netHandles.length < 1) {
            showToast(context, ResourceTable.String_no_internet, TOAST_DURATION_LONG_MS);
            return false;
        }

        return true;
    }

    public static String getResourceString(Context context,int resourseStringID) {
        ResourceManager resManager = context.getResourceManager();
        String result = "";
        try {
            result = resManager.getElement(resourseStringID).getString();
        } catch (IOException | NotExistException | WrongTypeException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void showToast(Context context, int resourseStringID, int duration) {

        String message = getResourceString(context,resourseStringID);

        DirectionalLayout toastLayout = (DirectionalLayout) LayoutScatter.getInstance(context)
                .parse(ResourceTable.Layout_toast_layout, null, false);

        if (toastLayout == null) {
            LogUtil.debug(TAG, "toastLayout  is null");
        } else {
            Text mgsComp = (Text) toastLayout.getComponentAt(0);

            if (mgsComp == null) {
                LogUtil.debug(TAG, "mgsComp is null");
            } else {
                mgsComp.setText(message);
            }
        }

        ToastDialog toastDialog = new ToastDialog(context)
                .setComponent(toastLayout)
                .setSize(DirectionalLayout.LayoutConfig.MATCH_CONTENT, DirectionalLayout.LayoutConfig.MATCH_CONTENT)
                .setAlignment(LayoutAlignment.CENTER);

        toastDialog.setDuration(duration)
                .show();
    }

  /*  public static void showLocationPermissionDeniedToast(Context context) {
        Util.showToast(context, ResourceTable.String_location_permission_denied, TOAST_DURATION_LONG_MS);
    }

    public static String formatDateTimeWithTimeZone(String dateTime) {
        DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = utcFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat eetFormat = new SimpleDateFormat("HH:mm");
        eetFormat.setTimeZone(TimeZone.getTimeZone("EET"));

        return eetFormat.format(date);
    }*/

}
