package com.bala.decathlon.fragments.main.data


object SampleDecathlonProducts{
    fun getDecathlonProducts(page: Int):List<DecathlonProduct>{
        return listOf(
            DecathlonProduct("Saree",100.0f,"","TATA"),
            DecathlonProduct("EarBuds",80.0f,"","BOAT"),
            DecathlonProduct("Trimmer",90.0f,"","PHILIPS"),
            DecathlonProduct("Mobile",10000.0f,"","IQOO"),
            DecathlonProduct("Bed",17000.0f,"","SLEEP"),
            DecathlonProduct("Towel",10.0f,"","RAMRAJ"),
            DecathlonProduct("Fan",1000.0f,"","PHILIPS"),
            DecathlonProduct("Refrigerator",9000.0f,"","SAMSUNG"),
            DecathlonProduct("Washing Machine",20000.0f,"","GODREJ"),
            DecathlonProduct("Washing Machine",25000.0f,"","SAMSUNG"),
            DecathlonProduct("Shirt",200.0f,"","PETER ENGLAND"),
            DecathlonProduct("Pants",300.0f,"","JEANS"),
            DecathlonProduct("Books",20.0f,"","CLASSMATE"),
            DecathlonProduct("Pen",10.0f,"","TATA"),
            DecathlonProduct("Pencil",5.0f,"","TATA"),
            DecathlonProduct("Watch",3000.0f,"","TATA"),
            DecathlonProduct("Watch",200000.0f,"","Rolex"),
            DecathlonProduct("Underwear",30.0f,"","Ramraj"),
            DecathlonProduct("Mobile",11000.0f,"","Samsung"),
            DecathlonProduct("Mobile",30000.0f,"","OnePlus"),
            DecathlonProduct("Television",50000.0f,"","Samsung")
        )
    }

    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}