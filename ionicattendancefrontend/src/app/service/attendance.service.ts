import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from '../model/student.model';
import {  HttpHeaders } from '@angular/common/http';
import { ImageUploadDTO } from '../model/student.model';
@Injectable({
  providedIn: 'root'
})
export class AttendanceService {
  
  private apiUrl:string = 'http://localhost:8080/student'
  constructor(private http:HttpClient) { }
  
  getStudents(): Observable<Student[]> {
    const headers = new HttpHeaders({'Content-Type': 'application/json',
      'Authorization':`bearer ${localStorage.getItem("token")}`
    })
    return this.http.get<Student[]>(this.apiUrl,{headers, withCredentials:true});
  }

  addStudent(student:Student):Observable<Student>{
    const headers = new HttpHeaders({'Content-Type': 'application/json',
      'Authorization':`bearer ${localStorage.getItem("token")}`
    })
    return this.http.post<Student>(this.apiUrl,student,{headers, withCredentials:true})
  }
  uploadImage(ImageUploadDTO:ImageUploadDTO, userId:number):void{
    const headers = new HttpHeaders({'Content-Type': 'application/json',
      'Authorization':`bearer ${localStorage.getItem("token")}`
    })
    this.http.put<void>(this.apiUrl+`/image/${userId}`,ImageUploadDTO,{headers, withCredentials:true})  
  }
  
}

