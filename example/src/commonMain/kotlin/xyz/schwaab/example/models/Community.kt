package xyz.schwaab.example.models

data class Community(val name: String){
    init {
        require(name.isNotEmpty()){
            "Invalid parameter, name is empty"
        }
    }
}
