package com.ms.meizinewsapplication.features.meizi.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.meizi.iview.DBMeiNvIView;
import com.ms.meizinewsapplication.features.meizi.model.DbGroupBreastModel;
import com.ms.meizinewsapplication.features.meizi.model.DbGroupButtModel;
import com.ms.meizinewsapplication.features.meizi.model.DbGroupLegModel;
import com.ms.meizinewsapplication.features.meizi.model.DbGroupSilkModel;
import com.ms.meizinewsapplication.features.meizi.pojo.DbMeiNv;

import org.loader.model.OnModelListener;
import org.loader.presenter.FragmentPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啟成 on 2016/3/6.
 */
public class DBMeiNuFragment extends FragmentPresenterImpl<DBMeiNvIView> {

    private DbGroupBreastModel dbGroupBreastModel;
    private DbGroupButtModel dbGroupButtModel;
    private DbGroupLegModel dbGroupLegModel;
    private DbGroupSilkModel dbGroupSilkModel;

    private int page;
    private int strId;

    public DBMeiNuFragment(int strId) {
        this.strId = strId;
        page = 1;
    }

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        mView.init(getActivity());
        mView.setOnRefreshListener(onRefreshListener);
        mView.addOnScrollListener(onScrollListener);
        initDbGroup();
    }




    //TODO Model======================================================

    private void initDbGroup() {
        switch (strId) {
            case R.string.tab_dbmeinv_daxiong:
                initDbGroupBreastModel();
                break;
            case R.string.tab_dbmeinv_qiaotun:
                initDbGroupButtModel();
                break;
            case R.string.tab_dbmeinv_meitui:
                initDbGroupLegModel();
                break;
            case R.string.tab_dbmeinv_heisi:
                initDbGroupSilkModel();
                break;

        }
    }

    private void initDbGroupBreastModel() {
        dbGroupBreastModel = new DbGroupBreastModel();
        breastLoad();

    }

    private void initDbGroupButtModel() {
        dbGroupButtModel = new DbGroupButtModel();
        buttLoad();
    }

    private void initDbGroupLegModel() {
        dbGroupLegModel = new DbGroupLegModel();
        legLoad();
    }

    private void initDbGroupSilkModel() {
        dbGroupSilkModel = new DbGroupSilkModel();
        silkLoad();
    }

    //TODO load======================================================

    private void dbGroupLoad() {
        switch (strId) {
            case R.string.tab_dbmeinv_daxiong:
                breastLoad();
                break;
            case R.string.tab_dbmeinv_qiaotun:
                buttLoad();
                break;
            case R.string.tab_dbmeinv_meitui:
                legLoad();
                break;
            case R.string.tab_dbmeinv_heisi:
                silkLoad();
                break;
        }
    }

    private void breastLoad() {
        mView.changeProgress(true);
        dbGroupBreastModel.loadWeb(getContext(), listenerDbMeiNv, page + "");
    }

    private void buttLoad() {

        mView.changeProgress(true);
        dbGroupButtModel.loadWeb(getContext(), listenerDbMeiNv, page + "");
    }

    private void legLoad() {

        mView.changeProgress(true);
        dbGroupLegModel.loadWeb(getContext(), listenerDbMeiNv, page + "");
    }

    private void silkLoad() {

        mView.changeProgress(true);
        dbGroupSilkModel.loadWeb(getContext(), listenerDbMeiNv, page + "");
    }

    //TODO Listener============================================================

    OnModelListener<List<DbMeiNv>> listenerDbMeiNv = new OnModelListener<List<DbMeiNv>>() {
        @Override
        public void onSuccess(List<DbMeiNv> dbMeiNvs) {
            mView.addDatas2QuickAdapter((ArrayList<DbMeiNv>) dbMeiNvs);
            mView.changeProgress(false);
        }

        @Override
        public void onError(String err) {
            mView.changeProgress(false);
        }

        @Override
        public void onCompleted() {

        }
    };

    /**
     * 下拉监听
     */
    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            mView.changeProgress(false);
        }
    };

    /**
     * 到底监听
     */
    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                return;
            }
            page++;
            dbGroupLoad();
        }
    };
}
