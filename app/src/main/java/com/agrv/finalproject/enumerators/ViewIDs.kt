package com.agrv.finalproject.enumerators

enum class ViewIDs (val id: String, val tag: String) {
    Splash(id="Splash", tag = "Splash"),
    Home(id="Home", tag = "Home"),
    Start(id="Start", tag = "Inicio"),
    Details(id = "Details", tag = "Detalles"),
    OrderSummary(id = "OrderSummary", tag = "Resumen de compra"),
    FinishOrder(id= "Finish", tag = "Finalizar")
}