package ru.wb.debugscreen.dataBase.tables.requests.relations

//data class FullRequestHeader(
//    @Embedded
//    val header: HeaderRequestTable,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "header_id"
//    )
//    val values: List<HeaderValueTable>
//)
//
//fun FullRequestHeader.toModel(): Pair<String, List<String>> {
//    return Pair(header.name, values.map { it.value })
//}
