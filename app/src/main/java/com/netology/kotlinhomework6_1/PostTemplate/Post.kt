package com.netology.kotlinhomework6_1.PostTemplate


open class Post (

    // Данные поста введенные автором
    val id: Long,
    val author: String,
    val content: String?,
    val created: Long,


    // Начальные данные поста
    val type: PostType = PostType.POST,
    val source: Post? = null,
    var likedByMe: Boolean = false,
    var CommentdByMe: Boolean = false,
    var ShareByMe: Boolean = false,

    // Счетчики интереса к посту
    var likedCounter: Int = 0,
    var commentCounter:Int = 0,
    var shareCounter: Int = 0,

    // Поля для поста Event
    val locationByMe: Boolean? = null,
    val address: String? = null,
    val coordinates_lat: Double? = 0.0,
    val coordinates_lng: Double? = 0.0,

    // Поля для поста VIDEO
    val videolink: String? = null,

    // Поля для поста ADVERTISING
    val advertisinglink: String? = null




)
