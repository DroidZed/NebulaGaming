package tn.esprit.nebulagaming.data

data class ForumPost(
    var idPost: Int,
    var nameUser: String,
    var photouser: Int,
    var photoPost: Int,
    var datePost: String,
    var descriptionPost: String,
    var likesPost: String,
    var savedPost: String
)
