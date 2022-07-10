package be.msdc.hypco.hypco_hyperion

import com.google.auto.service.AutoService
import com.willowtreeapps.hyperion.plugin.v1.Plugin
import com.willowtreeapps.hyperion.plugin.v1.PluginModule

@AutoService(Plugin::class)
class HypCoPlugin: Plugin() {
    override fun createPluginModule(): PluginModule = HypCoModule()
}