package tn.esprit.apimodule.models

data class OffreJob(
    val _id: String? = null,
    val jobTitle: String,
    val jobDescription: String,
    val jobAdress: String,
    val jobType: String,
    val jobStartDate: String,
    val jobEndDate: String,
    val jobPosition: String,
    val jobWebsite: String,
    val jobEmail: String,
    val company: String? = null
)
