package com.example.forecastfive.realm;

// Wrapper class to manage instances of Realm with Dagger due to thread limitations

import android.util.Log;

import io.realm.Realm;

public class RealmManager {
    private final ThreadLocal<Realm> realms = new ThreadLocal<>();

    public RealmManager() {

    }

    public Realm getRealmInstance() {
        checkDefaultConfiguration();
        if (realms.get() == null) {
            Realm realm = Realm.getDefaultInstance();
            realms.set(realm);
            return realm;
        } else  {
            return realms.get();
        }
    }

    public void closeRealmInstance() {
        checkDefaultConfiguration();
        final Realm realm = realms.get();
        if (realm != null) {
            realm.close();
        } else {
            Log.d("RealmManager", "No realm instances are open");
        }
        if (Realm.getLocalInstanceCount(Realm.getDefaultConfiguration()) <= 0) { //null check done via checkDefaultConfiguration
            realms.set(null);
        }
    }




    private void checkDefaultConfiguration() {
        if (Realm.getDefaultConfiguration() == null) {
            throw new IllegalStateException("No default configuration is set");
        }
    }
}
