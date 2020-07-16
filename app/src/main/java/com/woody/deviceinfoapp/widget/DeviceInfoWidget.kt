package com.woody.deviceinfoapp.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import com.woody.deviceinfoapp.R

/**
 * Implementation of App Widget functionality.
 */
class DeviceInfoWidget : AppWidgetProvider() {

    companion object {
        private const val TAG = "DeviceInfoWidget"
        private const val ACTION_COPY = "action_copy"
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        // Construct the RemoteViews object
        val views = RemoteViews(context.packageName, R.layout.device_info_widget)
        views.setTextViewText(R.id.model_content, Build.MODEL)
        views.setTextViewText(R.id.brand_content, Build.BRAND)

        views.setOnClickPendingIntent(R.id.copy_button, getPendingIntent(context, ACTION_COPY) )

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    private fun getPendingIntent(context: Context, action: String) : PendingIntent{
        val intent = Intent(context, this.javaClass)
        intent.action = action
        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        Log.i(TAG, "onReceive!! Action is ${intent?.action}")
        if (ACTION_COPY == intent?.action) {
            val clipboardManager = context?.getSystemService(Context.CLIPBOARD_SERVICE)
            if (clipboardManager is ClipboardManager) {
                val clipData = ClipData.newPlainText("DeviceInfo", "Model: ${Build.MODEL}\nBrand: ${Build.BRAND}")
                clipboardManager.setPrimaryClip(clipData)
            }
        }
    }


}