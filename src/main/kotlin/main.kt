fun main() {
    val post = Post(
        text = "Hello, World!",
        likes = Likes(count = 10, userLikes = true, canLike = true, canPublish = true)
    )
    val addedPost = WallService.add(post)
    println(addedPost)

    val updatedPost = addedPost.copy(text = "Updated post")
    val isUpdated = WallService.update(updatedPost)
    println(isUpdated) // true
}

data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int? = null,
    val date: Int = 0,
    val text: String = "",
    val replyOwnerId: Int? = null,
    val replyPostId: Int? = null,
    val friendsOnly: Boolean = false,
    val comments: Comments = Comments(),
    val copyright: Copyright? = null,
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val postType: String = "post",
    val postSource: PostSource? = null,
    val attachments: List<Attachment> = emptyList(),
    val geo: Geo? = null,
    val signerId: Int? = null,
    val copyHistory: List<Post> = emptyList(),
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val donut: Donut? = null,
    val postponedId: Int? = null
)

data class Comments(
    val count: Int = 0,
    val canPost: Boolean = false,
    val groupsCanPost: Boolean = false,
    val canClose: Boolean = false,
    val canOpen: Boolean = false
)

data class Copyright(
    val id: Int = 0,
    val link: String? = null,
    val name: String? = null,
    val type: String? = null
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = false,
    val canPublish: Boolean = false
)

data class Reposts(
    val count: Int = 0,
    val userReposted: Boolean = false
)

data class Views(
    val count: Int = 0
)

data class PostSource(
    val type: String = "",
    val platform: String = "",
    val data: String = "",
    val url: String = ""
)

data class Attachment(
    val type: String = "",
    val photo: Photo? = null,
    val video: Video? = null,
    val audio: Audio? = null,
    val doc: Doc? = null,
    val link: Link? = null,
    val poll: Poll? = null,
    val page: Page? = null,
    val note: Note? = null,
    val market: Market? = null,
    val marketAlbum: MarketAlbum? = null,
    val sticker: Sticker? = null,
    val gift: Gift? = null
)

data class Geo(
    val type: String = "",
    val coordinates: String = "",
    val place: Place? = null
)

data class Donut(
    val isDonut: Boolean = false,
    val paidDuration: Int = 0,
    val placeholder: Placeholder? = null,
    val canPublishFreeCopy: Boolean = false,
    val editMode: String = "all"
)

data class Placeholder(
    val text: String = ""
)

object WallService {
    private var posts: Array<Post?> = emptyArray()
    private var currentId: Int = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++currentId)
        return posts.last()!!
    }

    fun update(post: Post): Boolean {
        for ((index, existingPost) in posts.withIndex()) {
            if (existingPost?.id == post.id) {
                posts[index] = post
                return true
            }
        }
        return false
    }
}

data class Photo(
    val id: Int = 0,
    val albumId: Int = 0,
    val ownerId: Int = 0,
    val userId: Int = 0,
    val text: String = "",
    val date: Int = 0,
    val sizes: List<PhotoSize> = emptyList(),
    val width: Int = 0,
    val height: Int = 0,
    val hasTags: Boolean = false,
    val accessKey: String? = null,
    val postId: Int? = null
)

data class PhotoSize(
    val type: String = "",
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0
)

data class Video(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val description: String = "",
    val duration: Int = 0,
    val link: String = "",
    val date: Int = 0,
    val addingDate: Int = 0,
    val views: Int = 0,
    val width: Int = 0,
    val height: Int = 0,
    val userId: Int = 0,
    val canComment: Boolean = false,
    val canRepost: Boolean = false,
    val canLike: Boolean = false,
    val canAdd: Boolean = false,
    val isPrivate: Boolean = false,
    val accessKey: String? = null,
    val processing: Boolean = false,
    val isFavorite: Boolean = false,
    val canEdit: Boolean = false
)

data class Audio(
    val id: Int = 0,
    val ownerId: Int = 0,
    val artist: String = "",
    val title: String = "",
    val duration: Int = 0,
    val url: String = "",
    val lyrics: Lyrics? = null,
    val date: Int = 0,
    val noSearch: Boolean = false,
    val isHq: Boolean = false
)

data class Lyrics(
    val id: Int = 0,
    val lyrics: String = ""
)

