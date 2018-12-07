package com.tangbba.statusbarapp.utils;

import com.tangbba.statusbarapp.R;
import com.tangbba.statusbarapp.model.IconItem;

import java.util.ArrayList;
import java.util.List;

public class IconItemGenerator {
    public static List<IconItem> getIconItems() {
        List<IconItem> iconItems = new ArrayList<>();

        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_allience, "지갑"));
        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_checkaccount, "책"));
        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_credit, "멤버쉽"));
        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_gift, "연락처"));
        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_mealticket, "프로필"));
        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_membership, "구글 번역"));
        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_mileage, "라벨"));
        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_mycoupon, "잠금"));
        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_mywallet, "프린터"));
        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_order, "설정"));
        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_point, "쇼핑"));
        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_qrcode, "장바구니"));
        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_shopping, "좋아요"));
        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_tailor, "오늘의 일정"));
        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_tmoney, "터치"));
        iconItems.add(new IconItem(R.drawable.m_44_menu_ic_transfer, "터치"));

        return iconItems;
    }
}
