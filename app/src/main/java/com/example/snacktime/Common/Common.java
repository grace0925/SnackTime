package com.example.snacktime.Common;

import com.example.snacktime.Users.Users;
//todo: implement all text in strings.xml
//todo: adaptive app icon
//todo: dashboard
//log in with other accounts
//user: weather, search bar at top, recommended (sliding), little user icon, my profile page, food vlog, store list, rate food/comment, shopping cart, save store/order, speech
//seller: should display distance, rating, price; add item, sales, take pictures(vision api?), receive order, confirm order, set open time
//driver: receive notification, track location, timer
//admin: view all stores(transaction history), put up promotions, blacklist user/seller/driver, view transaction history, notification(store requests)
public class Common {
    public static final String USERS_COL = "Users";
    public static final String ADMINS_COL = "Admins";

    private static Users curUser;

    public static final String REMEMBER_USER_SHARED_PREF = "rememberUser";
    public static final String REMEMBER_ME = "rememberMe"; //boolean
    public static final String REMEMBER_USERNAME =  "dummyUsername"; //String
    public static final String REMEMBER_PASSWORD =  "dummyPassword"; //String

}