data class Doc(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val size: Int = 0,
    val ext: String = "",
    val url: String = "",
    val date: Int = 0,
    val type: Int = 0,
    val preview: DocPreview? = null
)

data class DocPreview(
    val photo: DocPhoto? = null,
    val video: DocVideo? = null,
    val graffiti: DocGraffiti? = null,
    val audioMessage: DocAudioMessage? = null
)

data class DocPhoto(
    val sizes: List<DocPhotoSize> = emptyList()
)

data class DocPhotoSize(
    val type: String = "",
    val src: String = "",
    val width: Int = 0,
    val height: Int = 0
)

data class DocVideo(
    val duration: Int = 0,
    val height: Int = 0,
    val width: Int = 0,
    val fileSize: Int = 0
)

data class DocGraffiti(
    val src: String = "",
    val width: Int = 0,
    val height: Int = 0
)

data class DocAudioMessage(
    val duration: Int = 0,
    val waveform: List<Int> = emptyList(),
    val linkOgg: String = "",
    val linkMp3: String = ""
)

data class Link(
    val url: String = "",
    val title: String = "",
    val caption: String = "",
    val description: String = "",
    val photo: Photo? = null,
    val product: Product? = null,
    val button: Button? = null,
    val previewPage: String = "",
    val previewUrl: String = ""
)

data class Product(
    val price: Price? = null,
    val merchant: Merchant? = null
)

data class Price(
    val amount: Double = 0.0,
    val currency: String = "",
    val text: String = ""
)

data class Merchant(
    val title: String = ""
)

data class Button(
    val title: String = "",
    val action: String = "",
    val url: String = ""
)

data class Poll(
    val id: Int = 0,
    val ownerId: Int = 0,
    val created: Int = 0,
    val question: String = "",
    val votes: Int = 0,
    val answers: List<PollAnswer> = emptyList(),
    val multiple: Boolean = false,
    val anonymous: Boolean = false,
    val isBoard: Boolean = false
)

data class PollAnswer(
    val id: Int = 0,
    val text: String = "",
    val votes: Int = 0
)

data class Page(
    val id: Int = 0,
    val ownerId: Int = 0,
    val createdById: Int = 0,
    val title: String = "",
    val currentUserCanEdit: Boolean = false,
    val currentUserCanEditAccess: Boolean = false,
    val whoCanView: String = "",
    val whoCanEdit: String = "",
    val views: Int = 0,
    val parent: PageParent? = null,
    val lastRevision: PageRevision? = null,
    val createdAt: Int = 0,
    val updatedAt: Int = 0
)

data class PageParent(
    val id: Int = 0,
    val type: String = ""
)

data class PageRevision(
    val id: Int = 0,
    val title: String = "",
    val text: String = "",
    val viewUrl: String = "",
    val viewState: String = "",
    val createdAt: Int = 0,
    val createdById: Int = 0
)

data class Note(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val text: String = "",
    val date: Int = 0,
    val comments: Int = 0,
    val readComments: Int = 0,
    val viewUrl: String = ""
)

data class Market(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val description: String = "",
    val price: Price? = null,
    val dimensions: Dimensions? = null,
    val weight: Int = 0,
    val availability: Int = 0,
    val thumb: Photo? = null,
    val date: Int = 0,
    val category: MarketCategory? = null,
    val mainPhoto: Photo? = null,
    val photos: List<Photo> = emptyList(),
    val url: String = "",
    val canComment: Boolean = false,
    val canRepost: Boolean = false,
    val isDeleted: Boolean = false,
    val isFavorite: Boolean = false
)

data class MarketAlbum(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val photo: Photo? = null,
    val isMainAlbum: Boolean = false,
    val canUpload: Boolean = false
)

data class Dimensions(
    val width: Int = 0,
    val height: Int = 0,
    val length: Int = 0
)

data class MarketCategory(
    val id: Int = 0,
    val name: String = ""
)

data class Sticker(
    val productId: Int = 0,
    val ownerId: Int = 0,
    val canAnswer: Boolean = false,
    val animation: StickerAnimation? = null
)

data class StickerAnimation(
    val type: String = "",
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0
)

data class Gift(
    val id: Int = 0,
    val thumb256: String = "",
    val thumb96: String = "",
    val thumb48: String = ""
)

data class Place(
    val id: Int = 0,
    val title: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val created: Int = 0,
    val icon: String = "",
    val country: String = "",
    val city: String = "",
    val address: String = ""
)