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

        studentListView.setOnItemClickListener { parent, view, position, id ->    // 6. 리스트뷰의 클릭 이벤트 : 리스트뷰.setOn"ItemClick"Listener { }

            val clickedStudent = mStudentList[position]                          // 7. 변수생성, 배열 담음 // 어느 줄이 눌렸는지를 position(또는 i) 변수가 알려줌

            Toast.makeText(this, "${clickedStudent.name}", Toast.LENGTH_SHORT).show()      // 8. 위의 변수 사용 및 활용
            
        }
        
        studentListView.setOnItemLongClickListener { parent, view, position, id ->     // 9. 길게 눌렀을 때의 이벤트 처리 (부가메뉴, 삭제, 경고 등으로 사용) -> 10번의 return 설정
            
            val longClickedStudent = mStudentList[position]

            AlertDialog.Builder(this)                       // 11. 심각한 경고 (확인받고 움직이는게 좋은 로직 > AlertDialog 처리
                .setTitle("학생 삭제")                                    // 12. etTitle("제목") //생략가능
                .setMessage("정말 ${longClickedStudent.name} 학생을 삭제하시겠습니까?")       //13.setMessage("물어볼 내용")
                .setPositiveButton("확인" ,DialogInterface.OnClickListener { dialogInterface, i ->    // 14. setPositiveButton("긍정 문구", Ctrl + space {

                    mStudentList.remove( longClickedStudent )            // 15. 확인시, 실행할 코드

                    mAdapter.notifyDataSetChanged()                      // 16. ArrayList 내용 변경 시 어댑터 변수도 처리할 수 있도록 반영 // 어댑터변수.notifyDataSetChanged()

                })
                .setNegativeButton("취소", null)            // 17. setNegativeButton("부정 문구",  null 로 할일이 없다고 명시.)
                .show()

            mStudentList.remove( longClickedStudent )                    // 18. 삭제

            mAdapter.notifyDataSetChanged()                              // 19. 16번과 동일하게 ArrayList 내용변경, 어댑터에 반영
            
            return@setOnItemLongClickListener true                            // 10. 롱클릭 리턴 이벤트 처리
        }


    }
}