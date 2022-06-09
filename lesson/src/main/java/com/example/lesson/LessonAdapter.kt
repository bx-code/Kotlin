package com.example.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.base.BaseViewHolder
import com.example.lesson.entity.Lesson

class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {
     private lateinit var list : ArrayList<Lesson>

     fun updateAndNotify(list: ArrayList<Lesson>){
         this.list = list
         notifyDataSetChanged()
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(parent)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
            holder.onBind(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class LessonViewHolder constructor(itemView : View) : BaseViewHolder(itemView){
        companion object{
             fun onCreate(parant : ViewGroup): LessonViewHolder{
                return LessonViewHolder(LayoutInflater.from(parant.context).inflate(R.layout.item_lesson,parant,false))
            }
        }
        fun onBind(lesson : Lesson){
            setText(R.id.tv_date,lesson.date?:"日期待定")
            setText(R.id.tv_content,lesson.content)
            val state = lesson.state.let {
                setText(R.id.tv_state,it.toString())
                var colorRes = when(it){
                    Lesson.State.PLAYBACK -> R.color.playback
                    Lesson.State.LIVE -> R.color.live
                    Lesson.State.WAIT -> R.color.wait
                }
                val backgroundColor = itemView.context.getColor(colorRes)
                getView<TextView>(R.id.tv_state).setBackgroundColor(backgroundColor)

            }
        }
    }

}