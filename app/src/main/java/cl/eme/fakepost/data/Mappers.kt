package cl.eme.fakepost.data

fun mapAPI2DB(post: Post): PostEntity {
    return PostEntity(post.userId, post.id, post.title, post.body)
}

fun mapDB2API(post: PostEntity): Post {
    return Post(post.userId, post.id, post.title, post.body)
}