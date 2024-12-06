import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student, StudentRequestDTO } from '../model/student.model';
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

  addStudent(student:StudentRequestDTO):Observable<Student>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
    return this.http.post<Student>(this.apiUrl,student,{headers, withCredentials:true})
  }
  uploadImage(formData: FormData, userId: number): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}` // Apenas o token, sem Content-Type
    });
  
    return this.http.put<any>(`${this.apiUrl}/image/${userId}`, formData, {
      headers,
      withCredentials: true
    });
  }
  
}

