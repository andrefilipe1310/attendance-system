import { Component } from '@angular/core';
import { NavController } from '@ionic/angular';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage {
  public email:string = ""
  public password:string = ""
  public errorMessage:string = ""

  constructor(private authService:AuthService,private navCtrl: NavController) { }

  login() {
   this.authService.login(this.email, this.password).subscribe({
    next: (response) => {

      const token = response.token
      this.authService.saveToken(token)
      this.navCtrl.navigateRoot('/tabs')
    },
    error: (error) => {
      console.log(error)
    }
   })
  }
}
