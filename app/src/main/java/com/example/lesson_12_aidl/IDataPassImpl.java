package com.example.lesson_12_aidl;

import android.os.RemoteException;

public class IDataPassImpl extends IDataPassInterface.Stub{

    private SPManager mSPManager;

    public IDataPassImpl(SPManager spManager) {
        mSPManager = spManager;
    }

    @Override
    public void setData(String aString, ISPServiceResponseListener listener) throws RemoteException {
        mSPManager.setData(aString);
        listener.onResponse(aString);
    }

    @Override
    public void getData(ISPServiceResponseListener listener) throws RemoteException {
        listener.onResponse(mSPManager.getData());
    }
}
