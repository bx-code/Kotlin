package com.example.lesson

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.base.BaseView
import com.example.core.utils.CacheUtils
import com.example.lesson.entity.Lesson
import kotlin.reflect.KProperty

class LessonActivity() : AppCompatActivity(),BaseView<LessonPresenter>,Toolbar.OnMenuItemClickListener {
//    private val lessonPresenter=

//    override fun getPresenter(): LessonPresenter { return lessonPresenter }

    /**
     * by 委托 lazy 初始化一次 代码块
     */
    override val p: LessonPresenter by lazy {
        LessonPresenter(this)
    }

    var token : String by Saver("hello")

    private val lessonAdapter = LessonAdapter()

    private lateinit var  refreshLayout : SwipeRefreshLayout

    /**
     * 使用 by 委托属性
     */
    class Saver(var str:String){
        operator fun getValue(lessonActivity: LessonActivity, property: KProperty<*>): String {
                Saver@return CacheUtils.get(str)!!
        }

        operator fun setValue(lessonActivity: LessonActivity, property: KProperty<*>, s: String) {
                CacheUtils.save(str,s)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        token="101"
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_lesson)
        toolbar.setOnMenuItemClickListener(this)

        val recyclerView = findViewById<RecyclerView>(R.id.list).run {
            this.layoutManager= LinearLayoutManager(this@LessonActivity)
            this.adapter= lessonAdapter
            this.addItemDecoration(DividerItemDecoration(this@LessonActivity,LinearLayout.VERTICAL))
        }


        findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout).run {
            refreshLayout = this
            refreshLayout.setOnRefreshListener { SwipeRefreshLayout.OnRefreshListener { p.fetchData() } }
            refreshLayout.isRefreshing = true
        }
        p.fetchData()
    }

    fun showResult(lessons : List<Lesson>){
        lessonAdapter.updateAndNotify(lessons as ArrayList<Lesson>)
        refreshLayout.isRefreshing=false
    }

    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        p.showPlayback()
        return false
    }
}