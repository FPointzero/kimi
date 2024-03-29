package xyz.fpointzero.android.utils.data;

import android.content.ContentValues;

import org.litepal.LitePal;

import java.security.PublicKey;
import java.util.ArrayList;

import xyz.fpointzero.android.data.User;
import xyz.fpointzero.android.utils.crypto.MD5Util;
import xyz.fpointzero.android.utils.crypto.RSAUtil;

public class UserUtil {
    public static ArrayList<User> getBlackList() {
        return new ArrayList<User>(LitePal.where("isBlack = ?", "1").find(User.class));
    }

    public static ArrayList<User> getWhiteList() {
        return new ArrayList<User>(LitePal.where("isWhite = ?", "1").find(User.class));
    }

    public static ArrayList<User> getWhiteList(String searchValue) {
        return new ArrayList<User>(LitePal.where("isWhite = ? and username like ?", "1", "%"+ searchValue +"%").find(User.class));
    }
    
    public static String getUserID(PublicKey publicKey) {
        return MD5Util.stringToMD5(RSAUtil.publicKeyToString(publicKey));
    }
    
    public static int removeWhiteList(String userID) {
        ContentValues cv = new ContentValues();
        cv.put("isWhite", "0");
        return LitePal.updateAll(User.class, cv, "userid = ?", userID);
    }
    public static int addWhiteList(String userID) {
        ContentValues cv = new ContentValues();
        cv.put("isWhite", "1");
        return LitePal.updateAll(User.class, cv, "userid = ?", userID);
    }
    
    public static User getUser(String userid) {
        return LitePal.where("userid = ?", userid).find(User.class).get(0);
    }
}
