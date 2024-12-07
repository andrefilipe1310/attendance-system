import { Component, OnInit } from '@angular/core';
import { Camera, CameraResultType, CameraSource } from '@capacitor/camera';
import { AttendanceService } from '../service/attendance.service';
@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss']
})
export class Tab3Page {
  photo: string | undefined;
  studentImage: Blob | undefined;

  studentValid:boolean = false

  constructor(private attedanceService: AttendanceService) {}

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
    this.compareFaceWithStudent(this.studentImage)
  }

  compareFaceWithStudent(file: Blob,) {
    this.studentValid = true
    const formData = new FormData();
    formData.append('file', file);
    this.attedanceService.compareFaceWithStudent(formData).subscribe({
      next: (response) => {
        this.studentValid = response
        console.log("valor do response da função compareFaceWithStudent",response);
      },
      error: (error: any) => {
        console.error(error);
      }
    });
  }

}
