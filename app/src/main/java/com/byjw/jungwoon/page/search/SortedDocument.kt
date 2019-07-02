package com.byjw.jungwoon.page.search

import com.byjw.jungwoon.retrofit.gson.BaseContent

data class SortedDocument (
    val date: String,
    val document: BaseContent.Document
)