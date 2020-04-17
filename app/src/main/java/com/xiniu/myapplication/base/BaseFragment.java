package com.xiniu.myapplication.base;

/**
 * 创建者：wyz
 * 创建时间：2020/4/17
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract  class BaseFragment<DB extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {
    public DB binding;
    public VM viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater,layout(),container,false);

    return  binding.getRoot();
    }

    private void initDataBindings() {
        //反射绑定
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType){
            Type[] types  =((ParameterizedType)type).getActualTypeArguments() ;
            Class clazz = (Class)types[1];
            viewModel =(VM) ViewModelProviders.of(this).get(clazz);


        }
        binding.setVariable(viewModelId(),viewModel);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected abstract int layout();
    protected abstract int viewModelId();
}
