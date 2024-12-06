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
    birth: "",
    guardians: [{ name: "", phone: "" }, { name: "", phone: "" }],
    absences:[],

  }
  studentImage:string|undefined = ""

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
    this.studentImage = this.photo

  }

  ngOnInit(): void {
    
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

