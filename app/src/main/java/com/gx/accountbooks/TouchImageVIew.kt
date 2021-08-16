package com.gx.accountbooks

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.appcompat.widget.AppCompatImageView
import com.gx.utils.log.LogUtil


class TouchImageVIew : AppCompatImageView {
    constructor(context: Context?) : super(context!!) {
        mGestureDetector = GestureDetector(context, OnSimpleListener())
//        mGestureDetector.setOnDoubleTapListener(MyDoubleTapListener())
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!,
        attrs
    ) {
        mGestureDetector = GestureDetector(context, OnSimpleListener())
//        mGestureDetector.setOnDoubleTapListener(MyDoubleTapListener())
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context!!, attrs, defStyleAttr) {
        mGestureDetector = GestureDetector(context, OnSimpleListener())
        isFocusable = true;
        isClickable = true;
        isLongClickable = true;

//        mGestureDetector.setOnDoubleTapListener(MyDoubleTapListener())
    }

    var mGestureDetector: GestureDetector
    val TAG = "TouchImageVIew"

    init {

    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        return mGestureDetector.onTouchEvent(event)
    }

    var currentScaleStatus = 0

    inner class OnSimpleListener : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            LogUtil.e(TAG, "onSingleTapUp")
            return super.onSingleTapUp(e)
        }

        override fun onDown(e: MotionEvent?): Boolean {
            LogUtil.e(TAG, "onDown")
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            LogUtil.e(TAG, "onFling")
            return super.onFling(e1, e2, velocityX, velocityY)
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            //设置缩放轴点为view的中心点(默认是中心点)
            val x = e!!.x
            val y = e.y
            LogUtil.e(TAG, "onDoubleTap x $x y $y e.action $e.action")
            val matrix = Matrix()
            val postScale = getMatrix().postScale(2f, 2f)
            if (currentScaleStatus == 0) {
                setTransform();
//                pivotX = x
//                pivotY = y
//                scaleX = 2.0f
//                scaleY = 2.0f
//                animate()
//                    .scaleX(2.0f)
//                    .scaleY(2.0f)
//                    .setDuration(500)
//                    .start()

//                属性动画
                val rotateX = ObjectAnimator.ofFloat(this@TouchImageVIew, "scaleX", 1f, 3f)
                val rotateY = ObjectAnimator.ofFloat(this@TouchImageVIew, "scaleY", 1f, 3f)
                val animSet = AnimatorSet()
                animSet.play(rotateX).with(rotateY)
                animSet.duration = 500
                animSet.start()

                val scaleAnimation = ScaleAnimation(
                    1f,
                    2f,
                    1f,
                    2f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f
                )
                scaleAnimation.duration = 500
                scaleAnimation.fillAfter = true
                startAnimation(scaleAnimation)
                LogUtil.e(TAG, "onDoubleTap width $width height $height ")

                currentScaleStatus = 1
            } else {
//                scaleX = 1.0f
//                scaleY = 1.0f
//                animate()
//                    .scaleX(1.0f)
//                    .scaleY(1.0f)
//                    .setDuration(500)
//                    .start()

//                val rotateX = ObjectAnimator.ofFloat(this@TouchImageVIew, "scaleX", 2f, 1f)
//                val rotateY = ObjectAnimator.ofFloat(this@TouchImageVIew, "scaleY", 2f, 1f)
//                val animSet = AnimatorSet()
//                animSet.play(rotateX).with(rotateY)
//                animSet.duration = 500
//                animSet.start()
                scaleY
                val scaleAnimation = ScaleAnimation(
                    2f,
                    1f,
                    2f,
                    1f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f
                )
                scaleAnimation.duration = 500
                scaleAnimation.fillAfter = true
                startAnimation(scaleAnimation)
                LogUtil.e(TAG, "onDoubleTap width $width height $height ")
                currentScaleStatus = 0
            }
            return super.onDoubleTap(e)
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            LogUtil.e(TAG, "onScroll")
            return super.onScroll(e1, e2, distanceX, distanceY)
        }

        override fun onContextClick(e: MotionEvent?): Boolean {
            LogUtil.e(TAG, "onContextClick")
            return super.onContextClick(e)
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            LogUtil.e(TAG, "onSingleTapConfirmed")
            return super.onSingleTapConfirmed(e)
        }

        override fun onShowPress(e: MotionEvent?) {
            LogUtil.e(TAG, "onShowPress")
            super.onShowPress(e)
        }

        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            LogUtil.e(TAG, "onDoubleTapEvent")
            return super.onDoubleTapEvent(e)
        }

        override fun onLongPress(e: MotionEvent?) {
            LogUtil.e(TAG, "onLongPress")
            super.onLongPress(e)
        }
    }

}