package rainorsun.com.rainorsun;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class AndroidPermissions extends AppCompatActivity {
  public boolean check(int requestCode, String Permission, Context context, String denyMessage) {
    int checkedPermission;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      checkedPermission = ActivityCompat.checkSelfPermission(context, Permission);
      if (checkedPermission != PackageManager.PERMISSION_GRANTED) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Permission)
            && denyMessage != null) {
          showMessageOKCancel(denyMessage, Permission, requestCode, context);
        } else {
          ActivityCompat.requestPermissions((Activity) context, new String[] { Permission },
              requestCode);
        }
        return false;
      }
    }
    return true;
  }

  public boolean multiCheck(List<String> permissionList, int requestCode, Context context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      List<String> needToAsk = new ArrayList<>();
      for (int i = 0; i < permissionList.size(); i++) {
        if (ActivityCompat.checkSelfPermission(context, permissionList.get(i))
            != PackageManager.PERMISSION_GRANTED) {
          needToAsk.add(permissionList.get(i));
        }
      }
      if (!needToAsk.isEmpty()) {
        String[] params = needToAsk.toArray(new String[needToAsk.size()]);
        ActivityCompat.requestPermissions((Activity) context, params, requestCode);
        return false;
      } else {
        return true;
      }
    }
    return true;
  }

  protected void showMessageOKCancel(String message, String Permission, int requestCode,
      Context context) {
    new AlertDialog.Builder(context).setMessage(message)
        .setPositiveButton("OK", (dialogInterface, i) -> {
          ActivityCompat.requestPermissions((Activity) context, new String[] { Permission },
              requestCode);
        })
        .setNegativeButton("Cancel", null)
        .show();
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResult) {
    switch (requestCode) {
      default:
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
    }
  }
}
