package com.example.huangyuwei.myapplication;

import android.app.Application;

/**
 * Created by XiDream on 2018/5/7.
 */

public class GlobalVariable extends Application{

    enum NextAction {
        TO_CURE,
        TO_MOVE,
        TO_EAT,
        TO_ASK,
        TO_LINK,
        TO_MEM,
        TO_TALK,
        TO_LAUGH,
        SIGN_OUT,
        NO_ACTION,
        EXIT
    }

    NextAction theNextAction = NextAction.NO_ACTION;

}
