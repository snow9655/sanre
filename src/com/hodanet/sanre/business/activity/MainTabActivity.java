package com.hodanet.sanre.business.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

import com.hodanet.sanre.R;
import com.hodanet.sanre.common.base.HodaTabActivity;
import com.hodanet.sanre.common.util.ActivityManager;

/**
 * <pre>
 * 主页tab切换
 * </pre>
 */
public class MainTabActivity extends HodaTabActivity implements OnCheckedChangeListener {

    private RadioGroup          mainTab;
    private static TabHost      mTabHost;

    private RadioButton         mSanreRadioButton;

    // 内容Intent
    private Intent              mSanreIntent;                       // 散热
    private Intent              mBaoyangIntent;                     // 保养
    private final static String TAB_TAG_SANRE   = "tab_tag_sanre";
    private final static String TAB_TAG_BAOYANG = "tab_tag_baoyang";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_tab);
        mainTab = (RadioGroup) findViewById(R.id.main_tab);
        mainTab.setOnCheckedChangeListener(this);
        prepareIntent();
        setupIntent();
        ActivityManager.getInstance().addActivity(MainTabActivity.this);
    }

    /**
     * 准备tab的内容Intent
     */
    private void prepareIntent() {
        mSanreIntent = new Intent(this, SanreActivity.class);
        mBaoyangIntent = new Intent(this, BaoyangActivity.class);
    }

    /**
     * Intent内容设置 & 设置当前Tab
     */
    private void setupIntent() {
        this.mTabHost = getTabHost();
        TabHost localTabHost = this.mTabHost;
        localTabHost.addTab(buildTabSpec(TAB_TAG_SANRE, R.string.main_sanre, R.drawable.footer_bg_checked, mSanreIntent));
        localTabHost.addTab(buildTabSpec(TAB_TAG_BAOYANG, R.string.main_baoyang, R.drawable.footer_bg, mBaoyangIntent));

        // 获取第一个tab，选中
        mSanreRadioButton = (RadioButton) findViewById(R.id.footer_id_sanre);
        mSanreRadioButton.setChecked(true);
    }

    /**
     * 构建TabHost的Tab页
     * 
     * @param tag 标记
     * @param resLabel 标签
     * @param resIcon 图标
     * @param content 该tab展示的内容
     * @return 一个tab
     */
    private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon, final Intent content) {
        return this.mTabHost.newTabSpec(tag).setIndicator(getString(resLabel), getResources().getDrawable(resIcon)).setContent(content);
    }

    /**
     * Tab点击切换
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.footer_id_sanre:
                this.mTabHost.setCurrentTabByTag(TAB_TAG_SANRE);
                break;
            case R.id.footer_id_baoyang:
                this.mTabHost.setCurrentTabByTag(TAB_TAG_BAOYANG);
                break;
        }
    }
}
