package com.xiniu.myapplication.base;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xiniu.myapplication.base.BaseViewModel;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

//
///**
// * 创建者：wyz
// * 创建时间：2020/4/16
// * 功能描述：
// * 更新者：
// * 更新时间：
// * 更新描述：
// */
public abstract class BaseActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {
    protected DB dataBinding;
    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        viewModel = ViewModelProviders.of(this).get(getViewModelClazz());
        dataBinding.setVariable(getViewModelId(), viewModel);
        initData();
        initView();
    }

    protected abstract  Class<VM> getViewModelClazz();

    private void initViewModel() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class clazz = (Class) parameterizedType.getActualTypeArguments()[1];
            viewModel = (VM) ViewModelProviders.of(this).get(clazz);
        }
    }

    protected abstract int getLayoutId();

    protected abstract int getViewModelId();

    protected abstract void initData();

    protected abstract void initView();


}
