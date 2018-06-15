package utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import rainorsun.com.rainorsun.Constant;

public class LocationHandler {
    private LocationHandlerListener locationHandlerListener;

    private boolean hasPermission(Context context) {
        AndroidPermissions androidPermissions = new AndroidPermissions();
        List<String> permissionNeeded = new ArrayList<>();
        permissionNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        return androidPermissions.multiCheck(permissionNeeded, Constant.REQUEST_CODE_LOCATION,
            context);
    }

    /**
     * Function to check whether the app has location service permission or not.
     * If no, prompt dialog to ask for the permission.
     *
     * @param context App context.
     */
    public void checkLocationService(Context context) {
        if (hasPermission(context)) {
            LocationManager locationManager =
                (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            boolean gpsEnabled;
            boolean networkEnabled;
            gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (gpsEnabled || networkEnabled) {
                getCurrentLocation(context);
            }
        }
    }

    /**
     * Function to get the current location by using location manager.
     *
     * @param context App context.
     */
    public void getCurrentLocation(Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager =
                (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            Criteria crit = new Criteria();
            crit.setAccuracy(Criteria.ACCURACY_FINE);
            String locationProvider = locationManager.getBestProvider(crit, false);
            locationManager.requestLocationUpdates(locationProvider, 0, 0, new LocationListener() {
                @Override public void onLocationChanged(Location location) {
                    // Once getting the location, remove the listener.
                    locationManager.removeUpdates(this);
                    if (locationHandlerListener != null) {
                        locationHandlerListener.onLocationReceived(location);
                    }
                }

                @Override public void onStatusChanged(String provider, int status, Bundle extras) {
                    Log.d("MHH", provider);
                }

                @Override public void onProviderEnabled(String provider) {
                    Log.d("MHH", "provider enable");
                }

                @Override public void onProviderDisabled(String provider) {
                    Log.d("MHH", "provider diable");
                }
            });
        }
    }

    public String getCompleteAddressString(Context context, double latitude, double longitude,
        GoogleAddressListener googleAddressListener) {
        try {
            new GoogleGeocoderAsyncTask(googleAddressListener, context, latitude,
                longitude).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static class GoogleGeocoderAsyncTask extends AsyncTask<Void, Void, String> {
        private GoogleAddressListener googleAddressListener;
        private Context context;
        private double latitude, longitude;

        public GoogleGeocoderAsyncTask(GoogleAddressListener googleAddressListener, Context context,
            double latitude, double longitude) {
            this.googleAddressListener = googleAddressListener;
            this.context = context;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override protected String doInBackground(Void... params) {
            List<Address> addresses = new ArrayList<>();
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return formatAddress(addresses);
        }

        @Override protected void onPostExecute(String address) {
            googleAddressListener.onAddressFetched(address);
        }
    }

    private static String formatAddress(List<Address> addresses) {
        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);
            if (address.getThoroughfare() != null) {
                return address.getThoroughfare();
            } else if (address.getSubAdminArea() != null) {
                return address.getSubAdminArea();
            } else if (address.getLocality() != null) {
                return address.getLocality();
            } else if (address.getAdminArea() != null) {
                return address.getAdminArea();
            }
        }

        return "";
    }

    /**
     * Register the listener for getting the location.
     *
     * @param context App context.
     * @param locationHandlerListener Listener for getting the location.
     */
    public void requestLocation(Context context, LocationHandlerListener locationHandlerListener) {
        checkLocationService(context);
        this.locationHandlerListener = locationHandlerListener;
    }

    public interface LocationHandlerListener {
        void onLocationReceived(Location location);

        void onLocationCancelled();
    }

    public interface GoogleAddressListener {
        void onAddressFetched(String address);
    }
}
