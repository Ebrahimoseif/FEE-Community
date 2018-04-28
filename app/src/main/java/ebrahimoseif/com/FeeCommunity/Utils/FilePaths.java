package ebrahimoseif.com.FeeCommunity.Utils;

import android.content.Context;
import android.os.Environment;

/**
 * Created by Android App team to cloud university graduation project.
 */

public class FilePaths {

    //"storage/emulated/0"
    public String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();

    public String PICTURES = ROOT_DIR + "/Pictures";
    public String CAMERA = ROOT_DIR + "/DCIM/camera";

    public String FIREBASE_IMAGE_STORAGE = "photos/users/";

}
