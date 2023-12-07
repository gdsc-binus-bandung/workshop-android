package com.example.gameshop

import androidx.annotation.DrawableRes

data class Model(
    val title: String,
    @DrawableRes val image: Int,
    val description: String,
    val rating: Double
)

val games = listOf(
    Model("Genshin Impact", R.drawable.genshin_impact, "Si Paling Primogems", 4.6),
    Model("Clash of Clans", R.drawable.coc, "Si Paling Primogems", 4.3),
    Model("Mobile Legend", R.drawable.mobile_legend, "Si Paling Primogems", 4.5),
    Model("Honkai Star Rail", R.drawable.star_rail, "Si Paling Primogems", 4.8),
    Model("Valorant", R.drawable.valorant, "Si Paling Primogems", 4.7),
)
