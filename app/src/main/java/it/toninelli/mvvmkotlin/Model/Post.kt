package it.toninelli.mvvmkotlin.Model

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
    ){
    init {
        println("primo costruttore")
    }

    init {
        println("secondo costruttore")
    }

   constructor(id: Int) : this(id,id,"ciao","ciao"){
       println("terzo costruttre")
   }
}

