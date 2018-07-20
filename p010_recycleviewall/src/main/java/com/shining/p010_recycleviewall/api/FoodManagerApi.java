package com.shining.p010_recycleviewall.api;

import com.shining.p010_recycleviewall.domain.DemoGLin;
import com.shining.p010_recycleviewall.domain.PackageOneKeyBuyBeanNewList;

import org.loader.glin.annotation.Arg;
import org.loader.glin.annotation.GET;
import org.loader.glin.annotation.JSON;
import org.loader.glin.call.Call;

/**
 * Created by qibin on 2016/8/4.
 */

public interface FoodManagerApi {

    @JSON("" + "food/food.category.query")
    Call<PackageOneKeyBuyBeanNewList> food_category_query(String json);

    @GET("http://192.168.200.69/mobile/index.php")
    Call<DemoGLin> getdemo(@Arg("act") String act, @Arg("op") String op, @Arg("curpage") String curpage,@Arg("state") String state, @Arg("alias") String alias);
}