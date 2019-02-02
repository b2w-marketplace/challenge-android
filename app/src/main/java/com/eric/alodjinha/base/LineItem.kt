package com.eric.alodjinha.base

class LineItem(obj: Any, isHeader: Boolean, sectionFirstPosition: Int, type: Int) {


    var sectionFirstPosition = 0
    var type = 0
    var isHeader = false
    var expanded = false
    var obj: Any? = null
    var searchText: String? = null

    init {
        this.sectionFirstPosition = sectionFirstPosition
        this.type = type
        this.isHeader = isHeader
        this.obj = obj
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LineItem

        if (sectionFirstPosition != other.sectionFirstPosition) return false
        if (type != other.type) return false
        if (isHeader != other.isHeader) return false
        if (expanded != other.expanded) return false
        if (obj != other.obj) return false
        if (searchText != other.searchText) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sectionFirstPosition
        result = 31 * result + type
        result = 31 * result + isHeader.hashCode()
        result = 31 * result + expanded.hashCode()
        result = 31 * result + (obj?.hashCode() ?: 0)
        result = 31 * result + (searchText?.hashCode() ?: 0)
        return result
    }


}