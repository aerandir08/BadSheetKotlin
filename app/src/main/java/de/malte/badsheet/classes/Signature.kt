package de.malte.badsheet.classes

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


/**
 * The View where the signature will be drawn
 */
class Signature(context: Context, attrs: AttributeSet) : View(context, attrs)
{
    private val paint = Paint()
    private val path = Path()
    private var lastTouchX = 0f
    private var lastTouchY = 0f
    private val dirtyRect = RectF()// important for saving signature

    /**
     * Get signature
     *
     */
    val signature: Bitmap
        get()
        {
            val signatureBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.RGB_565)

            // important for saving signature
            val canvas = Canvas(signatureBitmap)
            this.draw(canvas)
            return signatureBitmap
        }

    /**
     * clear signature canvas
     */
    fun clearSignature()
    {
        path.reset()
        this.invalidate()
    }

    // all touch events during the drawing
    override fun onDraw(canvas: Canvas)
    {
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean
    {
        val eventX = event.x
        val eventY = event.y
        when (event.action)
        {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(eventX, eventY)
                lastTouchX = eventX
                lastTouchY = eventY
                return true
            }
            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                resetDirtyRect(eventX, eventY)
                val historySize = event.historySize
                var i = 0
                while (i < historySize) {
                    val historicalX = event.getHistoricalX(i)
                    val historicalY = event.getHistoricalY(i)
                    expandDirtyRect(historicalX, historicalY)
                    path.lineTo(historicalX, historicalY)
                    i++
                }
                path.lineTo(eventX, eventY)
            }
            else -> return false
        }
        invalidate()
        lastTouchX = eventX
        lastTouchY = eventY
        return true
    }

    private fun expandDirtyRect(historicalX: Float, historicalY: Float)
    {
        if (historicalX < dirtyRect.left)
        {
            dirtyRect.left = historicalX
        }
        else if (historicalX > dirtyRect.right)
        {
            dirtyRect.right = historicalX
        }
        if (historicalY < dirtyRect.top)
        {
            dirtyRect.top = historicalY
        }
        else if (historicalY > dirtyRect.bottom)
        {
            dirtyRect.bottom = historicalY
        }
    }

    private fun resetDirtyRect(eventX: Float, eventY: Float)
    {
        dirtyRect.left = Math.min(lastTouchX, eventX)
        dirtyRect.right = Math.max(lastTouchX, eventX)
        dirtyRect.top = Math.min(lastTouchY, eventY)
        dirtyRect.bottom = Math.max(lastTouchY, eventY)
    }

    init
    {
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = 5f
    }
}