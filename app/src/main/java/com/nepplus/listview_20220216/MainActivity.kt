package com.nepplus.listview_20220216

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.nepplus.listview_20220216.adapters.StudentAdapter
import com.nepplus.listview_20220216.datas.StudentData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mAdapter : StudentAdapter         // 3. 멤버변수 생성 (어댑터 클래스타입으로 받는)
    val mStudentList = ArrayList<StudentData>()    // 1. ArrayList 배열 객체 멤버변수로 생성

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        2. 생성한 ArrayList에 add 로 담음, 리스트 뷰 연결
        mStudentList.add(StudentData("조경진", 1988, "서울시 동대문구"))
        mStudentList.add(StudentData("고재천", 1988, "서울시 중랑구"))
        mStudentList.add(StudentData("방우진", 1983, "경기도 고양시"))
        mStudentList.add(StudentData("서정민", 1984, "경기도 광명시"))
        mStudentList.add(StudentData("이승민", 1994, "경기도 안양시"))
        mStudentList.add(StudentData("이영종", 1991, "경기도 고양시"))
        mStudentList.add(StudentData("이윤주", 1989, "서울시 강서구"))
        mStudentList.add(StudentData("이진실", 1994, "서울시 송파구"))
        mStudentList.add(StudentData("정동준", 1992, "서울시 남양주시"))
        mStudentList.add(StudentData("최재훈", 1994, "경기도 수원시"))
        mStudentList.add(StudentData("이성노", 1988, "경기도 남양주시"))

//          4. StudentAdapter 객체화 진행 어댑터변수=어댑터클래스(필요 값)
        mAdapter = StudentAdapter(this, R.layout.student_list_item, mStudentList)

        studentListView.adapter = mAdapter    // 5. 리스트뷰의 어댑터로 = 어댑터 변수 대입

        studentListView.setOnItemClickListener { parent, view, position, id ->

            val clickedStudent = mStudentList[position]

            Toast.makeText(this, "${clickedStudent.name}", Toast.LENGTH_SHORT).show()
            
        }
        
        studentListView.setOnItemLongClickListener { parent, view, position, id -> 
            
            val longClickedStudent = mStudentList[position]

            AlertDialog.Builder(this)
                .setTitle("학생 삭제")
                .setMessage("정말 ${longClickedStudent.name} 학생을 삭제하시겠습니까?")
                .setPositiveButton("확인" ,DialogInterface.OnClickListener { dialogInterface, i ->

                    mStudentList.remove( longClickedStudent )

                    mAdapter.notifyDataSetChanged()

                })
                .setNegativeButton("취소", null)
                .show()

            mStudentList.remove( longClickedStudent )

            mAdapter.notifyDataSetChanged()
            
            return@setOnItemLongClickListener true
        }


    }
}