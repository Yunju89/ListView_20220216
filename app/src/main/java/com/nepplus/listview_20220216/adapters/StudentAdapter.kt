// 1. java 폴더 내의 프로젝트패키지에 하위 패키지 추가 - (adapters)
// 2. adapter class 제작 (adapter 패키지 하위 클래스 / 담을데이터Adapter 형태로)
// 어댑터(Adapter)란, 데이터와 리스트 뷰 사이에 존재하는 Object 객체, 데이터와 리스트 뷰 사이의 통신을 위한 다리역할
// 어댑터는 어댑터뷰가 출력할 수 있는 데이터로 만들어 놓는 공간을 의미, 어댑터뷰는 이 데이터를 출력하는 역할을 하게 된다.

package com.nepplus.listview_20220216.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.nepplus.listview_20220216.R
import com.nepplus.listview_20220216.datas.StudentData
import org.w3c.dom.Text


class StudentAdapter(
    val mContext: Context,  /// Context class, 강사님께 -> 어느 화면인지? Activity와 비슷한 개념 (좀더 포괄적임/)
    val resId: Int,    //변수
    val mList: ArrayList<StudentData>  //ArrayList 배열
) : ArrayAdapter<StudentData>(mContext, resId, mList) {  // : 상속받는것
//    ArrayAdapter 클래스 상속받는 클래스 <StudentData> 자료형

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {   //fun(메소드의미) :View 타입으로 리턴
//        getView => xml, 데이터클래스 조합 기능 구현, 우리가 만든 xml 모양으로 셋팅 (오버라이딩)
        var tempRow = convertView      // View StudentAdapter 생성 시 받은 레이아웃 (resId)
        if (tempRow == null) {
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.student_list_item,null)
//        LayoutInflater : xml파일을 가져다 씀 (레이아웃 갖다쓰겠다)

        }

        val row = tempRow!!   // 절대 null이 아니라는 뜻

        val data = mList[position]      //반영할 UI : row변수의 내부 태그들 가져오기

        val txtStudentName = row.findViewById<TextView>(R.id.txtStudentName)    //값 셋팅
        val txtAge = row.findViewById<TextView>(R.id.txtAge)
        val txtAdress = row.findViewById<TextView>(R.id.txtAdress)

        txtStudentName.text = data.name
        txtAdress.text = data.address

        val age = 2022 - data.birthYear + 1
        txtAge.text = "(${age}세)"

        return row

    }

}