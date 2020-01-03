package com.netology.kotlinhomework6_1

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netology.kotlinhomework6_1.PostTemplate.Post
import com.netology.kotlinhomework6_1.PostTemplate.PostType
import com.netology.kotlinhomework6_1.PostTemplate.publicationTime

import kotlinx.android.synthetic.main.post_card.view.*


// Адаптер
class PostAdapter(val list: List<Post>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.post_card, parent, false)
        return PostViewHolder(this, view)

    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as PostViewHolder) {
            bind(list[position])
        }
    }
}

// ОБрабатываем кнопки карточки поста
class PostViewHolder(val adapter: PostAdapter, view: View) : RecyclerView.ViewHolder(view) {
    init {
        with(itemView) {

            // Кнопка "Лайков"
            imageBtn_like.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]

                    if (item.likedByMe == false) {
                        imageBtn_like.setImageResource(R.drawable.ic_favorite_red_24dp)
                        textView_counte_like.setTextColor(Color.RED)
                        item.likedCounter += 1
                        item.likedByMe = true
                        textView_counte_like.text = item.likedCounter.toString()

                    } else {
                        imageBtn_like.setImageResource(R.drawable.ic_favorite_black_24dp)
                        textView_counte_like.setTextColor(Color.GRAY)
                        item.likedCounter -= 1
                        item.likedByMe = false
                        textView_counte_like.text = zeroСheck(item.likedCounter)
                    }

                    adapter.notifyItemChanged(adapterPosition)
                }
            }

            // Кнопка "Комментариев"
            imageBtn_commit.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]

                    if (item.CommentdByMe == false) {
                        imageBtn_commit.setImageResource(R.drawable.ic_mode_comment_red_24dp)
                        textView_counte_commit.setTextColor(Color.RED)
                        item.commentCounter += 1
                        item.CommentdByMe = true
                        textView_counte_commit.text = item.commentCounter.toString()
                    } else {
                        imageBtn_commit.setImageResource(R.drawable.ic_mode_comment_black_24dp)
                        textView_counte_commit.setTextColor(Color.GRAY)
                        item.commentCounter -= 1
                        item.CommentdByMe = false
                        textView_counte_commit.text = zeroСheck(item.commentCounter)
                    }

                    adapter.notifyItemChanged(adapterPosition)
                }
            }

            // Кнопка "Поделиться"
            imageBtn_share.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]

                    if (item.ShareByMe == false) {
                        imageBtn_share.setImageResource(R.drawable.ic_share_red_24dp)
                        textView_counte_share.setTextColor(Color.RED)
                        item.shareCounter += 1
                        item.ShareByMe = true
                        textView_counte_share.text = item.shareCounter.toString()

                        if (item.content != null) {
                            sharePost(item.author, publicationTimeString(item.created), item.content)
                        } else {
                            sharePost(
                                item.author,
                                publicationTimeString(item.created),
                                "R.String.null_massenge"
                            )
                        }

                    } else {
                        imageBtn_share.setImageResource(R.drawable.ic_share_black_24dp)
                        textView_counte_share.setTextColor(Color.GRAY)
                        item.shareCounter -= 1
                        item.ShareByMe = false
                        textView_counte_share.text = zeroСheck(item.shareCounter)
                    }
                }
            }

            // Кнопка "Реклама" для поста ADVERTISING
            imageBtn_special_post.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]

                    if (item.advertisinglink != null) {
                        openUrl(item.advertisinglink)
                    }

                    adapter.notifyItemChanged(adapterPosition)
                }
            }

            // Кнопка "Геопозиция"
            imageBtn_location.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]

                    if (item.coordinates_lat != null && item.coordinates_lng != null) {
                        geoShare(item.coordinates_lat, item.coordinates_lng)
                    }

                    adapter.notifyItemChanged(adapterPosition)
                }
            }

            // Кнопка "Видео" для поста VIDEO
            imageBtn_special_post.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]

                    if (item.videolink != null) {
                        openUrl(item.videolink)
                    }

                    adapter.notifyItemChanged(adapterPosition)
                }
            }
        }
    }

    // Визуализация данных из постов (для цикленного перечисления)
    fun bind(post: Post) {

        with(itemView) {
            textView_author.text = post.author
            textView_data.text = publicationTimeString(post.created)
            //textView_data.text = post.created

            imageBtn_special_post.visibility = View.GONE
            textView_postType.visibility = View.GONE
            textView_postType.setText(R.string.text_null)

            imageBtn_location.visibility = View.GONE
            textView_address.visibility = View.GONE
            textView_address.setText(R.string.text_null)


            // Визуализация постов типа REPOST
            if (post.type == PostType.REPOST) {
                textView_text_note.text = "Repost: " + " " + post.content
                textView_postType.visibility = View.VISIBLE
                textView_postType.setText(R.string.text_repost)
            } else {
                textView_text_note.text = post.content
            }

            textView_counte_share.text = zeroСheck(post.shareCounter)
            textView_counte_commit.text = zeroСheck(post.commentCounter)
            textView_counte_like.text = zeroСheck(post.likedCounter)

            // Визуализация лайков
            if (post.likedByMe) {
                imageBtn_like.setImageResource(R.drawable.ic_favorite_red_24dp)
                textView_counte_like.setTextColor(Color.RED)

            } else {
                imageBtn_like.setImageResource(R.drawable.ic_favorite_black_24dp)
                textView_counte_like.setTextColor(Color.GRAY)
            }

            // Визуализация комментариев
            if (post.CommentdByMe) {
                imageBtn_commit.setImageResource(R.drawable.ic_mode_comment_red_24dp)
                textView_counte_commit.setTextColor(Color.RED)
            } else {
                imageBtn_commit.setImageResource(R.drawable.ic_mode_comment_black_24dp)
                textView_counte_commit.setTextColor(Color.GRAY)
            }

            // Визуализация "поделиться"
            if (post.ShareByMe) {
                imageBtn_share.setImageResource(R.drawable.ic_share_red_24dp)
                textView_counte_share.setTextColor(Color.RED)
            } else {
                imageBtn_share.setImageResource(R.drawable.ic_share_black_24dp)
                textView_counte_share.setTextColor(Color.GRAY)
            }

            // Визуализация геометки и адреса для постов типа EVENT
            if (post.type == PostType.EVENT) {
                imageBtn_location.visibility = View.VISIBLE
                textView_address.visibility = View.VISIBLE
                textView_address.text = post.address
            }

            // Визуализация геометки и адреса для любых постов с геометкой
            if (post.address != null) {
                imageBtn_location.visibility = View.VISIBLE
                textView_address.visibility = View.VISIBLE
                textView_address.text = post.address
            }

            // Визуализация рекламного банера для постов типа ADVERTISING
            if (post.type == PostType.ADVERTISING) {
                imageBtn_special_post.visibility = View.VISIBLE
                imageBtn_special_post.setImageResource(R.drawable.ic_raspberry_pi_logo)
                textView_postType.visibility = View.VISIBLE
                textView_postType.setText(R.string.text_reklama)
            }

            // Визуализация постов типа VIDEO
            if (post.type == PostType.VIDEO) {
                imageBtn_special_post.visibility = View.VISIBLE

                imageBtn_special_post.setImageResource(R.drawable.ic_you_tube_logo)

                textView_postType.visibility = View.VISIBLE
                textView_postType.setText(R.string.text_video)
            }
        }
    }


    // Проверка на "0" счетчиков для визуализации в TextView
    fun zeroСheck(counter: Int): String {

        val counterText: String

        if (counter == 0) {
            counterText = ""
        } else {
            counterText = counter.toString()
        }

        return counterText
    }

    // Проверка на "0" счетчиков для визуализации в TextView
    fun sharePost(author: String, created: String, content: String): Unit {

        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT, """
                                ${author} (${created})
    
                                ${content}
                            """.trimIndent()
            )
            type = "text/plain"
        }
        itemView.context.startActivity(intent)
    }

    // Открытие карты по координатам из поста
    fun openUrl(open_url: String): Unit {

        val address = Uri.parse(open_url)
        val openlink = Intent(Intent.ACTION_VIEW, address)
        itemView.context.startActivity(openlink)

    }


    // Открытие карты по координатам из поста
    fun geoShare(lat: Double, lng:Double){

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$lat,$lng"))
        itemView.context.startActivity(browserIntent)

    }


    // Преобразование даты создания поста в отметки времени относительно текущей даты
    fun publicationTimeString (created: Long): String {

        return  publicationTime(System.currentTimeMillis() - created)

    }
}


