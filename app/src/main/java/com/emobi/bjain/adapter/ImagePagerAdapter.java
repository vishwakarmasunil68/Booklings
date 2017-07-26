package com.emobi.bjain.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by sunil on 28-04-2017.
 */

public class ImagePagerAdapter extends PagerAdapter {

    private Context context;
    private List<Integer> imageIdList;

    private int size;
    private boolean isInfiniteLoop;

    public ImagePagerAdapter(Context context, List<Integer> imageIdList) {
        this.context = context;
        this.imageIdList = imageIdList;
        this.size = imageIdList.size();
        isInfiniteLoop = false;
    }

    @Override
    public int getCount() {
        // Infinite loop
        return isInfiniteLoop ? Integer.MAX_VALUE : imageIdList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    private int getPosition(int position) {
        return isInfiniteLoop ? position % size : position;
    }

//    @Override
//    public View getView(int position, View view, ViewGroup container) {
//        ViewHolder holder;
//        if (view == null) {
//            holder = new ViewHolder();
//            view = holder.imageView = new ImageView(context);
//            view.setTag(holder);
//        } else {
//            holder = (ViewHolder) view.getTag();
//        }
//        holder.imageView.setImageResource(imageIdList.get(getPosition(position)));
//        return view;
//    }

    private static class ViewHolder {

        ImageView imageView;
    }

    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }
}