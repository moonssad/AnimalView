//package com.xiniu.myapplication.base;
//
//import android.arch.lifecycle.ViewModelProviders;
//import android.databinding.DataBindingUtil;
//import android.databinding.ViewDataBinding;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//
//import java.lang.reflect.Type;
//
///**
// * 创建者：wyz
// * 创建时间：2020/4/16
// * 功能描述：
// * 更新者：
// * 更新时间：
// * 更新描述：
// */
//public abstract class BaseActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {
//    DB dataBinding;
//    VM viewModel;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        dataBinding = DataBindingUtil.setContentView(this,getLayoutId());
//        Type type = getClass().getGenericSuperclass();
//
//        viewModel= ViewModelProviders.of(this).get(type);
//        dataBinding.setVariable(getViewModelId(),viewModel);
//
//
//    }
//
//    protected  abstract int getLayoutId() ;
//    protected  abstract int getViewModelId() ;
//}
