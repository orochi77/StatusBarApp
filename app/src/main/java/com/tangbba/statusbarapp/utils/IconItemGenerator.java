package com.tangbba.statusbarapp.utils;

import com.tangbba.statusbarapp.R;
import com.tangbba.statusbarapp.model.IconItem;

import java.util.ArrayList;
import java.util.List;

public class IconItemGenerator {
    public static List<IconItem> getIconItems() {
        List<IconItem> iconItems = new ArrayList<>();

        iconItems.add(new IconItem(R.drawable.twotone_account_balance_wallet_black_24, "지갑"));
        iconItems.add(new IconItem(R.drawable.twotone_book_black_24, "책"));
        iconItems.add(new IconItem(R.drawable.twotone_card_membership_black_24, "멤버쉽"));
        iconItems.add(new IconItem(R.drawable.twotone_contacts_black_24, "연락처"));
        iconItems.add(new IconItem(R.drawable.twotone_face_black_24, "프로필"));
        iconItems.add(new IconItem(R.drawable.twotone_g_translate_black_24, "구글 번역"));
        iconItems.add(new IconItem(R.drawable.twotone_label_black_24, "라벨"));
        iconItems.add(new IconItem(R.drawable.twotone_lock_open_black_24, "잠금"));
        iconItems.add(new IconItem(R.drawable.twotone_print_black_24, "프린터"));
        iconItems.add(new IconItem(R.drawable.twotone_settings_applications_black_24, "설정"));
        iconItems.add(new IconItem(R.drawable.twotone_shop_black_24, "쇼핑"));
        iconItems.add(new IconItem(R.drawable.twotone_shopping_cart_black_24, "장바구니"));
        iconItems.add(new IconItem(R.drawable.twotone_thumb_up_black_24, "좋아요"));
        iconItems.add(new IconItem(R.drawable.twotone_today_black_24, "오늘의 일정"));
        iconItems.add(new IconItem(R.drawable.twotone_touch_app_black_24, "터치"));

        return iconItems;
    }
}
