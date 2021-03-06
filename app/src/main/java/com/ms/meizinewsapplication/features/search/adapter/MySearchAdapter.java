package com.ms.meizinewsapplication.features.search.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.ms.greendaolibrary.db.HtmlEntity;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.utils.tool.ConstantData;
import com.ms.meizinewsapplication.features.main.activity.DevWeekDetailActivity;
import com.ms.meizinewsapplication.features.main.activity.ZhihuDetailActivity;
import com.test.basequickadapterlib.BaseAdapterHelper;
import com.test.basequickadapterlib.QuickAdapter;

import java.util.List;

/**
 * Created by 啟成 on 2016/4/10.
 */
public class MySearchAdapter extends QuickAdapter<HtmlEntity> {
    public MySearchAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public MySearchAdapter(Context context, int layoutResId, List<HtmlEntity> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(final BaseAdapterHelper helper, final HtmlEntity item, int position) {
        helper.getTextView(R.id.story_item_title).setText(item.getTitle());

        if (TextUtils.isEmpty(item.getSummary())) {
            helper.getTextView(R.id.story_excerpt).setVisibility(View.GONE);
        } else {

            helper.getTextView(R.id.story_excerpt).setVisibility(View.VISIBLE);
            helper.getTextView(R.id.story_excerpt).setText(item.getSummary());
        }

        helper.getTextView(R.id.story_author).setVisibility(View.GONE);
        helper.getTextView(R.id.story_date).setVisibility(View.GONE);

        helper.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.putExtra("url",item.getUrl());
                intent.putExtra("html",item.getHtml());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("type",item.getType());

                switch (item.getType()) {
                    case ConstantData.DB_HTML_TYPE_WEEK:
                        intent.setClass(context, DevWeekDetailActivity.class);
                        break;
                    case ConstantData.DB_HTML_TYPE_ZHIHU:
                        intent.setClass(context, ZhihuDetailActivity.class);

                        break;

                    default:

                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(item.getUrl());
                        intent.setData(content_url);
                        break;
                }

                context.startActivity(intent);
            }
        });
    }

}
