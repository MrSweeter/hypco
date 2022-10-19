package be.msdc.hypco.api.model

enum class ItemType(internal val collectionType: CollectionType) {
    SQUARE_CARD(CollectionType.GRID),
    PROGRESS_CARD(CollectionType.LIST),
    SIMPLE_CARD(CollectionType.LIST),
    SIMPLE_TEXT(CollectionType.LIST),
    SIMPLE_LINK(CollectionType.LIST),
    DEFAULT(CollectionType.LIST);

    internal enum class CollectionType {
        GRID,
        LIST,
    }
}