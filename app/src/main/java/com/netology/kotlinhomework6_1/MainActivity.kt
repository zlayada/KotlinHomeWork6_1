package com.netology.kotlinhomework6_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.netology.kotlinhomework6_1.PostTemplate.Post
import com.netology.kotlinhomework6_1.PostTemplate.PostType


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Пост для имитации репоста
        var post = Post(
            0,
            "Джонатон Уэйн",
            "Самое первое сообщение в этой социальной сети. Где отображаются переносы слов, но не более ограниченных строк.",
            1550864590000,
            likedByMe = false,
            CommentdByMe = false,
            ShareByMe = false,
            likedCounter = 5,
            commentCounter = 3,
            shareCounter = 2
        )

        // База предустановленных постов
        val list = mutableListOf(
            Post(
                1,
                "Мистер Смит",
                "Матрица жива!!!",
                1564864590000,
                type = PostType.REPOST,
                source = post,
                likedByMe = true,
                CommentdByMe = true,
                likedCounter = 15,
                commentCounter = 1
            ),
            Post(
                2,
                "Нео Протуберанец",
                "Великое деяние Архитектора заключается в поиске багов в процессе всей жизни.",
                1574864590000,
                type = PostType.REPOST,
                source = post,
                likedByMe = true,
                ShareByMe = true,
                likedCounter = 1,
                shareCounter = 1
            ),
            post,

            Post(
                3,
                "Петр Авергунг",
                "Добро пожаловать в мир цифровой недвижимости!",
                1584864590000,
                likedByMe = true,
                CommentdByMe = true,
                ShareByMe = true,
                likedCounter = 4,
                commentCounter = 11,
                shareCounter = 1,
                type = PostType.ADVERTISING,
                advertisinglink = "https://habr.com/ru/"

            ),
            Post(
                4,
                "Аноним",
                "Всемирная теория заговора включает неизменную идею мягкого влияния на людей, не имеющих доступа к власти и ресурсам.",
                1594864590000,
                type = PostType.VIDEO,
                videolink = "https://www.youtube.com/watch?v=EOkIIyuGAUs"
            ),
            Post(
                5,
                "Дед Мороз",
                "Подготовка к Новому году включает в себя список дел который известен каждому.",
                1604864590000,
                likedByMe = true,
                CommentdByMe = true,
                ShareByMe = true,
                likedCounter = 150,
                commentCounter = 190,
                shareCounter = 70,
                type = PostType.EVENT,
                address = "Великий Устюг",
                coordinates_lat = 41.40338,
                coordinates_lng = 2.17403
            ),
            Post(
                6,
                "Дед Мазай",
                "Исследование костромских болот на личном примере.",
                1614864590000,
                type = PostType.VIDEO,
                videolink = "https://www.youtube.com/watch?v=EOkIIyuGAUs",
                address = "Нея",
                coordinates_lat = 41.40338,
                coordinates_lng = 2.17403
            )

        )

        with(container) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(list)
        }
    }

}
