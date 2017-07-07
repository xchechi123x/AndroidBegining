package com.xiaolaogong.test.common.navigation;

import android.content.Context;

import com.xiaolaogong.test.R;

/**
 * Created by chechi on 2017/7/6.
 */

public class Support {

    public static int[] measureForFixedMode(Context context, int screenWidth, int noOfTabs, boolean scrollable) {

        int[] result = new int[2];

        int minWidth = (int) context.getResources().getDimension(R.dimen.fixed_min_width_small_views);

        int maxWidth = (int) context.getResources().getDimension(R.dimen.fixed_min_width);

        int itemWidth = screenWidth / noOfTabs;

        if (itemWidth < minWidth && scrollable) {

            itemWidth = (int) context.getResources().getDimension(R.dimen.fixed_min_width);

        } else if (itemWidth > maxWidth) {

            itemWidth = maxWidth;

        }

        result[0] = itemWidth;

        return result;

    }

    public static int[] measureForShiftingMode(Context context, int screenWidth, int noOfTabs, boolean scrollable) {

        int[] result = new int[2];

        int minWidth = (int) context.getResources().getDimension(R.dimen.shifting_min_width_inactive);

        int maxWidth = (int) context.getResources().getDimension(R.dimen.shifting_max_width_inactive);

        double minPossibleWidth = minWidth * (noOfTabs + 0.5);

        double maxPossibleWidth = maxWidth * (noOfTabs + 0.75);

        int itemWidth;

        int itemActiveWidth;

        if (screenWidth < minPossibleWidth) {

            if (scrollable) {

                itemWidth = minWidth;
                itemActiveWidth = (int) (minWidth * 1.5);

            } else {

                itemWidth = (int) (screenWidth / (noOfTabs + 0.5));
                itemActiveWidth = (int) (itemWidth * 1.5);

            }

        } else if (screenWidth > maxPossibleWidth) {

            itemWidth = maxWidth;
            itemActiveWidth = (int) (itemWidth * 1.75);

        } else {

            double minPossibleWidth1 = minWidth * (noOfTabs + 0.625);
            double minPossibleWidth2 = minWidth * (noOfTabs + 0.75);

            itemWidth = (int) (screenWidth / (noOfTabs + 0.5));

            itemActiveWidth = (int) (itemWidth * 1.5);

            if (screenWidth > minPossibleWidth1) {

                itemWidth = (int) (screenWidth / (noOfTabs + 0.625));
                itemActiveWidth = (int) (itemWidth * 1.625);

                if (screenWidth > minPossibleWidth2) {

                    itemWidth = (int) (screenWidth / (noOfTabs + 0.75));
                    itemActiveWidth = (int) (itemWidth * 1.75);

                }
            }
        }

        result[0] = itemWidth;

        result[1] = itemActiveWidth;

        return result;
    }

    public static void bindTabWithData(Item item, Tab tab, BottomLayout layout) {
        Context context = layout.getContext();
    }
}
