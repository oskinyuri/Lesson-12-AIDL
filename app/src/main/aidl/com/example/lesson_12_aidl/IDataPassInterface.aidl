// IDataPassInterface.aidl
package com.example.lesson_12_aidl;

import com.example.lesson_12_aidl.ISPServiceResponseListener;

interface IDataPassInterface {
    oneway void setData(in String aString, in ISPServiceResponseListener listener);
    oneway void getData(in ISPServiceResponseListener listener);
}
