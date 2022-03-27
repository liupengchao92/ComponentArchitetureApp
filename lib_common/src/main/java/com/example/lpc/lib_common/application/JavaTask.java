package com.example.lpc.lib_common.application;

import android.app.Application;

import com.blankj.utilcode.util.LogUtils;
import com.example.lpc.annotation.InitTask;
import com.example.lpc.api.IInitTask;

import androidx.annotation.NonNull;

/**
 * Author: liupengchao
 * Date: 2022/3/24
 * ClassName :JavaTask
 * Desc:
 */
@InitTask(name = "JavaTask")
public class JavaTask implements IInitTask {
    @Override
    public void execute(@NonNull Application application) {
        LogUtils.e("execute==============>>JavaTask");
    }
}
