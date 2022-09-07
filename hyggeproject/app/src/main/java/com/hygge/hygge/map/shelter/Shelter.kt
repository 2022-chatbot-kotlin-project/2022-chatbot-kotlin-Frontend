package com.hygge.hygge.map.shelter

data class Shelter(val _index : Int, var name : String, var address: String, var telephone1: String, var telephone2: String,
                   var user: String, var type: String, var personnel: Int, var link: String, var latitude: Double, var longitude: Double)