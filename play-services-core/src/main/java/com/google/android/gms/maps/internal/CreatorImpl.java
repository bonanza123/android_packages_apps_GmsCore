/*
 * Copyright 2013-2015 microG Project Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gms.maps.internal;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate;

import org.microg.gms.maps.MapFragmentImpl;
import org.microg.gms.maps.MapViewImpl;
import org.microg.gms.maps.ResourcesContainer;
import org.microg.gms.maps.bitmap.BitmapDescriptorFactoryImpl;
import org.microg.gms.maps.camera.CameraUpdateFactoryImpl;

public class CreatorImpl extends ICreator.Stub {
    private static final String TAG = "GmsMapCreator";

    @Override
    public void init(IObjectWrapper resources) throws RemoteException {
        initV2(resources, 0);
    }

    @Override
    public IMapFragmentDelegate newMapFragmentDelegate(IObjectWrapper activity) throws RemoteException {
        return new MapFragmentImpl((Activity) ObjectWrapper.unwrap(activity));
    }

    @Override
    public IMapViewDelegate newMapViewDelegate(IObjectWrapper context, GoogleMapOptions options) throws RemoteException {
        return new MapViewImpl((Context) ObjectWrapper.unwrap(context), options);
    }

    @Override
    public ICameraUpdateFactoryDelegate newCameraUpdateFactoryDelegate() throws RemoteException {
        return new CameraUpdateFactoryImpl();
    }

    @Override
    public IBitmapDescriptorFactoryDelegate newBitmapDescriptorFactoryDelegate() throws RemoteException {
        return new BitmapDescriptorFactoryImpl();
    }

    @Override
    public void initV2(IObjectWrapper resources, int flags) throws RemoteException {
        ResourcesContainer.set((Resources) ObjectWrapper.unwrap(resources));
    }

    @Override
    public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        if (super.onTransact(code, data, reply, flags)) return true;
        Log.d(TAG, "onTransact [unknown]: " + code + ", " + data + ", " + flags);
        return false;
    }
}
