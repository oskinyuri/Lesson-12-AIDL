package com.example.lesson_12_aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondFragment extends Fragment {

    private EditText mEditText;
    private Button mSaveBtn;
    private ServiceConnection mConnection;
    private IDataPassInterface mService;
    private Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, null);
        mEditText = view.findViewById(R.id.second_fragment_edit_text);
        mSaveBtn = view.findViewById(R.id.second_fragment_btn);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = IDataPassInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
            }
        };

        mHandler = new Handler(Looper.getMainLooper());

        if (getActivity() != null)
            getActivity().bindService(new Intent(getActivity(), SPService.class), mConnection, Context.BIND_AUTO_CREATE);

        initListeners();
    }

    private void initListeners() {
        mSaveBtn.setOnClickListener((view) -> {
            try {
                mService.setData(
                        mEditText.getText().toString(),
                        new ISPServiceResponseListener.Stub() {
                            @Override
                            public void onResponse(String response) throws RemoteException {
                                mHandler.post(() -> Toast.makeText(getActivity(), response + " added in SP.", Toast.LENGTH_SHORT).show());
                            }
                        }
                );
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onStop() {
        mService = null;
        super.onStop();
    }
}
