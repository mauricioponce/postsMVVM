package cl.eme.fakepost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.eme.fakepost.data.Post
import cl.eme.fakepost.databinding.PostItemBinding

class PostAdapter : RecyclerView.Adapter<PostVH>() {

    private var items = listOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context))

        return PostVH(binding)
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    fun update(list: List<Post>) {
        items = list
        notifyDataSetChanged()
    }

}

class PostVH(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Post) {
        binding.tvTitle.text = item.title
    }

}
