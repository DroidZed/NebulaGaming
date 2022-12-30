package tn.esprit.apimodule.models

import java.util.Date

data class User(val _id:String,val name: String,
                val photo: String,
                val email: String,
                val phone: String,
                val role: Int,
                val level: Int,
                val status: Int,
                val createdAd: Date)
