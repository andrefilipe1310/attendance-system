import { Guardian, StudentRequestDTO } from './../model/student.model';
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
  student: StudentRequestDTO = {
    name: "",
    email:"",
    password:"",
    phone:"",
    birth: "",
    guardians: [{ name: "", phone: "" }, { name: "", phone: "" }],
  }
  studentImage: Blob | undefined;

  constructor(private attedanceService: AttendanceService) { }

   async takePhoto() {
    const image = await Camera.getPhoto({
      quality: 90,
      allowEditing: false,
      resultType: CameraResultType.DataUrl,
      source: CameraSource.Camera,
    })
    this.photo = image.dataUrl;
    if(this.photo == undefined){
      return
    }
    const byteString = atob(this.photo.split(',')[1]);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const uint8Array = new Uint8Array(arrayBuffer);
    for (let i = 0; i < byteString.length; i++) {
      uint8Array[i] = byteString.charCodeAt(i);
    }
    this.studentImage = new Blob([arrayBuffer], { type: 'image/jpeg' });
  }

  ngOnInit(): void {}
 
  addStudent(newStudent: StudentRequestDTO) {
    console.log(newStudent);
    this.attedanceService.addStudent(newStudent).subscribe({
      next: (student) => {
        this.students.push(student);
        if (this.studentImage) {
          this.uploadImage(this.studentImage, student.id);
        }
      },
      error: (error) => {
        console.error('Error adding student', error);
      }
    });
  }

  uploadImage(file: Blob, studentId: number) {
    const formData = new FormData();
    formData.append('file', file);
    this.attedanceService.uploadImage(formData, studentId).subscribe({
      next: () => {
        console.log("Image uploaded successfully");
      },
      error: (error: any) => {
        console.error(error);
      }
    });
  }
}