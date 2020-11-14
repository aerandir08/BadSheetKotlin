package de.malte.badsheet.utility

import android.app.Application
import android.content.Context

/** Class to get the Application Context everywhere I want to. Usefull to reach R. **/
class ContextUtil : Application()
{
    override fun onCreate()
    {
        super.onCreate()
        mContext = this
    }

    companion object
    {
        private var mContext: Context? = null
        val context: Context?
            get()
            = mContext
    }
}