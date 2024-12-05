import { Guardian } from './../model/student.model';
import { Component, OnInit } from '@angular/core';
import { AttendanceService } from '../service/attendance.service';
import { Student } from '../model/student.model';
import { Camera, CameraResultType, CameraSource } from '@capacitor/camera';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page implements OnInit {
  photo: string | undefined;
  students: Student[] = []
  student: Student = {

    name: "",
    email:"",
    password:"",
    phone:"",
    studentImage: "",
    birth: "",
    guardians: [{ name: "", phone: "" }, { name: "", phone: "" }],
    absences:[],

  }
  constructor(private attedanceService: AttendanceService) { }

   async takePhoto() {
    const image = await Camera.getPhoto({
      quality: 90,
      allowEditing: false,
      resultType: CameraResultType.DataUrl,
      source: CameraSource.Camera,
    })
    this.photo = image.dataUrl
    console.log( this.photo?.length)
    this.student.studentImage = this.photo
  }

  ngOnInit(): void {
    this.loadStudents()
  }

 

  loadStudents() {
    this.attedanceService.getStudents().subscribe({
      next: (data: Student[]) => {
        this.students = data;
      },
      error: (error) => {
        console.error('Error fetching students', error);
      }
    })
  }
  addStudent(newStudent: Student) {
    console.log(newStudent)
    this.attedanceService.addStudent(newStudent).subscribe({
      next: (student) => {
        this.students.push(student);
      },
      error: (error) => {
        console.error('Error adding student', error);
      }
    })
  }


}

