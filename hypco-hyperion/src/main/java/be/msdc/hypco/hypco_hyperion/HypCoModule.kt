package be.msdc.hypco.hypco_hyperion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.msdc.hypco.api.HypCo
import com.willowtreeapps.hyperion.plugin.v1.PluginModule

class HypCoModule: PluginModule() {

    override fun createPluginView(layoutInflater: LayoutInflater, parent: ViewGroup): View? {
        val view = layoutInflater.inflate(R.layout.hypco_item_layout, parent, false)
        view.setOnClickListener { context.startActivity(HypCo.getLaunchIntent(context)) }
        return view
    }

}