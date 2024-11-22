import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth/login'; 

  constructor(private http: HttpClient) { }

  public login(email:string, password:string):Observable<any>{
    const headers = new HttpHeaders({'Content-Type': 'application/json'})
    const body = {email, password}
    let response = this.http.post<any>(this.apiUrl, body, { headers, withCredentials: true })
    
    return response;
  }

  public saveToken(token:string){
    localStorage.setItem("token",token)
  }


  public getToken():string | null{
    return localStorage.getItem("token")
  }

  isLoggedIn():boolean{
    return !!this.getToken()
  }

  logout():void{
    localStorage.removeItem('token')
  }

}
