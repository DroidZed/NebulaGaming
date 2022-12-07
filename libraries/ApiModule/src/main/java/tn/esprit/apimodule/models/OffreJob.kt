package tn.esprit.apimodule.models

import java.util.*


data class OffreJob(
    val _id: String? = null,
    val jobTitle: String,
    val jobDescription: String,
    val jobAdress: String,
    val jobType: String,
    val jobStartDate: String,
    val jobEndDate: String,
    val jobPosition : String,
    val jobWebsite: String,
    val jobEmail: String,
    val postedAt: Date,
    val company: UserRegister? = null // el 7assel nik rou7ek fil fields w bara
)
