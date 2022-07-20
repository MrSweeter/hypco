package be.msdc.hypco.api.model

enum class ItemContentType(internal val format: (String?) -> String?) {
    JSON({ text -> text }),
    XML({ text -> text }),
    TEXT({ text -> text }),
    NOTHING({ "" })
}