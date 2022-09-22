package com.gx.accountbooks.framework.ui.home

import android.Manifest
import android.content.SharedPreferences
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.gx.accountbooks.HomeViewModel
import com.gx.accountbooks.R
import com.gx.accountbooks.databinding.FragmentHomeBinding
import com.gx.base.base.vb.BaseVBFragment

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class HomeFragment : BaseVBFragment<FragmentHomeBinding>() {

    val homeViewModel: HomeViewModel by viewModels()

    override fun initData() {
        val testStr = "abc"
        val sumBy = sumBy(testStr) {
            it.toInt()
        }

        val sumByInline = sumByInline(testStr) {
            it.toInt()
        }
        println(sumBy)
        println(sumByInline)
        sumByT(testStr) { toInt1() }
//        val sum = testStr.sumBy { it.toInt() }
//        println(sum)
    }

    fun toInt1(): Int {
        return 10000
    }

    fun TestInterface.edit(commit: Boolean = false, action: TestInterface.() -> Unit) {

    }

//    @AndroidPermission(permissions = [Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE])
    open fun getTime() {
//        try {
//            Thread.sleep(3000);
//        } catch (e: InterruptedException) {
//            e.printStackTrace();
//        }
        android.util.Log.i("handleGetTime", "执行了getTime方法: ");
    }

    override fun FragmentHomeBinding.initBinding() {
        textView.setStyle()
        textView.myTextSize = 30f
//        LogUtil.d("homeViewModel ${textView.myTextSize}")
        homeViewModel.currentTimeTransformed.observe(this@HomeFragment) {
            textView.text = it
        }
        textView.setOnClickListener {
//            homeViewModel.getData()
            getTime()
        }
    }

    fun saveToken(token: String) {
        val sharedPreferences = activity?.getSharedPreferences("", 0)
        sharedPreferences!!.edit(true) { putString(KEY_TOKEN, token) }

    }

    //扩展函数定义
    fun TextView.setStyle() = this.apply {
        var textName = "HelloWorld";
        paint.isFakeBoldText = true
        textSize = 16f
        text = "HelloWorld"
        setTextColor(resources.getColor(R.color.color_red))
    }

    //扩展函数定义
    fun TextView.getName(): String {
        var textName = "HelloWorld";
        return text.toString()
    }

    var TextView.myTextSize: Float
        get() {
            return this.textSize
        }
        set(value) {
            this.textSize = value
        }

    fun sumBy(char: String, selector: (Char) -> Int): Int {
        var sum: Int = 0
        for (element in char) {
            sum += selector(element)
        }
        return sum
    }


    fun <T> sumByT(char: String, selector: () -> T): T {
        return selector()
    }

    inline fun sumByInline(char: String, selector: (Char) -> Int): Int {
        var sum: Int = 0
        for (element in char) {
            sum += selector(element)
        }
        return sum
    }

    // sumBy函数的源码
//    inline fun CharSequence.sumBy(selector: (Char) -> Int): Int {
//        var sum: Int = 0
//        for (element in this) {
//            sum += selector(element)
//        }
//        return sum
//    }

    fun SharedPreferences.edit(
        commit: Boolean = false,
        action: SharedPreferences.Editor.() -> Unit
    ) {
        val editor = edit()
        action(editor)
        if (commit) {
            editor.commit()
        } else {
            editor.apply()
        }
    }

    private val KEY_TOKEN = "token"


//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        homeViewModel =
//            ViewModelProviders.of(this).get(HomeViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: ImageView = root.findViewById(R.id.sliding_view_arrow)
////        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//////            textView.text = it
////        })
//        return root
//    }

//    override fun initView(view: View) {
////        sliding_view_arrow.animate().rotationX(1.0f).start()
//        linearLayout.setOnClickListener {
////            sliding_expansion_view.expansionView(sliding_view_arrow)
//        }
//        chart.setUsePercentValues(true)
//        chart.description.isEnabled = false
////        chart.setExtraOffsets(5f, 10f, 5f, 5f)
//        chart.dragDecelerationFrictionCoef = 0.95f
//        chart.isDrawHoleEnabled = true
//        chart.setHoleColor(Color.WHITE)
//
//        chart.setTransparentCircleColor(Color.WHITE)
////        chart.setTransparentCircleAlpha(110)
////        chart.holeRadius = 58f
////        chart.transparentCircleRadius = 61f
//        chart.setDrawCenterText(true)
//        chart.rotationAngle = 0f
//        // enable rotation of the chart by touch
//        // enable rotation of the chart by touch
//        chart.isRotationEnabled = true
//        chart.isHighlightPerTapEnabled = true
//        // chart.spin(2000, 0, 360);
//        val l = chart.legend
//        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
//        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
//        l.orientation = Legend.LegendOrientation.VERTICAL
//        l.setDrawInside(false)
//        l.textColor = Color.WHITE
////        l.xEntrySpace = 7f
////        l.yEntrySpace = 0f
////        l.yOffset = 0f
//
//        // chart.setUnit(" €");
//        // chart.setDrawUnitsInChart(true);
//
//        // add a selection listener
//
//        // chart.setUnit(" €");
//        // chart.setDrawUnitsInChart(true);
//        chart.setDrawEntryLabels(false)
//        chart.setDrawMarkers(false)
//        chart.setEntryLabelColor(Color.WHITE)
//        // add a selection listener
//        chart.animateY(1400, Easing.EaseInOutQuad)
//        //dataSet.setSelectionShift(0f);
//        setData(2, 10.0f);
//    }
//
//
//    private fun setData(count: Int, range: Float) {
//        val entries = ArrayList<PieEntry>()
//        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
//        // the chart.
//        for (i in 0 until count) {
//            entries.add(
//                PieEntry(
//                    (Math.random() * range + range / 5).toFloat(),
//                    "收入",
//                    resources.getDrawable(R.drawable.ic_launcher_background)
//                )
//            )
//        }
//        val dataSet = PieDataSet(entries, "")
//        dataSet.setDrawIcons(false)
//
//        val colors = ArrayList<Int>()
////        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
////        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
////        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
//        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
//        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
//        colors.add(ColorTemplate.getHoloBlue())
//        dataSet.colors = colors
//        //dataSet.setSelectionShift(0f);
//        val data = PieData(dataSet)
//        data.setValueFormatter(PercentFormatter())
//        data.setDrawValues(false)
////        data.setValueTextSize(11f)
////        data.setValueTextColor(Color.WHITE)
//        chart.data = data
//        // undo all highlights
//        chart.highlightValues(null)
//        chart.invalidate()
//    }

}