package be.msdc.hypco.api.model

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser

enum class ItemContentType(internal val format: (String?) -> String?) {
    JSON({ json ->
        val parser = JsonParser.parseString(json)
        val gson = GsonBuilder().setPrettyPrinting().create()
        gson.toJson(parser)
    }),
    TEXT({ text -> text }),
    NOTHING({ "" })
}