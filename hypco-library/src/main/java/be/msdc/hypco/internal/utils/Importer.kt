package be.msdc.hypco.internal.utils

// Activity Scope - Only work for ROOT - to be improved

/*
fun import()    {
    val importIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
    importIntent.addCategory(Intent.CATEGORY_OPENABLE)
    importIntent.type = StorageUtils.type
    importForResult.launch(importIntent)
}

private val importForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())    { result ->
    if (result.resultCode == ComponentActivity.RESULT_OK) {
        result.data?.data?.let { uri ->
            val items = StorageUtils.read(contentResolver, uri)
            importToStorage(items)
        }
    }
}

private fun importToStorage(items: List<NodeItem>) {
    launch {
        RepositoryProvider.items().deleteAll()
        RepositoryProvider.items().insertItem(*items.toTypedArray())
    }
}*/
