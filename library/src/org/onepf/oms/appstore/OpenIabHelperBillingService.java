package org.onepf.oms.appstore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import org.onepf.oms.IOpenInAppBillingService;

/**
 * Author: Yury Vasileuski
 * Date: 18.05.13
 */
public class OpenIabHelperBillingService extends IabHelperBillingService {
    public static final int BILLING_RESPONSE_RESULT_BUY_ALREADY_IN_PROGRESS = 10000;
    private IOpenInAppBillingService mBillingService;
    private Intent mBillingIntent;

    public OpenIabHelperBillingService(Context context) {
        super(context);
        LOG_PREFIX = "[OpenIab Billing Service] ";
    }

    @Override
    public int isBillingSupported(int apiVersion, String packageName, String type) throws RemoteException {
        return mBillingService.isBillingSupported(apiVersion, packageName, type);
    }

    @Override
    public Bundle getSkuDetails(int apiVersion, String packageName, String type, Bundle skusBundle) throws RemoteException {
        return mBillingService.getSkuDetails(apiVersion, packageName, type, skusBundle);
    }

    @Override
    public Bundle getBuyIntent(int apiVersion, String packageName, String sku, String type, String developerPayload) throws RemoteException {
        return mBillingService.getBuyIntent(apiVersion, packageName, sku, type, developerPayload);
    }

    @Override
    public Bundle getPurchases(int apiVersion, String packageName, String type, String continuationToken) throws RemoteException {
        return mBillingService.getPurchases(apiVersion, packageName, type, continuationToken);
    }

    @Override
    public int consumePurchase(int apiVersion, String packageName, String purchaseToken) throws RemoteException {
        return mBillingService.consumePurchase(apiVersion, packageName, purchaseToken);
    }

    @Override
    protected void didServiceConnected(android.os.IBinder service) {
        mBillingService = IOpenInAppBillingService.Stub.asInterface(service);
    }

    @Override
    protected void didServiceDisconnected() {
        mBillingService = null;
    }

    @Override
    protected Intent getServiceIntent() {
        return new Intent(mBillingIntent);
    }

    public void setServiceIntent(Intent intent) {
        mBillingIntent = new Intent(intent);
    }

    @Override
    public boolean isDataSignatureSupported() {
        return false;
    }

    public static String getResponseDesc(int code) {
        String value;
        switch(code) {
            case BILLING_RESPONSE_RESULT_BUY_ALREADY_IN_PROGRESS:
                value = "10000:Already in progress";
                break;
            default:
                value = IabHelperBillingService.getResponseDesc(code);
        }
        return value;
    }
}
