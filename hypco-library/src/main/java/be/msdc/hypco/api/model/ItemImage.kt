package be.msdc.hypco.api.model

import androidx.annotation.DrawableRes
import be.msdc.hypco.R

enum class ItemImage(internal val raw: String, @DrawableRes internal val drawable: Int) {
    IPHONE_IMG("device_iphone", R.drawable.ic_baseline_phone_iphone_24),
    IPAD_IMG("device_ipad", R.drawable.ic_baseline_ipad_24),
    ANDROID_IMG("device_android", R.drawable.ic_baseline_phone_android_24),
    ANDROID_TABLET_IMG("device_android_tablet", R.drawable.ic_baseline_tablet_android_24),
    TV_IMG("device_tv", R.drawable.ic_baseline_tv_24),
    CUSTOM_1_IMG("hypco_custom_1", R.drawable.hypco_custom_1_24),
    CUSTOM_2_IMG("hypco_custom_2", R.drawable.hypco_custom_2_24),
    CUSTOM_3_IMG("hypco_custom_3", R.drawable.hypco_custom_3_24),
    CUSTOM_4_IMG("hypco_custom_4", R.drawable.hypco_custom_4_24),
    DEFAULT_IMG("device_default", R.drawable.ic_baseline_info_24),
}