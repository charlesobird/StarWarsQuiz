package com.project.starwarsquiz

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

//@JsonClass(generateAdapter = true)
@Parcelize
data class Planet(
    val name: String? = null,
    @Json(name = "rotation_period") val rotationPeriod: String? = null,
    @Json(name = "orbital_period") val orbitalPeriod: String? = null,
    val diameter: String? = null,
    @Json(name = "climate") val rawClimates: String? = null,
    val gravity: String? = null,
    @Json(name = "surface_water") val surfaceWater: String? = null,
    @Json(name = "terrain") val rawTerrains: String? = null,
    @Json(name = "residents") val charactersURLs: List<String>? = null,
    @Json(name = "films") val filmsURLs: List<String>? = null,
    val created: String? = null,
    val edited: String? = null,
    val url: String? = null
) : Parcelable

@Parcelize
data class Character(
    val name: String? = null,
    val height: String? = null,
    val mass: String? = null,
    @Json(name = "hair_color") val hairColor: String? = null,
    @Json(name = "skin_color") val skinColor: String? = null,
    @Json(name = "eye_color") val eyeColor: String? = null,
    @Json(name = "birth_year") val birthYear: String? = null,
    val gender: String? = null,
    var homeworld: String? = null,
    val films: List<String>? = null,
    val species: List<String>? = null,
    val vehicles: List<String>? = null,
    val starships: List<String>? = null,
    val created: String? = null,
    val edited: String? = null,
    val url: String? = null
) : Parcelable

@Parcelize
data class Ship(
    val name: String? = null,
    val model: String? = null,
    val manufacturer: String? = null,
    @Json(name = "cost_in_credits") val costInCredits: String? = null,
    val length: String? = null,
    @Json(name = "max_atmosphering_speed") val maxAtmospheringSpeed: String? = null,
    val crew: String? = null,
    val passengers: String? = null,
    @Json(name = "cargo_capacity") val cargoCapacity: String? = null,
    val consumables: String? = null,
    @Json(name = "hyperdrive_rating") val hyperdriveRating: String? = null,
    val MGLT: String? = null,
    @Json(name = "starship_class") val starshipClass: String? = null,
    val pilots: List<String>? = null,
    val films: List<String>? = null,
    val created: String? = null,
    val edited: String? = null,
    val url: String? = null
) : Parcelable

@Parcelize
data class Species(
    val name: String? = null,
    val classification: String? = null,
    val designation: String? = null,
    @Json(name = "average_height") val averageHeight: String? = null,
    @Json(name = "skin_colors") val skinColors: String? = null,
    @Json(name = "hair_colors") val hairColors: String? = null,
    @Json(name = "eye_colors") val eyeColors: String? = null,
    @Json(name = "average_lifespan") val averageLifespan: String? = null,
    val homeworld: String? = null,
    val language: String? = null,
    val people: List<String>? = null,
    val films: List<String>? = null,
    val created: String? = null,
    val edited: String? = null,
    val url: String? = null
) : Parcelable