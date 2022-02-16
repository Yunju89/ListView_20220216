package com.nepplus.listview_20220216.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.nepplus.listview_20220216.datas.StudentData

class StudentAdapter(
    val mContext: Context,
    val resId: Int,
    val mList: ArrayList<StudentData>
) : ArrayAdapter<StudentData>(mContext, resId, mList) {
}