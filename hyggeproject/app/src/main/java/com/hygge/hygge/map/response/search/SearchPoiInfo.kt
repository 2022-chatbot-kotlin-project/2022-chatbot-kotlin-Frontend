package com.hygge.hygge.map.response.search

import com.hygge.hygge.map.response.search.Pois

data class SearchPoiInfo(
    val totalCount: String,
    val count: String,
    val page: String,
    val pois: Pois
)
