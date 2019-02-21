package com.example.jzycc.lib_whatrouter;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.reflect.Method;

public class WhatRouter {

    private Class clazz;
    private Context context;
    private Class goalClazz;

    private static WhatRouter instance;

    public static WhatRouter getInstance(){
        if (instance == null){
            synchronized (WhatRouter.class){
                if (instance == null){
                    instance = new WhatRouter();
                }
            }
        }

        return instance;
    }

    private WhatRouter(){
        try{
            clazz = Class.forName("com.example.jzycc.lib_whatrouter.WhatRouterTable");
        }catch (Exception e){
            Log.e("WhatRouterError", "WhatRouter: ",e );
        }
    }

    public WhatRouter with(Context context){
        this.context = context;
        return this;
    }

    public WhatRouter url(String url){
        try{
            goalClazz = Class.forName(clazz.getField(url).get("").toString());

        }catch (Exception e){
            Log.e("WhatRouterError", "url: ", e);
        }
        return this;
    }

    public void startActiviity(){
        try{
            Intent intent = new Intent(context, goalClazz);
            context.startActivity(intent);
        }catch (Exception e){
            Log.e("WhatRouterError", "startActiviity: ",e );
        }
    }
}
